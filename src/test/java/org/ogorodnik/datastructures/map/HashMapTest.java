package org.ogorodnik.datastructures.map;

import org.junit.Test;

import java.util.Iterator;
import java.util.Random;

import static org.junit.Assert.*;

public class HashMapTest {
    private HashMap hashMap = new HashMap();

    @Test
    public void testPut(){
        assertNull(hashMap.put(null, "value1"));
        assertEquals(1, hashMap.size());
        assertNull(hashMap.put("key2", "value2"));
        assertEquals(2, hashMap.size());
        assertEquals("value1", hashMap.put(null, "value3"));
        assertEquals(2, hashMap.size());
    }

    @Test
    public void testPutAndGet(){
        assertNull(hashMap.put("key1", "value1"));
        assertEquals(1, hashMap.size());
        assertEquals("value1", hashMap.get("key1"));
        assertNull(hashMap.get("key2"));
        assertNull(hashMap.put("key2", null));
        assertNull(hashMap.get("key2"));
        assertNull(hashMap.get("key6"));
    }

    @Test
    public void testRemove(){
        hashMap.put(null, "value1");
        hashMap.put("key2", "value2");
        hashMap.put("key3", "value3");
        assertEquals(3, hashMap.size());
        assertEquals("value1", hashMap.remove(null));
        assertEquals(2, hashMap.size());
        assertNull(hashMap.remove(null));
        assertEquals(null, hashMap.remove(null));
        assertNull(hashMap.remove("key4"));
        assertEquals(2, hashMap.size());
    }

    @Test
    public void testContains(){
        assertFalse(hashMap.containsKey("key1"));
        hashMap.put("key1", "value1");
        assertTrue(hashMap.containsKey("key1"));
        hashMap.put("key1", 'A');
        hashMap.put("key2", 'B');
        hashMap.put(null, 'C');
        hashMap.put("key4", 'D');
        hashMap.put("key5", 'E');
        hashMap.put("key6", 'F');
        assertTrue(hashMap.containsKey("key5"));
        assertTrue(hashMap.containsKey(null));
    }

    @Test
    public void testPutIfAbsent(){
        assertNull(hashMap.putIfAbsent("key1", "value1"));
        assertEquals(1, hashMap.size());
        assertNull(hashMap.putIfAbsent("key2", "value2"));
        assertTrue(hashMap.containsKey("key1"));
        assertEquals(2, hashMap.size());
        assertEquals("value2", hashMap.putIfAbsent("key2", "value3"));
        assertEquals(2, hashMap.size());
        assertNull(hashMap.putIfAbsent("key3", null));
        assertEquals(3, hashMap.size());
        assertNull(hashMap.putIfAbsent("key3", "value3"));
        assertEquals(3, hashMap.size());
        assertNull(hashMap.get("key3"));
    }

    @Test
    public void testIterator(){
        Random random = new Random();
        for(int i=0; i<101; i++){
            hashMap.put(i, random.nextInt(1000)+1);
        }
        for(Object element: hashMap){
            System.out.println(element);
        }
    }

    @Test
    public void testIteratorRemove(){
        hashMap.put("key1", 'A');
        hashMap.put("key2", 'B');
        hashMap.put("key3", 'C');
        hashMap.put("key4", 'D');
        hashMap.put("key5", 'E');

        Iterator iterator = hashMap.iterator();
        while(iterator.hasNext()){
            if(iterator.next().equals(new Map.Entry<>("key1", 'A'))){
                iterator.remove();
            }
        }
        assertFalse(hashMap.containsKey("key1"));
    }
}
