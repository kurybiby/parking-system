package com.techtech.parking.repository;

import com.techtech.parking.entities.ParkingSpace;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Long> {
    List<ParkingSpace> findByIsAvailableTrue();

    void deleteById (Long id);

    boolean existsByNumberOfSpace(Long numberOfSpace);

    ParkingSpace updateById (Long id);

    boolean existsByNumberOfSpaceAndIdNot(Long numberOfSpace, Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from ParkingSpace p where p.id = :id")
    Optional<ParkingSpace> findByIdForUpdate(@Param("id") Long id);
}
