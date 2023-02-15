package com.example.onlineTicketingService.services;

import com.example.onlineTicketingService.models.Event;
import com.example.onlineTicketingService.models.Image;
import com.example.onlineTicketingService.models.Ticket;
import com.example.onlineTicketingService.repository.EventRepository;
import com.example.onlineTicketingService.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private TicketRepository ticketRepository;

    public void updateEvent(Long eventId, String title, String description, LocalDate eventDate, int price, int capacity) {
        Event event = eventRepository.findById(eventId).orElseThrow();
        event.setTitle(title);
        event.setDescription(description);
        event.setEventDate(eventDate);
        event.setPrice(price);
        event.setCapacity(capacity);
        eventRepository.save(event);
    }

    public String openEditPage(long eventId, Model model) {
        if(!eventRepository.existsById(eventId)){
            return "events";
        }
        Optional<Event> event = eventRepository.findById(eventId);
        ArrayList<Event> res = new ArrayList<>();
        event.ifPresent(res::add);
        model.addAttribute("event", res);
        return "event-edit";
    }

    public void deleteEvent(long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow();
        eventRepository.delete(event);
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
    public void createEvent(String title, String description, LocalDate eventDate, int price, int capacity, MultipartFile fileImage) throws IOException {
        Event event = new Event(title,description,eventDate, price, capacity);
        Image image;
        if(fileImage.getSize() != 0){
            image = toImageEntity(fileImage);
            event.setPreviewImage(image);
        }
        eventRepository.save(event);
    }

    public String openEventDetails(long eventId, Model model) {
        Optional<Event> event = eventRepository.findById(eventId);
        ArrayList<Event> res = new ArrayList<>();
        event.ifPresent(res::add);
        model.addAttribute("event", res);
        return "event-details";
    }

    public void showEvents(Model model) {
        Iterable<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);
    }

    public void findEventByTitle(String title, Model model) {
        Event event = eventRepository.findEventByTitle(title).get();
        model.addAttribute("eventTitle", event.getId());

    }
}
