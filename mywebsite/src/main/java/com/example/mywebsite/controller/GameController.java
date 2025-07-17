package com.example.mywebsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {

    @GetMapping("/rps")
    public String game(Model model) {
        model.addAttribute("pageTitle", "Mini Game");
        model.addAttribute("contentFragment", "rps");
        return "layout";
    }

    @GetMapping("/snake")
    public String snake(Model model) {
        model.addAttribute("pageTitle", "Snake Game");
        model.addAttribute("contentFragment", "snake");
        return "layout";
    }

    @GetMapping("/dragon")
    public String dragon(Model model) {
        model.addAttribute("pageTitle", "Dragon Flight");
        model.addAttribute("contentFragment", "dragon");
        return "layout";
    }
}

