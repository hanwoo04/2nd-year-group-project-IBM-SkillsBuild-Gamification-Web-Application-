package org.example.ibmskillsbuildapp.controller;

import java.util.List;
import org.example.ibmskillsbuildapp.model.LearningPath;
import org.example.ibmskillsbuildapp.service.LearningPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private LearningPathService learningPathService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<LearningPath> learningPaths = learningPathService.getAllLearningPaths();
        model.addAttribute("learningPaths", learningPaths);
        return "dashboard";
    }
}
