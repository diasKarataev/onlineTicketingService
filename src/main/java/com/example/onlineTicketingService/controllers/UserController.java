package com.example.onlineTicketingService.controllers;


import com.example.onlineTicketingService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;


@Controller
@RequestMapping(path = "users")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public String openUsersPage(Model model){
        userService.getUsers(model);
        return "users";
    }
    @PostMapping("/new")
    public String addUser(@RequestParam String username, @RequestParam String name, @RequestParam String surname,
                                 @RequestParam String email, @RequestParam String city, @RequestParam LocalDate dateOfBirth,
                                 @RequestParam String password, Model model){
        userService.addUser(username,name,surname,email,city,dateOfBirth,password);
        return "redirect:/users";
    }
    @GetMapping("/new")
    public String openRegisterPage(Model model){
        return "users-add";
    }
    @GetMapping("/{id}")
    public String userDetails(@PathVariable(value = "id") long id, Model model){
        userService.openDetailsPage(id,model);
        return "user-details";
    }
    @GetMapping("/{id}/edit")
    public String userEdit(@PathVariable(value = "id") long id, Model model){
        userService.openEditPage(id, model);
        return "user-edit";
    }
    @PostMapping("/{id}/edit")
    public String userUpdate(@PathVariable(value = "id") long id, @RequestParam String username,
                             @RequestParam String name, @RequestParam String surname, @RequestParam String email,
                             @RequestParam String city, @RequestParam LocalDate dateOfBirth,
                             @RequestParam String password,Model model){
        userService.updateUser(id,username,name,surname,email,city,dateOfBirth,password);
        return "redirect:/users";
    }
    @PostMapping("/{id}/delete")
    public String userDelete(@PathVariable(value = "id") long id, Model model){
        userService.delete(id);
        return "redirect:/users";
    }
    @GetMapping("/registration")
    public String openRegistrationPage(){
        return "registration";
    }
}
