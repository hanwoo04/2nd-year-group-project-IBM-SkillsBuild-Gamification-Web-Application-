package org.example.ibmskillsbuildapp;

import org.example.ibmskillsbuildapp.model.Player;
import org.example.ibmskillsbuildapp.repo.LeaderboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class IbmSkillsbuildAppApplication implements CommandLineRunner {
    @Autowired
    private LeaderboardRepository repo;
    public static void main(String[] args) {
        SpringApplication.run(IbmSkillsbuildAppApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Player p1 = new Player();
        p1.setName("Jack");
        p1.setScore(200);
        p1 = repo.save(p1);

        Player p2 = new Player();
        p2.setName("Wil");
        p2.setScore(1000);
        p2 = repo.save(p2);

        Player p3 = new Player();
        p3.setName("Joe");
        p3.setScore(500);

        p3.setFriends(new ArrayList<>());
        p1.setFriends(new ArrayList<>());
        p2.setFriends(new ArrayList<>());

        p3.getFriends().add(p2);
        p3.getFriends().add(p1);

        p1.getFriends().add(p3);
        p1 = repo.save(p1);
        p2.getFriends().add(p3);
        p2 = repo.save(p2);






    }
}
