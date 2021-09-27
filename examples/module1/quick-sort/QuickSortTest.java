import java.util.Random;


public class QuickSortTest
{
	private static final int RANGE = 1000000;
	
	/**
	 * Generate random integers in the range [0,RANGE] and store in A[1..n]
	 */
	private static void generate_random_array(int A[], int n, long seed)
	{	
		Random generator = new Random(seed);
	    for (int i = 1; i <= n; i++)
	        A[i] = generator.nextInt() % RANGE;
    }
	
	
	/*
	 * Check if A[1..n] is sorted in nondecreasing order
	 *            
	 */     
	
	private static boolean check_if_sorted(int A[], int n) 
	{
		for (int i = 1; i < n; i++) {
			if (A[i] > A[i+1]) {
				return false;
			}
		}
		return true;
	}
	
	
	
	
	public static void main(String [] argv)
	{
		
		if (argv.length < 1) { // there must be at least one command-line arguments
			System.err.println("Usage: java QuickSortTest <input size>  [<seed>]");
			System.exit(1);
		}
	
		int n = Integer.parseInt(argv[0]);
		long seed = 0;
		if (argv.length == 2)
			seed = Long.parseLong(argv[1]);
		
		int [] A = new int[n+1]; // n+1 since we are using A[1]..A[n]
		
		generate_random_array(A, n, seed);
		long start_time;
		long  sorting_time;
		QuickSort sorter = new QuickSort();

		// sort the input (and time it)
		start_time = System.currentTimeMillis();
		sorter.quicksort(A, 1, n);
		sorting_time = System.currentTimeMillis() - start_time;
		
		// print results if correctly sorted otherwise cry foul and exit
		if (check_if_sorted(A,n)) {
			System.err.printf("QuickSortTest: Sorting  %8d  elements took %.2f secs without sentinels\n", n, sorting_time);;
		} else { 
			System.err.println("QuickSortTest: Sorting failed!!!!");
			System.exit(1);
		}
	} 
} 
