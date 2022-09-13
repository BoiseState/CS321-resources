/**
 * Extends MaxHeap and provides management to a queue
 * through various update, insert, removal methods,
 * along with a boolean to check for the queue.
 *
 * @author CS321 instructors
 */
public interface PriorityQueueInterface {
    /**
     * Adds a task to the rear of the list
     * via MaxHeap insert()
     *
     * @param task - Task to enqueue
     */
    public void enqueue(Object task);

    /**
     * Removes the front element from the list
     * via MaxHeap ExtractMax()
     *
     * @return first element in the Task array
     */
    public Task dequeue();

    /**
     * Boolean for if the Heap is empty
     *
     * @return T or F - depending on if the Heap is empty
     */
    public boolean isEmpty();

    /**
     * Increases waiting time for all other process and also
     * checks if priority needs increase based on maxPriority
     * and timeToIncrementPriority
     *
     * @param timeToIncrementPriority - the count to incrementation
     * @param maxPriority - the maximum Priority
     */
    public void update(int timeToIncrementPriority, int maxPriority);

}
