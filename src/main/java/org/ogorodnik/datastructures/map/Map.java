package org.ogorodnik.datastructures.map;

public interface Map<K, V> {
    //works like update; it adds key and value; but if key exists already,
    // it updates key value and returns legacy key value
    Object put(K key, V value);

    //Returns current value of the key
    Object get(K key);

    //Returns value that was removed
    Object remove(K key);

    boolean containsKey(K key);

    //Returns value of the key if it exists, and null, if it does not exist
    Object putIfAbsent(K key, V value);

    int size();

    boolean isEmpty();

    class Entry<K, V> {
        private K key;
        private V value;

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
