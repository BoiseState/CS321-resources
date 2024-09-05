/**
 * TODO - complete the summary javadoc for the class here.
 * @author CS321 Instructors
 */

public interface CacheInterface<K, V extends KeyInterface<K>> {

    /**
     * TODO - complete the javadoc here
     * @param key
     * @return
     */
    public V get(K key);

    /**
     * TODO - complete the javadoc here
     * @param value
     * @return
     */
    public V add(V value);

    /**
     * TODO - complete the javadoc here
     * @param key
     * @return
     */
    public V remove(K key);

    /**
     * TODO - complete the javadoc here
     */
    public void clear();

    /**
     * {@inheritDoc} 
     */
    public String toString();
}
