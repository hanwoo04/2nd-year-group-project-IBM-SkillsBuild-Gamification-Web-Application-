package org.example.ibmskillsbuildapp.service;

import org.example.ibmskillsbuildapp.model.Course;
import org.example.ibmskillsbuildapp.model.LearningPath;
import org.example.ibmskillsbuildapp.repo.CourseRepository;
import org.example.ibmskillsbuildapp.repo.LearningPathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing {@link Course} entities. This service provides methods for common
 * operations such as checking if the repository is empty and creating courses.
 */
@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private LearningPathRepository learningPathRepository;

    /**
     * Checks if the course repository is empty.
     *
     * @return true if the course repository is empty, false otherwise.
     */
    public boolean isEmpty() {
        return courseRepository.count() == 0;
    }

    /**
     * Creates and saves new courses in the course repository. The courses are associated with
     * specific learning paths.
     */
    public void createCourses() {
        LearningPath path1 = learningPathRepository.findByPathName("Artificial intelligence");
        LearningPath path2 = learningPathRepository.findByPathName("Cloud computing");
        LearningPath path3 = learningPathRepository.findByPathName("Design thinking");

        Course course1 = new Course("AI Foundations: A Collaboration of ISTE and IBM", "https://students.yourlearning.ibm.com/activity/PLAN-B2125F145F0E");
        course1.setLearningPath(path1);
        Course course2 = new Course("Artificial Intelligence Fundamentals", "https://students.yourlearning.ibm.com/activity/PLAN-CC702B39D429");
        course2.setLearningPath(path1);

        Course course3 = new Course("Cloud Computing Fundamentals", "https://students.yourlearning.ibm.com/activity/PLAN-58FA14F64C9B");
        course3.setLearningPath(path2);
        Course course4 = new Course("Introduction to Cloud", "https://students.yourlearning.ibm.com/activity/PLAN-4EB23B51588C");
        course4.setLearningPath(path2);

        Course course5 = new Course("Enterprise Design Thinking Practitioner", "https://ptech.yourlearning.ibm.com/activity/URL-1CA5E380CA4E");
        course5.setLearningPath(path3);

        courseRepository.save(course1);
        courseRepository.save(course2);
        courseRepository.save(course3);
        courseRepository.save(course4);
        courseRepository.save(course5);
    }
}