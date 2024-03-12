package org.example.ibmskillsbuildapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminDashboardController {
    @GetMapping("/viewAnalytics")
    public String adminDashboard() {
        return "analytics";
    }
}
