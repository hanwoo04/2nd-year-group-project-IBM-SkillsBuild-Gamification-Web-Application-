package org.example.ibmskillsbuildapp.model;

import java.util.ArrayList;
import java.util.List;

public class LearningPath {

    private String pathName;
    private List<Course> courses;

    public LearningPath(String pathName) {
        this.pathName = pathName;
    }

    public void addCourse(String courseName) {
        checkCoursesNotNull();
        courses.add(new Course(courseName, "AVAILABLE"));
    }

    public void addCourse(String courseName, String status) {
        checkCoursesNotNull();
        courses.add(new Course(courseName, status));
    }

    private void checkCoursesNotNull() {
        if (courses == null) {
            courses = new ArrayList<>();
        }
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
}
