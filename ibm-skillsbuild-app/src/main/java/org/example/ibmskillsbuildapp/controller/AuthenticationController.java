package org.example.ibmskillsbuildapp.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.StreamSupport;
import org.example.ibmskillsbuildapp.model.Course;
import org.example.ibmskillsbuildapp.model.LearningPath;
import org.example.ibmskillsbuildapp.model.LearningStatus;
import org.example.ibmskillsbuildapp.model.User;
import org.example.ibmskillsbuildapp.model.UserCourse;
import org.example.ibmskillsbuildapp.model.UserRoles;
import org.example.ibmskillsbuildapp.repo.CourseRepository;
import org.example.ibmskillsbuildapp.repo.LearningPathRepository;
import org.example.ibmskillsbuildapp.repo.UserCourseRepository;
import org.example.ibmskillsbuildapp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthenticationController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserCourseRepository userCourseRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LearningPathRepository learningPathRepository;

    @GetMapping(value = "/success-login")
    public String successLogin(Principal principal) {
        User user = repo.findByUserName(principal.getName());
        if (user.getUserRoles().isEmpty()) {
            return "denied";
        }
        return "redirect:/viewDashboard";
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
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, BindingResult result, Model model) {

        if (repo.findByUserName(user.getUserName()) != null) {
            result.rejectValue("userName", "error.user", "Username is already taken");
            return "register";
        }

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
