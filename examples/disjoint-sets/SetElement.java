
/**
 * Wrapper class for a set element.
 * 
 * @author amit
 *
 */
public class SetElement<T> 
{
	private int parent;
	private T element;
	private int rank;
	
	/**
	 * Create a set element containing the given value.
	 * @param element
	 */
	public SetElement(T element) {
		this.element = element;
	}
	/**
	 * Getter for the parent.
	 * @return
	 */
	public int getParent() { return parent;}
	
	/**
	 * Setter for the parent.
	 * @param parent
	 */
	public void setParent(int parent) { this.parent = parent; }
	
	/**
	 * Getter for the size of the element.
	 * @return
	 */
	public int getRank() { return rank;}
	
	/**
	 * Setter for the size of the element.
	 * @param size
	 */
	public void setRank(int size) { this.rank = size; }
	
	/**
	 * Getter for the actual element.
	 * @return
	 */
	public T getElement() { return element;}
	
	@Override
	public String toString()
	{
		return "element= " + element.toString() + " " + "rank = " + rank;
	}

}
