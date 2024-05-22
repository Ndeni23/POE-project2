package part2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    private Task task; 

    @BeforeEach
    void setUp() {
        task = new Task(); // Create a new Task object before each test
        Task.taskCounter = 0; // Reset the task counter
    }

    @Test
    void testCheckTaskDescriptionSuccess() {
        assertTrue(task.checkTaskDescription("Create Add Task feature"));
    }

    @Test
    void testCheckTaskDescriptionFailure() {
        assertFalse(task.checkTaskDescription("Create Add Task feature to add task users"));
    }

    @Test
    void testCreateTaskID() {
        task.taskName = "Add Task Feature";
        task.developerDetails = "Mike Smith";
        task.taskNumber = 1; 
        assertEquals("AD:1:ITH", task.createTaskID());
    }

    @Test
    void testReturnTotalHours() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(createTaskWithDuration(8));
        tasks.add(createTaskWithDuration(10));
        assertEquals(18, Task.returnTotalHours(tasks), 0.001);
    }

    @Test
    void testReturnTotalHoursWithAdditionalData() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(createTaskWithDuration(10));
        tasks.add(createTaskWithDuration(12));
        tasks.add(createTaskWithDuration(55));
        tasks.add(createTaskWithDuration(11));
        tasks.add(createTaskWithDuration(1));
        assertEquals(89, Task.returnTotalHours(tasks), 0.001);
    }

    @Test
    void testCreateTaskIDLoop() {
        String[] taskNames = {"Create Report", "Implement Feature", "Fix Bugs", "Conduct Tests"};
        String[] devDetails = {"Alice Brown", "Charlie Reed", "Dan Smith", "Eve Johnson"};
        String[] expectedIds = {"CR:0:OWN", "IM:1:EED", "FI:2:ITH", "CO:3:SON"};

        for (int i = 0; i < taskNames.length; i++) {
            Task testTask = new Task(); 
            testTask.taskName = taskNames[i];
            testTask.developerDetails = devDetails[i];
            testTask.taskNumber = i; // Assuming taskNumber is set correctly
            assertEquals(expectedIds[i], testTask.createTaskID(), "Task ID should be " + expectedIds[i]);
        }
    }

    // Helper method to create a Task with a specific duration
    private Task createTaskWithDuration(double duration) {
        Task task = new Task();
        task.taskDuration = duration;
        return task;
    }
}