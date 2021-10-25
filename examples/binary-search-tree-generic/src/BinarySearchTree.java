/**
 * 
 */

/**
 * A simple generic binary search tree implementation.
 * @author amit
 *
 */
public class BinarySearchTree<T extends Comparable<T>> {
	
	private int size;
	private Node<T> root;
	
	/**
	 * Inner class to represent a binary seaerch tree node.
	 * @author amit
	 *
	 */
	public class Node<S extends Comparable<S>> implements Comparable<Node<S>> {
		Node<S> left;
		Node<S> right;
		Node<S> parent;
		S key;
		
		/**
		 * @param element
		 */
		public Node(S element) {
			key = element;
			left = right = parent = null;
		}
		
		/*
		 * Implements Comparable interface. 
		 */
		public int compareTo(Node<S> otherNode){
			return key.compareTo(otherNode.key);
		}
		
		/**
		 * @override java.lang.Object.toString()
		 */
		public String toString() {
			return "Node:  key = " + key.toString();
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
	public Node<T> getRoot() {
		return root;
	}

	/**
	 * Insert the provided element into the binary search tree.
	 * @param element
	 */
	public void insert(T element) {
		size++;
		Node<T> z = new Node<T>(element);
		Node<T> y = null;
		Node<T> x = root;
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
	public int getHeight(Node<T> x) {
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
	public int getSize(Node<T> x) {
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
	public void inorderTreeWalk(Node<T> x) {
		if (x != null) {
			inorderTreeWalk(x.left);
			System.out.println(x);
			inorderTreeWalk(x.right);
		}
	}
}
