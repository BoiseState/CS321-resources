
public interface PriorityQueueInterface 
{
    public void enqueue(Process p);

    public Process dequeue(); 

    public boolean isEmpty();

    public void update(Process next, int timeToIncrementPriority, int maxPriority); 
	
}
