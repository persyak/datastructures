package org.ogorodnik.datastructures.list;

import java.util.Arrays;

public class LinkedList extends AbstractList {

    private Node head;
    private Node tail;

    public void add(Object value, int index) {
        validateIndexForAdd(index);
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else if (index == 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else if (index == size) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else {
            Node current = head.next;
            int pointer = 1;
            while (pointer < index) {
                current = current.next;
                pointer++;
            }
            Node temp = current.prev;
            newNode.prev = temp;
            newNode.next = current;
            current.prev = newNode;
            temp.next = newNode;
        }
        size++;
    }

    public Object remove(int index) {
        validateIndex(index);
        Object removed;
        if (head == tail) {
            removed = head.value;
            head = null;
            tail = null;
        } else if (index == 0) {
            removed = head.value;
            head = head.next;
            head.prev = null;
        } else if (index == size - 1) {
            removed = tail.value;
            tail = tail.prev;
            tail.next = null;
        } else {
            Node current = head.next;
            int pointer = 1;
            while (pointer < index) {
                current = current.next;
                pointer++;
            }
            removed = current.value;
            Node prev = current.prev;
            Node next = current.next;
            prev.next = next;
            next.prev = prev;
        }
        size--;
        return removed;
    }

    public Object get(int index) {
        Node current = head;
        validateIndex(index);
        int pointer = 0;
        while (pointer < index) {
            current = current.next;
            pointer++;
        }
        return current.value;
    }

    public Object set(Object value, int index) {
        validateIndex(index);
        Node current = head;
        int pointer = 0;
        while (pointer < index) {
            current = current.next;
            pointer++;
        }
        current.value = value;
        return current.value;
    }

    public void clear() {
        if (isEmpty()) {
            System.out.println("LinkedList is empty");
        } else {
            while (head != tail) {
                head = head.next;
                head.prev = null;
            }
            head = null;
            tail = null;
            size = 0;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object value) {
        return indexOf(value) != -1;
    }

    public int indexOf(Object value) {
        int index = 0;
        Node current = head;
        while (current.next != null) {
            if (current.value.equals(value)) {
                return index;
            } else {
                current = current.next;
                index++;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object value) {
        int index = size - 1;
        Node current = tail;
        while (index >= 0) {
            if (current.value.equals(value)) {
                return index;
            } else {
                current = current.prev;
                index--;
            }
        }
        return -1;
    }

    public String toString() {
        Node current = head;
        String result = "[";
        while (current != tail) {
            result += current.value;
            result += ",";
            current = current.next;
        }
        result += current.value;
        result +="]";
        return result;
    }

    class Node {
        Object value;
        Node next;
        Node prev;

        public Node(Object value) {
            this.value = value;
        }
    }
}
