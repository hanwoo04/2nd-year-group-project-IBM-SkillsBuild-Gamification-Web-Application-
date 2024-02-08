package org.example.ibmskillsbuildapp;

import org.example.ibmskillsbuildapp.model.Player;
import org.example.ibmskillsbuildapp.repo.LeaderboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IbmSkillsbuildAppApplication implements CommandLineRunner {
    @Autowired
    private LeaderboardRepository repo;
    public static void main(String[] args) {
        SpringApplication.run(IbmSkillsbuildAppApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Player player = new Player();
        player.setName("Joe");
        player.setScore(500);
        player = repo.save(player);

        Player player2 = new Player();
        player2.setName("Jack");
        player2.setScore(200);
        player2 = repo.save(player2);

        Player player3 = new Player();
        player3.setName("Wil");
        player3.setScore(1000);
        player3 = repo.save(player3);



    }
}
