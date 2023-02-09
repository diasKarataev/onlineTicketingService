package com.example.onlineTicketingService.models;

import jakarta.persistence.*;


import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

@Entity
@Table(name="users"
//        ,
//        uniqueConstraints = {
//            @UniqueConstraint(columnNames = "username"),
//            @UniqueConstraint(columnNames = "email")
//        }
        )
public class User {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;
    private String username;
    private String password;
//    private boolean active;
//    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
//    @Enumerated(EnumType.STRING)
//    private Set<Role> roles;
    private String email;
    private String name;
    private String surname;
    private String city;
    private LocalDate dateOfBirth;
    @Transient
    private Integer age;



    public User() {
    }

//    public User(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }

    public User(Long id, String username, String name, String surname, String email, String city, LocalDate dateOfBirth, String password) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.city = city;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
    }
    public User(String username, String name, String surname, String email, String city, LocalDate dateOfBirth, String password) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.city = city;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
    }

//    public boolean isActive() {
//        return active;
//    }
//
//    public void setActive(boolean active) {
//        this.active = active;
//    }

//    public Set<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", age=" + age +
                ", password='" + password + '\'' +
                '}';
    }
}

