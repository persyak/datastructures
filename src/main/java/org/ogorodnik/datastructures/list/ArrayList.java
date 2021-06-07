package org.ogorodnik.datastructures.list;

import java.util.Arrays;

public class ArrayList extends AbstractList {

    private final static int INITIAL_CAPACITY= 5;

    private Object[] array;

    ArrayList() {
        this(INITIAL_CAPACITY);
    }

    ArrayList(int initialCapacity) {
        array = new Object[initialCapacity];
    }

    public void add(Object value, int index) {
        validateIndexForAdd(index);
        if (size == array.length) {
            Object[] extendedArray = new Object[(int) (1.5 * size)];
            int a = 0;
            System.arraycopy(array, a, extendedArray, a, array.length);
            array = extendedArray;
        }
        for (int i = size; i > index; i--) {
            array[i] = array[i-1];
        }
        array[index] = value;
        size++;
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

    int getLength(){
        return array.length;
    }

//    @Override
    public String toString() {
        String result = "[";
        for(int i=0; i<size-1; i++){
            result+=array[i];
            result+=",";
        }
        result+=array[size-1];
        result+="]";
        return result;
    }
}
