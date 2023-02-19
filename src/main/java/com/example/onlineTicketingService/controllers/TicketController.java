package com.example.onlineTicketingService.controllers;

import com.example.onlineTicketingService.models.Ticket;
import com.example.onlineTicketingService.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "api/tickets")
public class TicketController {
    private final TicketService ticketService;
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Ticket> getTickets(){
        return ticketService.getTickets();
    }
    @PostMapping("/{id}/buyTicket")
    public void buyTicket(@PathVariable(value = "id") long id, long userId){
        ticketService.buyTicket(id, userId);
    }
}
