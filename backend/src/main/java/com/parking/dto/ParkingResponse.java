package com.parking.dto;

public class ParkingResponse {
    private Long ticketId;
    private Integer slotNumber;

    public ParkingResponse() {}

    public ParkingResponse(Long ticketId, Integer slotNumber) {
        this.ticketId = ticketId;
        this.slotNumber = slotNumber;
    }

    public Long getTicketId() { return ticketId; }
    public void setTicketId(Long ticketId) { this.ticketId = ticketId; }
    public Integer getSlotNumber() { return slotNumber; }
    public void setSlotNumber(Integer slotNumber) { this.slotNumber = slotNumber; }
}
