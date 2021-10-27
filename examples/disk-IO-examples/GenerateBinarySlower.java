import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;



/**
  Illusrates how to write a binary file. Show difference between buffered and unbuffered I/O
  @author Amit Jain

*/

public class GenerateBinarySlower
{
	final static int MAX_KEY = 1000000;
	final static int MAX_DOUBLE = 2148;
	final static int RECORD_SIZE = 28;
	final static int NUM_OF_RECORD_PER_BUFFER = 146;
	final static int BUFFER_SIZE = RECORD_SIZE * NUM_OF_RECORD_PER_BUFFER;


    private static void generateFile(int n, long seed, DataOutputStream fout)
	throws IOException
    {
		int i;
		int key;
		double value1;
		double value2;
		double value3;
		Random generator = new Random(seed);
	
		for (i=0; i<n; i++) {
			/*key = generator.nextInt(MAX_KEY); */
			key = 2 * i;
			value1 = generator.nextDouble() * MAX_DOUBLE;
			value2 = generator.nextDouble() * MAX_DOUBLE;
			value3 = generator.nextDouble() * MAX_DOUBLE;
			fout.writeInt(key);
			fout.writeDouble(value1);
			fout.writeDouble(value2);
			fout.writeDouble(value3);
		}
	}

	public static void main(String argv[])
	{
		int n;
		long seed = 0;

		if (argv.length < 1) {
			System.err.println("Usage: java GenerateText <n> [<seed>]\n");
			System.exit(1);
		}

		n = Integer.parseInt(argv[0]);
	    if (argv.length == 2) {
			seed = Long.parseLong(argv[1]);
		} 


		try {
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("data.bin"));
			DataOutputStream fout = new DataOutputStream(out);
			generateFile(n ,seed, fout);
			fout.close();
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);

		}
		System.exit(0);
	}

}
