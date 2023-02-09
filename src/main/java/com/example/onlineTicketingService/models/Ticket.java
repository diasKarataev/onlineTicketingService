package com.example.onlineTicketingService.models;
import jakarta.persistence.*;


@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @SequenceGenerator(name = "ticket_sequence", sequenceName = "ticket_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_sequence")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public Ticket() {

    }

    public Long getId() {
        return id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
