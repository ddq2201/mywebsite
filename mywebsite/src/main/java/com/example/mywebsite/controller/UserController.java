package com.example.mywebsite.controller;

import com.example.mywebsite.model.User;
import com.example.mywebsite.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepo;

    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    // Display list of users
    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userRepo.findAll());
        model.addAttribute("contentFragment", "users");
        return "layout";
    }

    // Show form to create a new user
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("contentFragment", "user-form");
        return "layout";
    }

    // Show form to edit an existing user
    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
        model.addAttribute("user", user);
        model.addAttribute("contentFragment", "user-form");
        return "layout";
    }

    // Save a new user
    @PostMapping
    public String saveUser(@ModelAttribute User user) {
        userRepo.save(user);
        return "redirect:/users";
    }

    // Update an existing user
    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user) {
        userRepo.save(user);
        return "redirect:/users";
    }

    // Delete a user
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepo.deleteById(id);
        return "redirect:/users";
    }
}
