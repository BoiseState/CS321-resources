
/**
 * 
 * Simulate the round robin scheduling of processes in a CPU.
 * 
 * @author cs321-instructors
 *
 */
public class CPUScheduling
{

    private  int maxProcessTime;
    private  int maxPriority;
    private  int timeToIncrementLevel;
    private  int simulationTime;
    private  double probability;
    private  long seed;
    private  ProcessGeneratorInterface pGenerator;

    /**
     * Show usage for the program.
     */
    public  void showUsage() {
	System.out.println(
	        "Usage: java CPUScheduling   <max-process-time>   <max-priority>   <time-to-increment-priority>"
	                + "   <simulation-time>   <process arrival probability: (0..1)>");
	System.exit(1);
    }


    private  void processArguments(String[] args) {
	maxProcessTime = Integer.parseInt(args[0]);
	if (maxProcessTime <= 0) throw new IllegalArgumentException("Illegal argument: maxProcessTime must >= 1.");

	maxPriority = Integer.parseInt(args[1]);
	if (maxPriority <= 0) throw new IllegalArgumentException("Illegal argument: maxPriorityLevel must >= 1.");

	timeToIncrementLevel = Integer.parseInt(args[2]);
	if (timeToIncrementLevel <= 0)
	    throw new IllegalArgumentException("Illegal argument: time-to-increment-level must >= 1.");

	simulationTime = Integer.parseInt(args[3]);
	if (simulationTime <= 0) throw new IllegalArgumentException("Illegal argument: simulationTime must >= 1.");

	probability = Double.parseDouble(args[4]);

	pGenerator = null;
	seed = 0L;
	if (args.length == 6) {
	    seed = Long.parseLong(args[5]);
	    pGenerator = new ProcessGenerator(probability, seed);
	} else {
	    pGenerator = new ProcessGenerator(probability);
	}
    }


    public  void runSimulation() {
	PriorityQueueInterface pqueue = new MyPriorityQueue();
	Averager averager = new Averager();

	for (int currentTime = 0; currentTime < simulationTime; currentTime++) {
	    System.out.format("second %3d: ", currentTime);

	    boolean arrival = false;
	    // Check to see if there is any incoming new process.
	    if (pGenerator.query()) {
		Process p = pGenerator.getNewProcess(currentTime, maxProcessTime, maxPriority);
		System.out.println("JOB " + currentTime + " arrives, timeRequired " + p.getTimeRemaining()
		        + ", priority " + p.getPriority());
		pqueue.enqueue(p);
		arrival = true;
	    }

	    // If the priority queue is not empty. Let the process with
	    // highest priority has this time slice. Update the status
	    // of this process. Also update the status of all other
	    // processes in the priority queue.
	    if (!pqueue.isEmpty()) {
		Process next = pqueue.dequeue();
		next.decrementTimeRemaining();
		pqueue.update(next, timeToIncrementLevel, maxPriority);
		if (arrival) System.out.print("\t    ");
		if (next.finished()) {
		    System.out.print("    (Assign to JOB " + next.getArrivalTime() + ": FINISH, priority "
		            + next.getPriority() + ")");
		    averager.addNumber(currentTime - next.getArrivalTime() + 1);
		} else {
		    System.out.print("    (Assign to JOB " + next.getArrivalTime() + ": timeRemaining "
		            + next.getTimeRemaining() + ", priority " + next.getPriority() + ")");
		    next.resetWaitingTime();
		    pqueue.enqueue(next);
		}
	    }
	    System.out.println();
	}

	System.out.println("--- Simulation completed ---");
	System.out.println("Simulation time: " + simulationTime + " unit time.");
	System.out.println("Number of finished processes: " + averager.getCount());
	System.out.printf("Average Turn around time for a process: %4.2f\n", averager.getAverage());
    }


    public static void main(String[] args) {
	CPUScheduling simulation = new CPUScheduling();
	if (args.length < 5) {
	    simulation.showUsage();
	}
	simulation.processArguments(args);

	simulation.runSimulation();
    }
}
