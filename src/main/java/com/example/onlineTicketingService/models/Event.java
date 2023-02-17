package com.example.onlineTicketingService.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
@Data
@Builder
@Entity
@Table(name="events")
public class Event {
    @Id
    @SequenceGenerator(name = "event_sequence", sequenceName = "event_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_sequence")
    private Long id;
    private String title;
    private String description;
    private LocalDate eventDate;
    private int capacity;
    private int ticketPrice;
    @OneToMany(mappedBy="event", cascade = CascadeType.ALL)
    private Set<Ticket> tickets;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Image previewImage;
    private LocalDateTime dateOfCreated;
    @PrePersist
    private void init(){
        dateOfCreated = LocalDateTime.now();
    }


    public Event() {
    }

    public Event(String title, String description, LocalDate eventDate, int capacity, int ticketPrice) {
        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.capacity = capacity;
        this.ticketPrice = ticketPrice;
    }

    public Event(Long id, String title, String description, LocalDate eventDate, int capacity, int ticketPrice, Set<Ticket> tickets, Image previewImage, LocalDateTime dateOfCreated) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.capacity = capacity;
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", eventDate=" + eventDate +
                ", capacity=" + capacity +
                ", ticketPrice=" + ticketPrice +
                ", tickets=" + tickets +
                ", previewImage=" + previewImage +
                ", dateOfCreated=" + dateOfCreated +
                '}';
    }
}
