package org.example.ibmskillsbuildapp.model;

/**
 * Represents a view of a course. This view includes the path name, course name, and learning
 * status.
 */
public class CourseView {

    private String pathName;
    private String courseName;
    private LearningStatus status;

    public CourseView(String pathName, String courseName, LearningStatus status) {
        this.pathName = pathName;
        this.courseName = courseName;
        this.status = status;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LearningStatus getStatus() {
        return status;
    }

    public void setStatus(LearningStatus status) {
        this.status = status;
    }
}
