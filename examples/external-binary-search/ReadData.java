
import java.io.IOException;
import java.io.EOFException;
import java.io.DataInputStream;
import java.io.FileInputStream;


/**
 * 
 * Demonstrates how to read a binary file.
 * @author amit
 *
 */
public class ReadData {

	/**
	 * Read the binary file and display on the console.
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
		    System.out.println("ReadData: reached end of file");
		}
	}
	
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) 
	throws IOException 
	{
		if (args.length < 1) {
			System.err.println("Usage: java ReadData <data file>");
			System.exit(1);
		}
		
		DataInputStream file = new DataInputStream(new FileInputStream("data.bin"));
		readFile(file);
		file.close();
	}

}
