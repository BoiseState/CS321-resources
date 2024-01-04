import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Black-box unit tests for the cache
 */
class CacheTest {

    private Cache<Integer, TestObject> cache;


    /**
     * Simple test of adding/getting.
     */
    @Test
    void addObject() {

        cache = new Cache<>(100);

        TestObject t1 = new TestObject(1);

        cache.add(t1);

        assertEquals(t1, cache.get(1));
    }


    /**
     * Add some objects and then call get() to retrieve one.
     * Cache is never full
     */
    @Test
    void getNotFull() {

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
     * Ensure one of the objects is kicked out when the cache is full up
     */
    @Test
    void getFull() {

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
     * tests that the right object is removed when the cache becomes
     * full.
     */
    @Test
    void lru() {


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
     * When the cache is cleared, there should be nothing in it!
     */
    @Test
    void clearCache() {

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