package parsers;

import model.Tag;
import model.Task;
import model.exceptions.EmptyStringException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Jsonifier;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import java.util.List;

public class TestTaskParser {
    // TODO: design tests for TaskParser class

//    public Tag t1Empty;
//    public Tag t2Empty;
//    public Tag tag1;
//    public Tag tag11;
//    public Tag tag3;
//    public Task task0;
//    public Task task1;
//    public Task task11;
//    public Task task2;
//    public Task task3;
//    public Task task4;

    private TaskParser tp;

    @BeforeEach
    void runBefore(){
//
//        tag1 = new Tag("asd");
//        tag11 = new Tag("asd");
//        tag3 = new Tag("a");
//
//        task0 = new Task("test ## tomorrow; in progress; important");
//        task1 = new Task("test ## tomorrow; tag4; tag5; tomorrow; in progress; important");
//        task11 = new Task("test ## tomorrow; tag4; tag6; tomorrow; in progress; important");
//        task2 = new Task("test2 ## tomorrow; tag4; tag6; tomorrow; in progress; important");
//        task3 = new Task("test ## today; tag4; tag6; tomorrow; in progress; important");
//        task4 = new Task("test ## tomorrow; tag4; tag5; tomorrow; todo; default");
//
        tp = new TaskParser();



//
    }
//
    @Test
    void testTaskParser(){

        String input1 = "[{\n" +
                "\"description\":\"Register for courses. \",\n" +
                "\"tags\":[{\"name\":\"cpsc221\"},{\"name\":\"cpsc213\"}],\n" +
                "\"due-date\":{\"year\":2019,\"month\":0,\"day\":16,\"hour\":23,\"minute\":59},\n" +
                "\"priority\":{\"important\":false,\"urgent\":false},\n" +
                "\"status\":\"IN_PROGRESS\"\n" +
                "}]";
       List<Task> listOfTask = tp.parse(input1);

       assertEquals("Register for courses. ", listOfTask.get(0).getDescription());
       assertEquals("[#cpsc221, #cpsc213]", listOfTask.get(0).getTags().toString());
       assertEquals("Wed Jan 16 2019 11:59 PM", listOfTask.get(0).getDueDate().toString());
       assertEquals("DEFAULT", listOfTask.get(0).getPriority().toString());
       assertEquals("IN PROGRESS", listOfTask.get(0).getStatus().toString());


    }

}
