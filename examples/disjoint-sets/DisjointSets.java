
/**
 * Disjoint Sets implementation from CLRS, Chapter 19.
 * Can perform m operations on n elements in O(m lg*n) time, which is 
 * O(m) for all practical purposes.
 * 
 * Shows use of assertions for checking pre/post conditions.
 * Run with java -enableassertions to use assert statements
 * 
 * @author amit
 */
public class DisjointSets<T>
{
	SetElement<T> [] sets;
	long counter = 0;
	int n = 0;
	
	/**
	 * @param n
	 */
	public DisjointSets(int n) 
	{
		sets = (SetElement<T>[]) new SetElement<?>[n];
		this.n = n;
	}
	
	/**
	 * Create a new set with x as its only member.
	 * @param obj
	 * @param x
	 */
	public void makeSet(T obj, int x) 
	{
		assert((x >= 0) && (x < n));
		SetElement<T> element = new SetElement<T>(obj);
		element.setParent(x);
		element.setSize(1);
		sets[x] = element;
	}
	
	/**
	 * 
	 * @param index
	 * @return
	 */
	public SetElement<T> getElement(int index) { return sets[index]; }
	
	/**
	 * 
	 * @return
	 */
	public long getCounter() { return counter; }
	
	/**
	 * Find what set the element x belongs to.
	 * @param x
	 * @return
	 */
	public int find(int x) 
	{
		assert((x >= 0) && (x < n));
		counter++;
		int parent = sets[x].getParent();
	    if (parent != x) {
	        sets[x].setParent(find(parent));
	    }
	    return sets[x].getParent();
	}
	
	/**
	 * Union the two sets that contain x and y.
	 * @param x
	 * @param y
	 * @return
	 */
	public int union(int x, int y) 
	{
		assert((x >= 0) && (x < n));
		assert((y >= 0) && (y < n));

		int sizex = sets[x].getSize();
		int sizey = sets[y].getSize();
		if (sizex > sizey) {
	        sets[y].setParent(x);
	        sets[x].setSize(sizex+sizey);
	    } else {
	        sets[x].setParent(y);
	        sets[y].setSize(sizex+sizey);
	    }
		return y;
	}
	
	/**
	 * 
	 * Print all the disjoint sets.
	 */
	public void printSets()
	{
		System.out.println();
		for (int i=0; i<n; i++) {
			System.out.println(sets[i]);
		}
		System.out.println();
		
	}
	
		
}
