import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Program to demo time difference between memory access and disk access.
 * @author amit
 *
 */
public class MemoryVersusDisk {
    
    private static long memorySpeed;
    private static long diskSpeed;
    private static long diskBufferedSpeed;


	/**
	 * Test speed of memory access by copying an array multiple times.
	 * @param n number of copies to make
	 */
	private static void runMemoryTest(int n)
	{
		int[] buffer1 = new int[n];
		int[] buffer2 = new int[n];

		long start = System.currentTimeMillis();
		for (int k = 0; k < 10000; k++) {
			System.arraycopy(buffer1, 0, buffer2, 0, n);
		}
		long end = System.currentTimeMillis();

		memorySpeed = (end - start); //in milliseconds
	}
	
	/**
	 * Test raw disk access speed. Note that this requires calling an operating
	 * system specific command to avoid memory buffer used by the operating system
	 * that masks the slowness of disk access. 
	 * 
	 * The char type uses 2 bytes whereas in uses 4 bytes, so we make an array of 
	 * 2*n char here to match the n int array in the memory test.
	 * 
	 * @param n number of access to try
	 */
	private static void runDiskTest(int n)
	{
		long start, end;
		
		try {
			Runtime system = Runtime.getRuntime();
			start = System.currentTimeMillis();

			char[] charBuffer = new char[n*2];
			FileWriter fout = new FileWriter("data");
			for (int k = 0; k < 10000; k++) {
				fout.write(charBuffer);
				fout.flush();
				// Call Linux system call sync to force writing to disk
				// Otherwise the OS just writes the file in memory to speed
				// up the operation
				system.exec("sync");
			}
			fout.close();
			
			end = System.currentTimeMillis();
			diskSpeed = end - start;
		} catch (FileNotFoundException e) {
			System.err.println(e);
			System.exit(1);
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
		}
	}
	
	/**
	 * Run disk access speed using built-in buffering in the operating system.
	 * This shows that even with buffering, disk access is much slower than memory access.
	 * 
	 * The char type uses 2 bytes whereas in uses 4 bytes, so we make an array of 
     * 2*n char here to match the n int array in the memory test.
     * 
	 * @param n Number of disk accessed to try.
	 */
	private static void runBufferedDiskTest(int n)
	{
		long start, end;
		
		try {
			start = System.currentTimeMillis();

			char[] charBuffer = new char[n*2];
			FileWriter fout = new FileWriter("data");
			for (int k = 0; k < 10000; k++) {
				fout.write(charBuffer);
			}
			fout.close();
			end = System.currentTimeMillis();
			diskBufferedSpeed = end - start;
		} catch (FileNotFoundException e) {
			System.err.println(e);
			System.exit(1);
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
		}
	}	
	
	
	/**
	 * The main driver to test the three scenarios and print results.
	 * 
	 * @param args No command line arguments are accepted.
	 */
	public static void main(String[] args) 
	{
		int n = 1024; // do this many operations
		
		runMemoryTest(n);
	    System.out.printf("time for memory access = %d microseconds\n", memorySpeed);
		
		runBufferedDiskTest(n);
        System.out.printf("time for buffered disk access = %d microseconds\n", diskBufferedSpeed);
		
		runDiskTest(n);
        System.out.printf("time for disk access = %d microseconds\n", diskSpeed);
        
        System.out.println("Raw disk access is " + diskSpeed/(double) memorySpeed + " times slower!");
		
	}

}
