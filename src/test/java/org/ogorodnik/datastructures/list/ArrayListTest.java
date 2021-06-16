package org.ogorodnik.datastructures.list;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayListTest {
    private ArrayList listWithZeroElements;
    private ArrayList listWithFourElements;
    private ArrayList listWithFiveElements;
    private ArrayList listWithDefaultCapacity;

    @Before
    public void before(){

        listWithDefaultCapacity = new ArrayList();
        listWithZeroElements = new ArrayList(0);

        listWithFourElements = new ArrayList();
        char value = 'A';
        for (int i = 0; i < 4; i++) {
            listWithFourElements.add(value);
            value++;
        }

        listWithFiveElements = new ArrayList();
        value = 'A';
        for (int i = 0; i < 5; i++) {
            listWithFiveElements.add(value);
            value++;
        }
    }

    @Test
    public void testAddWithNoExtension(){
        char value = 'A';
        listWithDefaultCapacity.add(value);
        assertEquals(1, listWithDefaultCapacity.size());
        assertEquals('A', listWithDefaultCapacity.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddValueGreaterThenSize() {
        listWithZeroElements.add("A", 2);
    }

    @Test
    public void testAddWithExtension(){
        listWithFiveElements.add('G');
        assertEquals(6, listWithFiveElements.size());
        assertEquals(8, listWithFiveElements.getLength());
        assertEquals('G', listWithFiveElements.get(5));
        listWithZeroElements.add('G');
        assertEquals(1, listWithZeroElements.size());
        assertEquals('G', listWithZeroElements.get(0));
        listWithZeroElements.add('H');
        assertEquals(2, listWithZeroElements.size());
        assertEquals('G', listWithZeroElements.get(0));
        assertEquals('H', listWithZeroElements.get(1));
    }

    @Test
    public void testAddByIndex(){
        char value = 'H';
        listWithFourElements.add(value, 2);
        assertEquals('H', listWithFourElements.get(2));
        assertEquals('C', listWithFourElements.get(3));
        assertEquals('D', listWithFourElements.get(4));
    }

    @Test
    public void testRemove(){
        Object removed = listWithFiveElements.remove(1);
        assertEquals(4, listWithFiveElements.size());
        assertEquals('B', removed);
        assertEquals('A', listWithFiveElements.get(0));
        assertEquals('C', listWithFiveElements.get(1));
        assertEquals('D', listWithFiveElements.get(2));
        assertEquals('E', listWithFiveElements.get(3));
    }

    @Test
    public void testGet(){
        char value ='A';
        for(int i=0; i<5; i++){
            assertEquals(value, listWithFiveElements.get(i));
            value++;
        }
    }

    @Test
    public void testSet(){
        Object setter = listWithFourElements.set('K', 2);
        assertEquals(4, listWithFourElements.size());
        assertEquals('C', setter);
        assertEquals('K', listWithFourElements.get(2));
        assertEquals('D', listWithFourElements.get(3));
    }

    @Test
    public void testClear(){
        listWithFiveElements.clear();
        assertTrue(listWithFiveElements.isEmpty());
        assertEquals(0, listWithFiveElements.size());
    }

    @Test
    public void testSize(){
        assertEquals(5, listWithFiveElements.size());
    }

    @Test
    public void testIsEmpty(){
        assertTrue(listWithZeroElements.isEmpty());
    }

    @Test
    public void testContains(){
        assertTrue(listWithFiveElements.contains('B'));
    }

    @Test
    public void testIndexOf(){
        assertEquals(2, listWithFiveElements.indexOf('C'));
    }

    @Test
    public void testIndexOfNull(){
        listWithFourElements.set(null, 1);
        assertEquals(1, listWithFourElements.indexOf(null));
    }

    @Test
    public void testLastIndexOf(){
        assertEquals(2, listWithFiveElements.lastIndexOf('C'));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetValueGreaterThenSize() {
        listWithZeroElements.set("A", 2);
    }

    @Test
    public void testToString(){
        assertEquals("[A,B,C,D,E]", listWithFiveElements.toString());
    }

    @Test
    public void testIterator(){
        char value = 'A';
        for (Object element: listWithFiveElements){
            assertEquals(value, element);
            value++;
        }
    }
}
