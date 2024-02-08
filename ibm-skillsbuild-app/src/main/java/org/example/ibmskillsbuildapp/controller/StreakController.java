package org.example.ibmskillsbuildapp.controller;

import org.example.ibmskillsbuildapp.model.UserStreak;
import org.example.ibmskillsbuildapp.service.StreakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StreakController {

    @Autowired
    private StreakService streakService;

    @PostMapping("/streaks/{userId}")
    public String updateStreak(@PathVariable Long userId) {
        streakService.updateStreak(userId);
        return "redirect:/streaks/" + userId;
    }

    @GetMapping("/streaks/{userId}")
    public String getUserStreak(@PathVariable Long userId, Model model) {
        UserStreak userStreak = streakService.getUserStreak(userId);
        model.addAttribute("userStreak", userStreak);
        return "streak"; 
    }
}
