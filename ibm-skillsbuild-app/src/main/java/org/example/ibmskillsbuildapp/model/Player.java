package org.example.ibmskillsbuildapp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Player {
    @Id @GeneratedValue
    private int id;
    private String name;
    private int score;

    public List<Player> getFriends() {
        return friends;
    }

    public void setFriends(List<Player> friends) {
        this.friends = friends;
    }

    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Player> friends;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
