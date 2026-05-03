package com.parking.repository;

import com.parking.model.Ticket;
import com.parking.model.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findByTicketIdAndStatus(Long ticketId, TicketStatus status);
}
