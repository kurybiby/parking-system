package com.techtech.parking.services;

import com.techtech.parking.dto.VehicleDto;
import com.techtech.parking.entities.Vehicle;

public interface VehicleService {

    VehicleDto create(VehicleDto dto);

    void deleteVehicle(Long id);

    VehicleDto updateVehicle(Long id, VehicleDto dto);

    VehicleDto getVehicleById(Long id);

    Vehicle findById(Long id);

}
