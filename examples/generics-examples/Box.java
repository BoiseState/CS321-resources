import java.util.LinkedList;

/**
 * A simple example of using a generic type in a wrapper class. This also illustrates how
 * we can limit the size of a linked list.
 * 
 * @author amit
 *
 */
public class Box<T>
{
    private LinkedList<T> box;
    private int size;
    
    public Box(int size) {
	this.size = size;
	box = new LinkedList<T>();
    }
    
    public T add(T toy) {
	T removed = null;
	if (box.size() == size) { 
	    removed = box.removeLast();
	    box.addFirst(toy);
	} else {
	    box.addFirst(toy);
	}
	return removed;
    }
    
    public int getSize() {
	return size;
    }
    
    public String toString() {
	String s = "";
	for (int i = 0; i < box.size(); i++)
	    s += box.get(i) + " ";
	s += "\n";
	return s;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
	// TODO Auto-generated method stub

	Box<Integer> myToys = new Box<Integer>(5);
	System.out.println("Box: created a box of size " + myToys.getSize());
	
	for (int i = 0; i < 8; i++) {
	    Integer removed = myToys.add(i);
	    System.out.println("MyToys box has " + myToys);
	    if (removed != null) System.out.println("Box: full - removed element " + removed);
	}
	System.out.println("MyToys box has " + myToys);
    }

}
