package org.example.ibmskillsbuildapp.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<UserRoles> userRoles = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserCourse> userCourses;

    @OneToMany(mappedBy = "user")
    private List<UserLearningPath> userLearningPaths;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRoles> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRoles> userRoles) {
        this.userRoles = userRoles;
    }

    public List<UserCourse> getUserCourses() {
        return userCourses;
    }

    public void setUserCourses(List<UserCourse> userCourses) {
        this.userCourses = userCourses;
    }

    public List<UserLearningPath> getUserLearningPaths() {
        return userLearningPaths;
    }

    public void setUserLearningPaths(
        List<UserLearningPath> userLearningPaths) {
        this.userLearningPaths = userLearningPaths;
    }
}
