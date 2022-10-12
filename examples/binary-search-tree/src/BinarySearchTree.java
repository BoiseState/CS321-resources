

/**
 * A simple binary search tree implementation. This isn't generic.
 * 
 * @author amit
 *
 */
public class BinarySearchTree {

	private int size;
	private Node root;

	/**
	 * Inner class to represent a binary search tree node.
	 * 
	 * @author amit
	 *
	 */
	public class Node implements Comparable<Node> {
		Node left;
		Node right;
		Node parent;
		Integer key;

		/**
		 * Constructor for a node.
		 * @param element
		 */
		public Node(Integer element) {
			key = element;
			left = right = parent = null;
		}

		
		/**
		 * {@inheritDoc}
		 */
		public int compareTo(BinarySearchTree.Node otherNode) {
			return key.compareTo(otherNode.key);
		}

		/**
		 * {@inheritDoc}
		 */
		public String toString() {
			return "Node:  key = " + key;
		}
	}

	/**
	 * Create an empty binary search tree.
	 */
	public BinarySearchTree() {
		root = null;
		size = 0;
	}

	/**
	 * @return the size of the tree (number of nodes)
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @return the reference to the root of the tree
	 */
	public Node getRoot() {
		return root;
	}

	/**
	 * Insert the provided element into the binary search tree. If we have duplicate key
	 * values, they are inserted into the right subtree because of the comparison in line 86
	 * below. A better way to handle duplicates is to store the frequency in the node itself. 
	 * This will prevent duplicates from causing the tree to be skewed in height.
	 * 
	 * @param element  the element to insert
	 */
	public void insert(Integer element) {
		size++;
		Node z = new Node(element);
		Node y = null;
		Node x = root;
		while (x != null) {
			y = x;
			if (z.compareTo(x) < 0)
				x = x.left;
			else
				x = x.right;
		}
		z.parent = y;
		if (y == null)
			root = z;
		else if (z.compareTo(y) < 0)
			y.left = z;
		else
			y.right = z;
	}

	/**
	 * Calculate the height of the subtree rooted at the provided node.
	 * 
	 * @param x the root of the subtree
	 * @return
	 */
	public int getHeight(Node x) {
		int height = 0;
		if (x.left != null)
			height = Math.max(height, getHeight(x.left) + 1);
		if (x.right != null)
			height = Math.max(height, getHeight(x.right) + 1);
		return height;
	}

	/**
	 * Calculate the number of elements in the subtree rooted at the provided node.
	 * 
	 * @param x the root of the subtree
	 * @return
	 */
	public int getSize(Node x) {
	      int size = 0;
	        if (x == null)
	            return 0;
	        else {
	            size = 1;
	            size += getSize(x.left);
	            size += getSize(x.right);
	            return size;
	        }
	}

	/**
	 * Print the tree out using an inorder tree traversal.
	 * 
	 * @param x  the root of the subtree
	 */
	public void inorderTreeWalk(Node x) {
		if (x != null) {
			inorderTreeWalk(x.left);
			System.out.println(x);
			inorderTreeWalk(x.right);
		}
	}
	
}
