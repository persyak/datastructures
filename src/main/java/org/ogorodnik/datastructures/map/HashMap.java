package org.ogorodnik.datastructures.map;

import org.ogorodnik.datastructures.list.ArrayList;

public class HashMap implements Map {
    private static final int INITIAL_CAPACITY = 5;

    private ArrayList[] buckets = new ArrayList[INITIAL_CAPACITY];
    int size;

    private int getHashIndex(Object key) {
        return key.hashCode() % buckets.length;
    }

    public Object put(Object key, Object value) {
        Object oldValue = null;
        ArrayList<Entry> bucket;
        int index = getHashIndex(key);
        if (!contains(key)) {
            if (buckets[index] == null) {
                bucket = buckets[index] = new ArrayList();
            } else {
                bucket = buckets[index];
            }
            if (size == 0) {
                bucket.add(new Entry(key, value));
            } else if (bucket.isEmpty()) {
                bucket.add(new Entry(key, value));
            }
            size++;
        } else {
            bucket = buckets[index];
            for (int i = 0; i < bucket.size(); i++) {
                if (bucket.get(i).key.equals(key)) {
                    oldValue = bucket.get(i).value;
                    bucket.get(i).value = value;
                }
            }
        }
        return oldValue;
    }

    public Object get(Object key) {
        if (contains(key)) {
            ArrayList<Entry> bucket = buckets[getHashIndex(key)];
            for (int i = 0; i < bucket.size(); i++) {
                if (bucket.get(i).key.equals(key)) {
                    return bucket.get(i).value;
                }
            }
        }
        return null;
    }

    public Object remove(Object key) {
        Object oldValue = null;
        if (contains(key)) {
            ArrayList<Entry> bucket = buckets[getHashIndex(key)];
            for (int i = 0; i < bucket.size(); i++) {
                if (bucket.get(i).key.equals(key)) {
                    oldValue = bucket.get(i).value;
                    bucket.remove(i);
                    size--;
                }
            }
        }
        return oldValue;
    }

    public boolean contains(Object key) {
        int index = getHashIndex(key);
        if (isEmpty() || buckets[index] == null) {
            return false;
        } else {
            ArrayList<Entry> bucket = buckets[index];
            for (int i = 0; i < bucket.size(); i++) {
                if (bucket.get(i).key.equals(key)) {
                    return true;
                }
            }
            return false;
        }
    }

    public Object putIfAbsent(Object key, Object value) {
        Object oldValue = null;
        if (!contains(key)) {
            ArrayList<Entry> bucket;
            int index = getHashIndex(key);
            if (buckets[index] == null) {
                bucket = buckets[index] = new ArrayList();
            } else {
                bucket = buckets[index];
            }
            if (size == 0) {
                bucket.add(new Entry(key, value));
            } else if (bucket.isEmpty()) {
                bucket.add(new Entry(key, value));
            }
            size++;
        } else {
            oldValue = get(key);
        }
        return oldValue;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
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
