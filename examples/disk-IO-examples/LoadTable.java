import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Enumeration;
import java.util.Hashtable;

/**
  Example of loading a serialized hash table from disk into memory.
*/


public class LoadTable {
	public static void main (String [] args) {
		try {
			FileInputStream fileIn = new FileInputStream("hash.serial");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			@SuppressWarnings("unchecked")
			Hashtable<Integer, StudentRecord> h = (Hashtable<Integer, StudentRecord>) in.readObject();
			System.out.println(h.toString());
			in.close();

			Enumeration<StudentRecord> list = h.elements();
			while (list.hasMoreElements())
			{
				System.out.println(list.nextElement());
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}	


