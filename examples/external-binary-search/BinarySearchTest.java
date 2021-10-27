import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.Random;

public class BinarySearchTest
{
	private int DEBUG = 0;

	private void conductRandomSearches(FileChannel dataFile, int n, int MAX_KEY)
	throws IOException
	{
		int i;
		int key;
	    Record record;
		Random generator = new Random();
		ExternalBinarySearch searcher = new ExternalBinarySearch();
		searcher.setDebug(DEBUG);

		for (i=0; i<n; i++) {
			key = generator.nextInt(MAX_KEY);
			if (DEBUG >= 1) {
				System.out.println("\nSearching for key = " + key + " \n");
			}
			record = searcher.search(dataFile, key);
			if (DEBUG >= 1) {
				if (record != null) {
					System.out.println(record);
					System.out.println("found: record with key "+key);
				} else {
					System.out.println("not found!: record with key "+key);
				}
			}
		}
	}


	public static void main (String [] argv)
	{
		long count;
		BinarySearchTest tester = new BinarySearchTest();
		Record record = new Record();

		if (argv.length != 3) {
			System.err.println("Usage: java BinarySearchTest <data file name> <number of searches> <max key>");
			System.exit(1);
		}
		int n = Integer.parseInt(argv[1]);
		int maxKey = Integer.parseInt(argv[2]);
		try {
			FileChannel dataFile = new RandomAccessFile(argv[0],"r").getChannel();

			count = dataFile.size()/(long ) record.getDiskSize();
			if (tester.DEBUG >= 1) {
		   		System.err.println("data file has " + count + "  records");
			}

			long startTime = System.currentTimeMillis();
			tester.conductRandomSearches(dataFile, n, maxKey);
			long totalTime = System.currentTimeMillis() - startTime;

			System.out.println("Elapsed time for " + n + " searches = " + totalTime/1000.0 
								+ " seconds"); 
			dataFile.close();
			System.exit(0);
		} catch (IOException e) {
		   	System.err.println(e);
			System.exit(1);
		}
	}
}
