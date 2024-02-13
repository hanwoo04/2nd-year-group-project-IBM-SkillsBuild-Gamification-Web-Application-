package org.example.ibmskillsbuildapp.controller;

import org.example.ibmskillsbuildapp.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TimeController {
    private long startTime;
    private long pausedTime = 0;
    private boolean isPaused = false;

    @RequestMapping("/record-start")
    public String recordStart(User user, Model model) { //User id could be interpreted by e.g. /record-start?userId=456
        if (!isPaused) {
            startTime = System.currentTimeMillis();
            model.addAttribute("notification", "User " + user.getId() + "Current course started at: " + startTime);
        } else {
            model.addAttribute("notification", "User " + user.getId() + "Current course is paused. Resume to continue the course.");
        }
        return "record-start";
    }

    @RequestMapping("/pause")
    public String pause(User user, Model model) {
        if (!isPaused) {
            pausedTime = System.currentTimeMillis();
            isPaused = true;
            model.addAttribute("notification", "User " + user.getId() + "Current course paused at: " + pausedTime);
        } else {
            model.addAttribute("notification", "User " + user.getId() + "Current course is already paused.");
        }
        return "pause";
    }

    @RequestMapping("/resume")
    public String resume(User user, Model model) {
        if (isPaused) {
            long resumeTime = System.currentTimeMillis();
            long pausedDuration = resumeTime - pausedTime;
            startTime += pausedDuration;
            pausedTime = 0;
            isPaused = false;
            model.addAttribute("notification", "User " + user.getId() + "Current course resumed at: " + resumeTime);
        } else {
            model.addAttribute("notification", "User " + user.getId() + "Current course is not paused.");
        }
        return "resume";
    }
}
