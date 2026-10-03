package com.techtech.parking.mapper;

import com.techtech.parking.dto.VehicleDto;
import com.techtech.parking.entities.Vehicle;

public interface VehicleMapper {
    VehicleDto toDto(Vehicle vehicle);
}
