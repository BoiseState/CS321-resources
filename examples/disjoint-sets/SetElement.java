
/**
 * Wrapper class for a set element
 * @author amit
 *
 */
public class SetElement<T> 
{
	private int parent;
	private T element;
	private int size;
	
	/**
	 * 
	 * @param element
	 */
	public SetElement(T element) {
		this.element = element;
	}
	/**
	 * 
	 * @return
	 */
	public int getParent() { return parent;}
	
	/**
	 * 
	 * @param parent
	 */
	public void setParent(int parent) { this.parent = parent; }
	
	/**
	 * 
	 * @return
	 */
	public int getSize() { return size;}
	
	/**
	 * 
	 * @param size
	 */
	public void setSize(int size) { this.size = size; }
	
	/**
	 * 
	 * @return
	 */
	public Object getElement() { return element;}
	
	/**
	 * 
	 */
	public String toString()
	{
		return "element= " + element.toString() + " " + "size = " + size;
	}

}
