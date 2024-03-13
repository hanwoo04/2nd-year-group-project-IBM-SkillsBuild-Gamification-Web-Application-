package com.example.avatarcreation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String avatarDataURL;

    @Column(columnDefinition = "TEXT")
    private String skinColor;

    @Column(columnDefinition = "TEXT")
    private String eyeColor;

    // Constructors
    public Avatar() {
    }

    public Avatar(String avatarDataURL, String skinColor, String eyeColor) {
        this.avatarDataURL = avatarDataURL;
        this.skinColor = skinColor;
        this.eyeColor = eyeColor;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatarDataURL() {
        return avatarDataURL;
    }

    public void setAvatarDataURL(String avatarDataURL) {
        this.avatarDataURL = avatarDataURL;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    // toString method for debugging/logging
    @Override
    public String toString() {
        return "Avatar{" +
                "id=" + id +
                ", avatarDataURL='" + avatarDataURL + '\'' +
                ", skinColor='" + skinColor + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                '}';
    }
}
