
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.Random;


/**
 * Generates a sample binary data file that can be used to test the ExternalBinarySearch class.
 * @author amit
 *
 */
public class GenerateData {

	private static int MAX_KEY = 1000000;
	
	/**
	 * Generate sorted binary data file with some fictitious data.
	 * @param n
	 * @param seed
	 * @param file
	 * @throws IOException
	 */
	private static void generateFile(int n, long seed, DataOutputStream file) 
	throws IOException
	{
	   Random generator = new Random(seed);
	   for (int i = 0; i < n; i++) {
	        int key =  i * 2;
	        double value1 = Math.abs(generator.nextInt()/((double)MAX_KEY));
	        double value2 = Math.abs(generator.nextInt()/((double)MAX_KEY));
	        double value3 = Math.abs(generator.nextInt()/((double)MAX_KEY));
	        file.writeInt(key);
	        file.writeDouble(value1);
	        file.writeDouble(value2);
	        file.writeDouble(value3);
			file.flush();
	    }
	}
	
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) 
	throws IOException 
	{
		long seed = 0;

		if (args.length < 1) {
			System.err.println("Usage: java GenerateData <n> [<seed>]");
			System.exit(1);
		}
		int n = Integer.parseInt(args[0]);
		if (args.length == 2) {
			seed = Long.parseLong(args[1]);
		}
		
		DataOutputStream file = new DataOutputStream(new FileOutputStream("data.bin"));
		generateFile(n, seed, file);
		file.close();

		System.out.println("GenerateData: generated data file for binary search: data.bin");
	}

}
