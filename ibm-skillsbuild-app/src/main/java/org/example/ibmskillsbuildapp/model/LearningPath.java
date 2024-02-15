package org.example.ibmskillsbuildapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a learning path with a name and a list of courses.
 */
@Entity
public class LearningPath {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String pathName;
    @OneToMany
    private List<Course> courses;

    public LearningPath() {
    }
    public LearningPath(String pathName) {
        this.pathName = pathName;
    }

    /**
     * Adds a course with the specified name to this learning path. The status of the course is set
     * to "AVAILABLE".
     *
     * @param courseName the name of the course
     */
    public void addCourse(String courseName) {
        checkCoursesNotNull();
        courses.add(new Course(courseName, "AVAILABLE"));
    }

    /**
     * Adds a course with the specified name and status to this learning path.
     *
     * @param courseName the name of the course
     * @param status     the status of the course
     */
    public void addCourse(String courseName, String status) {
        checkCoursesNotNull();
        courses.add(new Course(courseName, status));
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    /**
     * Checks if the list of courses is null and initializes it if necessary.
     */
    private void checkCoursesNotNull() {
        if (courses == null) {
            courses = new ArrayList<>();
        }
    }
}