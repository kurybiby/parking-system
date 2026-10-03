package com.techtech.parking.mapper;

import com.techtech.parking.dto.request.ParkingSpaceRequest;
import com.techtech.parking.dto.response.ParkingSpaceResponse;
import com.techtech.parking.entities.ParkingSpace;

public interface ParkingSpaceMapper {

    ParkingSpaceResponse toResponse (ParkingSpace parkingSpace);

    void update(ParkingSpace parkingSpace, ParkingSpaceRequest parkingSpaceRequest);

    ParkingSpace toEntity (ParkingSpaceRequest parkingSpaceRequest);
}
