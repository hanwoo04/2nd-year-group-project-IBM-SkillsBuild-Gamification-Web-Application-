package org.example.ibmskillsbuildapp.model;

public class Course {

    private String courseName;
    private String status;

    public Course(String courseName, String status) {
        this.courseName = courseName;
        this.status = status;
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
