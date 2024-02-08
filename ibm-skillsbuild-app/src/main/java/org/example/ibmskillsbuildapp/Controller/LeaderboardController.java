package org.example.ibmskillsbuildapp.Controller;

import org.example.ibmskillsbuildapp.repo.LeaderboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LeaderboardController {
    @Autowired
    private LeaderboardRepository repo;
    @RequestMapping("/Leaderboard")
    public String showLeaderboard(Model model){
        model.addAttribute("players",repo.findAllByOrderByScoreDesc());
        return "Leaderboard";
    }
}
