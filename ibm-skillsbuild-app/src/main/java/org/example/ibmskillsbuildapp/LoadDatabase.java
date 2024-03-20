package org.example.ibmskillsbuildapp;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.example.ibmskillsbuildapp.model.Course;
import org.example.ibmskillsbuildapp.model.LearningStatus;
import org.example.ibmskillsbuildapp.model.User;
import org.example.ibmskillsbuildapp.model.UserCourse;
import org.example.ibmskillsbuildapp.repo.CourseRepository;
import org.example.ibmskillsbuildapp.repo.UserCourseRepository;
import org.example.ibmskillsbuildapp.repo.UserRepository;
import org.example.ibmskillsbuildapp.service.CourseService;
import org.example.ibmskillsbuildapp.service.LearningPathService;
import org.example.ibmskillsbuildapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class for loading initial data into the database. This class checks if the
 * repositories for users, courses, and learning paths are empty, and if so, creates and saves new
 * entities.
 */
@Configuration
public class LoadDatabase {

    private final UserService userService;
    private final CourseService courseService;
    private final LearningPathService learningPathService;
    private final Random random = new Random();

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserCourseRepository userCourseRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Constructs a new LoadDatabase object with the specified user, course, and learning path
     * services.
     *
     * @param userService         the service for managing users.
     * @param courseService       the service for managing courses.
     * @param learningPathService the service for managing learning paths.
     */
    public LoadDatabase(UserService userService, CourseService courseService,
        LearningPathService learningPathService) {
        this.userService = userService;
        this.courseService = courseService;
        this.learningPathService = learningPathService;
    }

    /**
     * Initializes the database with users, courses, and learning paths if their respective
     * repositories are empty.
     *
     * @return a CommandLineRunner that runs the initialization logic.
     */
    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            if (learningPathService.isEmpty()) {
                learningPathService.createLearningPaths();
            }

            if (courseService.isEmpty()) {
                courseService.createCourses();
            }

            if (userService.isEmpty()) {
                userService.createUsers();
            }

            createDemoData();
        };
    }

    /**
     * This method creates demo data for the application. It generates 25 users and assigns them to
     * courses. Each user-course relation is assigned a random learning status. If the status is
     * STARTED or COMPLETED, a random start date within the last year is generated. If the status is
     * COMPLETED, a random completion date after the start date and a random rating between 1 and 5
     * is also generated.
     */
    private void createDemoData() {
        List<Course> courses = (List<Course>) courseRepository.findAll();

        for (int i = 0; i < 25; i++) {
            User user = new User();
            user.setUserName("User" + i);
            user.setPassword(passwordEncoder.encode("password" + i));
            user = userRepository.save(user);

            for (Course course : courses) {
                UserCourse userCourse = new UserCourse();
                userCourse.setUser(user);
                userCourse.setCourse(course);

                // Randomly assign a LearningStatus
                LearningStatus status = LearningStatus.values()[random.nextInt(
                    LearningStatus.values().length)];
                userCourse.setStatus(status);

                if (status != LearningStatus.AVAILABLE) {
                    // Randomly generate a startDate in the past year
                    LocalDate startDate = LocalDate.now().minusDays(random.nextInt(365));
                    userCourse.setStartDate(
                        Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));

                    if (status == LearningStatus.COMPLETED) {
                        // Randomly generate a completionDate that is after the startDate
                        LocalDate completionDate = startDate.plusDays(random.nextInt(365));
                        userCourse.setCompletionDate(Date.from(
                            completionDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));

                        // Randomly generate a rating
                        userCourse.setRating(random.nextInt(5) + 1);
                    }
                }

                userCourseRepository.save(userCourse);
            }
        }
    }
}
