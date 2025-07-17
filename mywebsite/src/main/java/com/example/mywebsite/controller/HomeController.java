package com.example.mywebsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "###");
        model.addAttribute("pageTitle", "Home");
        model.addAttribute("contentFragment", "index");
        return "layout";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("message", "About Us");
        model.addAttribute("pageTitle", "About");
        model.addAttribute("contentFragment", "about");
        return "layout";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("message", "Contact Us");
        model.addAttribute("pageTitle", "Contact");
        model.addAttribute("contentFragment", "contact");
        return "layout";
    }

    @PostMapping("/contact")
    public String handleContactForm(@RequestParam String name,
                                    @RequestParam String email,
                                    @RequestParam String message,
                                    Model model) {
        // TODO: Save to DB or send email
        System.out.println("Contact form submitted by: " + name + ", email: " + email);

        model.addAttribute("message", "Thank you, " + name + "! We’ve received your message.");
        model.addAttribute("pageTitle", "Contact");
        model.addAttribute("contentFragment", "contact");
        return "layout";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("pageTitle", "Dashboard");
        model.addAttribute("userCounts", new int[]{12, 19, 3, 5, 2, 3});
        model.addAttribute("labels", new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun"});
        model.addAttribute("contentFragment", "dashboard");
        return "layout";
    }
}

