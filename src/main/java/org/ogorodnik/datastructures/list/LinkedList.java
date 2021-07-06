package org.ogorodnik.datastructures.list;

public class LinkedList<T> extends AbstractList<T> {

    private Node<T> head;
    private Node<T> tail;

    public void add(T value, int index) {
        validateIndexForAdd(index);
        Node <T> newNode = new Node <>(value);
        if (isEmpty()) {
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
            Node <T> current = getNode(index);
            Node <T> temp = current.prev;
            newNode.prev = temp;
            newNode.next = current;
            current.prev = newNode;
            temp.next = newNode;
        }
        size++;
    }

    public T remove(int index) {
        validateIndex(index);
        T removed;
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
            Node <T> current = getNode(index);
            removed = current.value;
            Node <T> prev = current.prev;
            Node <T> next = current.next;
            prev.next = next;
            next.prev = prev;
        }
        size--;
        return removed;
    }

    private Node <T> getNode(int index) {
        Node <T> current = head.next;
        int pointer = 1;
        while (pointer < index) {
            current = current.next;
            pointer++;
        }
        return current;
    }

    public T get(int index) {
        validateIndex(index);
        if (index < size / 2) {
            Node <T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.value;
        } else {
            Node <T> current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
            return current.value;
        }
    }

    public T set(T value, int index) {
        validateIndex(index);
        Node <T> current = head;
        int pointer = 0;
        while (pointer < index) {
            current = current.next;
            pointer++;
        }
        T previous = current.value;
        current.value = value;
        return previous;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    public int indexOf(T value) {
        Node <T> current = head;
        if (value == null) {
            for (int index = 0; index < size; index++) {
                if (current.value == null) {
                    return index;
                }
                current = current.next;
            }
        } else {
            for (int index = 0; index < size; index++) {
                if (current.value.equals(value)) {
                    return index;
                }
                current = current.next;

            }
        }
        return -1;
    }

    public int lastIndexOf(T value) {
        Node <T> current = tail;
        if (value == null) {
            for (int index = size - 1; index >= 0; index--) {
                if (current.value == null) {
                    return index;
                }
                current = current.prev;
            }
        } else {
            for (int index = size - 1; index >= 0; index--) {
                if (current.value.equals(value)) {
                    return index;
                } else {
                    current = current.prev;
                }
            }
        }
        return -1;
    }

    public String toString() {
        Node <T> current = head;
        String result = "[";
        while (current != tail) {
            result += current.value;
            result += ",";
            current = current.next;
        }
        result += current.value;
        result += "]";
        return result;
    }

    private static class Node<E> {
        E value;
        Node <E> next;
        Node <E> prev;


        private Node(E value) {
            this.value = value;
        }
    }
}
