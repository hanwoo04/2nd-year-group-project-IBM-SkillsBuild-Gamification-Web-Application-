package org.example.ibmskillsbuildapp.model;

public class CourseView {

    private String pathName;
    private String courseName;
    private String status;

    public CourseView(String pathName, String courseName, String status) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
