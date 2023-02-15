package com.example.onlineTicketingService.controllers;

import com.example.onlineTicketingService.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@Controller
@RequestMapping(path = "events")
public class EventController {
    private final EventService eventService;
    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public String openEventsPage(Model model){
        eventService.showEvents(model);
        return "events";
    }
    @GetMapping("/new")
    public String openAddPage(){
        return "event-add";
    }
    @PostMapping("/new")
    public String createEvent(@RequestParam String title, @RequestParam String description,
                           @RequestParam LocalDate eventDate, @RequestParam int price,
                           @RequestParam int capacity, @RequestParam MultipartFile fileImage, Model model) throws IOException {
        eventService.createEvent(title,description,eventDate,price,capacity, fileImage);
        return "redirect:/events";
    }
    @GetMapping("/{id}")
    public String openDetailsPage(@PathVariable(value = "id") long id, Model model){
        eventService.openEventDetails(id, model);
        return "event-details";
    }
    @PostMapping("/{id}/delete")
    public String deleteEvent(@PathVariable(value = "id") long id, Model model){
        eventService.deleteEvent(id);
        return "redirect:/events";
    }
    @GetMapping("/{id}/edit")
    public String openEditPage(@PathVariable(value = "id") long id, Model model){
        eventService.openEditPage(id, model);
        return "event-edit";
    }
    @PostMapping("/{id}/edit")
    public String updateEvent(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String description,
                              @RequestParam LocalDate eventDate, @RequestParam int price,
                              @RequestParam int capacity, Model model){
        eventService.updateEvent(id, title, description, eventDate,price,capacity);
        return "redirect:/events";
    }

    @PostMapping("/find")
    public String findEventByTitle(@RequestParam String title, Model model){
        eventService.findEventByTitle(title, model);
        return "found-result";
    }

}
