package com.example.goorm_snack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.goorm_snack.model.Snack;
import com.example.goorm_snack.service.SnackService;

@Controller
@RequestMapping("/admin/snacks")
public class AdminSnackController {

    private final SnackService snackService;

    public AdminSnackController(SnackService snackService) {
        this.snackService = snackService;
    }

    @GetMapping
    public String listSnacks(Model model) {
        model.addAttribute("snacks", snackService.getAllSnacks());
        return "admin/snack/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("snack", new Snack());
        return "admin/snack/form";
    }

    @PostMapping("/add")
    public String addSnack(@ModelAttribute Snack snack) {
        snackService.addSnack(snack.getName(), snack.getTaste(), snack.getInventoryCount());
        return "redirect:/admin/snacks";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("snack", snackService.getSnackById(id));
        return "admin/snack/form";
    }

    @PostMapping("/edit/{id}")
    public String updateSnack(@PathVariable Long id, @ModelAttribute Snack snack) {
        snackService.updateSnack(id, snack.getName(), snack.getTaste(), snack.getInventoryCount());
        return "redirect:/admin/snacks";
    }

    @PostMapping("/delete/{id}")
    public String deleteSnack(@PathVariable Long id) {
        snackService.deleteSnack(id);
        return "redirect:/admin/snacks";
    }
}