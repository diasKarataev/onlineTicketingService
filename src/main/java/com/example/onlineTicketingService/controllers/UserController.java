package com.example.onlineTicketingService.controllers;

import com.example.onlineTicketingService.models.User;
import com.example.onlineTicketingService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public String users(Model model){
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }
    @PostMapping("/new")
    public String userAddByAdmin(@RequestParam String username, @RequestParam String name, @RequestParam String surname,
                                 @RequestParam String email, @RequestParam String city, @RequestParam LocalDate dateOfBirth,
                                 @RequestParam String password, Model model){
        User user = new User(username, name, surname, email, city, dateOfBirth, password);
        userRepository.save(user);
        return "redirect:/users";
    }
    @GetMapping("/new")
    public String register(Model model){
        return "users-add";
    }
    @GetMapping("/{id}")
    public String userDetails(@PathVariable(value = "id") long id, Model model){
        Optional<User> user = userRepository.findById(id);
        ArrayList<User> res = new ArrayList<>();
        user.ifPresent(res::add);
        model.addAttribute("user", res);
        return "user-details";
    }
    @GetMapping("/{id}/edit")
    public String userEdit(@PathVariable(value = "id") long id, Model model){
        if(!userRepository.existsById(id)){
            return "users";
        }
        Optional<User> user = userRepository.findById(id);
        ArrayList<User> res = new ArrayList<>();
        user.ifPresent(res::add);
        model.addAttribute("user", res);
        return "user-edit";
    }
    @PostMapping("/{id}/edit")
    public String userUpdate(@PathVariable(value = "id") long id, @RequestParam String username,
                             @RequestParam String name, @RequestParam String surname, @RequestParam String email,
                             @RequestParam String city, @RequestParam LocalDate dateOfBirth,
                             @RequestParam String password,Model model){
        User user = userRepository.findById(id).orElseThrow();
        user.setUsername(username);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setCity(city);
        user.setDateOfBirth(dateOfBirth);
        user.setPassword(password);
        userRepository.save(user);
        return "redirect:/users";
    }
    @PostMapping("/{id}/delete")
    public String userDelete(@PathVariable(value = "id") long id, Model model){
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
        return "redirect:/users";
    }
}
