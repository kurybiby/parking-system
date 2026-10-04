package com.techtech.parking.mapper;

import com.techtech.parking.dto.ParkingSessionDto;
import com.techtech.parking.entities.ParkingSession;

public interface ParkingSessionMapper {

    ParkingSessionDto toDto (ParkingSession session);
}
