package org.ogorodnik.datastructures.map;

public interface Map {
    //works like update; it adds key and value; but if key exists already,
    // it updates key value and returns legacy key value
    Object put(Object key, Object value);

    //Returns current value of the key
    Object get(Object key);

    //Returns value that was removed
    Object remove(Object key);

    boolean containsKey(Object key);

    //Returns value of the key if it exists, and null, if it does not exist
    Object putIfAbsent(Object key, Object value);

    int size();

    boolean isEmpty();
}
