package org.ogorodnik.datastructures.list;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList implements List, Iterable{

    private final static int INITIAL_CAPACITY= 5;

    private Object[] array;
    private int size;

    ArrayList() {
        this(INITIAL_CAPACITY);
    }

    ArrayList(int initialCapacity) {
        array = new Object[initialCapacity];
    }

    public Iterator iterator(){
        return new MyIterator();
    }

    class MyIterator implements Iterator{

        int index;
        public boolean hasNext(){
            return index < size;
        }

        public Object next(){
            Object next = get(index);
            index++;
            return next;
        }

        public void remove (){
            ArrayList.this.remove(index);
        }
    }

    public void add(Object value){
        add(value, size);
    }

    public void add(Object value, int index){
            validateIndexForAdd(index);
            if (size == array.length) {
                int range = size;
                Object[] extendedArray = new Object[(int) (1.5 * range)];
                for (int i = 0; i < array.length; i++) {
                    extendedArray[i] = array[i];
                }
                array = extendedArray;
            }
            array[size] = value;
            size++;
            for (int i = size - 1; i > index; i--) {
                Object temp;
                temp = array[i - 1];
                array[i - 1] = array[i];
                array[i] = temp;
            }
    }

    public Object remove(int index){
            validateIndex(index);
            Object removed = array[index];
            for (int i = index; i < size - 1; i++) {
                array[i] = array[i + 1];
            }
            size--;
            return removed;
    }

    public Object get(int index){
        validateIndex(index);
        return array[index];
    }

    public Object set(Object value, int index){
        validateIndex(index);
        array[index] = value;
        return array[index];
    }

    public void clear(){
        for(int i=0; i<size; i++){
            array[i] = null;
        }
        size = 0;
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
        for(int i=0; i<size; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;

    }

    public int lastIndexOf(Object value){
        for(int i=(size-1); i>=0; i--){
            if(array[i].equals(value)){
                return i;
            }
        }
        return -1;
    }

    private void validateIndex(int index){
            if (index < 0 || index >= size){
                throw new IndexOutOfBoundsException("LinkedList size is " + size +
                        ". Error: your index is bigger or equal than size or less than ZERO");
            }
    }

    private void validateIndexForAdd(int index){
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException("LinkedList size is " + size +
                        ". Error: your index is bigger than size or less than ZERO");
            }
    }

    int getLength(){
        return array.length;
    }

//    @Override
    public String toString() {
        Object[] completedlist = new Object[size];
        System.arraycopy(array, 0, completedlist, 0, size);
        return Arrays.toString(completedlist);
    }
}
