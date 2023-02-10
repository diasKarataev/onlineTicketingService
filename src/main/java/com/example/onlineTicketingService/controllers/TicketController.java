package com.example.onlineTicketingService.controllers;

import com.example.onlineTicketingService.models.Ticket;
import com.example.onlineTicketingService.models.User;
import com.example.onlineTicketingService.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "tickets")
public class TicketController {
    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping
    public String soldTickets(Model model){
        Iterable<Ticket> tickets = ticketRepository.findAll();
        model.addAttribute("tickets", tickets);
        return "tickets";
    }
}
