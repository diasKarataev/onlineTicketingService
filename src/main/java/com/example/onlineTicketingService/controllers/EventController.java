package com.example.onlineTicketingService.controllers;

import com.example.onlineTicketingService.models.Event;
import com.example.onlineTicketingService.models.Image;
import com.example.onlineTicketingService.models.Ticket;
import com.example.onlineTicketingService.repository.EventRepository;
import com.example.onlineTicketingService.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "events")
public class EventController {

    @Autowired
    public EventRepository eventRepository;
    @Autowired
    public TicketRepository ticketRepository;
//    private final EventService eventService;
//    @Autowired
//    public EventController(EventService eventService) {
//        this.eventService = eventService;
//    }

    @GetMapping
    public String events(Model model){
        Iterable<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);
        return "events";
    }
    @GetMapping("/new")
    public String eventRegistration(){
        return "event-add";
    }
    @PostMapping("/new")
    public String addEvent(@RequestParam String title, @RequestParam String description,
                           @RequestParam LocalDate eventDate, @RequestParam int price,
                           @RequestParam int capacity, @RequestParam MultipartFile fileImage, Model model) throws IOException {
        Event event = new Event(title,description,eventDate, price, capacity);
        Image image;
        if(fileImage.getSize() != 0){
            image = toImageEntity(fileImage);
            event.setPreviewImage(image);
        }
        eventRepository.save(event);
        return "redirect:/events";
    }
    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }
    @GetMapping("/{id}")
    public String eventDetails(@PathVariable(value = "id") long id, Model model){
        Optional<Event> event = eventRepository.findById(id);
        ArrayList<Event> res = new ArrayList<>();
        event.ifPresent(res::add);
        model.addAttribute("event", res);
        return "event-details";
    }
    @PostMapping("/{id}/delete")
    public String eventDelete(@PathVariable(value = "id") long id, Model model){
        Event event = eventRepository.findById(id).orElseThrow();
        eventRepository.delete(event);
        return "redirect:/events";
    }
    @GetMapping("/{id}/edit")
    public String eventEdit(@PathVariable(value = "id") long id, Model model){
        if(!eventRepository.existsById(id)){
            return "events";
        }
        Optional<Event> event = eventRepository.findById(id);
        ArrayList<Event> res = new ArrayList<>();
        event.ifPresent(res::add);
        model.addAttribute("event", res);
        return "event-edit";
    }
    @PostMapping("/{id}/edit")
    public String eventUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String description,
                              @RequestParam LocalDate eventDate, @RequestParam int price,
                              @RequestParam int capacity, Model model){
        Event event = eventRepository.findById(id).orElseThrow();
        event.setTitle(title);
        event.setDescription(description);
        event.setEventDate(eventDate);
        event.setPrice(price);
        event.setCapacity(capacity);
        eventRepository.save(event);
        return "redirect:/events";
    }
    @PostMapping("/{id}/buyTicket")
    public String buyTicket(@PathVariable(value = "id") long id, Model model){
        Ticket ticket = new Ticket();
        Event event = eventRepository.findById(id).orElseThrow();
        event.setCapacity(event.getCapacity()-1);
        ticket.setEvent(event);
        ticketRepository.save(ticket);
        eventRepository.save(event);
        return "redirect:/events";
    }
}
