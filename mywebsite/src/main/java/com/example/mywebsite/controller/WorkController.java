package com.example.mywebsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WorkController {

    @GetMapping("/materials")
    public String workMaterials(Model model) {
        model.addAttribute("pageTitle", "Work Materials");
        model.addAttribute("contentFragment", "materials"); // points to materials.html
        return "layout";
    }
}
