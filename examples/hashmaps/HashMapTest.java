import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 
 * Shows usage of a HashMap in a simple application. We read in a list of words (with duplicates).
 * The HashMap helps us identify duplicates and then we keep track of the frequency of each word (key).
 * Then we place wit load factor to see if it changes the run time.
 * @author amit
 *
 */
public class HashMapTest
{
    public static void main(String[] args) throws FileNotFoundException {
	
	HashMap<String, Integer> words = new HashMap<String, Integer>(95791, 0.5f); //use a twin prime, load factor of 90%
	
	long time = System.currentTimeMillis();
	Scanner in = new Scanner(new File("word-list"));
	while (in.hasNext()) {
	    
	    String next = in.next();
	    Integer value = words.get(next);
	    
	    if (value == null) {
		words.put(next,  1);
	    } else {
		words.put(next, value + 1);
	    }   
	}
	time = System.currentTimeMillis() - time;
	
	
	
	Iterator<String> itr =  words.keySet().iterator();
	while (itr.hasNext()) {
	    String s = itr.next();
	    Integer frequency = words.get(s);
	    System.out.println(s + " " + frequency);
	}
	
	System.out.println("\n\nHashMapTest: Found " + words.size() + " unique words");
	System.out.println("Time to build HashMap with loadFactor  0.75: " + time + " ms\n");
	
    }

}
