package com.techtech.parking.services.impl;

import com.techtech.parking.dto.ParkingSessionDto;
import com.techtech.parking.entities.ParkingSession;
import com.techtech.parking.entities.ParkingSpace;
import com.techtech.parking.entities.Vehicle;
import com.techtech.parking.enums.ParkingSessionStatus;
import com.techtech.parking.enums.VehicleType;
import com.techtech.parking.exceptions.ParkingBusinessException;
import com.techtech.parking.exceptions.ResourceNotFoundException;
import com.techtech.parking.mapper.ParkingSessionMapper;
import com.techtech.parking.mapper.ParkingSpaceMapper;
import com.techtech.parking.repository.ParkingSessionRepository;
import com.techtech.parking.repository.ParkingSpaceRepository;
import com.techtech.parking.repository.VehicleRepository;
import com.techtech.parking.services.ParkingSessionService;
import com.techtech.parking.services.ParkingSpaceService;
import com.techtech.parking.services.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ParkingSessionServiceImpl implements ParkingSessionService {

    private final ParkingSessionRepository parkingSessionRepository;
    private final ParkingSpaceRepository parkingSpaceRepository;
    private final VehicleRepository vehicleRepository;
    private final ParkingSessionMapper parkingSessionMapper;

    @Override
    @Transactional
    public ParkingSessionDto entry(Long vehicleId, Long spaceId) {
        Vehicle vehicle =vehicleRepository.findById(vehicleId).orElseThrow(() -> new ResourceNotFoundException("авто не найдено"));
        ParkingSpace parkingSpace =parkingSpaceRepository.findById(spaceId).orElseThrow(() -> new ResourceNotFoundException("авто не найдено"));

        if (parkingSessionRepository.existsByVehicleIdAndStatus(vehicleId, ParkingSessionStatus.ACTIVE)) {
            throw new ParkingBusinessException("Vehicle is already parked");
        }
        if (!parkingSpace.isAvailable()) {
            throw new ParkingBusinessException("Parking place is not available");
        }
        if (vehicle.getVehicleType() != parkingSpace.getVehicleType()) {
            throw new ParkingBusinessException("Vehicle type %s does not match parking place type %s"
                    .formatted(vehicle.getVehicleType(), parkingSpace.getVehicleType()));
        }
        parkingSpace.setAvailable(false);

        ParkingSession session = new ParkingSession();
        session.setVehicle(vehicle);
        session.setParkingSpace(parkingSpace);
        session.setEntryTime(LocalDateTime.now());
        session.setStatus(ParkingSessionStatus.ACTIVE);

        return parkingSessionMapper.toDto(parkingSessionRepository.save(session));

    }

    @Override
    @Transactional
    public ParkingSessionDto exit(Long sessionId) {
        ParkingSession session = getOrThrow(sessionId);

        if (session.getStatus() != ParkingSessionStatus.ACTIVE) {
            throw new ParkingBusinessException("Session is already completed");
        }

        LocalDateTime now = LocalDateTime.now();
        var price =calculate(session.getVehicle().getVehicleType(), session.getEntryTime(), now);
        session.setExitTime(now);
        session.setCost(price);
        session.setStatus(ParkingSessionStatus.COMPLETED);
        session.getParkingSpace().setAvailable(true);

        return parkingSessionMapper.toDto(session);
    }

    private BigDecimal calculate(
            VehicleType type,
            LocalDateTime entry,
            LocalDateTime exit
    ){
        long minutes = Duration.between(entry, exit).toMinutes();
        long hours = Math.max(1, (minutes + 59) / 60);
        return type.getPricePerHour().multiply(BigDecimal.valueOf(hours));
    }

    private ParkingSession getOrThrow(Long id) {
        return parkingSessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session %d not found".formatted(id)));
    }
}
