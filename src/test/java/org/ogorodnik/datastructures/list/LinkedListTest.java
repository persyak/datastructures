package org.ogorodnik.datastructures.list;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListTest {

    private LinkedList listWithZeroElements;
    private LinkedList listWithOneElement;
    private LinkedList listWithFiveElements;

    @Before
    public void before() {
        listWithZeroElements = new LinkedList();

        listWithOneElement = new LinkedList();
        char value = 'A';
        listWithOneElement.add(value);

        listWithFiveElements = new LinkedList();
        value = 'A';
        for (int i = 0; i < 5; i++) {
            listWithFiveElements.add(value);
            value++;
        }
    }

    @Test
    public void testAddWithZeroElements() {
        char value = 'A';
        listWithZeroElements.add(value);
        assertEquals(1, listWithZeroElements.size());
        assertEquals('A', listWithZeroElements.get(0));
    }

    @Test
    public void testAddAfterTail() {
        char value = 'F';
        listWithFiveElements.add(value);
        assertEquals(6, listWithFiveElements.size());
        assertEquals('F', listWithFiveElements.get(5));
        assertEquals('E', listWithFiveElements.get(4));
    }

    @Test
    public void testAddBeforeHead() {
        char value = 'E';
        listWithFiveElements.add(value, 0);
        assertEquals(6, listWithFiveElements.size());
        assertEquals('E', listWithFiveElements.get(0));
        assertEquals('A', listWithFiveElements.get(1));
    }

    @Test
    public void testAddByIndex() {
        char value = 'E';
        listWithFiveElements.add(value, 2);
        assertEquals(6, listWithFiveElements.size());
        assertEquals('E', listWithFiveElements.get(2));
        assertEquals('B', listWithFiveElements.get(1));
        assertEquals('C', listWithFiveElements.get(3));
    }

    @Test
    public void testRemoveListWithOneElement() {
        Object removed = listWithOneElement.remove(0);
        assertEquals(0, listWithOneElement.size());
        assertEquals('A', removed);
    }

    @Test
    public void testRemoveHeadForListWithMoreThanOneElement() {
        Object removed = listWithFiveElements.remove(0);
        assertEquals(4, listWithFiveElements.size());
        assertEquals('A', removed);
        assertEquals('B', listWithFiveElements.get(0));
    }

    @Test
    public void testRemoveTailForListWithMoreThanOneElement() {
        Object removed = listWithFiveElements.remove(4);
        assertEquals(4, listWithFiveElements.size());
        assertEquals('E', removed);
        assertEquals('D', listWithFiveElements.get(3));
    }

    @Test
    public void testRemoveElementByIndex() {
        Object removed = listWithFiveElements.remove(2);
        assertEquals(4, listWithFiveElements.size());
        assertEquals('C', removed);
        assertEquals('B', listWithFiveElements.get(1));
        assertEquals('D', listWithFiveElements.get(2));
    }

    @Test
    public void testGet() {
        char value = 'A';
        for (int i = 0; i < 5; i++) {
            listWithFiveElements.get(i);
            assertEquals(value, listWithFiveElements.get(i));
            value++;
        }
    }

    @Test
    public void testSet() {
        Object setter = listWithFiveElements.set('E', 2);
        assertEquals(5, listWithFiveElements.size());
        assertEquals('E', listWithFiveElements.get(2));
        assertEquals('C', setter);
    }

    @Test
    public void testClear() {
        listWithFiveElements.clear();
        assertTrue(listWithFiveElements.isEmpty());
        assertEquals(0, listWithFiveElements.size());
    }

    @Test
    public void testSize() {
        assertEquals(5, listWithFiveElements.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(listWithZeroElements.isEmpty());
    }

    @Test
    public void testContains() {
        assertTrue(listWithFiveElements.contains('B'));
    }

    @Test
    public void testIndexOf() {
        assertEquals(1, listWithFiveElements.indexOf('B'));
        listWithFiveElements.set(null, 2);
        assertEquals(2, listWithFiveElements.indexOf(null));
    }

    @Test
    public void testLastIndexOf() {
        assertEquals(2, listWithFiveElements.lastIndexOf('C'));
        listWithFiveElements.set(null, 1);
        assertEquals(1, listWithFiveElements.lastIndexOf(null));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetValueGreaterThenSize() {
        listWithZeroElements.set("A", 2);
    }

    @Test
    public void testToString() {
        assertEquals("[A,B,C,D,E]", listWithFiveElements.toString());
    }

    @Test
    public void testIterator() {
        char value = 'A';
        for (Object element : listWithFiveElements) {
            assertEquals(value, element);
            value++;
        }
    }
}


