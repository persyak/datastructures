package org.ogorodnik.datastructures.map;

import org.junit.Test;

import static org.junit.Assert.*;

public class HashMapTest {
    private HashMap hashMap = new HashMap();

    @Test
    public void testPut(){
        assertNull(hashMap.put("key1", "value1"));
        assertEquals(1, hashMap.size());
        assertNull(hashMap.put("key2", "value2"));
        assertEquals(2, hashMap.size());
        assertEquals("value1", hashMap.put("key1", "value3"));
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
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");
        hashMap.put("key3", "value3");
        assertEquals(3, hashMap.size());
        assertEquals("value2", hashMap.remove("key2"));
        assertEquals(2, hashMap.size());
        assertNull(hashMap.remove("key2"));
        assertNull(hashMap.remove("key4"));
        assertEquals(2, hashMap.size());
    }

    @Test
    public void testContains(){
        assertFalse(hashMap.contains("key1"));
        hashMap.put("key1", "value1");
        assertTrue(hashMap.contains("key1"));
    }

    @Test
    public void testPutIfAbsent(){
        assertNull(hashMap.putIfAbsent("key1", "value1"));
        assertEquals(1, hashMap.size());
        assertNull(hashMap.putIfAbsent("key2", "value2"));
        assertTrue(hashMap.contains("key1"));
        assertEquals(2, hashMap.size());
        assertEquals("value2", hashMap.putIfAbsent("key2", "value3"));
        assertNull(hashMap.putIfAbsent("key3", null));
        assertEquals(3, hashMap.size());
        assertNull(hashMap.putIfAbsent("key3", "value3"));
        assertEquals(3, hashMap.size());
        assertNull(hashMap.get("key3"));
    }
}
