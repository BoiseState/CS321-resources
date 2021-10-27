import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;

/**
  Example of freeze-drying (or serializing) a hash table to disk.
  Any class that extends the Serializable intercace can be saved to disk.
  The serialization interface has no methods or fields and serves only
  to identify the semantics of being serializable.
*/

public class SaveTable {
	public static void main (String [] args) 
	{
		Hashtable<Integer, StudentRecord> h = new Hashtable<Integer, StudentRecord>();  // implements Serializable
		// Hashtable implements the serializable interface
		// so we can try to  freeze dry it
		StudentRecord s1 = new StudentRecord("Larry", 11122);
		StudentRecord s2 = new StudentRecord("Moe", 11234);
		StudentRecord s3 = new StudentRecord("Curly", 32141);
		StudentRecord s4 = new StudentRecord("MooMoo", 22211);
		
		h.put(new Integer(s1.getId()), s1);
		h.put(new Integer(s2.getId()), s2);
		h.put(new Integer(s3.getId()), s3);
		h.put(new Integer(s4.getId()), s4); 
		
		try {
			FileOutputStream fileout = new FileOutputStream("hash.serial");
			ObjectOutputStream out = new ObjectOutputStream(fileout);
			out.writeObject(h);
			out.close();
			System.out.println("SaveTable: serializing hash table in file hash.serial");
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}	


