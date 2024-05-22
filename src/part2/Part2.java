package part2;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class Part2 {

    public static void main(String[] args) {
        Register register = new Register();
        Login login = new Login(register);
        List<Task> tasks = new ArrayList<>();
        boolean loggedIn = false;

        while (true) {
            // Initial menu with login, register, and quit options
            String[] options = {"Login", "Register", "Quit"};
            int choice = JOptionPane.showOptionDialog(null, "Welcome to EasyKanban", "EasyKanban", 
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0: // Login
                    while (!loggedIn) {
                        String enteredUsername = JOptionPane.showInputDialog("Enter your username:");
                        String enteredPassword = JOptionPane.showInputDialog("Enter your password:");
                        String loginStatus = login.loginUser(enteredUsername, enteredPassword);
                        JOptionPane.showMessageDialog(null, loginStatus);
                        if (loginStatus.startsWith("Welcome")) {
                            loggedIn = true;
                            break;
                        }
                    }
                    break;

                case 1: // Register
                    String registerStatus = register.registerUser();
                    JOptionPane.showMessageDialog(null, registerStatus);
                    break;

                case 2: // Quit
                    JOptionPane.showMessageDialog(null, "Exiting EasyKanban. Goodbye!");
                    System.exit(0);

                default: // Invalid choice
                    JOptionPane.showMessageDialog(null, "Invalid option. Please choose again.");
            }

            // Main application loop after successful login
            while (loggedIn) {
                String[] mainMenuOptions = {"Add tasks", "Show report (Coming Soon)", "Quit"};
                int mainChoice = JOptionPane.showOptionDialog(null, "Welcome to EasyKanban", "EasyKanban",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, mainMenuOptions, mainMenuOptions[0]);

                switch (mainChoice) {
                    case 0 -> {
                        // Add Tasks
                        try {
                            int taskCount = Integer.parseInt(JOptionPane.showInputDialog("Enter number of tasks:"));
                            for (int i = 0; i < taskCount; i++) {
                                Task task = new Task();
                                task.createTask(); // Create each task
                                tasks.add(task);
                                JOptionPane.showMessageDialog(null, task.printTaskDetails());
                            }
                            JOptionPane.showMessageDialog(null, "Total hours for all tasks: " + Task.returnTotalHours(tasks));
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Invalid number of tasks. Please enter a number.");
                        }
                    }

                    case 1 -> // Show report
                        JOptionPane.showMessageDialog(null, "Coming Soon!");

                    case 2 -> {
                        // Quit
                        JOptionPane.showMessageDialog(null, "Exiting EasyKanban. Goodbye!");
                        loggedIn = false;
                    }

                    default -> // Invalid choice
                        JOptionPane.showMessageDialog(null, "Invalid option. Please choose again.");
                }
            }
        }
    }
}
