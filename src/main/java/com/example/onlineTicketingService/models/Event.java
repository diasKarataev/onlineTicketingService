package com.example.onlineTicketingService.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="events")
public class Event {
    @Id
    @SequenceGenerator(name = "event_sequence", sequenceName = "event_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_sequence")
    private Long id;
    private String title;
    private String description;
    @ManyToOne (cascade =CascadeType.ALL)
    @JoinColumn(name = "place_id")
    private Place eventPlace;
    private LocalDateTime eventDate;
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
}
