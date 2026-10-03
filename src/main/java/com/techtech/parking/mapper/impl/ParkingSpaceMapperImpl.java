package com.techtech.parking.mapper.impl;

import com.techtech.parking.dto.request.ParkingSpaceRequest;
import com.techtech.parking.dto.response.ParkingSpaceResponse;
import com.techtech.parking.entities.ParkingSpace;
import com.techtech.parking.mapper.ParkingSpaceMapper;
import org.springframework.stereotype.Service;

@Service
public class ParkingSpaceMapperImpl implements ParkingSpaceMapper {
    @Override
    public ParkingSpaceResponse toResponse(ParkingSpace parkingSpace) {
        return new ParkingSpaceResponse(
                parkingSpace.getId(),
                parkingSpace.getNumberOfSpace(),
                parkingSpace.getVehicleType(),
                parkingSpace.isAvailable()
        );
    }

    @Override
    public void update(ParkingSpace parkingSpace,ParkingSpaceRequest request ) {
        parkingSpace.setNumberOfSpace(request.numberOfSpace());
        parkingSpace.setVehicleType(request.vehicleType());
    }

    @Override
    public ParkingSpace toEntity(ParkingSpaceRequest request) {
        ParkingSpace space = new ParkingSpace();
        space.setNumberOfSpace(request.numberOfSpace());
        space.setVehicleType(request.vehicleType());
        space.setAvailable(true);
        return space;
    }
}
