

/**
 * A simple object class that is used in the DiskReadWrite class as the object to store in 
 * an external binary search tree.
 * 
 * @author amit
 *
 */
public class TreeObject implements Comparable<TreeObject> {
	private long value;
	private long frequency;
	
	public TreeObject(long value, long frequency) {
		super();
		this.value = value;
		this.frequency = frequency;
	}
	
	public long getValue() { return value;}
	public long getFrequency() {return frequency;}
	
	public static int getDiskSize() {
		return Long.BYTES * 2;
	}

	@Override
	public int compareTo(TreeObject other) {
		if (value < other.value)
			return -1;
		else if (value == other.value) 
			return 0;
		else //value > other.value
			return  +1;
	}
	
	public String toString() {
	    return "TreeObject[value = " + value + ", frequency = " + frequency + "]";
	}
}
