package org.example.ibmskillsbuildapp.controller;

import jakarta.servlet.http.HttpSession;
import org.example.ibmskillsbuildapp.model.Avatar;
import org.example.ibmskillsbuildapp.service.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public String avatarPage(Model model) {
        // Retrieve the latest avatar data or set default values if no avatars are found
        List<Avatar> avatars = avatarService.getAllAvatars();
        Avatar latestAvatar = avatars.isEmpty() ? null : avatars.get(avatars.size() - 1);

        // Pass the latest avatar data to the avatar customization page
        if (latestAvatar != null) {
            model.addAttribute("skinColor", latestAvatar.getSkinColor());
            model.addAttribute("eyeColor", latestAvatar.getEyeColor());
            model.addAttribute("hairType", latestAvatar.getHairType());
            model.addAttribute("hairColor", latestAvatar.getHairColor());
            model.addAttribute("noseSize", latestAvatar.getNoseSize());
            model.addAttribute("mouthSize", latestAvatar.getMouthSize());
            model.addAttribute("glasses", latestAvatar.isGlasses());
        } else {
            // Set default values if no avatars are found
            model.addAttribute("skinColor", "#ffddb3"); // Default to light skin color
            model.addAttribute("eyeColor", "#66533d"); // Default to brown eye color
            model.addAttribute("hairType", "curly"); // Default to curly hair type
            model.addAttribute("hairColor", "black"); // Default to black hair color
            model.addAttribute("noseSize", "medium"); // Default to medium nose size
            model.addAttribute("mouthSize", "medium"); // Default to medium mouth size
            model.addAttribute("glasses", false); // Default to no glasses
        }

        return "avatar";
    }

    @PostMapping("/saveAvatar")
    public ResponseEntity<String> saveAvatar(@RequestParam("avatar") MultipartFile avatarFile,
                                             @RequestParam("skinColor") String skinColor,
                                             @RequestParam("eyeColor") String eyeColor,
                                             @RequestParam("hairType") String hairType,
                                             @RequestParam("hairColor") String hairColor,
                                             @RequestParam("noseSize") String noseSize,
                                             @RequestParam("mouthSize") String mouthSize,
                                             @RequestParam(value = "glasses", required = false, defaultValue = "false") boolean glasses) {
        try {
            if (avatarFile.isEmpty() || skinColor.isEmpty() || eyeColor.isEmpty() || hairType.isEmpty() ||
                    hairColor.isEmpty() || noseSize.isEmpty() || mouthSize.isEmpty()) {
                return ResponseEntity.badRequest().body("Missing avatar data in the request");
            }

            // Process the avatarFile (MultipartFile)
            byte[] avatarData = avatarFile.getBytes();
            String avatarDataURL = "data:" + avatarFile.getContentType() + ";base64," + Base64.getEncoder().encodeToString(avatarData);

            // Save the avatar to the database
            Avatar avatar = new Avatar();
            avatar.setAvatarDataURL(avatarDataURL);
            avatar.setSkinColor(skinColor);
            avatar.setEyeColor(eyeColor);
            avatar.setHairType(hairType);
            avatar.setHairColor(hairColor);
            avatar.setNoseSize(noseSize);
            avatar.setMouthSize(mouthSize);
            avatar.setGlasses(glasses);

            avatarService.saveAvatar(avatar);

            return ResponseEntity.ok("Avatar saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to save avatar. Please try again later.");
        }
    }

    @RequestMapping("/profile")
    public String profilePage(Model model, HttpSession session) {
        List<Avatar> avatars = avatarService.getAllAvatars();
        if (!avatars.isEmpty()) {
            // Pass the latest avatar data to the profile page
            Avatar latestAvatar = avatars.get(avatars.size() - 1);
            session.setAttribute("avatarDataURL", latestAvatar.getAvatarDataURL());
        } else {
            // No avatars found, set default data or handle as needed
            session.setAttribute("avatarDataURL", "/img/Null_Profile_Image.png");
        }
        model.addAttribute("avatars", avatars); // Pass all avatars to the profile page

        // Also add the avatarDataURL to the model for use in the nav bar
        model.addAttribute("avatarDataURL", session.getAttribute("avatarDataURL"));

        return "profile"; // Assuming your profile page JSP file is named profile.jsp
    }



}
