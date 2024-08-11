package com.example.goorm_snack.controller;

import java.util.ArrayList;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("username", authentication.getName());
            boolean isAdmin = new ArrayList<>(authentication.getAuthorities())
            		.get(0).getAuthority().equals("ROLE_ADMIN");
            model.addAttribute("isAdmin", isAdmin);
        }
        return "home";
    }

}