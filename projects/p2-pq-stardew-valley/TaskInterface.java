/**
 * This interface  is used to represent a single task with name, priority, energy taken/received,
 * money gained, and varying unfortunate event probabilities. It also contains a provided enum to 
 * categorize the six different task types.
 *
 * @author CS321 instructors
 */
public interface TaskInterface {
    /**
     * Enum for the six different types of Tasks.
     */
    public enum TaskType {

        MINING(100, 20, 0.1, 0.05),
        FISHING(150, 20, 0.1, 0),
        FARM_MAINTENANCE(25, 10, 0.1, 0),
        FEEDING(75, 10, 0.1, 0),
        FORAGING(75, 0, 0, 0),
        SOCIALIZING(25, 0, 0, 0);

        private final int moneyPerHour;
        private final int energyPerHour;
        private final double passingOutProbability;
        private final double dyingProbability;

        TaskType(int moneyPerHour, int energyPerHour, double passingOutProbability, double dyingProbability) {
            this.moneyPerHour = moneyPerHour;
            this.energyPerHour = energyPerHour;
            this.passingOutProbability = passingOutProbability;
            this.dyingProbability = dyingProbability;
        }

        public int getMoneyPerHour()
        {
            return moneyPerHour;
        }

        public int getEnergyPerHour()
        {
            return energyPerHour;
        }

        public double getPassingOutProbability()
        {
            return passingOutProbability;
        }

        public double getDyingProbability()
        {
            return dyingProbability;
        }
    }

    /**
     * Returns the priority.
     *
     * @return the priority
     */
    public int getPriority();

    /**
     * Sets the priority to the specified value.
     *
     * @param priority
     */
    public void setPriority(int priority);

    /**
     * Gets the type of a task.
     *
     * @return - Task.TaskType - the Task's type
     */
    public Task.TaskType getTaskType();
    
    
    /**
     * Gets the task description.
     * @return
     */
    public String getTaskDescription();
    

    /**
     * Increments the waiting time by one.
     */
    public void incrementWaitingTime();

    /**
     * Resets the waiting time to 0.
     */
    public void resetWaitingTime();

    /**
     * Gets the waiting time of a task.
     *
     * @return the waiting time
     */
    public int getWaitingTime();

    /**
     * {@inheritDoc}
     */
    public String toString();
}
