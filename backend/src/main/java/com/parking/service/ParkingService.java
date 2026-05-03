package com.parking.service;

import com.parking.dto.*;
import com.parking.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class ParkingService {
    @Autowired
    private SlotService slotService;
    @Autowired
    private TicketService ticketService;

    public ParkingResponse parkVehicle(ParkingRequest request) {
        Slot slot = slotService.findFirstAvailableSlotSortedByFloorAndNumber(request.getVehicleType());
        slotService.allocateSlot(slot);
        Ticket ticket = ticketService.createTicket(request.getVehicleNumber(), slot.getSlotId());
        
        return new ParkingResponse(ticket.getTicketId(), slot.getSlotNumber());
    }

    public ExitResponse exitVehicle(ExitRequest request) {
        Ticket ticket = ticketService.getActiveTicket(request.getTicketId());
        LocalDateTime exitTime = LocalDateTime.now();
        
        double amount = calculateFee(ticket.getEntryTime(), exitTime);
        
        ticketService.closeTicket(ticket, amount, exitTime);
        slotService.freeSlot(ticket.getSlotId());
        
        Duration duration = Duration.between(ticket.getEntryTime(), exitTime);
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        String durationStr = hours + " hours " + minutes + " minutes";
        
        return new ExitResponse(amount, durationStr);
    }

    private double calculateFee(LocalDateTime entry, LocalDateTime exit) {
        long minutes = Duration.between(entry, exit).toMinutes();
        if (minutes == 0) minutes = 1; // minimum 1 minute
        
        double hours = Math.ceil((double) minutes / 60.0);
        double fee = 0;
        
        if (hours <= 1) {
            fee = 20.0;
        } else {
            fee = 20.0 + ((hours - 1) * 10.0);
        }
        
        int exitHour = exit.getHour();
        if (exitHour >= 18 && exitHour < 22) {
            fee = fee * 1.5;
        }
        
        return fee;
    }

    public AvailableSlotsResponse getAvailableSlots() {
        return new AvailableSlotsResponse(
            slotService.getTotalSlots(),
            slotService.getFreeSlots(),
            slotService.getOccupiedSlots()
        );
    }
}
