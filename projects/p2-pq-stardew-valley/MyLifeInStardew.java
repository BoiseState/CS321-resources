import java.util.Random;

/**
 * Simulates a task management system for daily
 * activities/tasks based on the Stardew Valley game.
 *
 * @author CS321 instructors
 */
public class MyLifeInStardew {

    private  int maxPriority;
    private  int timeToIncrementLevel;
    private  int simulationDays;
    private  double taskGenerationProbability;
    private  long seed;
    
    private final int SHOW_HEAP = 1;
    private final int DEFAULT = 0;
    private int debugLevel = DEFAULT; 

    private  StardewDailyClock stardewDailyClock;
    private  TaskGenerator taskGenerator;

    private  Random rand;
    boolean  seeded = false;

    private  int moneyMade = 0;
    private  int badLuck = 0;
    private  int unluckyCounter = 0;
    private  int taskCounter = 0;

    private double probabilityOne;
    private double probabilityTwo;
    private int detailIndex;

    /**
     * Show usage for the program.
     *
     */
    public void showUsage() {
        System.out.println(
                "Usage: java MyLifeInStardew  " +
                        "<max-priority>   " +
                        "<time-to-increment-priority>   " +
                        "<total simulation-time in days>   " +
                        "<task-generation-probability>   " +
                        "[<seed>]");
        System.exit(1);
    }

    /**
     * Processes the arguments
     * @param args - the String arguments for the program
     */
    private void processArguments(String[] args) {

        maxPriority = Integer.parseInt(args[0]);
        if (maxPriority < 1) throw new IllegalArgumentException("Illegal argument: maxPriorityLevel must >= 1.");

        timeToIncrementLevel = Integer.parseInt(args[1]);
        if (timeToIncrementLevel < 1)
            throw new IllegalArgumentException("Illegal argument: time-to-increment-level must >= 1.");

        simulationDays = Integer.parseInt(args[2]);
        stardewDailyClock = new StardewDailyClock();
        if (simulationDays < 1) throw new IllegalArgumentException("Illegal argument: simulationTime must >= 1.");

        taskGenerationProbability = Double.parseDouble(args[3]);

        seed = 0L; //needs to be a long type
        if (args.length == 5) {
            seed = Long.parseLong(args[4]);
            taskGenerator = new TaskGenerator(taskGenerationProbability, seed);
            seeded = true;
        } else {
            taskGenerator = new TaskGenerator(taskGenerationProbability);
        }
    }

    /**
     * Gets the type of Task based on Luck of the Day,
     * random probability, and current energy levels.
     * @param luckOfTheDay - the daily luckiness
     */
    private TaskInterface.TaskType getTaskType(double luckOfTheDay){
        probabilityOne = rand.nextDouble();
        if (taskGenerator.getCurrentEnergyStorage() < 50) { //foraging and socializing
            if(probabilityOne > 0.5) {
                return TaskInterface.TaskType.FORAGING;
            }
            else {
                return TaskInterface.TaskType.SOCIALIZING;
            }
        }
        if(luckOfTheDay > 0.75 && probabilityOne > .10) { //fishing only
            return TaskInterface.TaskType.FISHING;
        }
        else {
            probabilityTwo = rand.nextDouble();
            if(probabilityOne > 0.5) { //even number
                if (probabilityTwo > 0.49) { //higher than 50
                    return TaskInterface.TaskType.FISHING;
                }
                else { //lower than 50
                    return TaskInterface.TaskType.MINING;
                }
            }
            if(probabilityOne > 0.2) {
                return TaskInterface.TaskType.FEEDING;
            }
            else {
                return TaskInterface.TaskType.FARM_MAINTENANCE;
            }
        }
    }

    /**
     * Gets the details of a particular Task specific to each sub-category of Task.
     *
     * @param taskType - the type of Task
     */
    private String getDetails(TaskInterface.TaskType taskType) {
        detailIndex = rand.nextInt();
        if(detailIndex < 0){ detailIndex = (detailIndex*(-1)); }
        detailIndex = detailIndex % 4;
        if(taskType == Task.TaskType.MINING) {
            String[] miningDetails = {"for Copper", "for Iron", "for Gold", "for Diamonds", "for Rocks",};
            return miningDetails[detailIndex];
        }
        if(taskType == Task.TaskType.FISHING) {
            String[] fishingDetails = {"in the Ocean", "in the River", "in the Pond","in the Lake", "in the Hatcheries",};
            return fishingDetails[detailIndex];
        }
        if(taskType == Task.TaskType.FARM_MAINTENANCE) {
            String[] maintenanceDetails = {"of Harvesting Wheat", "of Clearing Rocks", "of Watering Plants","of Wood Chopping", "of Beautification",};
            return maintenanceDetails[detailIndex];
        }
        if(taskType == Task.TaskType.FORAGING) {
            String[] foragingDetails = {"for Mushrooms", "for Berries", "for Roots","for Flowers", "for Herbs",};
            return foragingDetails[detailIndex];
        }
        if(taskType == Task.TaskType.FEEDING) {
            String[] animalDetails = {"the Goats", "the Chickens", "the Cows","the Pigs", "the Ducks",};
            return animalDetails[detailIndex];
        }
        if(taskType == Task.TaskType.SOCIALIZING) {
            String[] socialDetails = {"with Mayor Lewis", "with Store Owner Pierre", "with Carpenter Robin","with Blacksmith Clint", "with the Wizard",};
            return socialDetails[detailIndex];
        }
        else { return "nothing to see here"; } //should never reach
    }

