package mergesort;

import java.text.DecimalFormat;
import java.util.Random;


public class Utility {
	
	// calculate runtime based on startTime
	public String calculateRunTime(long startTime) {
		long endTime = System.nanoTime();
		double elaspedTime = (double) (endTime - startTime) / 1_000_000_000;
		return new DecimalFormat("0.00").format(elaspedTime);
	}

	// creates a list of random integers
	public int[] createList(int size) {
		Random generator = new Random(size);
		int[] list = new int[size];

		for (int i = 0; i < size; i++)
			list[i] = generator.nextInt() % 10000;

		return list;
	}

	// returns boolean result whether the list is sorted correctly
	public boolean isSorted(int[] list) {
		for (int i = 1; i < list.length; i++)
			if (list[i - 1] > list[i])
				return false;

		return true;
	}

	public void checkArgs(String[] args) {
		try {
			if (args.length < 1)
				throw new IllegalArgumentException();
			if (args.length > 3)
				throw new IllegalArgumentException();

			if (args.length > 1) 
				if (!args[1].equals("-p"))
					throw new IllegalArgumentException();
			
			if(args.length == 3)
				Integer.parseInt(args[2]);
			Integer.parseInt(args[0]);

		} catch(IllegalArgumentException e) {
			System.out.println("Usage: java MergeSort <num_elements> [-p [cutoff_level]]");
			System.exit(1);
		}
	}

}
