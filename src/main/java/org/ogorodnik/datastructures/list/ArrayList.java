package org.ogorodnik.datastructures.list;

public class ArrayList extends AbstractList {

    private final static int INITIAL_CAPACITY = 5;

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
            Object[] extendedArray = new Object[(int) (1.5 * size + 1)];
            System.arraycopy(array, 0, extendedArray, 0, array.length);
            array = extendedArray;
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    public Object remove(int index) {
        validateIndex(index);
        Object removed = array[index];
        System.arraycopy(array, index + 1, array, index, size - 1 - index);
        array[size - 1] = null;
        size--;
        return removed;
    }

    public Object get(int index) {
        validateIndex(index);
        return array[index];
    }

    public Object set(Object value, int index) {
        validateIndex(index);
        Object previous = array[index];
        array[index] = value;
        return previous;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
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
        if (value == null) {
            for (int index = 0; index < size; index++) {
                if (array[index] == null) {
                    return index;
                }
            }
        } else {
            for (int index = 0; index < size; index++) {
                if (array[index].equals(value)) {
                    return index;
                }
            }
        }
        return -1;
    }

    public int lastIndexOf(Object value) {
        if (value == null) {
            for (int index = (size - 1); index >= 0; index--) {
                if (array[index] == null) {
                    return index;
                }
            }
        } else {
            for (int index = (size - 1); index >= 0; index--) {
                if (array[index].equals(value)) {
                    return index;
                }
            }
        }
        return -1;
    }

    int getLength() {
        return array.length;
    }

    //    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i < size - 1; i++) {
            result += array[i];
            result += ",";
        }
        result += array[size - 1];
        result += "]";
        return result;
    }
}
