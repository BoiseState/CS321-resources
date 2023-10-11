/**
 * Provides priority queue management via enqueue, dequeue, isEmpty and update methods.
 * You are expected to implement this interface in your MyPriorityQueue class, which should
 * extend the MaxHeap class. In this case, the priority queue is the abstract data type while the
 * MaxHeap in the underlying concrete data structure.
 *
 * @author CS321 instructors
 */
public interface PriorityQueueInterface {
    /**
     * Adds a task to the rear of the list.
     * via MaxHeap insert()
     *
     * @param task - Task to enqueue
     */
    public void enqueue(Task task);

    /**
     * Removes the front element from the list.
     * via MaxHeap ExtractMax()
     *
     * @return first element in the Task array
     */
    public Task dequeue();

    /**
     * Boolean for if the max-heap is empty.
     *
     * @return true or false - depending on if the Heap is empty
     */
    public boolean isEmpty();

    /**
     * Increase the priorities for a given task in the queue if it has waited enough 
     * time but not to exceed its maximum priority.
     *
     * @param timeToIncrementPriority how long it should have waited for
     * @param maxPriority  the maximum priority value the task can have
     */
    public void update(int timeToIncrementPriority, int maxPriority);

}
