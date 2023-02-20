package com.example.onlineTicketingService.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Set;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="places")
public class Place {
    @Id
    @SequenceGenerator(name = "place_sequence", sequenceName = "place_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "place_sequence")
    private Long id;
    private String name;
    private String city;
    private String address;
    private int capacity;
}
