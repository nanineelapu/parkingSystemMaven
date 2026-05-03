package com.parking.dto;

import com.parking.model.VehicleType;

public class ParkingRequest {
    private String vehicleNumber;
    private VehicleType vehicleType;

    public String getVehicleNumber() { return vehicleNumber; }
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }
    public VehicleType getVehicleType() { return vehicleType; }
    public void setVehicleType(VehicleType vehicleType) { this.vehicleType = vehicleType; }
}
