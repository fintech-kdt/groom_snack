package com.example.goorm_snack.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.goorm_snack.model.Member;
import com.example.goorm_snack.model.Snack;
import com.example.goorm_snack.model.SnackLike;
import com.example.goorm_snack.service.SnackService;

@Controller
@RequestMapping("/snacks")
public class SnackController {

    private final SnackService snackService;

    public SnackController(SnackService snackService) {
        this.snackService = snackService;
    }

    @GetMapping
    public String listSnacks(Model model) {
        List<Snack> snacks = snackService.getAllSnacks();
        model.addAttribute("snacks", snacks);
        return "snack/list";
    }

    @GetMapping("/{id}")
    public String viewSnack(@PathVariable Long id, Model model,
    		Authentication authentication) {
        Snack snack = snackService.updateViewCount(id);
        model.addAttribute("snack", snack);
        boolean isLiked = snack.getLikes().stream().map(SnackLike::getMember)
        		.map(Member::getUsername)
        		.anyMatch((el) -> el.equals(authentication.getName()));
        model.addAttribute("isLiked", isLiked);
        return "snack/view";
    }

    @PostMapping("/{id}/like")
    public String likeSnack(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        snackService.addLike(id, userDetails.getUsername());
        return "redirect:/snacks/" + id;
    }

    @PostMapping("/{id}/unlike")
    public String unlikeSnack(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        snackService.removeLike(id, userDetails.getUsername());
        return "redirect:/snacks/" + id;
    }

    @PostMapping("/{id}/comment")
    public String addComment(@PathVariable Long id, @RequestParam String content,
                             @AuthenticationPrincipal UserDetails userDetails) {
        snackService.addComment(id, userDetails.getUsername(), content);
        return "redirect:/snacks/" + id;
    }

    @GetMapping("/liked")
    public String likedSnacks(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        List<Snack> likedSnacks = snackService.getLikedSnacksByUsername(userDetails.getUsername());
        model.addAttribute("likedSnacks", likedSnacks);
        return "snack/liked";
    }
}