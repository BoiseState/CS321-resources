/**
 * TODO - complete the summary javadoc for the class here.
 * @author CS321 Instructors
 * @author <yourname>
 */

public interface CacheInterface<K, V extends KeyInterface<K>> {

    /**
     * TODO - complete the javadoc here
     * @param key
     * @return
     */
    V get(K key);

    /**
     * TODO - complete the javadoc here
     * @param value
     * @return
     */
    V add(V value);

    /**
     * TODO - complete the javadoc here
     * @param key
     * @return
     */
    V remove(K key);

    /**
     * TODO - complete the javadoc here
     */
    void clear();

    /**
     * {@inheritDoc} 
     */
    String toString();
}
