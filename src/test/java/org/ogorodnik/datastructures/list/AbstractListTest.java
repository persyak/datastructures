package org.ogorodnik.datastructures.list;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class AbstractListTest<T> {

    private AbstractList<String> listWithZeroElements;
    private AbstractList<String> listWithOneElement;
    private AbstractList<String> listWithFiveElements;

    @Before
    public void before() {
        listWithZeroElements = getList();

        listWithOneElement = getList();
        listWithOneElement.add("A");

        listWithFiveElements = getList();
        listWithFiveElements.add("A");
        listWithFiveElements.add("B");
        listWithFiveElements.add("C");
        listWithFiveElements.add("D");
        listWithFiveElements.add("E");
    }

    abstract AbstractList<String> getList();

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddValueGreaterThenSize() {
        listWithOneElement.add("A", 2);
    }

    @Test
    public void testAddByIndex() {
        String value = "H";
        listWithFiveElements.add(value, 2);
        assertEquals("H", listWithFiveElements.get(2));
        assertEquals("C", listWithFiveElements.get(3));
        assertEquals("D", listWithFiveElements.get(4));
    }

    @Test
    public void testRemove() {
        Object removed = listWithFiveElements.remove(1);
        assertEquals(4, listWithFiveElements.size());
        assertEquals("B", removed);
        assertEquals("A", listWithFiveElements.get(0));
        assertEquals("C", listWithFiveElements.get(1));
        assertEquals("D", listWithFiveElements.get(2));
        assertEquals("E", listWithFiveElements.get(3));
    }

    @Test
    public void testGet() {
        assertEquals("A", listWithFiveElements.get(0));
        assertEquals("B", listWithFiveElements.get(1));
        assertEquals("C", listWithFiveElements.get(2));
        assertEquals("D", listWithFiveElements.get(3));
        assertEquals("E", listWithFiveElements.get(4));
    }

    @Test
    public void testSet() {
        Object setter = listWithFiveElements.set("K", 2);
        assertEquals(5, listWithFiveElements.size());
        assertEquals("C", setter);
        assertEquals("K", listWithFiveElements.get(2));
        assertEquals("D", listWithFiveElements.get(3));
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
        assertTrue(listWithFiveElements.contains("B"));
    }

    @Test
    public void testIndexOf() {
        assertEquals(2, listWithFiveElements.indexOf("C"));
        listWithFiveElements.set(null, 1);
        assertEquals(1, listWithFiveElements.indexOf(null));
    }

    @Test
    public void testLastIndexOf() {
        assertEquals(2, listWithFiveElements.lastIndexOf("C"));
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
        for (String element : listWithFiveElements) {
            String string = Character.toString(value);
            assertEquals(string, element);
            value++;
        }
    }
}
