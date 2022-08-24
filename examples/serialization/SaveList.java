import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

/**
 * Example of freeze-drying (or serializing) a list to disk. Any class that
 * extends the Serializable interface can be saved to disk. The serialization
 * interface has no methods or fields and serves only to identify the semantics
 * of being serializable.
 */

public class SaveList
{
    public static void main(String[] args) {
        
        // LinkedList implements the serializable interface
        // so we can try to freeze dry it!
        LinkedList<String> dance = new LinkedList<String>();

        String s1 = "fikshun";
        String s2 = "dab";
        String s3 = "wobble";
        String s4 = "shuffle";
        dance.add(s1);
        dance.add(s2);
        dance.add(s3);
        dance.add(s4);

        try {
            FileOutputStream fileout = new FileOutputStream("list.serial");
            ObjectOutputStream out = new ObjectOutputStream(fileout);
            out.writeObject(dance);
            out.close();
            System.out.println("SaveList: serializing list of dances in file list.serial");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
