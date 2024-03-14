package org.example.ibmskillsbuildapp.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.example.ibmskillsbuildapp.model.Comment;
import org.example.ibmskillsbuildapp.model.Rating;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/comment-rating")
public class CommentRatingController {

    @GetMapping
    public String showCommentRatingForm(Model model) {
        model.addAttribute("newComment", new Comment());
        model.addAttribute("newRating", new Rating());
        return "comment-rating";
    }

    @PostMapping("/comment")
    public String addComment(@ModelAttribute Comment newComment) {
        return "redirect:/comment-rating";
    }

    @PostMapping("/rating")
    public String addRating(@ModelAttribute Rating newRating, BindingResult result, Model model) {
        if (!newRating.isValid()) {
            model.addAttribute("ratingError", "Rating value must be between 1 and 5.");
            return "comment-rating";
        }

        if (result.hasErrors()) {
            return "comment-rating";
        }

        return "redirect:/comment-rating";
    }
}