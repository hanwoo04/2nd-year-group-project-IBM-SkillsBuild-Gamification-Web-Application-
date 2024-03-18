package org.example.ibmskillsbuildapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;
import org.example.ibmskillsbuildapp.model.Course;
import org.example.ibmskillsbuildapp.model.LearningPath;
import org.example.ibmskillsbuildapp.model.LearningStatus;
import org.example.ibmskillsbuildapp.model.User;
import org.example.ibmskillsbuildapp.model.UserCourse;
import org.example.ibmskillsbuildapp.model.UserRoles;
import org.example.ibmskillsbuildapp.repo.CourseRepository;
import org.example.ibmskillsbuildapp.repo.LearningPathRepository;
import org.example.ibmskillsbuildapp.repo.UserCourseRepository;
import org.example.ibmskillsbuildapp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for managing {@link UserService} entities. This service provides methods for common
 * operations such as creating users, setting roles, friends, learning paths and courses and adds them to the repository
 * /database.
 */

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

    /**
    * Checks if the user repository is empty.
    *
    * @return `true` if the repository is empty, otherwise `false`.
    */
    public boolean isEmpty() {
        return userRepository.count() == 0;
    }

    /**
     * Creates sample users, roles, and relationships.
     */
    public void createUsers() {
        // Create user1 and user2
        // Create roles
        User user1 = new User();
        User user2 = new User();
        UserRoles role = new UserRoles();
        UserRoles role2 = new UserRoles();

        user1.setUserName("USER");
        user1.setPassword(passwordEncoder.encode("PASSWORD123"));
        user2.setUserName("ADMIN");
        user2.setPassword(passwordEncoder.encode("ADMIN123"));

        // Create friendship (USER friends with ADMIN)
        role.setRoleName("USER");
        user1.getUserRoles().add(role);
        user1.setScore(300);
        user1.setFriends(new ArrayList<>());
        role2.setRoleName("ADMIN");
        user2.getUserRoles().add(role2);
        user2.setScore(400);
        user2.setFriends(new ArrayList<>());

        // Save users
        user1 = userRepository.save(user1);
        user2 = userRepository.save(user2);
        user1.getFriends().add(user2);//USER friends with ADMIN but not other way around
        userRepository.save(user1);
        userRepository.save(user2);

        // Fetch learning paths
        Iterable<LearningPath> iterable = learningPathRepository.findAll();
        List<LearningPath> learningPaths = StreamSupport.stream(iterable.spliterator(), false)
            .toList();

        // Create user-course relationships
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
