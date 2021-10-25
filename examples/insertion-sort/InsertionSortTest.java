import java.util.Random;

public class InsertionSortTest
{
	private static final int RANGE = 1000000;
	
	/**
	 * Generate random integers in the range [0,RANGE] and store in A[1..n]
	 */
	private static void generate_random_array(int A[], int n, long seed)
	{	
		Random generator = new Random(seed);
	    for (int i=1; i<=n; i++)
	        A[i] = generator.nextInt() % RANGE;
    }
	
	
	/*
	 * Check if A[1..n] is sorted in nondecreasing order
	 *            
	 */     
	
	private static boolean check_if_sorted(int A[], int n) 
	{
		for (int i=1; i<n; i++) {
			if (A[i] > A[i+1]) {
				return false;
			}
		}
		return true;
	}
	
	
	
	
	public static void main(String [] argv)
	{
		
		if (argv.length < 1) { // there must be at least one command-line arguments
			System.err.println("Usage: java InsertionSortTest <input size> [<seed>]");
			System.exit(1);
		}
	
		int n = Integer.parseInt(argv[0]);
		int seed = 1;
		if (argv.length == 3)
			seed = Integer.parseInt(argv[2]);
		
		int [] A = new int[n+1]; // n+1 since we are using A[1]..A[n]
		
		generate_random_array(A,n, seed);
		float start_time;
		float  sorting_time;
		InsertionSort sorter = new InsertionSort();

			// sort the input (and time it)
			start_time = NativeTiming.report_cpu_time();
			sorter.insertion_sort(A,1,n);
			sorting_time = NativeTiming.report_cpu_time() - start_time;
		
			// print results if correctly sorted otherwise cry foul and exit
			if (check_if_sorted(A,n)) {
				System.err.printf("InsertionSortTest: Sorting  %8d  elements took %.2f secs\n", 
				 					n, sorting_time);;
			} else { 
				System.err.println("InsertionSortTest: Sorting failed!!!!");
				System.exit(1);
			}
		}
	} 
