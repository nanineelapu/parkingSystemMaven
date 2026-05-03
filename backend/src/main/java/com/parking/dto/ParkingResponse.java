package com.parking.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class ParkingResponse {
    private Long ticketId;
    private Integer slotNumber;
}