    /**
     * Runs the simulation of Task management with a specified time period.
     */
        public  void runSimulation() {
            PriorityQueueInterface priorityQueue = new MyPriorityQueue();
            
            int day = 1;
            int hourTotal = 0;
            
            for (int currentTime = 0; currentTime < simulationDays; currentTime++) {
                
                int hour = 0;
                int dailyMoney = 0;
                stardewDailyClock.resetCurrentClockNumber();
                
                if (seeded == true) {
                    seed = seed << 2; //allows for different lucks and unfortunate probabilities
                    rand = new Random(seed);
                } else {
                    rand = new Random();
                }

                double luckOfTheDay = rand.nextDouble();
                double unluckyProbablity = rand.nextDouble();

                System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                System.out.format("\t\tDAY: %s\tLUCK: %.2f\n", day, luckOfTheDay);
                System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");

                while(!stardewDailyClock.getNight()) {
                    
                    if (debugLevel == SHOW_HEAP) System.out.println(priorityQueue);
                    
                    if (taskGenerator.generateTask()) {
                        TaskInterface.TaskType typeOfTask = getTaskType(luckOfTheDay);
                        Task newTask = taskGenerator.getNewTask(hourTotal, typeOfTask, getDetails(typeOfTask));
                        System.out.format("\t\t\t\t\t\t\tNEW TASK: %s\n",newTask.toString());
                        priorityQueue.enqueue(newTask);
                        taskCounter++;
                    }

                    System.out.format("\t\t%s\t", stardewDailyClock.toString(hour));
                    
                    if(!priorityQueue.isEmpty()) {
                        Task currentTask = priorityQueue.dequeue();
                        System.out.println(taskGenerator.toString(currentTask, currentTask.getTaskType()));
                        taskGenerator.decrementEnergyStorage(currentTask.getTaskType());
                        badLuck = taskGenerator.getUnlucky(currentTask, unluckyProbablity);
                        
                        if (badLuck > 0) {
                            if (badLuck == 1) { //Passing-out
                                System.out.println("\t\t\t\t\t =====================================================");
                                System.out.println("\t\t\t\t\t\t\t" + "YOU PASSED OUT");
                                System.out.println("\t\t\t\t\t\t\t" + "Energy meter depleted by 50% and");
                            } else if (badLuck == 2) { //Death in the Mines
                                System.out.println("\t\t\t\t\t =====================================================");
                                System.out.println("\t\t\t\t\t\t" + "YOU DIED in the mines");
                                System.out.println("\t\t\t\t\t\t" + "Energy meter depleted by 75% and");
                                stardewDailyClock.setCurrentClockNumber(19);
                            }
                            moneyMade = (moneyMade - (moneyMade >>4));
                            unluckyCounter++;
                            System.out.println("\t\t\t\t\t\t\t" + "Doctor's visit took 25% of your income!");
                            System.out.println("\t\t\t\t\t =====================================================");
                        }
                        priorityQueue.update(timeToIncrementLevel, maxPriority); //updates priorities and shifts
                        int cashMadeThisTask = currentTask.getTaskType().getMoneyPerHour();

                        moneyMade = moneyMade + cashMadeThisTask;
                        dailyMoney = dailyMoney + cashMadeThisTask;

                        currentTask.setPriority(0); 
                        currentTask.resetWaitingTime(); 
                        priorityQueue.enqueue(currentTask); //re-adds removed task
                    }
                    else { 
                        System.out.println(); 
                    }
                    if(taskGenerator.getCurrentEnergyStorage() < 0) { //Energy dips below 0 then
                        System.out.println("\t\t\t\t\t =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                        System.out.println("\t\t\t\t\t" + " THE DAY IS OVER");
                        System.out.println("\t\t\t\t\t" + " Energy sufficiently depleted!");
                        System.out.println("\t\t\t\t\t =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                        stardewDailyClock.setCurrentClockNumber(19);
                    }
                    stardewDailyClock.incrementCurrentClockNumber();
                    hour++;
                    hourTotal++;
                }

                taskGenerator.resetCurrentEnergyStorage(); //reset energy
                System.out.println("\t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.format("\t\t\t\t\t Cash made today: +%d$\n", dailyMoney);
                System.out.println("\t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                day++;
                System.out.println();
            }
            System.out.println("--- Simulation completed ---");
            System.out.println("Simulation time: " + simulationDays + " days");
            System.out.println("Total Tasks generated: " + taskCounter);
            System.out.println("Total Simulation hours: " + hourTotal + " hours");
            System.out.println("Total Money made: " + moneyMade + "$");
            System.out.println("Total deaths/passouts: " + unluckyCounter);
        }

    /**
     * Main method for the program.
     */
    public static void main(String[] args) {
        MyLifeInStardew simulation = new MyLifeInStardew();
        if (args.length < 3) {
            simulation.showUsage();
        }
        simulation.processArguments(args);
        simulation.runSimulation();
    }
}
