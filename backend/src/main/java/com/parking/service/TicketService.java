package com.parking.service;

import com.parking.model.Ticket;
import com.parking.model.TicketStatus;
import com.parking.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public Ticket createTicket(String vehicleNumber, Long slotId) {
        Ticket ticket = new Ticket();
        ticket.setVehicleNumber(vehicleNumber);
        ticket.setSlotId(slotId);
        ticket.setEntryTime(LocalDateTime.now());
        ticket.setStatus(TicketStatus.ACTIVE);
        return ticketRepository.save(ticket);
    }

    public Ticket getActiveTicket(Long ticketId) {
        return ticketRepository.findByTicketIdAndStatus(ticketId, TicketStatus.ACTIVE)
                .orElseThrow(() -> new RuntimeException("Ticket not found or already closed"));
    }

    public Ticket closeTicket(Ticket ticket, Double amount, LocalDateTime exitTime) {
        ticket.setExitTime(exitTime);
        ticket.setAmount(amount);
        ticket.setStatus(TicketStatus.CLOSED);
        return ticketRepository.save(ticket);
    }
}
