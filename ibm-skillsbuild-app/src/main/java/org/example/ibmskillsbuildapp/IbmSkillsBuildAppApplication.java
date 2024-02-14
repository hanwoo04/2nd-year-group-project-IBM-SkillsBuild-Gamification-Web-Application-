package org.example.ibmskillsbuildapp;

import org.example.ibmskillsbuildapp.Model.User;
import org.example.ibmskillsbuildapp.Model.UserRoles;
import org.example.ibmskillsbuildapp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class IbmSkillsBuildAppApplication implements CommandLineRunner {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(IbmSkillsBuildAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        User user2 = new User();
        UserRoles role = new UserRoles();
        UserRoles role2 = new UserRoles();
        user1.setUserName("yash");
        user1.setPassword(passwordEncoder.encode("password123"));

        user2.setUserName("ADMIN");
        user2.setPassword(passwordEncoder.encode("ADMIN123"));

        role.setRoleName("GUEST");
        user1.getRole().add(role);
        role2.setRoleName("ADMIN");
        user2.getRole().add(role2);

        repo.save(user1);
        repo.save(user2);
    }
}
