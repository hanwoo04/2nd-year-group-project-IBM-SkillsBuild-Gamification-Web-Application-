package org.example.ibmskillsbuildapp.model;

import org.example.ibmskillsbuildapp.repo.UserRepository;
import org.example.ibmskillsbuildapp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserTest {

    @InjectMocks
    private UserService userService; // Assuming you have a UserService

    @Mock
    private UserRepository userRepository; // Assuming you have a UserRepository

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateUser() {
        // Arrange
        User user = new User();
        user.setUserName("john_doe");
        user.setPassword("secret");

        // Mock UserRepository behavior
        when(userRepository.save(user)).thenReturn(user);

        // Assert
        assertEquals("john_doe", user.getUserName());
        assertEquals("secret", user.getPassword());
    }

    @Test
    void testSetUserRoles() {
        // Arrange
        User user = new User();
        List<UserRoles> roles = new ArrayList<>();
        UserRoles role1 = new UserRoles();
        UserRoles role2 = new UserRoles();
        role1.setRoleName("TestAdmin");
        role2.setRoleName("TestUser");
        roles.add(role1);
        roles.add(role2);

        // Act
        user.setUserRoles(roles);

        // Assert
        assertEquals(2, user.getUserRoles().size());
        assertTrue(user.getUserRoles().contains(role1));
        assertTrue(user.getUserRoles().contains(role2));
    }

    @Test
    void testSetUserCourses() {

        // Arrange
        User user = new User();
        List<UserCourse> courses = new ArrayList<>();

        UserCourse course1 = new UserCourse();
        Course course = new Course();
        course.setCourseName("AI");
        UserCourse course2 = new UserCourse(user,course, LearningStatus.AVAILABLE);
        courses.add(course2);

        // Act
        user.setUserCourses(courses);

        // Assert
        assertEquals(2, user.getUserCourses().size());
        assertTrue(user.getUserCourses().contains(course1));
        assertTrue(user.getUserCourses().contains(course2));
    }

    @Test
    void testSetUserLearningPaths() {
        // Arrange
        User user = new User();
        List<UserLearningPath> learningPaths = new ArrayList<>();
        LearningPath learningPath = new LearningPath("Software Engineering");
        UserLearningPath path1 = new UserLearningPath(user,learningPath,LearningStatus.AVAILABLE);
        learningPaths.add(path1);

        // Act
        user.setUserLearningPaths(learningPaths);

        // Assert
        assertEquals(2, user.getUserLearningPaths().size());
        assertTrue(user.getUserLearningPaths().contains(path1));

    }

    @Test
    void testAddFriend() {
        // Arrange
        User user1 = new User();
        user1.setUserName("alice");

        User user2 = new User();
        user2.setUserName("bob");

        User user3 = new User();
        user3.setUserName("yash");

        // Mock UserRepository behavior
        when(userRepository.findByUserName("alice")).thenReturn(user1);
        when(userRepository.findByUserName("bob")).thenReturn(user2);

        List<User> friends = new ArrayList<>();
        friends.add(user1);
        friends.add(user2);
        // Act
        user3.setFriends(friends);

        // Assert
        assertTrue(user3.getFriends().contains(user1));
        assertTrue(user3.getFriends().contains(user2));
    }

}

