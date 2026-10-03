package com.techtech.parking.controller;

import com.techtech.parking.dto.request.ParkingSpaceRequest;
import com.techtech.parking.dto.response.ParkingSpaceResponse;
import com.techtech.parking.services.ParkingSpaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parking-spaces")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Parking Space Controller", description = "CRUD operations for parking spaces")
public class ParkingSpaceController {

    ParkingSpaceService parkingSpaceService;

    @PostMapping
    @Operation(summary = "Create a new parking place")
    public ParkingSpaceResponse create(@Valid @RequestBody ParkingSpaceRequest request) {
        return parkingSpaceService.create(request);
    }

    @GetMapping
    @Operation(summary = "Get all places")
    public List<ParkingSpaceResponse> getAll() {
        return parkingSpaceService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get parking place by ID")
    public ParkingSpaceResponse getById(@PathVariable Long id) {
        return parkingSpaceService.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update parking place")
    public ParkingSpaceResponse update(
            @PathVariable Long id,
            @Valid @RequestBody ParkingSpaceRequest request
    ) {
        return parkingSpaceService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete parking place")
    public void delete(@PathVariable Long id) {
        parkingSpaceService.delete(id);
    }

}
