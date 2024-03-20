package org.example.ibmskillsbuildapp.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
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

    /**
     * Gathers, formats, and groups analytics data for each course.
     *
     * @return a List of Maps where each Map represents a course with its name and counts for each property
     */
    public List<Map<String, Object>> getAnalyticsData() {
        Map<String, Map<Course, Long>> analyticsData = gatherAnalyticsData();

        List<Map<String, Object>> dataArray = convertDataToArray(analyticsData);

        Map<String, Map<String, Object>> groupedData = groupDataByCourseName(dataArray);

        return convertGroupedData(groupedData);
    }

    /**
     * Gathers analytics data for each course.
     *
     * @return a Map where the keys are the property names (started, completed, rated,
     * enrollmentLast30Days) and the values are Maps with Course objects as keys and counts as
     * values
     */
    private Map<String, Map<Course, Long>> gatherAnalyticsData() {
        Map<String, Map<Course, Long>> analyticsData = new HashMap<>();
        analyticsData.put("started", getStartedCoursesCount());
        analyticsData.put("completed", getCompletedCoursesCount());
        analyticsData.put("rated", getRatedCoursesCount());
        analyticsData.put("enrollmentLast30Days", getEnrollmentsLast30DaysCount());
        return analyticsData;
    }

    /**
     * Converts the gathered analytics data to an array format.
     *
     * @param analyticsData the Map of analytics data gathered from the gatherAnalyticsData()
     *                      method
     * @return a List of Maps where each Map represents a course with its name, property, and count
     */
    private List<Map<String, Object>> convertDataToArray(
        Map<String, Map<Course, Long>> analyticsData) {
        return analyticsData.entrySet().stream().flatMap(entry -> {
            String propertyName = entry.getKey();
            Map<Course, Long> courseCounts = entry.getValue();
            return courseCounts.entrySet().stream().map(courseCountEntry -> {
                Map<String, Object> item = new HashMap<>();
                item.put("name", courseCountEntry.getKey().getCourseName());
                item.put("property", propertyName);
                item.put("count", courseCountEntry.getValue());
                return item;
            });
        }).toList();
    }

    /**
     * Groups the converted data by course name.
     *
     * @param dataArray the List of Maps returned from the convertDataToArray() method
     * @return a Map where the keys are the course names and the values are Maps with property names
     * as keys and counts as values
     */
    private Map<String, Map<String, Object>> groupDataByCourseName(
        List<Map<String, Object>> dataArray) {
        Map<String, Map<String, Object>> groupedData = new HashMap<>();
        for (Map<String, Object> item : dataArray) {
            String courseName = (String) item.get("name");
            Map<String, Object> courseData = groupedData.computeIfAbsent(courseName,
                k -> createNewCourseData(courseName));
            courseData.put((String) item.get("property"), item.get("count"));
        }
        return groupedData;
    }

    /**
     * Creates a new course data Map with default values.
     *
     * @param courseName the name of the course for which the data Map is to be created
     * @return a Map with property names as keys and default values (0) as values
     */
    private Map<String, Object> createNewCourseData(String courseName) {
        Map<String, Object> newCourseData = new HashMap<>();
        newCourseData.put("name", courseName);
        newCourseData.put("started", 0);
        newCourseData.put("completed", 0);
        newCourseData.put("rated", 0);
        newCourseData.put("enrollmentLast30Days", 0);
        return newCourseData;
    }

    /**
     * Converts the grouped data to a format suitable for the front-end.
     *
     * @param groupedData the Map of grouped data returned from the groupDataByCourseName() method
     * @return a List of Maps where each Map represents a course with its name and counts for each
     * property
     */
    private List<Map<String, Object>> convertGroupedData(
        Map<String, Map<String, Object>> groupedData) {
        return new ArrayList<>(groupedData.values());
    }
}
