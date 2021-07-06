package org.ogorodnik.datastructures.list;

public interface List<T> {
    // add value to the end of the list
    void add(T value);

    // we can add value by index between [0, size]
    // otherwise throw new IndexOutOfBoundsException
    void add(T value, int index);

    // we can remove value by index between [0, size - 1]
    // otherwise throw new IndexOutOfBoundsException
    // [A, B, C] remove = 0
    // [B (index = 0) , C (index = 1)]
    T remove(int index);

    // [A, B, C] size = 3
    // we can get value by index between [0, size - 1]
    // otherwise throw new IndexOutOfBoundsException
    T get(int index);

    // we can set value by index between [0, size - 1]
    // otherwise throw new IndexOutOfBoundsException
    T set(T value, int index);

    //clear all collection
    void clear();

    //gets the number of elements in the list
    int size();

    //checks if a list is empty or not
    boolean isEmpty();

    //checks if the special element exista in the given list or not
    boolean contains(T value);

    //returns from the list indext of special element
    int indexOf(T value);

    //returns from the list last indext of special element
    int lastIndexOf(T value);

    //[A, B, C]
    String toString();
}
