package com.example.onlineTicketingService.repository;

import com.example.onlineTicketingService.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository
        extends JpaRepository<Ticket, Long> {
}
