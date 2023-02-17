package com.example.onlineTicketingService.services;

import com.example.onlineTicketingService.models.Event;
import com.example.onlineTicketingService.models.Ticket;
import com.example.onlineTicketingService.models.User;
import com.example.onlineTicketingService.repository.EventRepository;
import com.example.onlineTicketingService.repository.TicketRepository;
import com.example.onlineTicketingService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserRepository userRepository;
    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }
    public void buyTicket(Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow();
        event.setCapacity(event.getCapacity()-1);
        Ticket ticket = new Ticket();
        if(userRepository.findById(1l) != null){
            User ticketOwner = userRepository.findById(1l).get();
            ticket.setOwner(ticketOwner);
        }
        ticket.setEvent(event);
        ticketRepository.save(ticket);
        eventRepository.save(event);
    }
}
