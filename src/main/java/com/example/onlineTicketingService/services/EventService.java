package com.example.onlineTicketingService.services;

import com.example.onlineTicketingService.models.Event;
import com.example.onlineTicketingService.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }
    public Optional<Event> getById(long eventId) {
        return eventRepository.findById(eventId);
    }
    public void addEvent(Event event) {
        eventRepository.save(event);
    }
    public void deleteEvent(long eventId) {
        eventRepository.delete(eventRepository.findById(eventId).orElseThrow());
    }
    public void updateEvent(Long eventId, Event event) {
        Event eventById = eventRepository.findById(eventId).orElseThrow();
        eventById.setTitle(event.getTitle());
        eventById.setDescription(event.getDescription());
        eventById.setEventDate(event.getEventDate());
        eventById.setTicketPrice(event.getTicketPrice());
        eventById.setCapacity(event.getCapacity());
        eventRepository.save(eventById);
    }
    public Event findByTitle(String title) {
        Event event = eventRepository.findEventByTitle(title).get();
        return event;
    }
}
