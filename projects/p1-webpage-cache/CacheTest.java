import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Black-box unit tests for the Cache class. 
 * See the Wikipedia page <a href="https://en.wikipedia.org/wiki/Black-box_testing">black-box testing</a> for more information.
 *
 * @author CS321 Instructors
 */
class CacheTest {

    private Cache<Integer, TestObject> cache;


    /**
     * Simple test of adding/getting.
     */
    @Test
    void testAddObject() {

        cache = new Cache<>(100);

        TestObject t1 = new TestObject(1);

        cache.add(t1);

        assertEquals(t1, cache.get(1));
    }


    /**
     * Add some objects and then call get() to retrieve one.
     * In this test, the cache still has capacity left to add more elements.
     */
    @Test
    void testGetNotFull() {

        cache = new Cache<>(100);

        TestObject t1 = new TestObject(1);
        TestObject t2 = new TestObject(2);
        TestObject t3 = new TestObject(3);
        TestObject t4 = new TestObject(4);
        TestObject t5 = new TestObject(5);

        cache.add(t1);
        cache.add(t2);
        cache.add(t3);
        cache.add(t4);
        cache.add(t5);

        assertEquals(t1, cache.get(1));

    }


    /**
     * Add more objects than the cache can hold.
     * Ensure one of the objects is kicked out when the cache is filled up.
     */
    @Test
    void testGetFull() {

        cache = new Cache<>(4);

        TestObject t1 = new TestObject(1);
        TestObject t2 = new TestObject(2);
        TestObject t3 = new TestObject(3);
        TestObject t4 = new TestObject(4);
        TestObject t5 = new TestObject(5);

        cache.add(t1);
        cache.add(t2);
        cache.add(t3);
        cache.add(t4);
        cache.add(t5);

        assertNull(cache.get(1));
        assertEquals(t2, cache.get(2));
    }

    /**
     * Tests that the right object is removed when the cache becomes full.
     */
    @Test
    void testLRU() {

        cache = new Cache<>(4);

        TestObject t1 = new TestObject(1);
        TestObject t2 = new TestObject(2);
        TestObject t3 = new TestObject(3);
        TestObject t4 = new TestObject(4);
        TestObject t5 = new TestObject(5);

        cache.add(t1);
        cache.add(t2);
        cache.add(t3);
        cache.add(t4);
        assertEquals(t1, cache.add(t5));
    }

    /**
     * Tests that Cache uses the MRU scheme by ensuring that an object that was recently referenced
     * remains in the Cache after adding another.
     */
    @Test
    void testMRU() {

        cache = new Cache<>(3);

        TestObject t1 = new TestObject(1);
        TestObject t2 = new TestObject(2);
        TestObject t3 = new TestObject(3);
        TestObject t4 = new TestObject(4);

        cache.add(t1);
        cache.add(t2);
        cache.add(t3);
        //At this point, list is t3, t2, t1
        assertEquals(t1, cache.get(1));
        //list should be t1, t3, t2

        assertEquals(t2, cache.add(t4));
        //t2 should have been removed and list should be t4, t1, t3

        assertNull(cache.get(2));
        assertEquals(t1, cache.get(1));
    }

    /**
     * When the cache is cleared, there should be nothing in it!
     */
    @Test
    void testClearCache() {

        cache = new Cache<>(100);

        TestObject t1 = new TestObject(1);
        TestObject t2 = new TestObject(2);
        TestObject t3 = new TestObject(3);
        TestObject t4 = new TestObject(4);
        TestObject t5 = new TestObject(5);

        cache.add(t1);
        cache.add(t2);
        cache.add(t3);
        cache.add(t4);
        cache.add(t5);

        cache.clear();

        assertNull(cache.get(1));
        assertNull(cache.get(2));
        assertNull(cache.get(3));
        assertNull(cache.get(4));
        assertNull(cache.get(5));
    }
}
