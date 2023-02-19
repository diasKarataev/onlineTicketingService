package com.example.onlineTicketingService.controllers;


import com.example.onlineTicketingService.models.User;
import com.example.onlineTicketingService.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/users")

public class UserController {
    private final UserService userService;
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> getUsers(){
        return userService.getUsers();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Optional<User> getUserById(@PathVariable(value = "id") long id){
        return userService.getById(id);
    }
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUser(@PathVariable(value = "id") long id){
        userService.delete(id);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void updateUser(@PathVariable(value = "id") long id, @RequestBody User user){
        userService.updateUser(id, user);
    }
}
