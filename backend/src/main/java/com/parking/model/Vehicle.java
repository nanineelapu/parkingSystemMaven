package com.parking.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;
    
    private String vehicleNumber;
    
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;
}
