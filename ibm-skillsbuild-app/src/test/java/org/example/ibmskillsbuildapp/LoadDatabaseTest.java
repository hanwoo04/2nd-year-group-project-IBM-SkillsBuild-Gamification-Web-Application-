package org.example.ibmskillsbuildapp;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.example.ibmskillsbuildapp.service.CourseService;
import org.example.ibmskillsbuildapp.service.LearningPathService;
import org.example.ibmskillsbuildapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.CommandLineRunner;


class LoadDatabaseTest {

    @InjectMocks
    private LoadDatabase loadDatabase;

    @Mock
    private UserService userService;

    @Mock
    private CourseService courseService;

    @Mock
    private LearningPathService learningPathService;

    @Test
    void testInitDatabase() throws Exception {
        MockitoAnnotations.openMocks(this);

        when(userService.isEmpty()).thenReturn(true);
        when(courseService.isEmpty()).thenReturn(true);
        when(learningPathService.isEmpty()).thenReturn(true);

        CommandLineRunner runner = loadDatabase.initDatabase();
        runner.run();

        verify(userService, times(1)).isEmpty();
        verify(userService, times(1)).createUsers();
        verify(courseService, times(1)).isEmpty();
        verify(courseService, times(1)).createCourses();
        verify(learningPathService, times(1)).isEmpty();
        verify(learningPathService, times(1)).createLearningPaths();
    }
}