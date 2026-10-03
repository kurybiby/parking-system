package com.techtech.parking.dto.request;

import com.techtech.parking.enums.VehicleType;

public record ParkingSpaceRequest(
        Long numberOfSpace,
        VehicleType vehicleType
) {}