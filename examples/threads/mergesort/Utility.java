package mergesort;

import java.util.Random;

public class Utility
{

    private final static int RANGE = 1000000;

    /**
     * Create an array of random integers in the range [0...RANGE).
     * 
     * @param size
     *                 the number of elements to generate
     * @return
     */
    public int[] createList(int size)
    {
	Random generator = new Random(size);
	int[] array = new int[size];

	for (int i = 0; i < size; i++)
	    array[i] = generator.nextInt() % RANGE;

	return array;
    }


    /**
     * Check if the array is sorted.
     * 
     * @param array
     *                  the array to check for being sorted
     * @return true if the array is sorted, false otherwise
     */
    public boolean isSorted(int[] array)
    {
	for (int i = 1; i < array.length; i++)
	    if (array[i - 1] > array[i]) return false;

	return true;
    }


    /**
     * Check command line arguments.
     * 
     * @param args
     */
    public void checkArgs(String[] args)
    {
	try {
	    if (args.length < 1) throw new IllegalArgumentException();
	    if (args.length > 3) throw new IllegalArgumentException();

	    if (args.length > 1) if (!args[1].equalsIgnoreCase("--useThreads")) throw new IllegalArgumentException();

	    if (args.length == 3) Integer.parseInt(args[2]);
	    Integer.parseInt(args[0]);

	} catch (IllegalArgumentException e) {
	    System.out.println("Usage: java MergeSort <num_elements> [--useThreads [cutoff_level]]");
	    System.exit(1);
	}
    }

}
