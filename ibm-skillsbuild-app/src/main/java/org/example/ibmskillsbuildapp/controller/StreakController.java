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

    // Endpoint for updating the streak count for a user
    @PostMapping("/streaks/{userId}")
    public String updateStreak(@PathVariable Long userId) {
        // Call the streak service to update the streak count for the user
        streakService.updateStreak(userId);
        // Redirect to the streak page for the user after updating the streak count
        return "redirect:/streaks/" + userId;
    }

    // Endpoint for retrieving the streak count for a user
    @GetMapping("/streaks/{userId}")
    public String getUserStreak(@PathVariable Long userId, Model model) {
        // Retrieve the user streak information using the streak service
        UserStreak userStreak = streakService.getUserStreak(userId);
        // Add the user streak information to the model to be displayed in the view
        model.addAttribute("userStreak", userStreak);
        // Return the view for displaying the user streak information
        return "streak";
    }
}
