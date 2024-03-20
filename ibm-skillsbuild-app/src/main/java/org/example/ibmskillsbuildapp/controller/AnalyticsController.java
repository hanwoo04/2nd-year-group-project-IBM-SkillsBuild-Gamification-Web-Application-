package org.example.ibmskillsbuildapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for handling requests related to analytics view.
 */
@Controller
public class AnalyticsController {

    /**
     * Handles GET requests to the /viewAnalytics endpoint.
     *
     * @param model the Model object to be used in the view
     * @return the name of the analytics view
     */
    @GetMapping("/viewAnalytics")
    public String viewAnalytics(Model model) {
        return "analytics";
    }
}
