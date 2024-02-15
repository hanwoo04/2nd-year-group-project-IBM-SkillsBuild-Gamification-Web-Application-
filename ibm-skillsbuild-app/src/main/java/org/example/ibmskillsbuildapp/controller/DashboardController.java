package org.example.ibmskillsbuildapp.controller;

import java.util.Comparator;
import java.util.List;
import org.example.ibmskillsbuildapp.model.CourseView;
import org.example.ibmskillsbuildapp.service.LearningPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for handling requests related to the dashboard.
 */
@Controller
public class DashboardController {

    @Autowired
    private LearningPathService learningPathService;

    /**
     * Handles GET requests to the /dashboard endpoint. Retrieves all CourseView objects, sorts them
     * by their status, and adds the sorted list to the model.
     *
     * @param model the Model object to which the sorted list of CourseView objects is added
     * @return the name of the view to be rendered, in this case "dashboard"
     */
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<CourseView> courseViews = learningPathService.getAllCourseViews();
        courseViews.sort(Comparator.comparing(CourseView::getStatus));
        model.addAttribute("courseViews", courseViews);
        return "dashboard";
    }
}