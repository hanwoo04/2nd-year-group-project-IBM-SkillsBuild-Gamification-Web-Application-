package org.example.ibmskillsbuildapp.model;

public class CompletionRecord {


    private String taskName;
    private String completionDate;
    private boolean isSuccess;


    public CompletionRecord(String taskName, String completionDate, boolean isSuccess) {
        this.taskName = taskName;
        this.completionDate = completionDate;
        this.isSuccess = isSuccess;
    }

    public void displayRecord() {
        System.out.println("Task Name: " + taskName);
        System.out.println("Completion Date: " + completionDate);
        System.out.println("Success: " + isSuccess);
    }


    public static void main(String[] args) {

        CompletionRecord record = new CompletionRecord("Sample Task", "2024-02-13", true);


        record.displayRecord();
    }
}
