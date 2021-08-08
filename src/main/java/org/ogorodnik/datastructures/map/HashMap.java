package org.ogorodnik.datastructures.map;

import org.ogorodnik.datastructures.list.ArrayList;

import java.util.Iterator;
import java.util.Objects;

public class HashMap implements Map, Iterable {
    private static final int INITIAL_CAPACITY = 5;

    private ArrayList[] buckets = new ArrayList[INITIAL_CAPACITY];
    int size;

    private int getHashIndex(Object key) {
        int index = Math.abs(Objects.hashCode(key)) % buckets.length;
        return index;
    }

    private boolean isFirstKeyEqualSecondKey(Object key1, Object key2) {
        return (Objects.equals(key1, key2));
    }

    public Object put(Object key, Object value) {
        Object oldValue = null;
        boolean updated = false;
        ArrayList<Entry> bucket;
        int index = getHashIndex(key);
        if (buckets[index] == null) {
            bucket = buckets[index] = new ArrayList();
        } else {
            bucket = buckets[index];
        }
        for (Entry element : bucket) {
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

    public Object get(Object key) {
        ArrayList<Entry> bucket = buckets[getHashIndex(key)];
        if (bucket != null) {
            for (Entry element: bucket) {
                if (element.key.equals(key)) {
                    return element.value;
                }
            }
        }
        return null;
    }

    public Object remove(Object key) {
        Object oldValue = null;
        ArrayList<Entry> bucket = buckets[getHashIndex(key)];
        if (bucket != null) {
            Iterator iterator = bucket.iterator();
            while(iterator.hasNext()){
                Entry item = (Entry) iterator.next();
                if(item.key.equals(key)){
                    oldValue = item.value;
                    iterator.remove();
                    size--;
                }
            }
        }
        return oldValue;
    }

    public boolean containsKey(Object key) {
        int index = getHashIndex(key);
        if (isEmpty() || buckets[index] == null) {
            return false;
        } else {
            ArrayList<Entry> bucket = buckets[index];
            for (int i = 0; i < bucket.size(); i++) {
                if (isFirstKeyEqualSecondKey(bucket.get(i).key, key)) {
                    return true;
                }
            }
            return false;
        }
    }

    public Object putIfAbsent(Object key, Object value) {
        Object oldValue = null;
        boolean isPresent = false;
        ArrayList<Entry> bucket;
        int index = getHashIndex(key);
        if (buckets[index] == null) {
            bucket = buckets[index] = new ArrayList();
        } else {
            bucket = buckets[index];
        }
        for (int i = 0; i < bucket.size(); i++) {
            if (isFirstKeyEqualSecondKey(bucket.get(i).key, key)) {
                oldValue = bucket.get(i).value;
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

        private int getBucketNumber(int bucketNumber) {
            if (bucketNumber == 4) {
                bucketNumber = bucketNumber;
            } else if (buckets[bucketNumber] == null) {
                bucketNumber += 1;
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

            if (arrayListIndex == buckets[bucketNumber].size()) {
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

        private Entry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
    }
}
