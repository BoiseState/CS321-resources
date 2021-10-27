
import java.io.IOException;
import java.io.EOFException;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.Random;


public class ReadData {

	private static int MAX_KEY = 1000000;
	
	/**
	 * @param n
	 * @param seed
	 * @param file
	 * @throws IOException
	 */
	private static void readFile(DataInputStream file) 
	throws IOException
	{
		try {
	   		for (;;) {
	        	System.out.print(file.readInt()+" ");
	        	System.out.printf(" %8.2f %8.2f %8.2f \n", file.readDouble(), 
										  file.readDouble(),file.readDouble());
	    	}
		} catch (EOFException e) {
			// reached end of file
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
			System.err.println("Usage: java ReadData <data file>");
			System.exit(1);
		}
		
		DataInputStream file = new DataInputStream(new FileInputStream("data.bin"));
		readFile(file);
		file.close();
	}

}
