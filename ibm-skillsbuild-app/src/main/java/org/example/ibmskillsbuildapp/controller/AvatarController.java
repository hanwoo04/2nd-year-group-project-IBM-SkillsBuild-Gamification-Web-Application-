package com.example.avatarcreation.controller;

import com.example.avatarcreation.model.Avatar;
import com.example.avatarcreation.service.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

@Controller
public class AvatarController {

    private final AvatarService avatarService;

    @Autowired
    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @RequestMapping("/avatar")
    public String avatarPage() {
        return "avatar"; // Assuming your JSP file is named avatar.jsp
    }

    @PostMapping("/saveAvatar")
    @ResponseBody
    public ResponseEntity<String> saveAvatar(@RequestParam("avatar") MultipartFile avatarFile,
                                             @RequestParam("skinColor") String skinColor,
                                             @RequestParam("eyeColor") String eyeColor,
                                             @RequestParam("hairType") String hairType,
                                             @RequestParam("hairColor") String hairColor,
                                             @RequestParam("noseSize") String noseSize,
                                             @RequestParam("mouthSize") String mouthSize,
                                             @RequestParam(value = "glasses", required = false, defaultValue = "false") boolean glasses) {
        try {
            if (avatarFile == null || skinColor == null || eyeColor == null || hairType == null || hairColor == null || noseSize == null || mouthSize == null) {
                return ResponseEntity.badRequest().body("Missing avatar data in the request");
            }

            // Process the avatarFile (MultipartFile) here
            byte[] avatarData = avatarFile.getBytes(); // Get the byte array from MultipartFile

            // Save avatarData to the database using avatarService.saveAvatar
            Avatar avatar = new Avatar();
            avatar.setAvatarDataURL(Base64.getEncoder().encodeToString(avatarData));
            avatar.setSkinColor(skinColor);
            avatar.setEyeColor(eyeColor);
            avatar.setHairType(hairType); // Set the hair type
            avatar.setHairColor(hairColor); // Set the hair color
            avatar.setNoseSize(noseSize); // Set the nose size
            avatar.setMouthSize(mouthSize); // Set the mouth size
            avatar.setGlasses(glasses); // Set the glasses

            avatarService.saveAvatar(avatar);

            return ResponseEntity.ok("Avatar saved successfully!");
        } catch (Exception e) {
            String errorMessage = "Failed to save avatar. Please try again later.";
            // Log the error for debugging purposes
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorMessage);
        }
    }


    @RequestMapping("/profile")
    public String profilePage(Model model) {
        // Get the most recent avatar from the database
        Avatar mostRecentAvatar = avatarService.getMostRecentAvatar();

        if (mostRecentAvatar != null) {
            // Pass the data URL of the most recent avatar to the view
            model.addAttribute("avatarDataURL", mostRecentAvatar.getAvatarDataURL());
        } else {
            // If no avatars are found, set a default avatar image URL or handle it as needed
            model.addAttribute("avatarDataURL", "path/to/default/avatar/image.jpg");
        }

        // Pass the list of all avatars to the profile page (optional, depending on your requirements)
        List<Avatar> avatars = avatarService.getAllAvatars();
        model.addAttribute("avatars", avatars);

        return "profile"; // Assuming your profile page JSP file is named profile.jsp
    }

}
