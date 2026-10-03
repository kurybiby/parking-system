package com.techtech.parking.services;

import com.techtech.parking.dto.request.ParkingSpaceRequest;
import com.techtech.parking.dto.response.ParkingSpaceResponse;

import java.util.List;

public interface ParkingSpaceService {
    ParkingSpaceResponse create(ParkingSpaceRequest request);

    List<ParkingSpaceResponse> findAll();

    List<ParkingSpaceResponse> findAvailable();

    ParkingSpaceResponse findById(Long id);

    ParkingSpaceResponse update(Long id, ParkingSpaceRequest request);

    void delete(Long id);
}
