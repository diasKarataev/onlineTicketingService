package com.example.onlineTicketingService.controllers;

import com.example.onlineTicketingService.models.User;
//import com.example.onlineTicketingService.service.UserService;
import com.example.onlineTicketingService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//@RestController

@Controller
@RequestMapping(path = "users")
public class UserController {
//    private final UserService userService;
//    @Autowired
//    public UserController(UserService userService){
//        this.userService = userService;
//    }
//    @GetMapping("/list")
//    public List<User> getUser(){
//        return userService.getUsers();
//    }
//    @PostMapping
//    public void addUser(@RequestBody User user){
//        userService.addNewUser(user);
//    }
//    @PostMapping("/add")
//    public String addUser(@RequestParam String name, @RequestParam String email, @RequestParam LocalDate dateOfBirth){
//        userService.addNewUser(new User(name,email,dateOfBirth));
//        return "users";
//    }
//    @DeleteMapping(path = "{userId}")
//    public void deleteUser(@PathVariable("userId") Long userId){
//        userService.deleteUser(userId);
//    }
//    @PutMapping(path = "{userId}")
//    public void updateUser(@PathVariable("userId") Long userId, String name, String email){
//        userService.updateUser(userId, name, email);
//    }
    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public String users(Model model){
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }
    @GetMapping("/new")
    public String register(Model model){
        return "register";
    }
    @PostMapping("/new")
    public String userAdd(@RequestParam String name, @RequestParam String email, @RequestParam LocalDate dateOfBirth, Model model){
        User user = new User(name,email,dateOfBirth);
        userRepository.save(user);

        return "redirect:/users";
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
    public String userUpdate(@PathVariable(value = "id") long id, @RequestParam String name, @RequestParam String email, @RequestParam LocalDate dateOfBirth, Model model){
        User user = userRepository.findById(id).orElseThrow();
        user.setName(name);
        user.setEmail(email);
        user.setDateOfBirth(dateOfBirth);
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
