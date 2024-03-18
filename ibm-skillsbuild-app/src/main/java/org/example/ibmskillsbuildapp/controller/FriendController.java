package org.example.ibmskillsbuildapp.controller;

import org.example.ibmskillsbuildapp.model.User;
import org.example.ibmskillsbuildapp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FriendController {
    @Autowired
    private UserRepository repo;

    @RequestMapping("/viewFriends")
    public String showFriends(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        User user = repo.findByUserName(username);//Gets user
        model.addAttribute("friends",user.getFriends());
        model.addAttribute("followers", returnFollowers(user));//Return anyone who followers user
        return "friendsPage";

    }
    @RequestMapping("viewFriends/search")
    public String search(Model model,String usernameSearch){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        User user = repo.findByUserName(username);//Gets user

        model.addAttribute("user",user);
        model.addAttribute("friends",user.getFriends());
        model.addAttribute("followers", returnFollowers(user));
        List<User> users = repo.findByUserNameStartingWith(usernameSearch);
        users.remove(user);
        users.removeAll(user.getFriends());//Filters out user and friends from search
        model.addAttribute("searchResults",users);
        model.addAttribute("search", usernameSearch);//This is to save the search so when add friend is pressed the list is still there
        return "friendsPage";
    }

    @RequestMapping("viewFriends/addFriend")
    public String addFriend(Model model,@RequestParam("userId") Long userId, @RequestParam("friendId") Long friendId,@RequestParam("search") String search){
        User user = repo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId));
        User friend = repo.findById(friendId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + friendId));
        user.getFriends().add(friend);
        user= repo.save(user);
        return "redirect:/viewFriends/search?usernameSearch="+search;
    }
    public List<User> returnFollowers(User user) {
        List<User> followers = new ArrayList<>();
        for (User u : repo.findAll()) {
            if (u.getFriends().contains(user)) {
                followers.add(u);
            }
        }
        return followers;
    }
}
