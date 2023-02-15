package com.example.onlineTicketingService.controllers;

import com.example.onlineTicketingService.models.Event;
import com.example.onlineTicketingService.models.Ticket;
import com.example.onlineTicketingService.repository.EventRepository;
import com.example.onlineTicketingService.repository.TicketRepository;
import com.example.onlineTicketingService.services.EventService;
import com.example.onlineTicketingService.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping(path = "tickets")
public class TicketController {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private EventRepository eventRepository;

    private TicketService ticketService;
    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public String getTickets(Model model){
        ticketService.getTickets(model);
        return "tickets";
    }
    @PostMapping("/{id}/buyTicket")
    public String buyTicket(@PathVariable(value = "id") long id, Model model){
        ticketService.buyTicket(id);
        return "redirect:/events";
    }
}
