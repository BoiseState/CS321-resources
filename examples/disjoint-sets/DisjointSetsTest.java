import java.util.Random;


/**
 * A smoke test and a performance test class for disjoint sets.
 * 
 * @author amit
 *
 */
public class DisjointSetsTest 
{
	private static final int DEBUG = 0;
	
	/**
	 * Run unions at random until only one set is left
	 * @param S
	 * @param n
	 * @param seed
	 */
	@SuppressWarnings("unused")
    private static void runRandomExperiments(DisjointSets<Integer> S, int n, long seed)
	{
	    // set the seed for random number generator

	    Random generator = new Random(seed);
	    
	    int num_unions = 0;
	    int num_finds = 0;
	    int x, y, Sx, Sy;
	    long before = 0;
	    long after = 0;
	    long maxht = 0;

	    // perform random unions until only one set is left
	    while (num_unions < n-1) {
	        
	        // pick two elements at random
	        x = generator.nextInt(n);
	        y = generator.nextInt(n);
	        
	        // find the representatives of the sets to which x and y belong
	        before = S.getCounter();
	        Sx = S.find(x);
	        after = S.getCounter();
	        maxht = (maxht > (after-before)? maxht: (after-before));

	        before = S.getCounter();
	        Sy = S.find(y);
	        after = S.getCounter();
	        maxht = (maxht > (after-before)? maxht: (after-before));
	        num_finds += 2;

	        // if x and y belong to distinct sets, then
	        // union the corresponding sets into one set.
	        if (Sx != Sy) {
	            S.union(Sx,Sy);
	            num_unions++;
	        }
	    }
		if (DEBUG > 0) {
		    S.printSets();
		}

		System.out.println("number of finds = " + num_finds);
		System.out.println("average height = " + S.getCounter()/(float) num_finds);
		System.out.println("maximum height = " + maxht);	 
	}

	/**
	 * The main driver.
	 * @param args
	 */
	public static void main(String[] args) 
	{
		if (args.length != 2) {
			System.err.println("Usage: java DisjointSetsTest <n> <random seed>");
			System.exit(1);
		}
		int n = Integer.parseInt(args[0]);
		long seed = Long.parseLong(args[1]);
		
		DisjointSets<Integer> S = new DisjointSets<Integer>(n);
		for (int i=0; i<n; i++) {
			S.makeSet(i, i);
		}
		runRandomExperiments(S, n, seed);
	}

}
