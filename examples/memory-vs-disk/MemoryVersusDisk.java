import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Program to demo time difference between memory access and disk access
 * @author amit
 *
 */
public class MemoryVersusDisk {

	private static void runMemoryTest(int n)
	{
		int[] buffer1 = new int[n];
		int[] buffer2 = new int[n];

		long start = System.currentTimeMillis();
		for (int k = 0; k < 10000; k++) {
			System.arraycopy(buffer1, 0, buffer2, 0, n);
		}
		long end = System.currentTimeMillis();

		System.out.printf("time for memory access = %d microseconds\n", (end - start) * 1000);
	}
	
	private static void runDiskTest(int n)
	{
		long start, end;
		
		try {
			Runtime system = Runtime.getRuntime();
			start = System.currentTimeMillis();

			char[] charBuffer = new char[n*2];
			FileWriter fout = new FileWriter("data");
			for (int k = 0; k < 1000; k++) {
				fout.write(charBuffer);
				fout.flush();
				// Call Linux system call sync to force writing to disk
				// Otherwise the OS just writes the file in memory to speed
				// up the operation
				system.exec("sync");
			}
			fout.close();

			end = System.currentTimeMillis();
			System.out.printf("time for disk access = %d microseconds\n", (end - start) * 1000);

		} catch (FileNotFoundException e) {
			System.err.println(e);
			System.exit(1);
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
		}
	}
	
	private static void runBufferedDiskTest(int n)
	{
		long start, end;
		
		try {
			start = System.currentTimeMillis();

			char[] charBuffer = new char[n*2];
			FileWriter fout = new FileWriter("data");
			for (int k = 0; k < 1000; k++) {
				fout.write(charBuffer);
			}
			fout.close();
			end = System.currentTimeMillis();
			System.out.printf("time for buffered disk access = %d microseconds\n", (end - start) * 1000);

		} catch (FileNotFoundException e) {
			System.err.println(e);
			System.exit(1);
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
		}
	}	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		int n = 1024;
		
		runMemoryTest(n);
		
		runBufferedDiskTest(n);
		
		runDiskTest(n);
		
	}

}
