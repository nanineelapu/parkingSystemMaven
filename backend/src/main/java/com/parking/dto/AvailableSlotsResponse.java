package com.parking.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class AvailableSlotsResponse {
    private long totalSlots;
    private long freeSlots;
    private long occupiedSlots;
}
