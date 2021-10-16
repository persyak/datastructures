package org.ogorodnik.datastructures.map;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class HashMapTest {

    @Test
    public void givenEmptyMapWhenGetByNullKeyThenNullShouldBeReturned() {
        Map<String, String> map = new HashMap<>();
        assertThat(map.get(null), is(nullValue()));
    }

    @Test
    public void givenEmptyMapWhenGetByNotNullKeyThenNullShouldBeReturned() {
        Map<String, String> map = new HashMap<>();
        assertThat(map.get("key"), is(nullValue()));
    }

    @Test
    public void givenNullKeyWhenPutOnceThenSizeShouldBeEqualToOneAndValueShouldBeEqualToInserted() {
        Map<String, String> map = new HashMap<>();

        String key = null;
        String value = "test";

        map.put(key, value);

        assertThat(map.size(), is(1));
        assertThat(map.get(key), is(value));
    }

    @Test
    public void givenNullKeyWhenPutMultipleTimesThenSizeShouldBeEqualToOneAndValueShouldBeOverwrittenWithLast() {
        Map<String, String> map = new HashMap<>();

        String key = null;
        String firstValue = "test1";
        String secondValue = "test2";
        String thirdValue = "test3";

        map.put(key, firstValue);
        map.put(key, secondValue);
        map.put(key, thirdValue);

        assertThat(map.size(), is(1));
        assertThat(map.get(key), is(thirdValue));
    }

    @Test
    public void givenNotNullKeyWhenPutThenSizeShouldBeEqualToOneAndValueShouldBeEqualToInserted() {
        Map<String, String> map = new HashMap<>();

        String key = "key";
        String value = "value";

        map.put(key, value);

        assertThat(map.size(), is(1));
        assertThat(map.get(key), is(value));
    }

    @Test
    public void givenMultipleNotNullKeysWhenPutThenSizeShouldBeEqualToSizeOfKeysAndGetByKeyReturnsCorrespondingValue() {
        Map<String, String> map = new HashMap<>();

        String firstKey = "key1";
        String secondKey = "key2";
        String firstValue = "value1";
        String secondValue = "value2";

        map.put(firstKey, firstValue);
        map.put(secondKey, secondValue);

        assertThat(map.size(), is(2));
        assertThat(map.get(firstKey), is(firstValue));
        assertThat(map.get(secondKey), is(secondValue));
    }

    @Test
    public void givenMultipleNodesInSameBucketWhenGetByExistingKeyThenGetByKeyReturnsCorrespondingValue() {
        Map<String, String> map = new HashMap<>();

        String firstKey = "key2"; //same bucket
        String secondKey = "key-1"; //same bucket
        String thirdKey = "key-10"; //same bucket

        String firstValue = "value2";
        String secondValue = "value3";
        String thirdValue = "value4";

        map.put(firstKey, firstValue);
        map.put(secondKey, secondValue);
        map.put(thirdKey, thirdValue);

        assertThat(map.size(), is(3));

        assertThat(map.get(firstKey), is(firstValue));
        assertThat(map.get(secondKey), is(secondValue));
        assertThat(map.get(thirdKey), is(thirdValue));
    }

    @Test
    public void givenNotExistingKeyWhenGetByKeyThenNullShouldBeReturned() {
        Map<String, String> map = new HashMap<>();
        map.put("existing key", "value");

        assertThat(map.get("not existing key"), is(nullValue()));
    }

    @Test
    public void givenNotNullKeyWhenPutMultipleTimesWithSameKeyThenSizeShouldBeEqualToOneAndValueShouldBeOverwrittenWithLast() {
        Map<String, String> map = new HashMap<>();

        String key = "key";
        String firstValue = "test1";
        String secondValue = "test2";
        String thirdValue = "test3";

        map.put(key, firstValue);
        map.put(key, secondValue);
        map.put(key, thirdValue);

        assertThat(map.size(), is(1));
        assertThat(map.get(key), is(thirdValue));
    }

    @Test
    public void givenEmptyMapWhenRemoveByNullKeyThenSizeShouldBeEqualToZero() {
        Map<String, String> map = new HashMap<>();
        map.remove(null);
        assertThat(map.size(), is(0));
    }

    @Test
    public void givenEmptyMapWhenRemoveByNotNullKeyThenSizeShouldBeEqualToZero() {
        Map<String, String> map = new HashMap<>();
        map.remove("key");
        assertThat(map.size(), is(0));
    }

    @Test
    public void givenNotEmptyMapWhenRemoveByNullKeyThenSizeShouldBeEqualToZero() {
        Map<String, String> map = new HashMap<>();
        map.put(null, "value");
        map.remove(null);

        assertThat(map.size(), is(0));
    }

    @Test
    public void givenNotEmptyMapWithNotNullKeyWhenPutWithNullKeyAndRemoveByNullKeyThenSizeShouldDecreaseByOne() {
        Map<String, String> map = new HashMap<>();
        map.put(null, "value");
        map.put("not null key", "value");

        assertThat(map.size(), is(2));

        map.remove(null);
        assertThat(map.size(), is(1));
    }

    @Test
    public void givenEmptyMapWhenRemoveThenSizeShouldBeEqualToZero() {
        Map<String, String> map = new HashMap<>();
        map.remove("key");
        assertThat(map.size(), is(0));
    }

    @Test
    public void givenNotEmptyMapWhenRemoveThenSizeShouldBeEqualToZero() {
        Map<String, String> map = new HashMap<>();

        map.put("key", "value");
        assertThat(map.size(), is(1));

        map.remove("key");
        assertThat(map.size(), is(0));
    }

    @Test
    public void givenNotEmptyMapWhenRemoveOneByOneThenSizeShouldDecreaseAfterEachRemovalByOne() {
        Map<String, String> map = new HashMap<>();

        String firstKey = "key1";
        String secondKey = "key2"; //same bucket
        String thirdKey = "key-1"; //same bucket

        String firstValue = "value1";
        String secondValue = "value2";
        String thirdValue = "value3";

        map.put(firstKey, firstValue);
        map.put(secondKey, secondValue); //case: remove first node
        map.put(thirdKey, thirdValue); //case: remove last node

        assertThat(map.size(), is(3));

        map.remove(firstKey);
        assertThat(map.size(), is(2));

        map.remove(secondKey);
        assertThat(map.size(), is(1));

        map.remove(thirdKey);
        assertThat(map.size(), is(0));
    }

    @Test
    public void givenNotEmptyMapAndObjectsInSameBucketWhenRemoveFirstNodeThenSizeShouldDecreaseByOne() {
        Map<String, String> map = new HashMap<>();

        String firstKey = "key1";
        String secondKey = "key2"; //same bucket
        String thirdKey = "key-1"; //same bucket
        String fourthKey = "key-10"; //same bucket

        String firstValue = "value1";
        String secondValue = "value2";
        String thirdValue = "value3";
        String fourthValue = "value4";

        map.put(firstKey, firstValue);
        map.put(secondKey, secondValue);
        map.put(thirdKey, thirdValue);
        map.put(fourthKey, fourthValue);

        assertThat(map.size(), is(4));

        map.remove(secondKey); //case: remove first node
        assertThat(map.size(), is(3));
    }

    @Test
    public void givenNotEmptyMapAndObjectsInSameBucketWhenRemoveLastNodeThenSizeShouldDecreaseByOne() {
        Map<String, String> map = new HashMap<>();

        String firstKey = "key1";
        String secondKey = "key2"; //same bucket
        String thirdKey = "key-1"; //same bucket
        String fourthKey = "key-10"; //same bucket

        String firstValue = "value1";
        String secondValue = "value2";
        String thirdValue = "value3";
        String fourthValue = "value4";

        map.put(firstKey, firstValue);
        map.put(secondKey, secondValue);
        map.put(thirdKey, thirdValue);
        map.put(fourthKey, fourthValue);

        assertThat(map.size(), is(4));

        map.remove(fourthKey); //case: remove first node
        assertThat(map.size(), is(3));
    }

    @Test
    public void givenNotEmptyMapAndObjectsInSameBucketWhenRemoveNodeInTheMiddleThenSizeShouldDecreaseByOne() {
        Map<String, String> map = new HashMap<>();

        String firstKey = "key1";
        String secondKey = "key2"; //same bucket
        String thirdKey = "key-1"; //same bucket
        String fourthKey = "key-10"; //same bucket

        String firstValue = "value1";
        String secondValue = "value2";
        String thirdValue = "value3";
        String fourthValue = "value4";

        map.put(firstKey, firstValue);
        map.put(secondKey, secondValue);
        map.put(thirdKey, thirdValue);
        map.put(fourthKey, fourthValue);

        assertThat(map.size(), is(4));

        map.remove(thirdKey); //case: remove node in the middle
        assertThat(map.size(), is(3));
    }

    @Test
    public void givenEmptyMapNotAndNotExistingKeyWhenRemoveThenSizeShouldNotChange() {
        Map<String, String> map = new HashMap<>();
        map.remove("not existing key");
        assertThat(map.size(), is(0));
    }

    @Test
    public void givenNotEmptyMapNotAndNotExistingKeyWhenRemoveThenSizeShouldNotChange() {
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");

        assertThat(map.size(), is(1));

        map.remove("not existing key");
        assertThat(map.size(), is(1));
    }

    @Test
    public void givenEmptyMapWhenContainsNullKeyThenFalseShouldBeReturned() {
        Map<String, String> map = new HashMap<>();
        assertThat(map.containsKey(null), is(false));
    }

    @Test
    public void givenEmptyMapWhenContainsNotNullKeyThenFalseShouldBeReturned() {
        Map<String, String> map = new HashMap<>();
        assertThat(map.containsKey("key"), is(false));
    }

    @Test
    public void givenMapWithExistingNullKeyWhenContainsNullKeyThenTrueShouldBeReturned() {
        Map<String, String> map = new HashMap<>();
        map.put(null, "value");

        assertThat(map.containsKey(null), is(true));
    }

    @Test
    public void givenMapWithNotExistingNullKeyWhenContainsNullKeyThenFalseShouldBeReturned() {
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");

        assertThat(map.containsKey(null), is(false));
    }

    @Test
    public void givenNotExistingKeyWhenContainsKeyThenFalseShouldBeReturned() {
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");

        assertThat(map.containsKey("key"), is(true));
    }

    @Test
    public void givenExistingKeyWhenContainsKeyThenTrueShouldBeReturned() {
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");

        assertThat(map.containsKey("not existing key"), is(false));
    }

    @Test
    public void givenMultipleNodesInSameBucketAndExistingKeyWhenContainsByKeyThenTrueShouldBeReturned() {
        Map<String, String> map = new HashMap<>();

        String firstKey = "key1";
        String secondKey = "key2"; //same bucket
        String thirdKey = "key-1"; //same bucket
        String fourthKey = "key-10"; //same bucket

        String firstValue = "value1";
        String secondValue = "value2";
        String thirdValue = "value3";
        String fourthValue = "value4";

        map.put(firstKey, firstValue);
        map.put(secondKey, secondValue);
        map.put(thirdKey, thirdValue);
        map.put(fourthKey, fourthValue);

        assertThat(map.containsKey(firstKey), is(true));
        assertThat(map.containsKey(secondKey), is(true));
        assertThat(map.containsKey(thirdKey), is(true));
        assertThat(map.containsKey(fourthKey), is(true));
    }

    @Test
    public void givenMultipleNodesInSameBucketAndNotExistingKeyWithHashLeadingToSameBucketWhenContainsByKeyThenFalseShouldBeReturned() {
        Map<String, String> map = new HashMap<>();

        String firstKey = "key1";
        String secondKey = "key2"; //same bucket
        String thirdKey = "key-1"; //same bucket
        String fourthKey = "key-10"; //same bucket
        String notExistingKeyWithHashLeadingToSameBucket = "key+12";

        String firstValue = "value1";
        String secondValue = "value2";
        String thirdValue = "value3";
        String fourthValue = "value4";

        map.put(firstKey, firstValue);
        map.put(secondKey, secondValue);
        map.put(thirdKey, thirdValue);
        map.put(fourthKey, fourthValue);

        assertThat(map.containsKey(firstKey), is(true));
        assertThat(map.containsKey(secondKey), is(true));
        assertThat(map.containsKey(thirdKey), is(true));
        assertThat(map.containsKey(fourthKey), is(true));
        assertThat(map.containsKey(notExistingKeyWithHashLeadingToSameBucket), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void givenEmptyMapWhenIteratorNextThenNoSuchElementExceptionShouldBeRaised() {
        new HashMap<>().iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void givenIteratorWhenNextAfterLastElementThenNoSuchElementExceptionShouldBeRaised() {
        HashMap<String, String> map = new HashMap<>();

        map.put("key", "value");

        Iterator<Map.Entry<String, String>> iterator = map.iterator();

        Map.Entry<String, String> entry = iterator.next();
        assertThat(entry.getKey(), is("key"));
        assertThat(entry.getValue(), is("value"));
        iterator.next();
    }

    @Test
    public void givenEmptyMapWhenIteratorHasNextThenShouldReturnFalse() {
        HashMap<String, String> map = new HashMap<>();
        assertThat(map.iterator().hasNext(), is(false));
    }

    @Test(expected = IllegalStateException.class)
    public void givenEmptyMapWhenIteratorRemoveThenNoSuchElementExceptionShouldBeRaised() {
        new HashMap<>().iterator().remove();
    }

    @Test(expected = IllegalStateException.class)
    public void givenIteratorWhenRemoveCalledWithoutNextThenIllegalStateExceptionShouldBeRaised() {
        HashMap<String, String> map = new HashMap<>();
        map.put("key", "value");

        assertThat(map.size(), is(1));

        map.iterator().remove();
    }

    @Test
    public void testHashMapGrowsWhenLoadFactorMultipliedByLengthReachedSize(){
        HashMap<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");
        map.put("key5", "value5");
        assertThat(map.getBuckets().length, is(10));
        map.put("key6", "value6");
        map.put("key7", "value7");
        map.put("key8", "value8");
        map.put("key9", "value9");
        assertThat(map.getBuckets().length, is(20));
        map.put("key10", "value10");
    }
}
