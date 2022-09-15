/**
 * Interface for a task generator.
 *
 * @author CS321 instructors
 */
public interface TaskGeneratorInterface {

	public final int DEFAULT_ENERGY = 200;

    /**
     * Creates a new Task with default zero priority.
     *
     * @param hourCreated hour that the Task was created
     * @param taskType type of the Task
     * @param taskDescription the Task's description
     */
    public Task getNewTask(int hourCreated, TaskInterface.TaskType taskType, String taskDescription);

    /**
     * Decrements the total energy storage based on the current task.
     *
     * @param taskType - the type of Task
     */
    public void decrementEnergyStorage(Task.TaskType taskType);

    /**
     * Resets the total energy storage to DEFAULT_ENERGY level.
     */
    public void resetCurrentEnergyStorage();

    /**
     * Returns the current energy storage.
     *
     * @returns current energy storage
     */
    public int getCurrentEnergyStorage();

    /**
     * Sets the current energy storage.
     *
     * @param newEnergyNum number to set the energy
     */
    public void setCurrentEnergyStorage(int newEnergyNum);

    /**
     * Determines if a new task is to be generated.
     *
     * @return true or false - if task should be generated
     */
    public boolean generateTask();

    /**
     * Determines the likelihood of passing out or dying
     * based on the task and death probability.  Where
     * 0 is not dying or passing out, 1 is passing out,
     * and 2 is dying in the Mines.
     *
     * @param task - the Task
     * @returns  0, 1 or 2 depending on the unluckiness
     */
    public int getUnlucky(Task task, double unluckyProbability);

    /**
     * Given method that creates a String containing the Task's information.
     *
     * @param task  the Task
     * @param taskType the Task's type
     */
    public String toString(Task task, Task.TaskType taskType);

}
