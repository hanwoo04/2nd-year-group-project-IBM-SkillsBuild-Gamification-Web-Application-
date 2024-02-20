package org.example.ibmskillsbuildapp.controller;

import org.example.ibmskillsbuildapp.model.User;
import org.example.ibmskillsbuildapp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LeaderboardController {

    @Autowired
    private UserRepository repo;

    @GetMapping("/viewLeaderboard/{userId}")
    public String showLeaderboard(Model model, @PathVariable long userId) {
        User user = repo.findById(userId).get(); //Gets user
        List<Long> friendsListId = new ArrayList<>();
        friendsListId.add(user.getId());
        for (User f : user.getFriends()) {
            friendsListId.add(f.getId());
        }
        //Loop provides a list of friend ids which we can use to sort the order via CrudRepository
        model.addAttribute("friends", repo.findByIdInOrderByScoreDesc(friendsListId));//Friends Only
        model.addAttribute("players", repo.findAllByOrderByScoreDesc());//Global Leaderboard
        model.addAttribute("user", user);
        return "leaderboard";
    }
}
