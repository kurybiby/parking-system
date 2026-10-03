package com.techtech.parking.services.impl;

import com.techtech.parking.dto.VehicleDto;
import com.techtech.parking.entities.Vehicle;
import com.techtech.parking.exceptions.ParkingBusinessException;
import com.techtech.parking.exceptions.ResourceNotFoundException;
import com.techtech.parking.mapper.impl.VehicleMapperImpl;
import com.techtech.parking.repository.VehicleRepository;
import com.techtech.parking.services.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    VehicleRepository vehicleRepository;
    VehicleMapperImpl vehicleMapper;

    @Override
    @Transactional
    public VehicleDto create(VehicleDto dto) {
        if (vehicleRepository.existsByLicensePlate(dto.getLicensePlate())) {
            throw new ParkingBusinessException("Такой вехикле уже есть");
        }
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate(dto.getLicensePlate());
        vehicle.setVehicleType(dto.getVehicleType());
        vehicle = vehicleRepository.save(vehicle);

        return vehicleMapper.toDto(vehicle);
    }

    @Override
    @Transactional
    public void deleteVehicle(Long id) {
        vehicleRepository.delete(findById(id));
    }

    @Override
    @Transactional
    public VehicleDto updateVehicle(Long id, VehicleDto dto) {
        Vehicle vehicle = findById(id);
        vehicle.setLicensePlate(dto.getLicensePlate());
        vehicle.setVehicleType(dto.getVehicleType());
        return vehicleMapper.toDto(vehicleRepository.save(vehicle));
    }

    @Override
    @Transactional(readOnly = true)
    public VehicleDto getVehicleById(Long id) {
        return vehicleMapper.toDto(findById(id));
    }

    @Override
    public Vehicle findById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Вехикле отсутствует по такому id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehicleDto> getAllVehicles() {
        return vehicleRepository.findAll().stream()
                .map(vehicleMapper::toDto)
                .collect(Collectors.toList());
    }
}
