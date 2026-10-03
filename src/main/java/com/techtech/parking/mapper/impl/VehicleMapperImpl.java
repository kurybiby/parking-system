package com.techtech.parking.mapper.impl;


import com.techtech.parking.dto.VehicleDto;
import com.techtech.parking.entities.Vehicle;
import com.techtech.parking.mapper.VehicleMapper;
import org.springframework.stereotype.Service;

@Service
public class VehicleMapperImpl implements VehicleMapper {
    public VehicleDto toDto(Vehicle vehicle) {
        VehicleDto dto = new VehicleDto();
        dto.setId(vehicle.getId());
        dto.setLicensePlate(vehicle.getLicensePlate());
        dto.setVehicleType(vehicle.getVehicleType());
        return dto;
    }
}
