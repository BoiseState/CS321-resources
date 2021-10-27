
/**
  Example of loading a serialized hash table from disk into memory.
*/

import java.io.*;
import java.util.*;

public class LoadTable {
	public static void main (String [] args) {
		try {
			FileInputStream fileIn = new FileInputStream("hash.serial");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Hashtable<Integer, StudentRecord> h = (Hashtable<Integer, StudentRecord>) in.readObject();
			System.out.println(h.toString());

			Enumeration list = h.elements();
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


