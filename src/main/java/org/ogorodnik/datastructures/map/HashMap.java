package org.ogorodnik.datastructures.map;

import org.ogorodnik.datastructures.list.AbstractList;
import org.ogorodnik.datastructures.list.ArrayList;

import java.util.Arrays;
import java.util.Iterator;

public class HashMap implements Map, Iterable {
    private static final int INITIAL_CAPACITY = 5;

    private ArrayList[] buckets = new ArrayList[INITIAL_CAPACITY];
    int size;

    private int getHashIndex(Object key) {
        int index = key.hashCode() % buckets.length;
        return index;
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
            bucket.add(new Entry(key, value));
            size++;
        } else {
            bucket = buckets[index];
            for (int i = 0; i < bucket.size(); i++) {
                if (bucket.get(i).key.equals(key) && bucket.get(i).key.hashCode() == key.hashCode()) {
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
                if (bucket.get(i).key.equals(key) && bucket.get(i).key.hashCode() == key.hashCode()) {
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
                if (bucket.get(i).key.equals(key) && bucket.get(i).key.hashCode() == key.hashCode()) {
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
                if (bucket.get(i).key.equals(key) && bucket.get(i).key.hashCode() == key.hashCode()) {
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

    @Override
    public Iterator iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator {
        private int arrayListIndex = 0;
        private int hashMapIndex = 0;
        private int bucketNumber = 0;
        private int arrayListIndexToRemove = 0;
        private int bucketNumberToRemove = 0;
        private boolean removable = false;

        private int getBucketNumber(int bucketNumber){
            if(bucketNumber == 4){
                bucketNumber = bucketNumber;
            }
            else if(buckets[bucketNumber] == null){
                bucketNumber+=1;
                getBucketNumber(bucketNumber);
            }
            return bucketNumber;
        }

        public boolean hasNext() {
            return hashMapIndex < size;
        }

        public Object next() {
            bucketNumber = getBucketNumber(bucketNumber);

            ArrayList bucket = buckets[bucketNumber];
            Entry element = (Entry) bucket.get(arrayListIndex);
            arrayListIndexToRemove = arrayListIndex;
            bucketNumberToRemove = bucketNumber;
            arrayListIndex++;
            hashMapIndex++;
            removable = true;

            if(arrayListIndex == buckets[bucketNumber].size()){
                bucketNumber++;
                arrayListIndex = 0;
            }

            return element.value;
        }

        public void remove() {
            if (!removable) {
                throw new IllegalStateException();
            }
            ArrayList bucket = buckets[bucketNumberToRemove];
            Entry element = (Entry) bucket.get(arrayListIndexToRemove);
            Object key = element.key;
            HashMap.this.remove(key);
            hashMapIndex--;
            removable = false;
        }
    }

    private static class Entry {
        private Object key;
        private Object value;

        public Entry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
    }
}
