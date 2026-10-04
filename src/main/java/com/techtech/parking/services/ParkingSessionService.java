package com.techtech.parking.services;

import com.techtech.parking.dto.ParkingSessionDto;

public interface ParkingSessionService {

    ParkingSessionDto entry (Long vehicleId, Long spaceId);

    ParkingSessionDto exit (Long vehicleId);
}
