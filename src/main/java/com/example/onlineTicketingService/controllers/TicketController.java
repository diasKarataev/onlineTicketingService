package com.example.onlineTicketingService.controllers;

import com.example.onlineTicketingService.models.Ticket;
import com.example.onlineTicketingService.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "api/tickets")
public class TicketController {
    private TicketService ticketService;
    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    @GetMapping
    public List<Ticket> getTickets(){
        return ticketService.getTickets();
    }
    @PostMapping("/{id}/buyTicket")
    public String buyTicket(@PathVariable(value = "id") long id, Model model){
        ticketService.buyTicket(id);
        return "redirect:/events";
    }
}
