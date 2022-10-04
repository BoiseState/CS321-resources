import java.lang.reflect.Array;
import java.util.LinkedList;


/**
 * Uses the Set class to show how to pass in various types.
 * 
 * @author amit
 *
 */
public class SetTest
{
    /**
     * @param args
     */
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        
        Set<Integer> numbers = new Set<Integer>(Integer.class, 10);
        Integer[] values = new Integer[10];
        for (int i = 0; i < 10; i++) {
            values[i] = i;
        }
        numbers.addElements(values);
        System.out.println(numbers);
        
        
        
        Set<String> setOfWords = new Set<String>(String.class, 10);
        String[] words = new String[10];
        for (int i = 0; i < 10; i++) {
            words[i] = i + " word";
        }
        setOfWords.addElements(words);
        System.out.println(setOfWords);

   

        Set<Task> setOfTasks = new Set<Task>(Task.class, 10);
        Task[] tasks = new Task[10];
        for (int i = 0; i < 10; i++) {
            tasks[i] = new Task(i);
        }
        setOfTasks.addElements(tasks);
        System.out.println(setOfTasks);

        
        // we can even put generic types inside our set!       
        LinkedList<Integer> list = new LinkedList<Integer>();
        Set<LinkedList<Integer>> setOfLists = new Set<LinkedList<Integer>>((Class<LinkedList<Integer>>)list.getClass(), 10);
        LinkedList<Integer>[] lists = (LinkedList<Integer>[]) Array.newInstance((Class<LinkedList<Integer>>)list.getClass(), 10);
        for (int i = 0; i < 10; i++) {
            lists[i] = new LinkedList<Integer>();
        }
        setOfLists.addElements(lists);
        System.out.println(setOfLists);
    }

}
