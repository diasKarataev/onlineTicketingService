package com.example.onlineTicketingService.repository;

import com.example.onlineTicketingService.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EventRepository
        extends JpaRepository<Event, Long> { }
