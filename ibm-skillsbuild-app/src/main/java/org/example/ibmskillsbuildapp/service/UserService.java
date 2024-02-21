package org.example.ibmskillsbuildapp.service;

import java.util.ArrayList;
import java.util.stream.StreamSupport;
import org.example.ibmskillsbuildapp.model.*;
import org.example.ibmskillsbuildapp.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserCourseRepository userCourseRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LearningPathRepository learningPathRepository;

    public boolean isEmpty() {
        return userRepository.count() == 0;
    }

    public void createUsers() {
        User user1 = new User();
        User user2 = new User();
        UserRoles role = new UserRoles();
        UserRoles role2 = new UserRoles();

        user1.setUserName("USER");
        user1.setPassword(passwordEncoder.encode("PASSWORD123"));
        user2.setUserName("ADMIN");
        user2.setPassword(passwordEncoder.encode("ADMIN123"));

        role.setRoleName("USER");
        user1.getUserRoles().add(role);
        user1.setScore(300);
        user1.setFriends(new ArrayList<>());
        role2.setRoleName("ADMIN");
        user2.getUserRoles().add(role2);
        user2.setScore(400);
        user2.setFriends(new ArrayList<>());

        user1=userRepository.save(user1);
        user2=userRepository.save(user2);
        user1.getFriends().add(user2);//USER friends with ADMIN but not other way around


        Iterable<LearningPath> iterable = learningPathRepository.findAll();
        List<LearningPath> learningPaths = StreamSupport.stream(iterable.spliterator(), false)
            .toList();

        for (LearningPath learningPath : learningPaths) {
            List<Course> courses = courseRepository.findByLearningPath(learningPath);
            for (Course course : courses) {
                UserCourse userCourse1 = new UserCourse(user1, course,
                    LearningStatus.AVAILABLE);
                UserCourse userCourse2 = new UserCourse(user2, course,
                    LearningStatus.AVAILABLE);
                userCourseRepository.save(userCourse1);
                userCourseRepository.save(userCourse2);
            }
        }
    }
}
