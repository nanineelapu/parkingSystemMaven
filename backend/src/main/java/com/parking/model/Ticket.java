package com.parking.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
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
}
