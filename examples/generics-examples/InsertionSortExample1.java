import java.util.Random;

/**
 * A simple example of using generic code to write a generic insertion sort.
 * Note that we restrict the values of the generic parameter T to only
 * comparable types by having T be a subclass of Comparable.
 * 
 * @author amit
 *
 */
public class InsertionSortExample1<T extends Comparable<T>>
{

	public void sort(T A[]) {
		insertion_sort(A, 0, A.length - 1);
	}


	/**
	 * Sort the section of the array A[p..r] using Insertion sort. See Section 2.1
	 * of CLRS book.
	 * 
	 * @param A
	 *            the input array
	 * @param p
	 *            the starting index in the array
	 * @param r
	 *            the ending index in the array
	 */
	private void insertion_sort(T A[], int p, int r) {
		for (int j = p + 1; j <= r; j++) {
			T key = A[j];
			int i = j - 1;
			while ((i > p - 1) && (A[i].compareTo(key) > 0)) {
				A[i + 1] = A[i];
				i--;
			}
			A[i + 1] = key;
		}
	}


	private static void generateRandomData(Integer[] A) {

		int range = A.length;
		Random generator = new Random();

		for (int i = 0; i < A.length; i++) {
			A[i] = generator.nextInt(range);
		}
	}


	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("Usage: java InsertionSortExample1 <#elements>");
			System.exit(1);
		}
		int n = Integer.parseInt(args[0]);
		Integer A[] = new Integer[n];

		generateRandomData(A);

		InsertionSortExample1<Integer> sorter = new InsertionSortExample1<>();
		sorter.sort(A);

		for (Integer x : A)
			System.out.println(x);
	}

}
