package org.ogorodnik.datastructures.list;

import java.util.Iterator;

public abstract class AbstractList <T> implements List<T>, Iterable<T> {
    int size;

    public Iterator <T> iterator() {
        return new MyIterator();
    }

    class MyIterator implements Iterator <T> {
        private int index = 0;
        private boolean removable = false;

        public boolean hasNext() {
            return index < size;
        }

        public T next() {
            T next = get(index);
            index++;
            removable = true;
            return next;
        }

        public void remove() {
            if (!removable) {
                throw new IllegalStateException();
            }
            int indexToRemove = index - 1;
            AbstractList.this.remove(indexToRemove);
            index--;
            removable = false;
        }
    }

    public void add(T value) {
        add(value, size);
    }

    void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Your List size is " + size +
                    ". Error: your index is bigger or equal than size or less than ZERO");
        }
    }

    void validateIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Your List size is " + size +
                    ". Error: your index is bigger than size or less than ZERO");
        }
    }
}
