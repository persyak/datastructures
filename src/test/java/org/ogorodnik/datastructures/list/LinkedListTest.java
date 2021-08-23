package org.ogorodnik.datastructures.list;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListTest<T> extends AbstractListTest<T> {

    @Override
    AbstractList<String> getList() {
        return new LinkedList<>();
    }

    @Test
    public void testAddWithZeroElements() {
        LinkedList<String> linkedlist = new LinkedList<>();
        linkedlist.add("A");
        assertEquals(1, linkedlist.size());
        assertEquals("A", linkedlist.get(0));
    }

    @Test
    public void testAddAfterTail() {
        LinkedList<String> linkedlist = new LinkedList<>();
        linkedlist.add("A");
        linkedlist.add("B");
        linkedlist.add("C");
        assertEquals(3, linkedlist.size());
        assertEquals("B", linkedlist.get(1));
        assertEquals("C", linkedlist.get(2));
    }

    @Test
    public void testAddBeforeHead() {
        LinkedList<String> linkedlist = new LinkedList<>();
        linkedlist.add("A");
        linkedlist.add("B");
        linkedlist.add("C");
        linkedlist.add("D", 0);
        assertEquals(4, linkedlist.size());
        assertEquals("D", linkedlist.get(0));
        assertEquals("A", linkedlist.get(1));
    }

    @Test
    public void testRemoveListWithOneElement() {
        LinkedList<String> linkedlist = new LinkedList<>();
        linkedlist.add("A");
        Object removed = linkedlist.remove(0);
        assertEquals(0, linkedlist.size());
        assertEquals("A", removed);
    }

    @Test
    public void testRemoveHeadForListWithMoreThanOneElement() {
        LinkedList<String> linkedlist = new LinkedList<>();
        linkedlist.add("A");
        linkedlist.add("B");
        linkedlist.add("C");
        Object removed = linkedlist.remove(0);
        assertEquals(2, linkedlist.size());
        assertEquals("A", removed);
        assertEquals("B", linkedlist.get(0));
    }

    @Test
    public void testRemoveTailForListWithMoreThanOneElement() {
        LinkedList<String> linkedlist = new LinkedList<>();
        linkedlist.add("A");
        linkedlist.add("B");
        linkedlist.add("C");
        Object removed = linkedlist.remove(2);
        assertEquals(2, linkedlist.size());
        assertEquals("C", removed);
        assertEquals("B", linkedlist.get(1));
    }
}


