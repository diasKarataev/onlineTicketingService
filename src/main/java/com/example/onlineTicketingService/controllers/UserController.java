package com.example.onlineTicketingService.controllers;


import com.example.onlineTicketingService.models.User;
import com.example.onlineTicketingService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "api/users")

public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable(value = "id") long id){
        return userService.getById(id);
    }
    @PostMapping("/add")
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(value = "id") long id){
        userService.delete(id);
    }
    @PutMapping("/{id}")
    public void updateUser(@PathVariable(value = "id") long id, @RequestBody User user){
        userService.updateUser(id, user);
    }
}
