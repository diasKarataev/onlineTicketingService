package com.example.onlineTicketingService.services;

import com.example.onlineTicketingService.models.User;
import com.example.onlineTicketingService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void delete(long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        userRepository.delete(user);
    }

    public void updateUser(long id, String username, String name, String surname, String email, String city, LocalDate dateOfBirth, String password) {
        User user = userRepository.findById(id).orElseThrow();
        user.setUsername(username);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setCity(city);
        user.setDateOfBirth(dateOfBirth);
        user.setPassword(password);
        userRepository.save(user);
    }

    public String openEditPage(long eventId, Model model) {
        if(!userRepository.existsById(eventId)){
            return "users";
        }
        Optional<User> user = userRepository.findById(eventId);
        ArrayList<User> res = new ArrayList<>();
        user.ifPresent(res::add);
        model.addAttribute("user", res);
        return "user-edit";
    }

    public String openDetailsPage(long id, Model model) {
        Optional<User> user = userRepository.findById(id);
        ArrayList<User> res = new ArrayList<>();
        user.ifPresent(res::add);
        model.addAttribute("user", res);
        return "user-details";
    }

    public void addUser(String username, String name, String surname, String email, String city, LocalDate dateOfBirth, String password) {
        User user = new User(username, name, surname, email, city, dateOfBirth, password);
        userRepository.save(user);
    }

    public String getUsers(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }
}
