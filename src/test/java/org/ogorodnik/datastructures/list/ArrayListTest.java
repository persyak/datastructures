package org.ogorodnik.datastructures.list;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayListTest <T> extends AbstractListTest<T> {

    @Override
    AbstractList<String> getList() {
        return new ArrayList<> ();
    }

    @Test
    public void testAddWithNoExtension() {
        ArrayList <Object> arrayList = new ArrayList<>();
        arrayList.add('A');
        arrayList.add('B');
        assertEquals(2, arrayList.size());
        assertEquals('B', arrayList.get(1));
    }

    @Test
    public void testAddWithExtension() {
        ArrayList <String> arrayList = new ArrayList<>(0);
        arrayList.add("G");
        assertEquals(1, arrayList.size());
        assertEquals(1, arrayList.getLength());
        assertEquals("G", arrayList.get(0));
        arrayList.add("K");
        assertEquals(2, arrayList.size());
        assertEquals("K", arrayList.get(1));
        arrayList.add("H");
        assertEquals(3, arrayList.size());
        assertEquals("G", arrayList.get(0));
        assertEquals("K", arrayList.get(1));
        assertEquals("H", arrayList.get(2));
    }

    @Test
    public void testTrimToSize(){
        ArrayList<Character> arrayList = new ArrayList<>();
        assertEquals(5, arrayList.getLength());
        char symbol = 'A';
        for(int i=0; i<10; i++){
            arrayList.add(symbol);
            symbol++;
        }
        assertEquals(10, arrayList.size());
        assertEquals(13, arrayList.getLength());
        arrayList.trimToSize();
        assertEquals(10, arrayList.getLength());
    }

}
