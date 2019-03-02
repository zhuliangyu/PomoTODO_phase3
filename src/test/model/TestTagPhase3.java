package model;

import model.exceptions.EmptyStringException;
import model.exceptions.NullArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestTagPhase3 {
    // TODO: design tests for new behaviour added to Tag class

    public Tag t1Empty;
    public Tag t2Empty;
    public Tag tag1;
    public Tag tag11;
    public Tag tag3;
    public Task task0;
    public Task task1;
    public Task task11;
    public Task task2;
    public Task task3;
    public Task task4;

    @BeforeEach
    void runBefore(){
        tag1 = new Tag("asd");
        tag11 = new Tag("asd");
        tag3 = new Tag("a");

        task0 = new Task("test ## tomorrow; in progress; important");
        task1 = new Task("test ## tomorrow; tag4; tag5; tomorrow; in progress; important");
        task11 = new Task("test ## tomorrow; tag4; tag6; tomorrow; in progress; important");
        task2 = new Task("test2 ## tomorrow; tag4; tag6; tomorrow; in progress; important");
        task3 = new Task("test ## today; tag4; tag6; tomorrow; in progress; important");
        task4 = new Task("test ## tomorrow; tag4; tag5; tomorrow; todo; default");

    }

    @Test
    void testEqualWithTwoEmpty(){
        try {
            t1Empty = new Tag("");
            t2Empty = new Tag("");
            assertTrue(t1Empty.equals(t2Empty));
            fail();
        } catch (EmptyStringException e) {

        }

    }
    @Test
    void testEqualWithONeEmpty(){
        try {
            t1Empty = new Tag("");
            assertFalse(t1Empty.equals(tag1));
            fail();
        } catch (EmptyStringException e) {

        }

    }

    @Test
    void testEqualWithTheSame(){
        try {
            assertTrue(tag1.equals(tag11));
        } catch (EmptyStringException e) {
            fail();
        }

    }

    @Test
    void testEqualWithDiff(){
        try {
            assertFalse(tag1.equals(tag3));
        } catch (EmptyStringException e) {
            fail();
        }

    }

    @Test
    void testAddTagOneTask(){
        tag1.addTask(task1);
        assertTrue(task1.getTags().contains(tag1));
        assertTrue(tag1.getTasks().contains(task1));
    }

    @Test
    void testAddTagTwoTask(){
        tag1.addTask(task1);
        tag1.addTask(task2);
        assertTrue(task1.getTags().contains(tag1));
        assertTrue(task1.getTags().contains(tag11));
        assertFalse(task1.getTags().contains(tag3));
        assertTrue(tag1.getTasks().contains(task1));
        assertTrue(tag1.getTasks().contains(task2));
        assertFalse(tag1.getTasks().contains(task3));
    }
    @Test
    void testAddTagTwoTask2(){
        tag1.addTask(task0);
        tag11.addTask(task0);
        tag1.addTask(task0);
        assertEquals(1, task0.getTags().size());

    }

    @Test
    void testAddTagNull(){
        try {
            tag1.addTask(null);
            fail();
        } catch (NullArgumentException e) {
        }

    }

    @Test
    void testAddTagString(){
        try {
            tag1.addTask(null);
            fail();
        } catch (NullArgumentException e) {
        }

    }

    @Test
    void testRemoveTask(){
        tag1.addTask(task0);
        assertEquals(1,tag1.getTasks().size());

        tag1.removeTask(task0);
        assertEquals(0, tag1.getTasks().size());
        assertEquals(0, task0.getTags().size());

    }
    @Test
    void testRemoveTaskNULL(){

        try {
            tag1.removeTask(null);
            fail();
        } catch (NullArgumentException e) {

        }


    }





}
