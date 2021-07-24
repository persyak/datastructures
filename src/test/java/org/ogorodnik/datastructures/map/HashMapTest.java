package org.ogorodnik.datastructures.map;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
    }
}
