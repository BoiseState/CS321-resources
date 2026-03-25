import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * Example of loading a serialized hash table from disk into memory.
 */

public class LoadTable
{
	public static void main(String[] args)
	{
		try {
			FileInputStream fileIn = new FileInputStream("hash.serial");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			@SuppressWarnings("unchecked")
			HashMap<Integer, StudentRecord> h = 
				(HashMap<Integer, StudentRecord>) in.readObject();
			System.out.println(h.toString());
			in.close();

			for (StudentRecord value: h.values()) {
				System.out.println(value);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
