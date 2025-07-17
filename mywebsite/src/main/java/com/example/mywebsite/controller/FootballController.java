package com.example.mywebsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FootballController {

    @GetMapping("/matches")
    public String matches(Model model) {
        model.addAttribute("pageTitle", "Mini Game");
        model.addAttribute("contentFragment", "match");
        return "layout";
    }
}

