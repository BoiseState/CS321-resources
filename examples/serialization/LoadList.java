import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

/**
 * Example of loading a serialized hash table from disk into memory.
 */

public class LoadList
{
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
	
	    FileInputStream fileIn;
        try {
            fileIn = new FileInputStream("list.serial");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            
            LinkedList<String> list = (LinkedList<String>) in.readObject();
            System.out.println(list.toString());
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }    
	}
}
