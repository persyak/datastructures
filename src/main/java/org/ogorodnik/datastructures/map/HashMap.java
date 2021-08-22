package org.ogorodnik.datastructures.map;

import org.ogorodnik.datastructures.list.ArrayList;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class HashMap <K, V> implements Map <K, V>, Iterable<Map.Entry<K, V>> {
    private static final int INITIAL_CAPACITY = 5;

    private ArrayList[] buckets = new ArrayList[INITIAL_CAPACITY];
    int size;

    private int getHashIndex(K key) {
        return Math.abs(Objects.hashCode(key)) % buckets.length;
    }

    private boolean isFirstKeyEqualSecondKey(K key1, K key2) {
        return (Objects.equals(key1, key2));
    }

    public Object put(K key, V value) {
        Object oldValue = null;
        boolean updated = false;
        ArrayList<Entry> bucket;
        int index = getHashIndex(key);
        if (buckets[index] == null) {
            bucket = buckets[index] = new ArrayList();
        } else {
            bucket = buckets[index];
        }
        for (Entry element: bucket) {
            if (isFirstKeyEqualSecondKey(element.key, key)) {
                oldValue = element.value;
                element.value = value;
                updated = true;
            }
        }
        if (!updated) {
            bucket.add(new Entry(key, value));
            size++;
        }
        return oldValue;
    }

    public Object get(K key) {
        ArrayList<Entry> bucket = buckets[getHashIndex(key)];
        if (bucket != null) {
            for (Entry element : bucket) {
                if (isFirstKeyEqualSecondKey(element.key, key)) {
                    return element.value;
                }
            }
        }
        return null;
    }

    public Object remove(K key) {
        Object oldValue = null;
        ArrayList<Entry> bucket = buckets[getHashIndex(key)];
        if (bucket != null) {
            Iterator iterator = bucket.iterator();
            while (iterator.hasNext()) {
                Entry element = (Entry) iterator.next();
                if (isFirstKeyEqualSecondKey(element.key, key)) {
                    oldValue = element.value;
                    iterator.remove();
                    size--;
                }
            }
        }
        return oldValue;
    }

    public boolean containsKey(K key) {
        int index = getHashIndex(key);
        if (isEmpty() || buckets[index] == null) {
            return false;
        } else {
            ArrayList<Entry> bucket = buckets[index];
            Iterator iterator = bucket.iterator();
            while (iterator.hasNext()) {
                Entry element = (Entry) iterator.next();
                if (isFirstKeyEqualSecondKey(element.key, key)) {
                    return true;
                }
            }
            return false;
        }
    }

    public Object putIfAbsent(K key, V value) {
        Object oldValue = null;
        boolean isPresent = false;
        ArrayList<Entry> bucket;
        int index = getHashIndex(key);
        if (buckets[index] == null) {
            bucket = buckets[index] = new ArrayList();
        } else {
            bucket = buckets[index];
        }
        Iterator iterator = bucket.iterator();
        while (iterator.hasNext()) {
            Entry element = (Entry) iterator.next();
            if (isFirstKeyEqualSecondKey(element.key, key)) {
                oldValue = element.value;
                isPresent = true;
            }
        }
        if (!isPresent) {
            bucket.add(new Entry(key, value));
            size++;
        }
        return oldValue;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new HashMapIterator();
    }

    private class HashMapIterator implements Iterator<Map.Entry<K, V>> {
        private Iterator<Entry> iterator;
        private int bucketIndex = -1;
        private int count;
        private boolean deleted;


        public boolean hasNext() {
            return count < size;
        }

        public Entry next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            deleted = false;
            if (iterator == null || !iterator.hasNext()) {
                bucketIndex++;
                bucketIsNull();
            }
            count++;
            return iterator.next();
        }

        private void bucketIsNull() {
            while (buckets[bucketIndex] == null) {
                bucketIndex++;
            }
            iterator = buckets[bucketIndex].iterator();
        }

        public void remove() {
            if (count == 0 || deleted) {
                throw new IllegalStateException();
            }
            iterator.remove();
            if (buckets[bucketIndex].size() == 0) {
                buckets[bucketIndex] = null;
            }
            size--;
            count--;
            deleted = true;
        }
    }

    class Entry extends Map.Entry<K, V> {
        public K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
