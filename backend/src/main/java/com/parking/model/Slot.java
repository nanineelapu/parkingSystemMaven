package com.parking.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long slotId;
    
    private Integer slotNumber;
    private Boolean isAvailable;
    
    @Enumerated(EnumType.STRING)
    private VehicleType slotType;
    
    private Integer floorNumber;
}
