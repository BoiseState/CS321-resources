
/**
 * An example of how to use an inner class from an external class.
 * 
 * @author amit
 *
 */
public class InnerClassExample
{

    /**
     * The inner class has to be declared protected to allow access from other classes in the same package.
     */
    protected class Node {
	protected int n;
	protected long left, right;
	
	public Node (int n, long left, long right) {
	    this.n = n;
	    this.left = left;
	    this.right = right;
	}
	
	public String toString() {
	    return "Node[n=" + n + ", left=" + left + ", right=" + right + "]";
	}
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
	// TODO Auto-generated method stub

	Node n1 = new InnerClassExample().new Node(1, 8, 16); //create a node with arbitrary values for an example
	System.out.println(n1);
	
    }

}
