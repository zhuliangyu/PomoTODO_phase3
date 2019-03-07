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


    private TaskParser tp;

    @BeforeEach
    void runBefore() {
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
    void testTaskParser() {

        String input1 = "[{\n" +
                "\"description\":\"Register for courses. \",\n" +
                "\"tags\":[{\"name\":\"cpsc221\"},{\"name\":\"cpsc213\"}],\n" +
                "\"due-date\":{\"year\":2019,\"month\":1,\"day\":16,\"hour\":23,\"minute\":59},\n" +
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

    @Test
    void testTaskParserW1() {

        String input1 = "[{}," +
                "{\n" +
                "  \"description\":\"Download the syllabus. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":null,\n" +
                "  \"priority\":{\"important\":false,\"urgent\":false},\n" +
                "  \"status\":\"UP_NEXT\"\n" + "}" +
                ",{\n" +
                "  \"description\":\"Register for the course. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":{\"year\":abc,\"month\":0,\"day\":16,\"hour\":23,\"minute\":59},\n" +
                "  \"priority\":{\"important\":true,\"urgent\":true},\n" +
                "  \"status\":\"IN_PROGRESS\"\n" +
                "}]";
        List<Task> listOfTask = tp.parse(input1);

//        assertEquals("Register for courses. ", listOfTask.get(0).getDescription());
//        assertEquals("[#cpsc221, #cpsc213]", listOfTask.get(0).getTags().toString());
//        assertEquals("Wed Jan 16 2019 11:59 PM", listOfTask.get(0).getDueDate().toString());
//        assertEquals("DEFAULT", listOfTask.get(0).getPriority().toString());
//        assertEquals("IN PROGRESS", listOfTask.get(0).getStatus().toString());

    }

    @Test
    void testTaskParserW2() {

        String input1 = "[{\n" +
                "  \"description\":\"Download the syllabus. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":null,\n" +
                "  \"priority\":{\"important\":false,\"urgent\":false},\n" +
                "  \"status\":\"UP_NEXT\"\n" + "}" +
                ",{\n" +
                "  \"description\":\"Register for the course. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":{\"year\":1998,\"month\":1,\"day\":16,\"hour\":23,\"minute\":59},\n" +
                "  \"priority\":{\"important\":true,\"urgent\":true},\n" +
                "  \"status\":\"IN_PROGRESS\"\n" +
                "}]";
        List<Task> listOfTask = tp.parse(input1);
        assertEquals(2, listOfTask.size());

//        assertEquals("Register for courses. ", listOfTask.get(0).getDescription());
//        assertEquals("[#cpsc221, #cpsc213]", listOfTask.get(0).getTags().toString());
//        assertEquals("Wed Jan 16 2019 11:59 PM", listOfTask.get(0).getDueDate().toString());
//        assertEquals("DEFAULT", listOfTask.get(0).getPriority().toString());
//        assertEquals("IN PROGRESS", listOfTask.get(0).getStatus().toString());

    }

    @Test
    void testTaskParserW3() {

        String input1 = "[{\n" +
                "  \"descriptionaa\":\"Download the syllabus. \",\n" +
                "  \"tagsaa\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-dateaa\":null,\n" +
                "  \"priorityaa\":{\"important\":false,\"urgent\":false},\n" +
                "  \"statusaa\":\"UP_NEXT\"\n" + "}" +
                ",{\n" +
                "  \"description\":\"Register for the course. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":{\"year\":1998,\"month\":0,\"day\":16,\"hour\":23,\"minute\":59},\n" +
                "  \"priority\":{\"important\":true,\"urgent\":true},\n" +
                "  \"status\":\"IN_PROGRESS\"\n" +
                "}]";
        List<Task> listOfTask = tp.parse(input1);
        assertEquals(0, listOfTask.size());

//        assertEquals("Register for courses. ", listOfTask.get(0).getDescription());
//        assertEquals("[#cpsc221, #cpsc213]", listOfTask.get(0).getTags().toString());
//        assertEquals("Wed Jan 16 2019 11:59 PM", listOfTask.get(0).getDueDate().toString());
//        assertEquals("DEFAULT", listOfTask.get(0).getPriority().toString());
//        assertEquals("IN PROGRESS", listOfTask.get(0).getStatus().toString());

    }

    @Test
    void testTaskParserW4() {

        String input1 = "[{\n" +
                "  \"description\":\"Download the syllabus. \",\n" +
                "  \"tags\":[{\"namejj\":\"cpsc210\"}],\n" +
                "  \"due-date\":{\"year\":1999,\"month\":0,\"day\":16,\"hour\":23,\"minute\":59},\n" +
                "  \"priority\":{\"important\":false,\"urgent\":false},\n" +
                "  \"status\":\"UP_NEXT\"\n" + "}" +
                ",{\n" +
                "  \"description\":\"Register for the course. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":{\"year\":1998,\"month\":1,\"day\":16,\"hour\":23,\"minute\":59},\n" +
                "  \"priority\":{\"important\":true,\"urgent\":true},\n" +
                "  \"status\":\"IN_PROGRESS\"\n" +
                "}]";
        List<Task> listOfTask = tp.parse(input1);
        assertEquals(1, listOfTask.size());

//        assertEquals("Register for courses. ", listOfTask.get(0).getDescription());
//        assertEquals("[#cpsc221, #cpsc213]", listOfTask.get(0).getTags().toString());
//        assertEquals("Wed Jan 16 2019 11:59 PM", listOfTask.get(0).getDueDate().toString());
//        assertEquals("DEFAULT", listOfTask.get(0).getPriority().toString());
//        assertEquals("IN PROGRESS", listOfTask.get(0).getStatus().toString());

    }

    @Test
    void testTaskParserBase() {
        String input1 = "[{\n" +
                "  \"description\":\"Register for the course. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":{\"year\":2019,\"month\":2,\"day\":28,\"hour\":23,\"minute\":59},\n" +
                "  \"priority\":{\"important\":false,\"urgent\":true},\n" +
                "  \"status\":\"IN_PROGRESS\"\n" +
                "},{\n" +
                "  \"description\":\"Register for the course. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":{\"year\":19,\"month\":1,\"day\":16,\"hour\":23,\"minute\":59},\n" +
                "  \"priority\":{\"important\":true,\"urgent\":true},\n" +
                "  \"status\":\"IN_PROGRESS\"\n" +
                "},{\n" +
                "  \"description\":\"Register for the course. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":{\"year\":2019,\"month\":1,\"day\":16,\"hour\":23,\"minute\":59},\n" +
                "  \"priority\":{\"important\":true,\"urgent\":true},\n" +
                "  \"status\":\"IN_PROGRESS\"\n" +
                "}]";
        List<Task> listOfTask = tp.parse(input1);
        assertEquals(3, listOfTask.size());
    }

    @Test
    void testTaskParserW6() {
        String input1 = "[{\n" +
                "  \"description\":\"Register for the course. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":{\"year\":2019,\"month\":0,\"day\":33,\"hour\":25,\"minute\":59},\n" +
                "  \"priority\":{\"important\":false,\"urgent\":true},\n" +
                "  \"status\":\"IN_PROGRESSss\"\n" +
                "},{\n" +
                "  \"description\":\"Register for the course. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":{\"year\":19,\"month\":1,\"day\":16,\"hour\":23,\"minute\":59},\n" +
                "  \"priority\":{\"important\":true,\"urgent\":true},\n" +
                "  \"status\":\"IN_PROGRESS\"\n" +
                "},{\n" +
                "  \"description\":\"Register for the course. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":{\"year\":2019,\"month\":1,\"day\":16,\"hour\":23,\"minute\":59},\n" +
                "  \"priority\":{\"important\":true,\"urgent\":true},\n" +
                "  \"status\":\"IN_PROGRESS\"\n" +
                "}]";
        List<Task> listOfTask = tp.parse(input1);
        assertEquals(2, listOfTask.size());
    }

    @Test
    void testTaskParserW7() {
        String input1 = "[{\n" +
                "  \"description\":\"Register for the course. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":{\"year\":2019,\"month\":1,\"day\":15,\"hour\":23,\"minute\":59},\n" +
                "  \"priority\":{\"important\":false,\"urgent\":true},\n" +
                "  \"status\":\"TODO\"\n" +
                "},{\n" +
                "  \"description\":\"Register for the course. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":{\"year\":19,\"month\":1,\"day\":16,\"hour\":23,\"minute\":59},\n" +
                "  \"priority\":{\"important\":true,\"urgent\":true},\n" +
                "  \"status\":\"UP_NEXT\"\n" +
                "},{\n" +
                "  \"description\":\"Register for the course. \",\n" +
                "  \"tags\":[{\"name\":\"cpsc210\"}],\n" +
                "  \"due-date\":{\"year\":2019,\"month\":1,\"day\":16,\"hour\":23,\"minute\":59},\n" +
                "  \"priority\":{\"important\":true,\"urgent\":true},\n" +
                "  \"status\":\"DONE\"\n" +
                "}]";
        List<Task> listOfTask = tp.parse(input1);
        assertEquals(3, listOfTask.size());
    }

}
