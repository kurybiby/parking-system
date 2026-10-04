package com.techtech.parking.dto;

import com.techtech.parking.enums.ParkingSessionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSessionDto {
    private Long id;
    private Long vehicleId;
    private Long parkingSpaceId;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private BigDecimal cost;
    private ParkingSessionStatus status;
}
