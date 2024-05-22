package part2;

import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author RC_Student_lab
 */

class Task {
    private static int taskCounter = 0;
    String taskName;
    int taskNumber;
    private String taskDescription;
    String developerDetails;
    double taskDuration;
    private String taskID;
    private String taskStatus;

    public Task() {
    }

    public boolean checkTaskDescription(String taskDescription) {
        return taskDescription.length() <= 50;
    }

    public String createTaskID() {
        String firstTwo = taskName.substring(0, Math.min(2, taskName.length())).toUpperCase();
        String lastThree = developerDetails.substring(Math.max(0, developerDetails.length() - 3)).toUpperCase();
        return firstTwo + ":" + taskNumber + ":" + lastThree;
    }

    public String printTaskDetails() {
        return "Task Status: " + taskStatus + "\n" +
               "Developer Details: " + developerDetails + "\n" +
               "Task Number: " + taskNumber + "\n" +
               "Task Name: " + taskName + "\n" +
               "Task Description: " + taskDescription + "\n" +
               "Task Duration: " + taskDuration + "\n" +
               "Task ID: " + taskID + "\n";
    }

    // Modify createTask to use JOptionPane
    public void createTask() {
        taskName = JOptionPane.showInputDialog("Enter task name:");

        while (true) {
            taskDescription = JOptionPane.showInputDialog("Enter task description:");
            if (checkTaskDescription(taskDescription)) {
                JOptionPane.showMessageDialog(null, "Task successfully captured");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters");
            }
        }

        developerDetails = JOptionPane.showInputDialog("Enter developer details (First Last):");

        try {
            taskDuration = Double.parseDouble(JOptionPane.showInputDialog("Enter task duration in hours:"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid task duration. Please enter a number.");
            return; // Exit task creation if duration is invalid
        }

        String[] statusOptions = {"To Do", "Done", "Doing"};
        int statusChoice = JOptionPane.showOptionDialog(null, "Choose task status:", "Task Status",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, statusOptions, statusOptions[0]);

        switch (statusChoice) {
            case 0 -> taskStatus = "To Do";
            case 1 -> taskStatus = "Done";
            case 2 -> taskStatus = "Doing";
            default -> {
                JOptionPane.showMessageDialog(null, "Invalid status choice."); return; // Exit if invalid
            }
        }

        taskNumber = taskCounter++;
        taskID = createTaskID();
    }

    public static double returnTotalHours(List<Task> tasks) {
        double totalHours = 0;
        for (Task task : tasks) {
            totalHours += task.taskDuration;
        }
        return totalHours;
    }
}
