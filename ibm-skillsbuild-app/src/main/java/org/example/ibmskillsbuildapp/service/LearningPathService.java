package org.example.ibmskillsbuildapp.service;

import java.util.ArrayList;
import java.util.List;
import org.example.ibmskillsbuildapp.model.Course;
import org.example.ibmskillsbuildapp.model.CourseView;
import org.example.ibmskillsbuildapp.model.LearningPath;
import org.springframework.stereotype.Service;

/**
 * Service for managing learning paths. This class provides the service to manage learning paths.
 * Note: Can be replaced by a repository once the application is connected to a database?
 */
@Service
public class LearningPathService {

    private List<LearningPath> learningPaths;

    public LearningPathService() {
        learningPaths = new ArrayList<>();
        LearningPath path1 = new LearningPath("Path1");
        path1.addCourse("Course1", "AVAILABLE");
        path1.addCourse("Course2", "STARTED");
        path1.addCourse("Course3", "COMPLETED");

        LearningPath path2 = new LearningPath("Path2");
        path2.addCourse("Course4", "AVAILABLE");
        path2.addCourse("Course5", "AVAILABLE");
        path2.addCourse("Course6", "STARTED");

        learningPaths.add(path1);
        learningPaths.add(path2);
    }


    /**
     * Retrieves all courses from all learning paths and wraps them into CourseView objects.
     *
     * @return a list of CourseView objects representing all courses from all learning paths
     */
    public List<CourseView> getAllCourseViews() {
        List<CourseView> courseViews = new ArrayList<>();
        for (LearningPath path : learningPaths) {
            for (Course course : path.getCourses()) {
                CourseView courseView = new CourseView(path.getPathName(), course.getCourseName(),
                    course.getStatus());
                courseViews.add(courseView);
            }
        }
        return courseViews;
    }
}