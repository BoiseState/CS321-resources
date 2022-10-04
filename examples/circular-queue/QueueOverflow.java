
/**
 * Represents a queue overflow exception, thrown when circular queue is full.
 * 
 * @author amit
 *
 */
public class QueueOverflow extends Exception
{
    /**
     * 
     */
    private static final long serialVersionUID = 6024127223781434656L;

    public QueueOverflow(String s) {
        super(s);
    }
}
