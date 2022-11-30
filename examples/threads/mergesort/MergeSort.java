package mergesort;

public class MergeSort {
	private static int cutoffLevel;
	private final static int DEFAULT_CUTOFF = 4;
	private static Utility helper = new Utility();

	public static void main(String[] args) {
		helper.checkArgs(args);
		cutoffLevel = args.length == 3 ? Integer.parseInt(args[2]) : DEFAULT_CUTOFF;
		boolean parallel = args.length !=1 ? true : false;;
		int numElements = Integer.parseInt(args[0]);

		int list[] = helper.createList(numElements);		long startTime = System.nanoTime();
		
		MergeSort sorter = new MergeSort();

		if (parallel)
			sorter.parallelMergeSort(list, 0, numElements-1, 0);
		else
			sorter.mergeSort(list, 0, numElements-1);

		if (!helper.isSorted(list))
			System.out.println("Merge Sort did not successfully sort list!");
		else
			System.out.println("Merge Sort with " + (parallel ? "" : "out ") + "threads\n" + "\tNum elements:  "
					+ numElements + "\n\tRuntime: " + helper.calculateRunTime(startTime) + " seconds");

	}

	// Recursively splits list[] in two halves to be sorted by merge function.
	public void mergeSort(int[] list, int start, int end) {
		if (start >= end)
			return;

		int midIndex = start + (end-start)/ 2;
		
		mergeSort(list, start, midIndex);
		mergeSort(list, midIndex + 1, end);

		merge(list, start, midIndex,  end);
	}

	// Splits list[] in two halves to be sorted by merge function.
	//Left and Right side are recursively split and done in parallel by the use of threads
	public void parallelMergeSort(int[] list, int start, int end, int level) {
		// call regular mergeSort if cutoff level is reached
		if (level >= cutoffLevel) {
			mergeSort(list, start, end);
			return;
		}
		if (start >= end)
			return;
		
		int midIndex = start + (end-start)/ 2;
		
		//Thread that will call parallel merge on left list (this does not start thread)
		Thread leftThread = new Thread() {
			public void run() {
				parallelMergeSort(list, start,  midIndex,  level + 1);
			}
		};

		// creates thread that will call parallel merge on right list (this does not start thread)
		Thread rightThread = new Thread() {
			public void run() {
				parallelMergeSort(list, midIndex + 1, end, level + 1);
			}
		};

		// starts both threads that will execute in parallel
		leftThread.start();
		rightThread.start();
		try {
			// calling thread waits until leftThread terminates
			leftThread.join();
			// calling thread waits until rightThread terminates
			rightThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		merge(list, start, midIndex, end);

	}

	// Compare subArray list[start..midIndex] with list[midIndex+1...end] to sort elements in order
	private void merge(int[] list, int start, int midIndex, int end) {
		int[] temp = new int[end-start+1];
		int l = start, r = midIndex+1, i = 0;

		while (l <= midIndex && r <= end) {
			if (list[l] <= list[r])
				temp[i++] = list[l++];
			else
				temp[i++] = list[r++];
		}
		
		while (l <= midIndex)
			temp[i++] = list[l++];

		while (r <= end)
			temp[i++] = list[r++];
		
		i = 0;
		for(int x = start; x <=end; x++) 
			list[x] = temp[i++];

	}

}
