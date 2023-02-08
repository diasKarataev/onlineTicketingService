package com.example.onlineTicketingService.controllers;

import com.example.onlineTicketingService.models.Event;
import com.example.onlineTicketingService.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "events")
public class EventController {
    @Autowired
    public EventRepository eventRepository;
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
                           @RequestParam int capacity, Model model){
        Event event = new Event(title,description,eventDate, price, capacity);
        eventRepository.save(event);
        return "redirect:/events";
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
}
