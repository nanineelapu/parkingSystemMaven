package com.parking.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;
    
    private String vehicleNumber;
    private Long slotId;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    
    private Double amount;

    public Long getTicketId() { return ticketId; }
    public void setTicketId(Long ticketId) { this.ticketId = ticketId; }
    public String getVehicleNumber() { return vehicleNumber; }
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }
    public Long getSlotId() { return slotId; }
    public void setSlotId(Long slotId) { this.slotId = slotId; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public void setEntryTime(LocalDateTime entryTime) { this.entryTime = entryTime; }
    public LocalDateTime getExitTime() { return exitTime; }
    public void setExitTime(LocalDateTime exitTime) { this.exitTime = exitTime; }
    public TicketStatus getStatus() { return status; }
    public void setStatus(TicketStatus status) { this.status = status; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
}
