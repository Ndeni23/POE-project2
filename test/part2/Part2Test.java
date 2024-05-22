
package part2;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author RC_Student_lab
 */


class TaskTest {

    @Test
    void testCheckTaskDescription() {
        Task task = new Task();
        assertTrue(task.checkTaskDescription("Short description"));
        assertFalse(task.checkTaskDescription("This is a very long task description that exceeds 50 characters."));
    }

    @Test
    void testCreateTaskID() {
        Task task = new Task();
        task.taskName = "Login Feature";
        task.taskNumber = 0;
        task.developerDetails = "Robyn Harrison";
        assertEquals("LO:0:SON", task.createTaskID());
    }

    @Test
    void testReturnTotalHours() {
        Task task1 = new Task();
        task1.taskDuration = 8;
        Task task2 = new Task();
        task2.taskDuration = 10;

        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);

        assertEquals(18, Task.returnTotalHours(tasks), 0.001); 
    }
}