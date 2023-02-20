package com.example.onlineTicketingService.repository;

import com.example.onlineTicketingService.models.Event;
import com.example.onlineTicketingService.models.Place;
import com.example.onlineTicketingService.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PlaceRepository
        extends JpaRepository<Place, Long> {

}
