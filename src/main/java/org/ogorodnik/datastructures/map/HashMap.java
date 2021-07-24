package org.ogorodnik.datastructures.map;

import org.ogorodnik.datastructures.list.ArrayList;

import java.lang.reflect.Array;

public class HashMap implements Map {
    private static final int INITIAL_CAPACITY = 5;

    private ArrayList[] buckets = new ArrayList[INITIAL_CAPACITY];
    int size;

    public Object put(Object key, Object value) {
        Object oldValue = null;
        ArrayList<Entry> bucket = buckets[key.hashCode() % buckets.length];
        if (size == 0) {
            bucket.add(new Entry(key, value));
        } else if (bucket.isEmpty()) {
            bucket.add(new Entry(key, value));
        } else {
            for (int i = 0; i < bucket.size(); i++) {
                if (bucket.get(i).key.equals(key)) {
                    oldValue = bucket.get(i).value;
                    bucket.get(i).value = value;
                } else {
                    bucket.add(new Entry(key, value));
                }
            }
        }
        size++;
        return oldValue;
    }

    @Override
    public Object get(Object key) {
        return null;
    }

    @Override
    public Object remove(Object key) {
        return null;
    }

    @Override
    public Object contains(Object key) {
        return null;
    }

    @Override
    public Object putIfAbsent(Object key, Object value) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public static class Entry {
        private Object key;
        private Object value;

        public Entry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
    }
}
