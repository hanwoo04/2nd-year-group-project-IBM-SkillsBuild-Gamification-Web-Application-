package org.example.ibmskillsbuildapp.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@Controller
public class LoginController {

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.addValidators(new LoginValidator());
    }
}
