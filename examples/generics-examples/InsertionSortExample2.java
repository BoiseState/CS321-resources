import java.util.Random;


/**
 * A more sophisticated example using two parameter types K, V  in a generic class. Now we have
 * keys and values in each value (element). Each value has an embedded key that we can extract 
 * because the values implement a KeyInterface. Also, each key has to be Comparable. For sorting,  
 * we don't really need two generic parameters but in other cases, this is handy to have!
 * 
 * @author amit
 *
 */
public class InsertionSortExample2<K extends Comparable<K>, V extends KeyInterface<K>>
{
    
    public void sort (V A[]) {
	insertion_sort(A, 0, A.length - 1);
    }
    
    
    /**
     * Sort the section of the array A[p..r] using Insertion sort. See Section 2.1
     * of CLRS book.
     * 
     * @param A the input array
     * @param p the starting index in the array
     * @param r the ending index in the array
     */
    private void insertion_sort(V A[], int p, int r) {
        for (int j = p + 1; j <= r; j++) {
            V value = A[j];
            K key = A[j].getKey();
            int i = j - 1;
            while ((i > p - 1) && (A[i].getKey().compareTo(key) > 0)) {
                A[i + 1] = A[i];
                i--;
            }
            A[i + 1] = value;
        }
    }
    

    private static void generateRandomData(Element[] A) {

	int range = A.length;
	Random generator = new Random();

	for (int i = 0; i < A.length; i++) {
	    int key = generator.nextInt(range);
	    String value = "bada boom";
	    A[i] = new Element(key, value);
	}
    }
    
    
    public static void main(String[] args) {
	if (args.length < 1) {
	    System.err.println("Usage: java InsertionSortExample2 <#elements>");
	    System.exit(1);
	}
	int n = Integer.parseInt(args[0]);
	Element A[] = new Element[n];
	
	generateRandomData(A);
	
	InsertionSortExample2<Integer, Element> sorter = new InsertionSortExample2<Integer, Element>();
	sorter.sort(A);
	
	for (Element x: A)
	    System.out.println(x);
    }
    
}
