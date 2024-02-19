package org.example.ibmskillsbuildapp.controller;
import org.example.ibmskillsbuildapp.model.*;
import org.example.ibmskillsbuildapp.repo.CourseRepository;
import org.example.ibmskillsbuildapp.repo.LearningPathRepository;
import org.example.ibmskillsbuildapp.repo.UserCourseRepository;
import org.example.ibmskillsbuildapp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.stream.StreamSupport;

@Controller
public class AuthenticationController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LearningPathRepository learningPathRepository;

    @Autowired
    private UserCourseRepository userCourseRepository;


    @GetMapping(value = "/success-login")
    public String successLogin(Principal principal) {
        User user = repo.findByUserName(principal.getName());
        if (user.getUserRoles().isEmpty()) {
            return "denied";
        }
        return "redirect:/dashboard";
    }

    @GetMapping(value = "/login-form")
    public String loginForm(Model model) {
        model.addAttribute("service", "what");
        return "login";
    }

    @RequestMapping(value = "/error-login")
    public String errorLogin() {
        return "login";
    }

    @RequestMapping(value = "/access-denied")
    public String accessDenied() {
        return "denied";
    }

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        UserRoles role = new UserRoles();
        role.setRoleName("default");
        user.getUserRoles().add(role);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repo.save(user);

        Iterable<LearningPath> iterable = learningPathRepository.findAll();
        List<LearningPath> learningPaths = StreamSupport.stream(iterable.spliterator(), false)
                .toList();

        for (LearningPath learningPath : learningPaths) {
            List<Course> courses = courseRepository.findByLearningPath(learningPath);
            for (Course course : courses) {
                UserCourse userCourse1 = new UserCourse(user, course,
                        LearningStatus.AVAILABLE);
                userCourseRepository.save(userCourse1);

            }
        }

        return "redirect:/login";
    }


}
