package org.example.ibmskillsbuildapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.ibmskillsbuildapp.model.UserCourse;
import org.example.ibmskillsbuildapp.model.User;
import org.example.ibmskillsbuildapp.model.Course;
import org.example.ibmskillsbuildapp.model.LearningStatus;
import org.example.ibmskillsbuildapp.repo.UserCourseRepository;

/**
 * Service class for managing {@link UserCourse} entities. This service provides methods for common
 * operations such as enrolling a user in a course.
 */
@Service
public class UserCourseService {

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
}
