package org.example.ibmskillsbuildapp.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.example.ibmskillsbuildapp.model.Course;
import org.example.ibmskillsbuildapp.model.LearningStatus;
import org.example.ibmskillsbuildapp.model.User;
import org.example.ibmskillsbuildapp.model.UserCourse;
import org.example.ibmskillsbuildapp.repo.UserCourseRepository;
import org.example.ibmskillsbuildapp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing {@link UserCourse} entities. This service provides methods for common
 * operations such as enrolling a user in a course.
 */
@Service
public class UserCourseService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserCourseRepository userCourseRepository;

    /**
     * Enrolls a user in a course. This method finds the UserCourse object that corresponds to the
     * given user and course, sets the status of the UserCourse object to STARTED, and saves the
     * updated UserCourse object in the repository.
     *
     * @param user   the User object representing the user who is enrolling in the course
     * @param course the Course object representing the course the user is enrolling in
     */
    public void enroll(User user, Course course) {
        UserCourse userCourse = userCourseRepository.findByUserAndCourse(user, course);
        userCourse.setStatus(LearningStatus.STARTED);
        userCourse.setStartDate(new Date());
        userCourseRepository.save(userCourse);
    }

    /**
     * Marks a course as completed for a user. This method finds the UserCourse object that
     * corresponds to the given user and course, sets the status of the UserCourse object to
     * COMPLETED, and saves the updated UserCourse object in the repository. It also increments the
     * user's score by 100.
     *
     * @param user   the User object representing the user who is completing the course
     * @param course the Course object representing the course the user is completing
     */
    public void complete(User user, Course course) {
        UserCourse userCourse = userCourseRepository.findByUserAndCourse(user, course);
        userCourse.setStatus(LearningStatus.COMPLETED);
        userCourse.setCompletionDate(new Date());
        userCourseRepository.save(userCourse);

        // Update the user's score
        user.setScore(user.getScore() + 100);
        userRepository.save(user);
    }

    /**
     * Calculates the number of users who have started each course.
     *
     * @return a Map where the keys are the Course objects and the values are the counts of users
     * who have started each course
     */
    public Map<Course, Long> getStartedCoursesCount() {
        List<UserCourse> userCourses = new ArrayList<>(
            (Collection<? extends UserCourse>) userCourseRepository.findAll());
        Stream<UserCourse> startedCoursesStream = userCourses.stream()
            .filter(userCourse -> userCourse.getStatus() == LearningStatus.STARTED
                && userCourse.getStartDate() != null);
        return startedCoursesStream.collect(
            Collectors.groupingBy(UserCourse::getCourse, Collectors.counting()));
    }

    /**
     * Calculates the number of users who have completed each course.
     *
     * @return a Map where the keys are the Course objects and the values are the counts of users
     * who have completed each course
     */
    public Map<Course, Long> getCompletedCoursesCount() {
        List<UserCourse> userCourses = new ArrayList<>(
            (Collection<? extends UserCourse>) userCourseRepository.findAll());
        Stream<UserCourse> completedCoursesStream = userCourses.stream()
            .filter(userCourse -> userCourse.getStatus() == LearningStatus.COMPLETED
                && userCourse.getCompletionDate() != null);
        return completedCoursesStream.collect(
            Collectors.groupingBy(UserCourse::getCourse, Collectors.counting()));
    }

    /**
     * Calculates the number of users who have rated each course.
     *
     * @return a Map where the keys are the Course objects and the values are the counts of users
     * who have rated each course
     */
    public Map<Course, Long> getRatedCoursesCount() {
        List<UserCourse> userCourses = new ArrayList<>(
            (Collection<? extends UserCourse>) userCourseRepository.findAll());
        Stream<UserCourse> ratedCoursesStream = userCourses.stream()
            .filter(userCourse -> userCourse.getRating() != null);
        return ratedCoursesStream.collect(
            Collectors.groupingBy(UserCourse::getCourse, Collectors.counting()));
    }

    /**
     * Calculates the number of users who have enrolled in each course in the last 30 days.
     *
     * @return a Map where the keys are the Course objects and the values are the counts of users
     * who have enrolled in the last 30 days for each course
     */
    public Map<Course, Long> getEnrollmentsLast30DaysCount() {
        Date thirtyDaysAgo = Date.from(
            LocalDate.now().minusDays(30).atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<UserCourse> userCourses = new ArrayList<>(
            (Collection<? extends UserCourse>) userCourseRepository.findAll());
        Stream<UserCourse> last30DaysEnrollmentStream = userCourses.stream()
            .filter(userCourse -> userCourse.getStartDate() != null && userCourse.getStartDate()
                .after(thirtyDaysAgo));
        return last30DaysEnrollmentStream.collect(
            Collectors.groupingBy(UserCourse::getCourse, Collectors.counting()));
    }
}
