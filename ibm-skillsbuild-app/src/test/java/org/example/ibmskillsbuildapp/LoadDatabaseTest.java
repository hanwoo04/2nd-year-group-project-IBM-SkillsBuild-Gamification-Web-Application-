package org.example.ibmskillsbuildapp;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.example.ibmskillsbuildapp.model.Course;
import org.example.ibmskillsbuildapp.model.User;
import org.example.ibmskillsbuildapp.model.UserCourse;
import org.example.ibmskillsbuildapp.repo.CourseRepository;
import org.example.ibmskillsbuildapp.repo.UserCourseRepository;
import org.example.ibmskillsbuildapp.repo.UserRepository;
import org.example.ibmskillsbuildapp.service.CourseService;
import org.example.ibmskillsbuildapp.service.LearningPathService;
import org.example.ibmskillsbuildapp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;

class LoadDatabaseTest {

    @Mock
    private UserService userService;

    @Mock
    private CourseService courseService;

    @Mock
    private LearningPathService learningPathService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private UserCourseRepository userCourseRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private LoadDatabase loadDatabase;

    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.openMocks(this);
        loadDatabase = new LoadDatabase(userService, courseService, learningPathService);

        Field userRepositoryField = LoadDatabase.class.getDeclaredField("userRepository");
        userRepositoryField.setAccessible(true);
        userRepositoryField.set(loadDatabase, userRepository);

        Field courseRepositoryField = LoadDatabase.class.getDeclaredField("courseRepository");
        courseRepositoryField.setAccessible(true);
        courseRepositoryField.set(loadDatabase, courseRepository);

        Field userCourseRepositoryField = LoadDatabase.class.getDeclaredField("userCourseRepository");
        userCourseRepositoryField.setAccessible(true);
        userCourseRepositoryField.set(loadDatabase, userCourseRepository);

        Field passwordEncoderField = LoadDatabase.class.getDeclaredField("passwordEncoder");
        passwordEncoderField.setAccessible(true);
        passwordEncoderField.set(loadDatabase, passwordEncoder);
    }

    @Test
    void testInitDatabase() throws Exception {
        when(learningPathService.isEmpty()).thenReturn(true);
        when(courseService.isEmpty()).thenReturn(true);
        when(userService.isEmpty()).thenReturn(true);

        loadDatabase.initDatabase().run();

        verify(learningPathService, times(1)).createLearningPaths();
        verify(courseService, times(1)).createCourses();
        verify(userService, times(1)).createUsers();
    }

    @Test
    void testCreateDemoData() throws Exception {
        List<Course> courses = Collections.singletonList(new Course());

        when(courseRepository.findAll()).thenReturn(courses);

        Method method = LoadDatabase.class.getDeclaredMethod("createDemoData");
        method.setAccessible(true);
        method.invoke(loadDatabase);

        verify(userRepository, times(25)).save(any(User.class));
        verify(userCourseRepository, times(25)).save(any(UserCourse.class));
    }
}