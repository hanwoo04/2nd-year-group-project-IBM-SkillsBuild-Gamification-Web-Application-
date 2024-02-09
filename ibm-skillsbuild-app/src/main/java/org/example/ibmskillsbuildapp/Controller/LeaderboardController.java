package org.example.ibmskillsbuildapp.Controller;

import org.example.ibmskillsbuildapp.model.Player;
import org.example.ibmskillsbuildapp.repo.LeaderboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LeaderboardController {
    @Autowired
    private LeaderboardRepository repo;

    @RequestMapping("/Leaderboard/{userId}")
    public String showLeaderboard(Model model, @PathVariable int userId) {
        Player user = repo.findById(userId).get(); //Example  user
        List<Integer> friendsListId = new ArrayList<>();
        friendsListId.add(user.getId());
        for (Player f : user.getFriends()){
            friendsListId.add(f.getId());
        }
        //Loop provides a list of friend ids which we can use to sort the order via CrudRepository
        model.addAttribute("friends", repo.findByIdInOrderByScoreDesc(friendsListId));//Friends Only
        model.addAttribute("players", repo.findAllByOrderByScoreDesc());//Global Leaderboard
        return "Leaderboard";
    }
}


