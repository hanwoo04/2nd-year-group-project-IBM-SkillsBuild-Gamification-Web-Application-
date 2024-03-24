package org.example.ibmskillsbuildapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.security.Principal;
import java.util.Collections;
import org.example.ibmskillsbuildapp.model.User;
import org.example.ibmskillsbuildapp.model.UserRoles;
import org.example.ibmskillsbuildapp.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

class AuthenticationControllerTest {

    @InjectMocks
    private AuthenticationController authenticationController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSuccessLogin() {
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("USER");
        User user = new User();
        UserRoles role1 = new UserRoles();
        role1.setRoleName("USER");
        user.setUserRoles(Collections.singletonList(role1));
        when(userRepository.findByUserName("USER")).thenReturn(user);

        String result = authenticationController.successLogin(principal);

        assertEquals("redirect:/viewDashboard", result);
    }

    @Test
    void testLoginForm() {

        Model model = mock(Model.class);

        String result = authenticationController.loginForm(model);

        assertEquals("login", result);
        verify(model).addAttribute(eq("service"), eq("what"));
    }

    @Test
    void testErrorLogin() {
        String result = authenticationController.errorLogin();

        assertEquals("login", result);
    }

    @Test
    void testAccessDenied() {
        String result = authenticationController.accessDenied();

        assertEquals("accessDenied", result);
    }

    @Test
    void testRegisterForm() {
        Model model = mock(Model.class);

        String result = authenticationController.registerForm(model);

        assertEquals("register", result);
        verify(model).addAttribute(eq("user"), any(User.class));
    }
}
