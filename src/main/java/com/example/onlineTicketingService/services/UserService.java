package com.example.onlineTicketingService.services;

import com.example.onlineTicketingService.models.User;
import com.example.onlineTicketingService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    public Optional<User> getById(long userId) {
        return userRepository.findById(userId);
    }
    public void addUser(User user) {
        userRepository.save(user);
    }
    public void delete(long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        userRepository.delete(user);
    }
    public void updateUser(long id, User user) {
        User userById = userRepository.findById(id).orElseThrow();
        userById.setUsername(user.getUsername());
        userById.setName(user.getName());
        userById.setSurname(user.getSurname());
        userById.setEmail(user.getEmail());
        userById.setCity(user.getCity());
        userById.setDateOfBirth(user.getDateOfBirth());
        userById.setPassword(user.getPassword());
        userRepository.save(userById);
    }
}
