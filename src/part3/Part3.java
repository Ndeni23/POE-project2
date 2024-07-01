package part3;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class Part3 {
    static List<Task> tasks = new ArrayList<>(); // Store all tasks here
    static Register register = new Register(); // Create a Register object globally
    static Login login = new Login(register); // Create a Login object globally

    public static void main(String[] args) {
        boolean loggedIn = false;

        // Initial registration prompt (required before main menu)
        String registerStatus = register.registerUser();
        if (registerStatus == null) { // User canceled registration
            System.exit(0); // Exit the program
        } else {
            JOptionPane.showMessageDialog(null, registerStatus);
        }

        while (true) {
            // Initial menu with login, and quit options
            String[] options = {"Login", "Quit"};
            int choice = JOptionPane.showOptionDialog(null, "Welcome to EasyKanban", "EasyKanban",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0: // Login
                    while (!loggedIn) {
                        String enteredUsername = JOptionPane.showInputDialog("Enter your username:");
                        if (enteredUsername == null) { // Cancel button was pressed
                            break; // Go back to the main menu
                        }
                        String enteredPassword = JOptionPane.showInputDialog("Enter your password:");
                        if (enteredPassword == null) { // Cancel button was pressed
                            break; // Go back to the main menu
                        }
                        String loginStatus = login.loginUser(enteredUsername, enteredPassword);
                        JOptionPane.showMessageDialog(null, loginStatus);
                        if (loginStatus.startsWith("Welcome")) {
                            loggedIn = true;
                            break; // Exit the login loop
                        }
                    }
                    break; // Exit the main switch-case after login

                case 1: // Quit
                    JOptionPane.showMessageDialog(null, "Exiting EasyKanban. Goodbye!");
                    System.exit(0); // Exit the program

                default: // Invalid choice
                    JOptionPane.showMessageDialog(null, "Invalid option. Please choose again.");
            }

            // Main application loop after successful login
            while (loggedIn) {
                String[] mainMenuOptions = {"Add tasks", "Show report", "Quit"};
                int mainChoice = JOptionPane.showOptionDialog(null, "Welcome to EasyKanban", "EasyKanban",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, mainMenuOptions, mainMenuOptions[0]);

                switch (mainChoice) {
                    case 0 -> {
                        // Add Tasks
                        // Input validation and Task creation
                        while (true) {
                            String taskName = JOptionPane.showInputDialog("Enter task name:");
                            if (taskName == null) { // Cancel button was pressed
                                break; // Go back to the main menu
                            }
                            String taskDescription = JOptionPane.showInputDialog("Enter task description:");
                            if (taskDescription == null) { // Cancel button was pressed
                                break; // Go back to the main menu
                            }
                            // **Validation for task description**
                            if (!isValidString(taskDescription)) {
                                JOptionPane.showMessageDialog(null, "Task description cannot contain numbers. Please try again.");
                                continue; // Ask for task description again
                            }
                            String developerDetails = JOptionPane.showInputDialog("Enter developer details (First Last):");
                            if (developerDetails == null) { // Cancel button was pressed
                                break; // Go back to the main menu
                            }
                            // **Validation for developer's name**
                            if (!isValidString(developerDetails)) {
                                JOptionPane.showMessageDialog(null, "Developer's name cannot contain numbers. Please try again.");
                                continue; // Ask for developer details again
                            }
                            double taskDuration;
                            try {
                                taskDuration = Double.parseDouble(JOptionPane.showInputDialog("Enter task duration in hours:"));
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Invalid task duration. Please enter a number.");
                                continue; // Go back to the beginning of the task adding loop
                            }
                            if (taskDuration < 0) {
                                JOptionPane.showMessageDialog(null, "Task duration cannot be negative. Please enter a positive number.");
                                continue; // Go back to the beginning of the task adding loop
                            }


                            String[] statusOptions = {"To Do", "Done", "Doing"};
                            int statusChoice = JOptionPane.showOptionDialog(null, "Choose task status:", "Task Status",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, statusOptions, statusOptions[0]);


                            String taskStatus = "To Do"; // Default status
                            switch (statusChoice) {
                                case 0 -> taskStatus = "To Do";
                                case 1 -> taskStatus = "Done";
                                case 2 -> taskStatus = "Doing";
                            }


                            // Add task to list
                            Task task = new Task(taskName, taskDescription, developerDetails, taskDuration, taskStatus);
                            tasks.add(task);
                            JOptionPane.showMessageDialog(null, "Task added successfully!");


                            // Now, prompt the user to add another task or go back to the main menu
                            String[] addTaskOptions = {"Add another task", "Back to main menu"};
                            int addTaskChoice = JOptionPane.showOptionDialog(null, "Add Task", "EasyKanban",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, addTaskOptions, addTaskOptions[0]);


                            if (addTaskChoice == 1) { // User chose "Back to main menu"
                                break; // Exit the "Add tasks" switch case
                            }
                            
                            // If the user chose "Add another task", the loop will continue
                            // and the code will prompt for the next task details.


                            break; // Exit the "Add tasks" switch case
                        }
                        // Exit the "Add tasks" switch case
                    }
                    case 1 -> { // Show Report
                        finalFeatures(); // Call your feature menu function
                    }


                    case 2 -> { // Quit
                        loggedIn = false;
                        // Exit the main menu loop
                        // Reset loggedIn to false
                    }


                    default -> // Invalid choice
                            JOptionPane.showMessageDialog(null, "Invalid option. Please choose again.");
                }
            }
        }
    }


    // Feature Menu
    static void finalFeatures() {
        while (true) {
            String menuText = """
                              Choose a Report Option:
                              1. Display Done Tasks
                              2. Display the Longest Task
                              3. Search for a Task
                              4. Search for Developer's Tasks
                              5. Delete a Task
                              6. Display Report
                              7. Back to Main Menu""";


            try {
                int choice = Integer.parseInt(JOptionPane.showInputDialog(null, menuText));


                switch (choice) {
                    case 1 -> displayDoneTasks();
                    case 2 -> displayLongestTask();
                    case 3 -> searchForTask();
                    case 4 -> searchDeveloperTasks();
                    case 5 -> deleteTask();
                    case 6 -> displayReport();
                    case 7 -> { // Back to main menu
                        return; // Exit the feature menu
                    }
                    default -> JOptionPane.showMessageDialog(null, "Invalid Choice. Please enter a number between 1 and 7");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
            }
        }
    }


    // Methods for Report Options
    private static void displayDoneTasks() {
        String output = "";
        for (Task task : tasks) {
            if (task.taskStatus.equals("Done")) {
                output += "Task Name: " + task.taskName + "\n";
                output += "Developer: " + task.developerDetails + "\n";
                output += "Task Duration: " + task.taskDuration + " hours\n\n";
            }
        }
        if (output.isEmpty()) {
            output = "No tasks found with the status 'Done'.";
        }
        JOptionPane.showMessageDialog(null, output);
    }


    private static void displayLongestTask() {
        if (tasks.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tasks available.");
            return;
        }


        Task longestTask = tasks.get(0);
        for (Task task : tasks) {
            if (task.taskDuration > longestTask.taskDuration) {
                longestTask = task;
            }
        }


        String output = "Longest Duration Task:\n";
        output += "Task Name: " + longestTask.taskName + "\n";
        output += "Developer: " + longestTask.developerDetails + "\n";
        output += "Task Duration: " + longestTask.taskDuration + " hours\n";
        JOptionPane.showMessageDialog(null, output);
    }


    private static void searchForTask() {
        String taskNameToSearch = JOptionPane.showInputDialog("Enter the task name to search:");
        if (taskNameToSearch == null) { // Cancel button was pressed
            return; // Go back to the report menu
        }
        String output = "";


        for (Task task : tasks) {
            if (task.taskName.equalsIgnoreCase(taskNameToSearch)) {
                output += "Task Name: " + task.taskName + "\n";
                output += "Developer: " + task.developerDetails + "\n";
                output += "Task Status: " + task.taskStatus + "\n\n";
            }
        }


        if (output.isEmpty()) {
            output = "No task found with the name '" + taskNameToSearch + "'";
        }
        JOptionPane.showMessageDialog(null, output);
    }


    private static void searchDeveloperTasks() {
        String developerToSearch = JOptionPane.showInputDialog("Enter the developer's name to search:");
        if (developerToSearch == null) { // Cancel button was pressed
            return; // Go back to the report menu
        }
        String output = "";


        for (Task task : tasks) {
            if (task.developerDetails.equalsIgnoreCase(developerToSearch)) {
                output += "Task Name: " + task.taskName + "\n";
                output += "Task Status: " + task.taskStatus + "\n\n";
            }
        }


        if (output.isEmpty()) {
            output = "No tasks found for developer '" + developerToSearch + "'";
        }
        JOptionPane.showMessageDialog(null, output);
    }


    private static void deleteTask() {
        String taskNameToDelete = JOptionPane.showInputDialog("Enter the task name to delete:");
        if (taskNameToDelete == null) { // Cancel button was pressed
            return; // Go back to the report menu
        }
        boolean taskFound = false;


        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).taskName.equalsIgnoreCase(taskNameToDelete)) {
                tasks.remove(i);
                taskFound = true;
                break;
            }
        }


        if (taskFound) {
            JOptionPane.showMessageDialog(null, "Task '" + taskNameToDelete + "' deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "No task found with the name '" + taskNameToDelete + "'");
        }
    }


    private static void displayReport() {
        String output = "";
        for (Task task : tasks) {
            output += task.printTaskDetails() + "\n";
        }
        if (output.isEmpty()) {
            output = "No tasks available.";
        }
        JOptionPane.showMessageDialog(null, output);
    }
    
    // Helper function to check if a string contains only letters and spaces
    private static boolean isValidString(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                return false; // Contains a non-letter, non-space character
            }
        }
        return true; // String is valid
    }

    static String displayLongestTaskAndGetResult(List<Task> tasks) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static String searchForTaskAndGetResult(List<Task> tasks, String create_Login) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static String searchDeveloperTasksAndGetResult(List<Task> tasks, String samantha_Paulson) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static void deleteTask(List<Task> tasks, String taskToDelete) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static String displayReportAndGetResult(List<Task> tasks) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
