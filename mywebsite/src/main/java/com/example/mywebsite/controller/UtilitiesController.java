package com.example.mywebsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UtilitiesController {

    @GetMapping("/json")
    public String jsonBeautifier(Model model) {
        model.addAttribute("pageTitle", "JSON Beautifier");
        model.addAttribute("contentFragment", "json");
        return "layout";
    }
}
