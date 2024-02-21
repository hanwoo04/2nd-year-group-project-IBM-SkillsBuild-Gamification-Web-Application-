package org.example.ibmskillsbuildapp.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.example.ibmskillsbuildapp.model.Course;
import org.example.ibmskillsbuildapp.model.LearningStatus;
import org.example.ibmskillsbuildapp.model.User;
import org.example.ibmskillsbuildapp.model.UserCourse;
import org.example.ibmskillsbuildapp.repo.UserCourseRepository;
import org.example.ibmskillsbuildapp.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserCourseServiceTest {

    @InjectMocks
    private UserCourseService userCourseService;

    @Mock
    private UserCourseRepository userCourseRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEnroll() {
        User user = new User();
        Course course = new Course();
        UserCourse userCourse = new UserCourse();
        userCourse.setUser(user);
        userCourse.setCourse(course);
        userCourse.setStatus(LearningStatus.STARTED);

        when(userCourseRepository.findByUserAndCourse(user, course)).thenReturn(userCourse);

        userCourseService.enroll(user, course);

        verify(userCourseRepository, times(1)).save(userCourse);
    }

    @Test
    void testComplete() {
        // Arrange
        User user = new User();
        user.setScore(0);
        Course course = new Course();
        UserCourse userCourse = new UserCourse();
        userCourse.setUser(user);
        userCourse.setCourse(course);
        userCourse.setStatus(LearningStatus.COMPLETED);

        when(userCourseRepository.findByUserAndCourse(user, course)).thenReturn(userCourse);

        // Act
        userCourseService.complete(user, course);

        // Assert
        verify(userCourseRepository, times(1)).save(userCourse);
        verify(userRepository, times(1)).save(user);
    }
}