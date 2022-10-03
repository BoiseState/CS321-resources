

/**
 * A circular queue implemented in a  fixed-size array. This follows the pseudocode from the book:
 * <a href="https://mitpress.mit.edu/9780262046305/introduction-to-algorithms/">Intro to Algorithms</a>
 * 
 *  @author amit
 *
 */
public class CircularQueue
{
    private Integer[] Q;
    private int size = 0; 
    private int head;
    private int tail;
    
    /**
     * Create a queue that can store size elements.  We use Q[1:n] and ignore Q[0] to follow
     * the pseudocode from the book. The size denotes the maximum capacity of the 
     * queue, so the queue array size is size+1, as we have to leave one space empty for the 
     * circular queue to work properly.
     * 
     * @param size The maximum capacity of the queue.
     */
    public CircularQueue(int size) {
        if (size < 1)
            throw new IllegalArgumentException("CircularQueue: size must be > 1");
        this.size = size + 1;
        
        Q = new Integer[this.size + 1]; //we will use location 1..size and ignore location 0
        head = tail = 1;
    }
    
    /**
     * Check if the queue is empty.
     * 
     * @return true if the queue is empty
     */
    public boolean emptyQueue() {
        return head == tail;
    }
    
    /**
     * Add an element to the tail of the queue. If the queue is full, we will
     * get an overflow exception as the backing array is of fixed size.
     * 
     * @param x  the element to add
     * @throws QueueOverflow  thrown when queue is full
     */
    public void enqueue(Integer x) throws QueueOverflow {
        if (head == (tail + 1) % size)
            throw new QueueOverflow("CircularQueue: queue is full!");
        else {
            Q[tail] = x;
            if (tail == size)
                tail = 1; // wrap around
            else tail = tail + 1;
        }
    }
    
    /**
     * Remove the head element from the queue. If the queue is empty, we will get
     * an underflow exception.
     * 
     * @return the element at the head of the queue
     * @throws QueueUnderflow thrown when queue is empty
     */
    public Integer dequeue() throws QueueUnderflow {
        Integer x = null;
        if (emptyQueue()) 
            throw new QueueUnderflow("CircularQueue: queue is empty!");
        else {
            x = Q[head];
            if (head == size)
                head = 1;
            else head = head + 1;
        }
        return x;
    }
    
    /**
     * {@inheritDoc}
     */
    public String toString() {
        String value = "Queue[";
        int index = head;
        
        while (index != tail) {
            value += Q[index] + ", ";
            index = index + 1;
            // break if we are at the last element in the queue
            if ((index == tail) || (index == size && tail == 1)) {
                break;
            }
            if (index == size)
                index = 1;
        }
        value +=  Q[index] + "]";
        return value;
    }
    
}


