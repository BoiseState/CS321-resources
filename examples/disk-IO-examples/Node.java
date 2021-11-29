

/**
 * 
 * A simple Node class used to demo diskRead and diskWrite methods.
 * @author amit
 *
 */
public class Node
{
    private int n;
    private boolean leaf;
    private long left, right;
    
    public Node() {
	n = 0;
	leaf = false;
	left = right = 0;
    }
    
    public Node(int n, boolean leaf, long left, long right) {
	this.n = n;
	
	this.left = left;
	this.right = right;this.leaf = leaf;
    }
    
    public static int getDiskSize() {
	return  Integer.BYTES + 1 + 2 * Long.BYTES; 
	//We will store Boolean as 1 byte (the size is not defined in Java)
    }
    
    public String toString() {
	return "Node[n=" + n + ",leaf=" + leaf + ",left-child=" + left + ",right-child = " + right + "]";
    }
}
