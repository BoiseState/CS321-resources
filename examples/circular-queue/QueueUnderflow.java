
/**
 * Represents a queue underflow exception, thrown when circular queue is empty.
 * 
 * @author amit
 *
 */
@SuppressWarnings("serial")
public class QueueUnderflow extends Exception
{
    public QueueUnderflow(String s) {
        super(s);
    }
}
