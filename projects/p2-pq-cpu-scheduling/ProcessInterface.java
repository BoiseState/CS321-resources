
public interface ProcessInterface 
{
	public int getPriority(); 
	
	public void setPriority(int priority);

	public int getTimeRemaining(); 
	
	public void decrementTimeRemaining(); 
	
	public boolean finished(); 

	public int getArrivalTime(); 

	public int getWaitingTime(); 
	public void incrementWaitingTime(); 
	public void resetWaitingTime(); 
}
