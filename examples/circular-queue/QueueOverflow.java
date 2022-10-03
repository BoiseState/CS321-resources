
/**
 * Represents a queue overflow exception, thrown when circular queue is full.
 * @author amit
 *
 */
@SuppressWarnings("serial")
public class QueueOverflow extends Exception
{
    public QueueOverflow(String s) {
        super(s);
    }
}
