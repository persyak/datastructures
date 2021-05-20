package org.ogorodnik.datastructures.list;

import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;

public class LinkedList implements List{

    private Node head;
    private Node tail;
    private int size;

    public void add(Object value){
        add(value, size);
    }

    public void add(Object value, int index){
        try {
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
        catch(IndexOutOfBoundsException e){
            System.out.println("LinkedList size is " + size +
                    ". Error: your index is bigger than size or less than ZERO");
        }
    }

    public Object remove(int index){
        try {
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
        catch(IndexOutOfBoundsException e){
            System.out.println("LinkedList size is " + size +
                    ". Error: your index is bigger or equal than size or less than ZERO");
        }
        return null;
    }

    public Object get(int index){
        try {
            Node current = head;
            validateIndex(index);
            int pointer = 0;
            while (pointer < index) {
                current = current.next;
                pointer++;
            }
            return current.value;
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("LinkedList size is " + size +
                    ". Error: your index is bigger or equal than size or less than ZERO");
        }
        return null;
    }

    public Object set(Object value, int index){
        try {
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
        catch(IndexOutOfBoundsException e){
            System.out.println("LinkedList size is " + size +
                    ". Error: your index is bigger or equal than size or less than ZERO");
        }
        return null;
    }

    public void clear(){
        if(isEmpty()){
            System.out.println("LinkedList is empty");
        }
        else{
            while (head != tail) {
                head = head.next;
                head.prev = null;
            }
            head = null;
            tail = null;
            size = 0;
        }
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean contains(Object value){
        return indexOf(value) !=-1;
    }

    public int indexOf(Object value){
        if(isEmpty()){
            System.out.println("LinkedList is empty");
        }
        else{
            int index = 0;
            Node current = head;
            while(current.next != null){
//TODO: лучше на индекс ориентироваться, или на следующий элемент, когда итерируемся по листу?
// если количество элементов соответствует size, то должно быть без разницы;
                if (current.value.equals(value)){
                    return index;
                } else{
                    current = current.next;
                    index ++;
                }
            }
        }
        return -1;
    }

    public int lastIndexOf(Object value){
        if(isEmpty()){
            System.out.println("LinkedList is empty");
        }
        else{
            int index = size-1;
            Node current = tail;
            while(index>=0){
                if (current.value.equals(value)){
                    return index;
                } else{
                    current = current.prev;
                    index --;
                }
            }
        }
        return -1;
    }

    public String toString(){
        Object[] completedlist = new Object[size];
        Node current = head;
        int index = 0;
        while(index < size){
            completedlist[index] = current.value;
            current = current.next;
            index++;
        }
        return Arrays.toString(completedlist);
    }

    class Node{
        Object value;
        Node next;
        Node prev;

        public Node(Object value){
            this.value = value;
        }
    }

    private void validateIndex(int index){
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }
    }

    private void validateIndexForAdd(int index){
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException();
        }
    }
}
