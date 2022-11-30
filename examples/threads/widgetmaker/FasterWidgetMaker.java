package widgetmaker;

import java.util.Random;

/**
 * Runs the widgetmaker faster by using multiple threads.
 * 
 * @author amit
 * @author taylor
 */
public class FasterWidgetMaker extends Thread
{
    private Random generator = new Random();
    private final int RANGE = 100;
    private final int BASE = 100;
    private static final int INCORRECT_ARGUMENTS = 1;
    private int count;

    /**
     * @param count
     */
    public FasterWidgetMaker(int count) {
        this.count = count;
    }


    /**
     * The main method for a thread. Just calls make.
     */
    public void run() {
        make();
    }


    /**
     * Pretends to make widgets.
     */
    public void make() {
        for (int i = 0; i < count; i++) {
            int time = generator.nextInt(RANGE) + BASE;
            // simulate variable amount of time to make one widget
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Widget# " + i + " ready.");
        }
    }


    /**
     * Creates the threads and then divide the work of making widgets amongst the threads.
     * @param args  
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        if (args.length != 1) {
            System.out.println("Usage: java WidgetMaker <number of widgets>");
            System.exit(INCORRECT_ARGUMENTS);
        }
        int n = Integer.parseInt(args[0]);

        long startTime = System.currentTimeMillis();
        int numThreads = Runtime.getRuntime().availableProcessors();
        //int numThreads = 16;
	//System.out.println("FasterWidgetMaker: using " + numThreads + " threads");

        FasterWidgetMaker[] robotFactory = new FasterWidgetMaker[numThreads];
        for (int i = 0; i < numThreads - 1; i++) {
            robotFactory[i] = new FasterWidgetMaker(n / numThreads);
        }
        robotFactory[numThreads - 1] = new FasterWidgetMaker(n / numThreads + n % numThreads);

        for (int i = 0; i < numThreads; i++) {
            robotFactory[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            robotFactory[i].join();
        }

        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println("WidgetMaker: made " + n + " widgets in " + totalTime / 1000.0 + " seconds -- using " + numThreads + " threads");
    }

}
