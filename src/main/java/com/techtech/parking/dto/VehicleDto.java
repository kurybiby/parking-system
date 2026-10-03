package com.techtech.parking.dto;

import com.techtech.parking.enums.VehicleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto {
    private Long id;

    @NotNull
    private String licensePlate;

    @NotBlank
    private VehicleType vehicleType;
}
