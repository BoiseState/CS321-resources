
/**
 * Perform various types of tests on CircularQueue class.
 * 
 * @see CircularQueue
 * @author amit
 *
 */
public class CircularQueueTest
{
    /**
     *  Run a simple smoke test. Add 10 elements, check queue, then remove ten elements
     *  and check queue again.
     */
    private static void smokeTest() {
        CircularQueue queue = new CircularQueue(10);
        try {
            System.out.println("CircularQueueTest: Enqueueing 10 elements...");
            for (int i = 1; i <= 10; i++)
                queue.enqueue(i);
            System.out.println("CircularQueueTest: Current state of queue.");
            System.out.println(queue);
         
            System.out.println("CircularQueueTest: Dequeuing 10 elements.");
            for (int i = 1; i <= 10; i++)
                System.out.print(" " + queue.dequeue());
            System.out.println();
            System.out.println("CircularQueueTest: Current state of queue.");
            System.out.println(queue);
            
        } catch (QueueOverflow e) {
            e.printStackTrace();
        } catch (QueueUnderflow e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Check for underflow by doing a dequeue on an empty queue.
     */
    private static void testUnderflow() {
        CircularQueue queue = new CircularQueue(1);
        try {
            queue.dequeue();
        } catch (QueueUnderflow e) {
            e.printStackTrace();
            System.out.println("CircularQueueTest: caught underflow on an empty queue");
        }
    }
    
    /**
     *  Check for overflow by doing an enqueue into a full queue.
     */
    private static void testOverflow() {
        CircularQueue queue = new CircularQueue(1);
            try {
                queue.enqueue(1);
                queue.enqueue(2); //this should trigger an overflow
            } catch (QueueOverflow e) {
                e.printStackTrace();
                System.out.println("CircularQueueTest: caught overflow on a full queue");
            }    
    }
    /**
     * Run various tests.
     * @param args no command line arguments used at present
     */
    public static void main(String[] args) {
        System.out.println("CircularQueueTest: a simple smoke test with 10 elements");
        smokeTest();
        System.out.println();
        
        System.out.println("CircularQueueTest: testing underflow...");
        testUnderflow();
        System.out.println();
        
        System.out.println("CircularQueueTest: testing overflow...");
        testOverflow();
        System.out.println();
    }
}
