/**
 * 
 */

/**
 * @author amit
 *
 */
public class Element implements KeyInterface<Integer>
{
    Integer key;
    String value;
    
    public Element(int key, String value) {
	this.key = key;
	this.value = value;
    }
    
    
    @Override
    public Integer getKey() {
	return key;
    }


    @Override
    public String toString() {
	return "Element [key=" + key + ", value=\"" + value + "\"]";
    }
    

}
