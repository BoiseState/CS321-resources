
public interface ProcessGeneratorInterface {
	/**
	 * Generate a new random process within the specified range.
	 * @param currentTime   The current time in the simulation.
	 * @param maxProcessTime Range for the time required to complete the process [1..maxProcessTime]
	 * @param maxLevel Range for the priority level [1..maxLevel]
	 * @return
	 */
	public Process getNewProcess(int currentTime, int maxProcessTime, int maxPriority); 

	/**
	 * Check if a new process will arrive (based on the probability of arrival at a given instance of time).
	 * @return
	 */
	public boolean query(); 

}
