import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class GenerateText
{
	final static int MAX_KEY = 1000000;
	final static int MAX_DOUBLE = 2148;

    private static void generateFile(int n, long seed, PrintWriter out)
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
			out.println(key+" " + value1 + " " + value2 + " " + value3);
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
			FileWriter fout = new FileWriter("data.txt");
			PrintWriter out = new PrintWriter(new BufferedWriter(fout));
			generateFile(n ,seed, out);
			out.close();
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
		}
		System.exit(0);
	}

}
