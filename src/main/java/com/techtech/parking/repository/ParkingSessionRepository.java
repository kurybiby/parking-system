package com.techtech.parking.repository;

import com.techtech.parking.entities.ParkingSession;
import com.techtech.parking.enums.ParkingSessionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingSessionRepository extends JpaRepository<ParkingSession, Long> {

    boolean existsByVehicleIdAndStatus(
            Long vehicleId,
            ParkingSessionStatus status
    );

    boolean existsByParkingSpaceIdAndStatus(
            Long placeId,
            ParkingSessionStatus status
    );

    List<ParkingSession> findByStatus(
            ParkingSessionStatus status
    );
}