package com.techtech.parking.controller;

import com.techtech.parking.dto.ParkingSessionDto;
import com.techtech.parking.entities.ParkingSession;
import com.techtech.parking.services.ParkingSessionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/sessions")
@RequiredArgsConstructor
@Tag(name = "Parking session controller", description = "exit, entry operations")
public class ParkingSessionController {

    private final ParkingSessionService parkingSessionService;

    @PostMapping("/entry")
    public ParkingSessionDto entry (Long vehicleId, Long spaceId){
        return parkingSessionService.entry(vehicleId, spaceId);
    }

    @PostMapping("/exit")
    public ParkingSessionDto exit (Long vehicleId){
        return parkingSessionService.exit(vehicleId);
    }
}
