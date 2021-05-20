package org.ogorodnik.datastructures.list;

import java.util.Arrays;

public class ArrayList implements List{

    private final static int INITIAL_CAPACITY= 5;

    private Object[] array;
    private int size;

    ArrayList() {
        this(INITIAL_CAPACITY);
    }

    ArrayList(int initialCapacity) {
        array = new Object[initialCapacity];
    }

    public void add(Object value){
        add(value, size);
    }

    public void add(Object value, int index){
        try {
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
        catch(IndexOutOfBoundsException e){
            System.out.println("ArrayList size is " + size +
                    ". Error: your index is bigger than size or less than ZERO");
        }
    }

    public Object remove(int index){
        try {
            validateIndex(index);
            Object removed = array[index];
            for (int i = index; i < size - 1; i++) {
                array[i] = array[i + 1];
            }
            size--;
            return removed;
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("ArrayList size is " + size +
                    ". Error: your index is bigger or equal than size or less than ZERO");
        }
        return null;
    }

    public Object get(int index){
        try {
            validateIndex(index);
            return array[index];
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("ArrayList size is " + size +
                    ". Error: your index is bigger or equal than size or less than ZERO");
        }
        return null;
    }

    public Object set(Object value, int index){
        try {
            validateIndex(index);
            array[index] = value;
            return array[index];
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("ArrayList size is " + size +
                    ". Error: your index is bigger or equal than size or less than ZERO");
        }
        return null;
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
                throw new IndexOutOfBoundsException();
            }
    }

    private void validateIndexForAdd(int index){
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException();
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
