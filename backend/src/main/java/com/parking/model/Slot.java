package com.parking.model;

import javax.persistence.*;

@Entity
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long slotId;
    
    private Integer slotNumber;
    private Boolean isAvailable;
    
    @Enumerated(EnumType.STRING)
    private VehicleType slotType;
    
    private Integer floorNumber;

    public Long getSlotId() { return slotId; }
    public void setSlotId(Long slotId) { this.slotId = slotId; }
    public Integer getSlotNumber() { return slotNumber; }
    public void setSlotNumber(Integer slotNumber) { this.slotNumber = slotNumber; }
    public Boolean getIsAvailable() { return isAvailable; }
    public void setIsAvailable(Boolean isAvailable) { this.isAvailable = isAvailable; }
    public VehicleType getSlotType() { return slotType; }
    public void setSlotType(VehicleType slotType) { this.slotType = slotType; }
    public Integer getFloorNumber() { return floorNumber; }
    public void setFloorNumber(Integer floorNumber) { this.floorNumber = floorNumber; }
}
