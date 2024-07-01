package part3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

class Part3Test {

    // Test data for tasks
    private static final Task task1 = new Task("Create Login", "Create the login feature", "Mike Smith", 5, "To Do");
    private static final Task task2 = new Task("Create Add Features", "Implement new features", "Edward Harrison", 8, "Doing");
    private static final Task task3 = new Task("Create Reports", "Build reports functionality", "Samantha Paulson", 2, "Done");
    private static final Task task4 = new Task("Add Arrays", "Implement array functionality", "Glenda Oberholzer", 11, "To Do");

    @Test
    void testDeveloperArrayPopulation() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);

        String[] expectedDevelopers = {"Mike Smith", "Edward Harrison", "Samantha Paulson", "Glenda Oberholzer"};
        String[] actualDevelopers = new String[tasks.size()];

        for (int i = 0; i < tasks.size(); i++) {
            actualDevelopers[i] = tasks.get(i).developerDetails;
        }

        assertArrayEquals(expectedDevelopers, actualDevelopers);
    }

    @Test
    void testDisplayLongestTask() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);

        String expectedLongestTask = "Glenda Oberholzer, 11.0";
        String actualLongestTask = Part3.displayLongestTaskAndGetResult(tasks);

        assertEquals(expectedLongestTask, actualLongestTask);
    }

    @Test
    void testSearchForTask() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);

        String expectedResult = "Mike Smith, Create Login";
        String actualResult = Part3.searchForTaskAndGetResult(tasks, "Create Login");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testSearchDeveloperTasks() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);

        String expectedResult = "Create Reports";
        String actualResult = Part3.searchDeveloperTasksAndGetResult(tasks, "Samantha Paulson");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testDeleteTask() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);

        String taskToDelete = "Create Reports";
        Part3.deleteTask(tasks, taskToDelete);

        boolean taskFound = false;
        for (Task task : tasks) {
            if (task.taskName.equalsIgnoreCase(taskToDelete)) {
                taskFound = true;
                break;
            }
        }

        assertFalse(taskFound); // Task should no longer be in the list
    }

    @Test
    void testDisplayReport() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);

        String expectedReport = """
                                Task Status: To Do
                                Developer Details: Mike Smith
                                Task Number: 0
                                Task Name: Create Login
                                Task Description: Create the login feature
                                Task Duration: 5.0
                                Task ID: CR:0:Smi
                                
                                Task Status: Doing
                                Developer Details: Edward Harrison
                                Task Number: 1
                                Task Name: Create Add Features
                                Task Description: Implement new features
                                Task Duration: 8.0
                                Task ID: CR:1:son
                                
                                Task Status: Done
                                Developer Details: Samantha Paulson
                                Task Number: 2
                                Task Name: Create Reports
                                Task Description: Build reports functionality
                                Task Duration: 2.0
                                Task ID: CR:2:son
                                
                                Task Status: To Do
                                Developer Details: Glenda Oberholzer
                                Task Number: 3
                                Task Name: Add Arrays
                                Task Description: Implement array functionality
                                Task Duration: 11.0
                                Task ID: AD:3:zer
                                """;

        String actualReport = Part3.displayReportAndGetResult(tasks);

        assertEquals(expectedReport, actualReport);
    }

    // Helper methods to get results from private methods in Part3
    public static String displayLongestTaskAndGetResult(List<Task> tasks) {
        String output = "";
        if (tasks.isEmpty()) {
            output = "No tasks available.";
        } else {
            Task longestTask = tasks.get(0);
            for (Task task : tasks) {
                if (task.taskDuration > longestTask.taskDuration) {
                    longestTask = task;
                }
            }

            output = longestTask.developerDetails + ", " + longestTask.taskDuration;
        }
        return output;
    }

    public static String searchForTaskAndGetResult(List<Task> tasks, String taskNameToSearch) {
        String output = "";
        for (Task task : tasks) {
            if (task.taskName.equalsIgnoreCase(taskNameToSearch)) {
                output = task.developerDetails + ", " + task.taskName;
            }
        }
        return output;
    }

    public static String searchDeveloperTasksAndGetResult(List<Task> tasks, String developerToSearch) {
        String output = "";
        for (Task task : tasks) {
            if (task.developerDetails.equalsIgnoreCase(developerToSearch)) {
                output = task.taskName;
            }
        }
        return output;
    }

    public static String displayReportAndGetResult(List<Task> tasks) {
        String output = "";
        for (Task task : tasks) {
            output += task.printTaskDetails() + "\n";
        }
        
        if (output.isEmpty()) {
            output = "No tasks available.";
        }
        return output;
    }
}