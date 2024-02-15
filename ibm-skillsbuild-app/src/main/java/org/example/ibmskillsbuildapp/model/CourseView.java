package org.example.ibmskillsbuildapp.model;

/**
 * Represents a view of a course. This view includes the path name, course name, learning
 * status, and URL.
 */
public class CourseView {

    private String pathName;
    private String courseName;
    private LearningStatus status;
    private String url;

    public CourseView(String pathName, String courseName, LearningStatus status, String url) {
        this.pathName = pathName;
        this.courseName = courseName;
        this.status = status;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
