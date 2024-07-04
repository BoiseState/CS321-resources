
import java.math.*;

/**
 * A simple test class to demonstrate the power of better algorithms by comparing
 * the O(n brute-force versus O(lg n) recursive doubling vs the built in power
 * function in Java.
 * 
 * @author amit
 *
 */
public class BigPowerTest
{
	private static final boolean DEBUG = false;

    /**
     * The main driver to run the experiment.
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        if (args.length != 2) {
            System.err.println("Usage: java BigPowerTest <value> <exponent>");
            System.exit(1);
        }
        BigInteger x = new BigInteger(args[0]);
        int n = Integer.parseInt(args[1]);

        long startTime = System.currentTimeMillis();
        BigInteger result1 = BigPower.power_brute_force(x, n);
        float totalTime = System.currentTimeMillis() - startTime;
        if (DEBUG) System.out.println(result1);
        System.err.println("time taken for version 1 = " + totalTime + " milliseconds\n");

         startTime = System.currentTimeMillis();
        BigInteger result2 = BigPower.power_recursive_doubling(x, n);
         totalTime = System.currentTimeMillis() - startTime;
        if (DEBUG) System.out.println(result2);
        System.err.println("time taken for version 2 = " + totalTime + " milliseconds\n");

        startTime = System.currentTimeMillis();
        BigInteger result3 = x.pow(n);
        totalTime = System.currentTimeMillis() - startTime;
        if (DEBUG) System.out.println(result3);
        System.err.println("time taken for pow = " + totalTime + " milliseconds\n");
    }
}
