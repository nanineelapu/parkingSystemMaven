package com.parking.dto;

import com.parking.model.VehicleType;
import lombok.Data;

@Data
public class ParkingRequest {
    private String vehicleNumber;
    private VehicleType vehicleType;
}
