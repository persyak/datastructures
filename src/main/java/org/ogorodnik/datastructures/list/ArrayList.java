package org.ogorodnik.datastructures.list;

public class ArrayList <T> extends AbstractList <T> {

    private final static int INITIAL_CAPACITY = 5;

    private Object[] array;

    public ArrayList() {
        this(INITIAL_CAPACITY);
    }

    ArrayList (int initialCapacity) {
        this.array = new Object [initialCapacity];
    }

    public void add(T value, int index) {
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

    public T remove(int index) {
        validateIndex(index);
        T removed = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - 1 - index);
        array[size - 1] = null;
        size--;
        return removed;
    }

    public T get(int index) {
        validateIndex(index);
        return (T) this.array[index];
    }

    public T set(T value, int index) {
        validateIndex(index);
        T previous = (T) array[index];
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

    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    public int indexOf(T value) {
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

    public int lastIndexOf(T value) {
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
