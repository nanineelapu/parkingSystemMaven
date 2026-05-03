package com.parking.dto;

public class AvailableSlotsResponse {
    private long totalSlots;
    private long freeSlots;
    private long occupiedSlots;

    public AvailableSlotsResponse() {}

    public AvailableSlotsResponse(long totalSlots, long freeSlots, long occupiedSlots) {
        this.totalSlots = totalSlots;
        this.freeSlots = freeSlots;
        this.occupiedSlots = occupiedSlots;
    }

    public long getTotalSlots() { return totalSlots; }
    public void setTotalSlots(long totalSlots) { this.totalSlots = totalSlots; }
    public long getFreeSlots() { return freeSlots; }
    public void setFreeSlots(long freeSlots) { this.freeSlots = freeSlots; }
    public long getOccupiedSlots() { return occupiedSlots; }
    public void setOccupiedSlots(long occupiedSlots) { this.occupiedSlots = occupiedSlots; }
}
