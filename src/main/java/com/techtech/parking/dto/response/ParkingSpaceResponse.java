package com.techtech.parking.dto.response;

import com.techtech.parking.enums.VehicleType;

public record ParkingSpaceResponse(
        Long id,
        Long numberOfSpace,
        VehicleType type,
        boolean isAvailable
) {}
