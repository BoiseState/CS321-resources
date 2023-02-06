import java.util.Random;

/**
 * 
 * A tester class for insertion sort. It allows us to generate a random array,
 * sort it using insertion sort, and then check if it is sorted.
 * 
 * @author amit
 *
 */
public class InsertionSortTest
{
    private int[] A;
    private int n;
    private int seed;
    private static final int RANGE = 1000000;

    /**
     * Constructor: process command line arguments and allocate array.
     */
    public InsertionSortTest(String[] argv) {
        processCmdlineArgs(argv);
        A = new int[n + 1]; // n+1 since we are using A[1]..A[n]
    }


    /**
     * Process the command line arguments.
     * 
     * @param argv
     */
    private void processCmdlineArgs(String[] argv) {
        if (argv.length < 1) { // there must be at least one command-line argument
            System.err.println("Usage: java InsertionSortTest <input size> [<seed>]");
            System.exit(1);
        }

        n = Integer.parseInt(argv[0]);
        seed = 1;
        if (argv.length == 3) seed = Integer.parseInt(argv[2]);
    }


    /**
     * @return the size of array to be sorted
     */
    public int getSize() {
        return n;
    }


    /**
     * Generate random integers in the range [0,RANGE] and store in A[1..n].
     * 
     * @param A
     */
    public void generate_random_array() {
        Random generator = new Random(seed);
        for (int i = 1; i <= n; i++)
            A[i] = generator.nextInt() % RANGE;
    }


    /**
     * Check if A[1..n] is sorted in monotonically increasing order.
     * 
     * @param A
     *            The array to check
     * @param n
     *            The size of the array
     * @return true if array is sorted, false otherwise
     */
    public boolean check_if_sorted() {
        for (int i = 1; i < n; i++) {
            if (A[i] > A[i + 1]) {
                return false;
            }
        }
        return true;
    }


    /**
     * Sorts the random array and times it.
     * 
     * @return how long it took (in milliseconds)
     */
    public long runSort() {
        long start_time;
        long sorting_time;
        InsertionSort sorter = new InsertionSort();

        start_time = System.currentTimeMillis();
        sorter.insertion_sort(A, 1, n);
        sorting_time = System.currentTimeMillis() - start_time;
        return sorting_time;
    }


    /**
     * The main method processes command line arguments and runs the sort.
     * 
     * @param argv the command line arguments
     */
    public static void main(String[] argv) {
        InsertionSortTest tester = new InsertionSortTest(argv);
        tester.generate_random_array();
        long sortingTime = tester.runSort();

        // print results if correctly sorted otherwise cry foul and exit
        if (tester.check_if_sorted()) {
            System.err.printf("InsertionSortTest: Sorting  %8d  elements took %.2f secs\n", tester.getSize(),
                    sortingTime / 1000.0);
        } else {
            System.err.println("InsertionSortTest: Sorting failed!!!!");
            System.exit(1);
        }
    }
}
