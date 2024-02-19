package org.example.ibmskillsbuildapp.controller;
import org.example.ibmskillsbuildapp.model.User;
import org.example.ibmskillsbuildapp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class AuthenticationController {

    @Autowired
    private UserRepository repo;

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
}
