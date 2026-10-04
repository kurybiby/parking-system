package com.techtech.parking.mapper.impl;

import com.techtech.parking.dto.ParkingSessionDto;
import com.techtech.parking.entities.ParkingSession;
import com.techtech.parking.mapper.ParkingSessionMapper;
import org.springframework.stereotype.Service;

@Service
public class ParkingSessionMapperImpl implements ParkingSessionMapper {
    public ParkingSessionDto toDto(ParkingSession session) {
        ParkingSessionDto dto = new ParkingSessionDto();
        dto.setId(session.getId());
        dto.setVehicleId(session.getVehicle().getId());
        dto.setParkingSpaceId(session.getParkingSpace().getId());
        dto.setEntryTime(session.getEntryTime());
        dto.setExitTime(session.getExitTime());
        dto.setCost(session.getCost());
        dto.setStatus(session.getStatus());
        return dto;
    }
}
