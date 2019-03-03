package persistence;

import model.DueDate;
import model.Tag;
import model.Task;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestJsonifier {
    // TODO: design tests for Jsonifier class

    // TODO: design tests for TaskParser class

    public Tag t1Empty;
    public Tag t2Empty;
    public Tag tag1;
    public Tag tag11;
    public Tag tag3;
    public Task task0;
    public Task task00;
    public Task task1;
    public Task task11;
    public Task task2;
    public Task task3;
    public Task task4;

    @BeforeEach
    void runBefore() {

        tag1 = new Tag("asd");
        tag11 = new Tag("asd");
        tag3 = new Tag("a");

        task0 = new Task("test ## tomorrow; in progress; important");
        task00 = new Task("test ## tomorrow; in progress; urgent");
        task1 = new Task("test ## tomorrow; tag4; tag5; tomorrow; in progress; important");
        task11 = new Task("test ## tomorrow; tag4; tag6; tomorrow; in progress; important");
        task2 = new Task("test2 ## tomorrow; tag4; tag6; tomorrow; in progress; important");
        task3 = new Task("test ## today; tag4; tag6; tomorrow; in progress; important");
        task4 = new Task("test ## tomorrow; tag4; tag5; tomorrow; todo; default");


    }

    @Test
    void testConstruct(){
        JSONObject tagJson = new JSONObject();

    }

    @Test
    void testTagToJson() {
//        JSONObject tagJson = new JSONObject();
        Task task1 = new Task("test ## tomorrow; in progress; important; urgent");
//        Task task2 = new Task("test ## tomorrow; in progress; urgent; important;");
        Task task3 = new Task("test ## tomorrow; in progress; important");
        Task task4 = new Task("test ## tomorrow; in progress; urgent");
        Task task5 = new Task("test ## tomorrow; in progress;");

        assertEquals("asd", Jsonifier.tagToJson(tag1).get("name"));

    }

    @Test
    void testPriorityToJson() {
//        JSONObject tagJson = new JSONObject();
        Task task1 = new Task("test ## tomorrow; in progress; important; urgent");
//        Task task2 = new Task("test ## tomorrow; in progress; urgent; important;");
        Task task3 = new Task("test ## tomorrow; in progress; important");
        Task task4 = new Task("test ## tomorrow; in progress; urgent");
        Task task5 = new Task("test ## tomorrow; in progress;");

        assertEquals(true, Jsonifier.priorityToJson(task1.getPriority()).get("important"));
        assertEquals(true, Jsonifier.priorityToJson(task1.getPriority()).get("urgent"));
        assertEquals(false, Jsonifier.priorityToJson(task3.getPriority()).get("urgent"));
        assertEquals(true, Jsonifier.priorityToJson(task3.getPriority()).get("important"));
        assertEquals(false, Jsonifier.priorityToJson(task5.getPriority()).get("important"));
        assertEquals(false, Jsonifier.priorityToJson(task5.getPriority()).get("urgent"));

    }

    @Test
    void testdueDateToJson() {
//        JSONObject tagJson = new JSONObject();
        Task task1 = new Task("test ## today; in progress; important; urgent");
//        Task task2 = new Task("test ## tomorrow; in progress; urgent; important;");
        Task task3 = new Task("test ## today; in progress; important");
        Task task4 = new Task("test ## today; in progress; urgent");
        Task task5 = new Task("test ## today; in progress;");
        Task taskNull = new Task("test ##  in progress;");

        assertEquals(Calendar.getInstance().get(Calendar.YEAR), Jsonifier.dueDateToJson(task1.getDueDate()).get("year"));
        assertEquals(Calendar.getInstance().get(Calendar.MONTH), Jsonifier.dueDateToJson(task1.getDueDate()).get("month"));
        assertEquals(Calendar.getInstance().get(Calendar.DATE), Jsonifier.dueDateToJson(task1.getDueDate()).get("day"));
        assertEquals(23, Jsonifier.dueDateToJson(task1.getDueDate()).get("hour"));
        assertEquals(59, Jsonifier.dueDateToJson(task1.getDueDate()).get("mintue"));


        try {
            assertEquals(null, Jsonifier.dueDateToJson(taskNull.getDueDate()).get("mintue"));
            fail();
        } catch (NullPointerException e) {
        }

        assertEquals(null, Jsonifier.dueDateToJson(null));




    }

    @Test
    void testTaskToJson() {
//        JSONObject tagJson = new JSONObject();
        Task task1 = new Task("test ## today; CPSC210; CPSC310; in progress; important; urgent");
//        Task task2 = new Task("test ## tomorrow; in progress; urgent; important;");
        Task task3 = new Task("test ## today; in progress; important");
        Task task4 = new Task("test ## today; in progress; urgent");
        Task task5 = new Task("test ## today; in progress;");
        Task taskNull = new Task("test ##  in progress;");

        JSONObject jDueDate = (JSONObject) Jsonifier.taskToJson(task1).get("due-date");
        JSONObject jPriority = (JSONObject) Jsonifier.taskToJson(task1).get("priority");
        JSONArray l = (JSONArray) Jsonifier.taskToJson(task1).get("tags");
        assertEquals(23, jDueDate.get("hour"));
        assertEquals(2, l.length());
        assertEquals("test ", Jsonifier.taskToJson(task1).get("description"));
        assertEquals(true, jPriority.get("important"));
        assertEquals("IN_PROGRESS", Jsonifier.taskToJson(task1).get("status"));


    }

    @Test
    void testTasksToJson() {
//        JSONObject tagJson = new JSONObject();
        Task task1 = new Task("test ## today; CPSC210; CPSC310; in progress; important; urgent");
//        Task task2 = new Task("test ## tomorrow; in progress; urgent; important;");
        Task task3 = new Task("test ## today; in progress; important");
        Task task4 = new Task("test ## today; in progress; urgent");
        Task task5 = new Task("test ## today; in progress;");
        Task taskNull = new Task("test ##  in progress;");

        List<Task> arrTasks = new ArrayList<>();
        arrTasks.add(task1);
        arrTasks.add(task3);
        arrTasks.add(task4);
        arrTasks.add(task5);

        assertEquals(4, Jsonifier.taskListToJson(arrTasks).length());


    }
}
