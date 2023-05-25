
/**
 * An interface that guarantees access to a key in an object.
 * 
 * @author amit
 *
 * @param <K>
 */
public interface KeyInterface<K extends Comparable<K>>
{
    K getKey();
}
