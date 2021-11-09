/**
 * 
 */

/**
 * A simple generic binary search tree implementation.
 * @author amit
 *
 */
public class BinarySearchTree {
	
	private int size;
	private Node root;
	
	/**
	 * Inner class to represent a binary seaerch tree node.
	 * @author amit
	 *
	 */
	public class Node implements Comparable<Node> {
		Node left;
		Node right;
		Node parent;
		Integer key;
		
		/**
		 * @param element
		 */
		public Node(Integer element) {
			key = element;
			left = right = parent = null;
		}
		
		/*
		 * Implements Comparable interface. 
		 */
		public int compareTo(BinarySearchTree.Node otherNode){
			return key.compareTo(otherNode.key);
		}
		
		
		/**
		 *
		 */
		public String toString() {
			return "Node:  key = " + key;
		}	
	}
	
	/**
	 * 
	 */
	public BinarySearchTree() {
		root = null;
		size = 0;
	}
	
	/**
	 * @return the size of the tree
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
	 * Insert the provided element into the binary search tree.
	 * @param element
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
	 * @param x  the root of the subtree
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
	 * @param x
	 * @return
	 */
	public int getSize(Node x) {
		int size = 0;
		if (x.left != null || x.right != null)
			size++;
		if (x.left != null)
			size +=  getSize(x.left);
		if (x.right != null)
			size += getSize(x.right);
		return size;	
	}
	
	/**
	 * Print the tree out using an inorder tree traversal.
	 * @param x
	 */
	public void inorderTreeWalk(Node x) {
		if (x != null) {
			inorderTreeWalk(x.left);
			System.out.println(x);
			inorderTreeWalk(x.right);
		}
	}
}
