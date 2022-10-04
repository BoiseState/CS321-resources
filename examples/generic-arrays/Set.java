import java.lang.reflect.Array;

/**
 * Demonstrates how to create an array of a generic type.
 * For more information, see <a href="https://www.baeldung.com/java-generic-array">java-generic-array</a>
 * 
 * @author amit
 *
 */
public class Set<E>
{
    private int size;
    private E[] array;
    
    @SuppressWarnings("unchecked")
    public Set(Class<E> clazz, int size) {
        array = (E[]) Array.newInstance(clazz, size);
        this.size = size;
    }
    
    public void addElements(E[] values) {
        if (values.length > array.length)
            throw new IllegalArgumentException("Set: array parameter too big");
        
        for (int i = 0; i < values.length; i++) 
            array[i] = values[i];
    }
    
    public String toString() {
        String s = "Set[";
        for (int i = 0 ; i < size - 1; i++)
            s += array[i] + ", ";
        s += array[size - 1] + "]";
        return s;
    }

}
