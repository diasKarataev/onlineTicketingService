package com.example.onlineTicketingService.services;

import com.example.onlineTicketingService.models.Event;
import com.example.onlineTicketingService.models.Place;
import com.example.onlineTicketingService.repository.EventRepository;
import com.example.onlineTicketingService.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {
    @Autowired
    private PlaceRepository placeRepository;
    public Optional<Place> getById(long eventId) {
        return placeRepository.findById(eventId);
    }
    public void addPlace(Place place) {
        placeRepository.save(place);
    }
    public void deletePlace(long placeId) {
        placeRepository.delete(placeRepository.findById(placeId).orElseThrow());
    }
    public void updateEvent(Long eventId, Event event) {
        Place placeById = placeRepository.findById(eventId).orElseThrow();
        placeById.setName(placeById.getName());
        placeById.setCapacity(placeById.getCapacity());
        placeRepository.save(placeById);
    }

    public List<Place> getPlaces() {
       return placeRepository.findAll();
    }

    public void updatePlace(long id, Place place) {
    }
}
