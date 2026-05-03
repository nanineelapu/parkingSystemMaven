package com.parking.controller;

import com.parking.dto.*;
import com.parking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @PostMapping("/park")
    public ResponseEntity<ParkingResponse> parkVehicle(@RequestBody ParkingRequest request) {
        return ResponseEntity.ok(parkingService.parkVehicle(request));
    }

    @PostMapping("/exit")
    public ResponseEntity<ExitResponse> exitVehicle(@RequestBody ExitRequest request) {
        return ResponseEntity.ok(parkingService.exitVehicle(request));
    }

    @GetMapping("/slots/available")
    public ResponseEntity<AvailableSlotsResponse> getAvailableSlots() {
        return ResponseEntity.ok(parkingService.getAvailableSlots());
    }
}
