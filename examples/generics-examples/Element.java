
 

/**
 * An example to show the use of a key interface.
 * 
 * @author amit
 *
 */
public class Element implements KeyInterface<Integer>
{
    Integer key;
    String value;
    
    /**
     * Constructor for an element.
     * @param key   The key for the element
     * @param value The associated value (or satellite data)
     */
    public Element(int key, String value) {
	this.key = key;
	this.value = value;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getKey() {
	return key;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
	return "Element [key=" + key + ", value=\"" + value + "\"]";
    }
    

}
