import java.util.Random;



public class MergeSortTest
{
	private static final int RANGE = 1000000;
	private static boolean sentinels = false;
	
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
		
		if (argv.length < 2) { // there must be at least two command-line arguments
			System.err.println("Usage: java MergeSortTest <input size> <cutoff size> <sentinels|nosentinels>  [<seed>]");
			System.exit(1);
		}
	
		int n = Integer.parseInt(argv[0]);
		int cutoff = Integer.parseInt(argv[1]);
		if (argv[2].startsWith("s"))
			sentinels = true;
		int seed = 1;
		if (argv.length == 4)
			seed = Integer.parseInt(argv[3]);
		
		int [] A = new int[n+1]; // n+1 since we are using A[1]..A[n]
		
		generate_random_array(A,n, seed);
		float start_time;
		float  sorting_time;
		MergeSort sorter = new MergeSort();

		if (!sentinels) {
			// sort the input (and time it)
			start_time = NativeTiming.report_cpu_time();
			sorter.mergesort(A,1,n, cutoff);
			sorting_time = NativeTiming.report_cpu_time() - start_time;
		
			// print results if correctly sorted otherwise cry foul and exit
			if (check_if_sorted(A,n)) {
				System.err.printf("MergeSortTest: Sorting  %8d  elements took %.2f secs without sentinels\n", n, sorting_time);;
			} else { 
				System.err.println("MergeSortTest: Sorting failed!!!!");
				System.exit(1);
			}
		} else {
			start_time = NativeTiming.report_cpu_time();
			sorter.mergesort_with_sentinels(A,1,n, cutoff);
			sorting_time = NativeTiming.report_cpu_time() - start_time;
		
			// print results if correctly sorted otherwise cry foul and exit
			if (check_if_sorted(A,n)) {
				System.err.printf("MergeSortTest: Sorting  %8d  elements took %.2f secs with sentinels\n", n, sorting_time);;
			} else { 
				System.err.println("MergeSortTest: Sorting failed!!!!");
				System.exit(1);
			}
		}
	} // main

} // MergeSortTest
