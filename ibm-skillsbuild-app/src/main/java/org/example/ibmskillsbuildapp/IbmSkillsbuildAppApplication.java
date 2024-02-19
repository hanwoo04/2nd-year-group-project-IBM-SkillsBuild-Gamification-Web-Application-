package org.example.ibmskillsbuildapp;

import org.example.ibmskillsbuildapp.model.User;
import org.example.ibmskillsbuildapp.model.UserRoles;
import org.example.ibmskillsbuildapp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class IbmSkillsBuildAppApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(IbmSkillsBuildAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
