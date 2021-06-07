package org.ogorodnik.datastructures.list;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListTest {

    private LinkedList listWithZeroElements;
    private LinkedList listWithOneElement;
    private LinkedList listWithFourElements;

    @Before
    public void before() {
        listWithZeroElements = new LinkedList();

        listWithOneElement = new LinkedList();
        char value = 'A';
        listWithOneElement.add(value);

        listWithFourElements = new LinkedList();
        value = 'A';
        for (int i = 0; i < 4; i++) {
            listWithFourElements.add(value);
            value++;
        }
    }

        @Test
        public void testAddWithZeroElements(){
            char value = 'A';
            listWithZeroElements.add(value);
            assertEquals(1, listWithZeroElements.size());
            assertEquals('A', listWithZeroElements.get(0));
        }

        @Test
        public void testAddAfterTail(){
            char value = 'E';
            listWithFourElements.add(value);
            assertEquals(5, listWithFourElements.size());
            assertEquals('E', listWithFourElements.get(4));
            assertEquals('D', listWithFourElements.get(3));
        }

        @Test
        public void testAddBeforeHead(){
            char value = 'E';
            listWithFourElements.add(value, 0);
            assertEquals(5, listWithFourElements.size());
            assertEquals('E', listWithFourElements.get(0));
            assertEquals('A', listWithFourElements.get(1));
        }

        @Test
        public void testAddByIndex(){
            char value = 'E';
            listWithFourElements.add(value, 2);
            assertEquals(5, listWithFourElements.size());
            assertEquals('E', listWithFourElements.get(2));
            assertEquals('B', listWithFourElements.get(1));
            assertEquals('C', listWithFourElements.get(3));
        }

        @Test
        public void testRemoveListWithOneElement(){
        Object removed = listWithOneElement.remove(0);
        assertEquals(0, listWithOneElement.size());
        assertEquals('A',removed);
        }

        @Test
        public void testRemoveHeadForListWithMoreThanOneElement(){
        Object removed = listWithFourElements.remove(0);
        assertEquals(3, listWithFourElements.size());
        assertEquals('A', removed);
        assertEquals('B', listWithFourElements.get(0));
        }

        @Test
        public void testRemoveTailForListWithMoreThanOneElement(){
        Object removed = listWithFourElements.remove(3);
        assertEquals(3, listWithFourElements.size());
        assertEquals('D', removed);
        assertEquals('C', listWithFourElements.get(2));
        }

        @Test
        public void testRemoveElementByIndex(){
        Object removed = listWithFourElements.remove(2);
        assertEquals(3, listWithFourElements.size());
        assertEquals('C', removed);
        assertEquals('B', listWithFourElements.get(1));
        assertEquals('D', listWithFourElements.get(2));
        }

        @Test
        public void testGet(){
            char value = 'A';
            for(int i=0; i<4; i++){
                listWithFourElements.get(i);
                assertEquals(value, listWithFourElements.get(i));
                value++;
            }
        }

        @Test
        public  void testSet(){
            Object setter = listWithFourElements.set('E', 2);
            assertEquals(4, listWithFourElements.size());
            assertEquals('E', setter);
        }

        @Test
        public void testClear(){
        listWithFourElements.clear();
        assertTrue(listWithFourElements.isEmpty());
        assertEquals(0, listWithFourElements.size());
        }

        @Test
    public void testSize(){
        assertEquals(4, listWithFourElements.size());
        }

        @Test
    public void testIsEmpty(){
        assertTrue(listWithZeroElements.isEmpty());
        }

        @Test
    public void testContains(){
        assertTrue(listWithFourElements.contains('B'));
        }

        @Test
    public void testIndexOf(){
        assertEquals(1, listWithFourElements.indexOf('B'));
        }

        @Test
    public void testLastIndexOf(){
        assertEquals(2, listWithFourElements.lastIndexOf('C'));
        }

        @Test(expected = IndexOutOfBoundsException.class)
    public void testSetValueGreaterThenSize() {
        listWithZeroElements.set("A", 2);
    }

        @Test
    public void testToString(){
        assertEquals("[A,B,C,D]", listWithFourElements.toString());
    }

    @Test
    public void testIterator(){
        char value = 'A';
        for (Object element: listWithFourElements){
            assertEquals(value, element);
            value++;
        }
    }
}


