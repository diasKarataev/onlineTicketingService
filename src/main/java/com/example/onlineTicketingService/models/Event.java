package com.example.onlineTicketingService.models;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    private int price;
    private int capacity;

    @OneToMany(mappedBy="event", cascade = CascadeType.ALL)
    private Set<Ticket> tickets;
//    @OneToMany(cascade = CascadeType.ALL)
//    private ArrayList<Ticket> eventTickets = new ArrayList<>();
//    public ArrayList<Ticket> getEventTickets() {
//        return eventTickets;
//    }
//    public void addTicket(Ticket ticket) {
//        eventTickets.add(ticket);
//    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Image previewImage;



    private LocalDateTime dateOfCreated;
    @PrePersist
    private void init(){
        dateOfCreated = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public Event() {
    }

    public Event(String title, String description, LocalDate eventDate, int price, int capacity) {
        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.price = price;
        this.capacity = capacity;
    }

    public Event(Long id, String title, String description, LocalDate eventDate, int price, int capacity) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.price = price;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", eventDate=" + eventDate +
                ", price=" + price +
                ", capacity=" + capacity +
                '}';
    }

    public void setPreviewImage(Image previewImage) {
        this.previewImage = previewImage;
    }

    public Image getPreviewImage() {
        return previewImage;
    }

    public LocalDateTime getDateOfCreated() {
        return dateOfCreated;
    }

    public Long getPreviewImageId() {
        return previewImage.getId();
    }
}
