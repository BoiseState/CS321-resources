import java.util.Random;

/**
 * A test driver for experimenting with mergesort.
 * 
 * @author amit
 *
 */
public class MergeSortTest
{
    private static final int RANGE = 1000000;
    private static boolean sentinels = false;

    /**
     * Generate random integers in the range [0,RANGE] and store in A[1..n].
     * 
     * @param A
     *            the output array
     * @param n
     *            the size of the output
     * @param seed
     *            the random seed
     */
    private static void generate_random_array(int A[], int n, long seed) {
        Random generator = new Random(seed);
        for (int i = 1; i <= n; i++)
            A[i] = generator.nextInt() % RANGE;
    }


    /**
     * Check if A[1..n] is sorted in nondecreasing order.
     * 
     * @param A the input array
     * @param n the size of array A[1..n]
     * @return true if sorted, false otherwise
     */
    private static boolean check_if_sorted(int A[], int n) {
        for (int i = 1; i < n; i++) {
            if (A[i] > A[i + 1]) {
                return false;
            }
        }
        return true;
    }


    /**
     * The main driver to process arguments and run the experiment.
     * 
     * @param argv the command line arguments
     */
    public static void main(String[] argv) {

        if (argv.length < 2) { // there must be at least two command-line arguments
            System.err.println("Usage: java MergeSortTest <input size> <cutoff size> [<seed>]");
            System.exit(1);
        }

        int n = Integer.parseInt(argv[0]);
        int cutoff = Integer.parseInt(argv[1]);
        int seed = 1;
        if (argv.length == 3) seed = Integer.parseInt(argv[2]);

        int[] A = new int[n + 1]; // n+1 since we are using A[1]..A[n]

        generate_random_array(A, n, seed);
        long start_time;
        long sorting_time;
        MergeSort sorter = new MergeSort();

        // sort the input (and time it)
        start_time = System.currentTimeMillis();
        sorter.mergesort(A, 1, n, cutoff);
        sorting_time = System.currentTimeMillis() - start_time;

        // print results if correctly sorted otherwise cry foul and exit
        if (check_if_sorted(A, n)) {
            System.err.printf("MergeSortTest: Sorting  %8d  elements took %.2f secs without sentinels\n", 
                    n, sorting_time / 1000.0);
        } else {
            System.err.println("MergeSortTest: Sorting failed!!!!");
            System.exit(1);
        }
    }

}
