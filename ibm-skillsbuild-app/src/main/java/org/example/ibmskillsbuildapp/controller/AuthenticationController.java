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

/**
 * Controller for handling requests related to the login/register page.
 */
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

    /**
     * Handles GET requests to the /success-login endpoint. Retrieves user by username and if assigned a role.
     * it will redirect them to the dashboard.
     * @return the name of the view to be rendered, in this case "viewDashboard"
     */

    @GetMapping(value = "/success-login")
    public String successLogin(Principal principal) {
        User user = repo.findByUserName(principal.getName());
        if (user.getUserRoles().isEmpty()) {
            return "denied";
        }
        return "redirect:/viewDashboard";
    }

    /**
     * Handles GET requests to the /login-form endpoint. Retrieves user request to log-in.
     * @return the name of the view to be rendered, in this case "login"
     */

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

    /**
     * Handles GET requests to the /register endpoint. Retrieves user request to signup to the website.
     * it will redirect them to register form.
     * @return the name of the view to be rendered, in this case "register"
     */
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    /**
     * Handles GET requests to the /register endpoint. Retrieves user username and password, assigns it a role, encrypts
     * password sets their learning path and sets their courses. This then creates the new user.
     * it will redirect them to login.
     * @return the name of the view to be rendered, in this case "register"/"login"
     */
    @PostMapping("/register")
    public String register(@ModelAttribute User user, BindingResult result) {

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
