

/**
 * Simple class to demo a concept.
 * Javadocs left incomplete on purpose.
 * @author amit
 *
 */
public class Task {
    private int priority;
    
    public Task(int priority) {
        this.priority = priority;
    }
    
    public String toString() {
        String s = "Task[" + priority + "]";
        return s;
    }
}