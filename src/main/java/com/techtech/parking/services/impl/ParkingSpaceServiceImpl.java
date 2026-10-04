package com.techtech.parking.services.impl;

import com.techtech.parking.dto.request.ParkingSpaceRequest;
import com.techtech.parking.dto.response.ParkingSpaceResponse;
import com.techtech.parking.entities.ParkingSpace;
import com.techtech.parking.enums.ParkingSessionStatus;
import com.techtech.parking.exceptions.ParkingBusinessException;
import com.techtech.parking.exceptions.ResourceNotFoundException;
import com.techtech.parking.mapper.ParkingSpaceMapper;
import com.techtech.parking.repository.ParkingSessionRepository;
import com.techtech.parking.repository.ParkingSpaceRepository;
import com.techtech.parking.services.ParkingSpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingSpaceServiceImpl implements ParkingSpaceService {

    private final ParkingSpaceRepository parkingSpaceRepository;
    private final ParkingSpaceMapper mapper;
    private final ParkingSessionRepository sessionRepository;

    @Override
    public ParkingSpaceResponse create(ParkingSpaceRequest request) {
        if (parkingSpaceRepository.existsByNumberOfSpace(request.numberOfSpace())) {
            throw new ParkingBusinessException("Такой уже есть");
        }
        return mapper.toResponse(parkingSpaceRepository.save(mapper.toEntity(request)));
    }

    @Override
    public List<ParkingSpaceResponse> findAll() {
        return parkingSpaceRepository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public List<ParkingSpaceResponse> findAvailable() {
        return parkingSpaceRepository.findByIsAvailableTrue().stream().map(mapper::toResponse).toList();
    }

    @Override
    public ParkingSpaceResponse findById(Long id) {
        return mapper.toResponse(
                parkingSpaceRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Такого места нет"))
        );
    }

    @Override
    @Transactional
    public ParkingSpaceResponse update(Long id, ParkingSpaceRequest request) {
        ParkingSpace place = parkingSpaceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "не нашел такого места"));

        if (parkingSpaceRepository.existsByNumberOfSpaceAndIdNot(request.numberOfSpace(), id)) {
            throw new ParkingBusinessException(
                    "Такое место уже есть");
        }

        place.setNumberOfSpace(request.numberOfSpace());
        place.setVehicleType(request.vehicleType());

        return mapper.toResponse(place);
    }

    @Override
    public void delete(Long id) {
        if (parkingSpaceRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Такого места не существует");
        } else if (sessionRepository.existsByParkingSpaceIdAndStatus(id, ParkingSessionStatus.ACTIVE)) {
            throw new ParkingBusinessException("Нельзя удалить активную сессию");
        }
        parkingSpaceRepository.deleteById(id);
    }

}
