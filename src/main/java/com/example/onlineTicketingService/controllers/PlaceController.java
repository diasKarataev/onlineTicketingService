package com.example.onlineTicketingService.controllers;

import com.example.onlineTicketingService.models.Event;
import com.example.onlineTicketingService.models.Place;
import com.example.onlineTicketingService.services.EventService;
import com.example.onlineTicketingService.services.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/places")
public class PlaceController {
    private final PlaceService placeService;
    @GetMapping
    public List<Place> getPlaces(){
        return placeService.getPlaces();
    }
    @GetMapping("/{id}")
    public Optional<Place> getById(@PathVariable(value = "id") long id){
        return placeService.getById(id);
    }
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void addPlace(@RequestBody Place place) {
        placeService.addPlace(place);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deletePlace(@PathVariable(value = "id") long id){
        placeService.deletePlace(id);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void updatePlace(@PathVariable(value = "id") long id, @RequestBody Place place){
        placeService.updatePlace(id, place);
    }
    }
