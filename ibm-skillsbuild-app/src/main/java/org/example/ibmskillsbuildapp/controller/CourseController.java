package org.example.ibmskillsbuildapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseController {
    private long startTime;
    private long pausedTime = 0;
    private boolean isPaused = false;

    @GetMapping("/record-start")
    public String recordStart(Model model) {
        if (!isPaused) {
            startTime = System.currentTimeMillis();
            model.addAttribute("message", "Course started at: " + startTime);
        } else {
            model.addAttribute("message", "Course is paused. Resume to start again.");
        }
        return "record-start";
    }

}
