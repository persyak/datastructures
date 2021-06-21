package org.ogorodnik.datastructures.list;

import java.util.Iterator;

public abstract class AbstractList implements List, Iterable {
    int size;

    public Iterator iterator() {
        return new MyIterator();
    }

    class MyIterator implements Iterator {
        private int index = 0;
        private boolean removable = false;

        public boolean hasNext() {
            return index < size;
        }

        public Object next() {
            Object next = get(index);
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

    public void add(Object value) {
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
