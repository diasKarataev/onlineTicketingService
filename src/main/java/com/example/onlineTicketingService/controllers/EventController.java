package com.example.onlineTicketingService.controllers;

import com.example.onlineTicketingService.models.Event;
import com.example.onlineTicketingService.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/events")
public class EventController {
    private final EventService eventService;
    @GetMapping
    public List<Event> getEvents(){
        return eventService.getEvents();
    }
    @GetMapping("/{id}")
    public Optional<Event> getById(@PathVariable(value = "id") long id){
        return eventService.getById(id);
    }
    @PostMapping("/add")
    public void addEvent(@RequestBody Event event) {
        eventService.addEvent(event);
    }
    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable(value = "id") long id){
        eventService.deleteEvent(id);
    }
    @PutMapping("/{id}")
    public void updateEvent(@PathVariable(value = "id") long id, @RequestBody Event event){
        eventService.updateEvent(id, event);
    }
    @PostMapping("/find")
    public Long findByTitle(@RequestBody Event event){
        return eventService.findByTitle(event.getTitle()).getId();
    }

}
