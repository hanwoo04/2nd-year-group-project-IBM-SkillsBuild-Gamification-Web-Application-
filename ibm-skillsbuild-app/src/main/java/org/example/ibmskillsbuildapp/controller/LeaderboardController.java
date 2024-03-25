package org.example.ibmskillsbuildapp.controller;

import jakarta.servlet.http.HttpSession;
import org.example.ibmskillsbuildapp.model.Avatar;
import org.example.ibmskillsbuildapp.model.User;
import org.example.ibmskillsbuildapp.repo.UserRepository;
import org.example.ibmskillsbuildapp.service.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class LeaderboardController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private AvatarService avatarService;

    @GetMapping("/viewLeaderboard")
    public String showLeaderboard(Model model, HttpSession session) {
        // Retrieve the latest avatar data or set default values if no avatars are found
        List<Avatar> avatars = avatarService.getAllAvatars();
        Avatar latestAvatar = avatars.isEmpty() ? null : avatars.get(avatars.size() - 1);

        // Retrieve the currently logged-in user
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        User user = repo.findByUserName(username);

        // Pass the current user and avatar URL to the model
        model.addAttribute("currentUser", user);

        // Initialize avatar object for the current user if not already initialized
        if (user.getAvatar() == null) {
            user.setAvatar(new Avatar()); // Create a new Avatar object
        }

        // Set the avatar data URL for the current user
        user.getAvatar().setAvatarDataURL(latestAvatar != null ? latestAvatar.getAvatarDataURL() : "/img/Null_Profile_Image.png");
        model.addAttribute("currentUserAvatarURL", user.getAvatar().getAvatarDataURL());

        // Separate the current user from other players in the global leaderboard
        List<User> allPlayers = repo.findAllByOrderByScoreDesc();

        // Set default avatar URL for players who don't have an avatar
        for (User player : allPlayers) {
            if (player.getAvatar() == null) {
                player.setAvatar(new Avatar()); // Create a new Avatar object
                player.getAvatar().setAvatarDataURL("/img/Null_Profile_Image.png");
            }
        }

        // Sort the allPlayers list based on their scores
        Collections.sort(allPlayers, Comparator.comparingInt(User::getScore).reversed());

        // Pass the allPlayers list to the model
        model.addAttribute("allPlayers", allPlayers);

        // Sort the friends list based on their scores
        List<User> friends = new ArrayList<>(user.getFriends());
        if (!friends.contains(user)) {
            friends.add(user); // Add current user to friends list if not already present
        }
        Collections.sort(friends, Comparator.comparingInt(User::getScore).reversed());
        model.addAttribute("friends", friends);

        return "leaderboard";
    }
}
