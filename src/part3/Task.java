package part3;

import java.util.List;

final class Task {
    static int taskCounter = 0;
    String taskName;
    int taskNumber;
    String taskDescription;
    String developerDetails;
    double taskDuration;
    String taskID;
    String taskStatus;


    // Constructor
    public Task(String taskName, String taskDescription, String developerDetails, double taskDuration, String taskStatus) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.developerDetails = developerDetails;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
        this.taskNumber = taskCounter++;
        this.taskID = createTaskID();
    }


    // Method to check if the task description is within the character limit
    public boolean checkTaskDescription(String taskDescription) {
        return taskDescription.length() <= 50;
    }


    // Method to generate a unique task ID
    public String createTaskID() {
        String firstTwo = taskName.substring(0, Math.min(2, taskName.length())).toUpperCase();
        String lastThree = developerDetails.substring(Math.max(0, developerDetails.length() - 3)).toUpperCase();
        return firstTwo + ":" + taskNumber + ":" + lastThree;
    }


    // Method to format and print task details
    public String printTaskDetails() {
        return "Task Status: " + taskStatus + "\n" +
               "Developer Details: " + developerDetails + "\n" +
               "Task Number: " + taskNumber + "\n" +
               "Task Name: " + taskName + "\n" +
               "Task Description: " + taskDescription + "\n" +
               "Task Duration: " + taskDuration + "\n" +
               "Task ID: " + taskID + "\n";
    }


    // Static method to calculate the total hours for all tasks in a list
    public static double returnTotalHours(List<Task> tasks) {
        double totalHours = 0;
        for (Task task : tasks) {
            totalHours += task.taskDuration;
        }
        return totalHours;
    }
}
