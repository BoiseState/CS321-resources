import java.util.HashMap;
import java.util.LinkedHashMap;

public class LinkedHashMapSample
{

    @SuppressWarnings("deprecation")
	public static void main(String[] args) {

        LinkedHashMap<String, Integer> designDictionary = new LinkedHashMap<String, Integer>();

        // basic software engineering design principles

        // The HashMap require an Integer object as the value, but we are providing
        // the primitive int value below. Java converts it into the Integer object for
        // us.
        designDictionary.put("decomposition", 1);
        designDictionary.put("aggregation", 2);
        designDictionary.put("inheritance", 3);
        designDictionary.put("encapsulation", 4);
        designDictionary.put("modularity", 5);

        // principles for cybersecurity

        // We can actually use the constructor to build a Integer object.
        // We are showing it here to make a point but t is deprecated as Java
        // will automagically do that for us!
        designDictionary.put("least privilege", new Integer(7));
        designDictionary.put("current authority", 8);

        // principles for human computer interaction and machine learning!
        designDictionary.put("respect!", 6);

        // value can be null, for a given key
        designDictionary.put("elegance", null);

        // even the key can be null
        designDictionary.put(null, 0);
        // but there can only be one null key
        designDictionary.put(null, 100);

        System.out.println("Size of the map: " + designDictionary.size());
        // Print the dictionary -- note the order of items is in the order we
        // inserted them -- due to the linked list
        // inside with the HashMap
        System.out.println(designDictionary);

        String key = "decomposition";
        if (designDictionary.containsKey(key)) {
            Integer value = designDictionary.get(key);
            System.out.println("value or key " + key + " is: " + value);
        }
    }

}
