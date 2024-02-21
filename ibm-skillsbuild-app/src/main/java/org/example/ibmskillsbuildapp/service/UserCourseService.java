package org.example.ibmskillsbuildapp.service;

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
        userCourseRepository.save(userCourse);

        // Update the user's score
        // TODO: Implement different scores for courses based on length of course?
        user.setScore(user.getScore() + 100);
        userRepository.save(user);

    }
}
