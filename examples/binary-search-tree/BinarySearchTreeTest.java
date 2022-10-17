import java.util.Random;

/**
 * Run an experiment to check the average height of randomly generated binary search trees. 
 * This is an example of performance testing.
 * 
 * @author amit
 *
 */
public class BinarySearchTreeTest
{
    private static Random generator = new Random();
    private static int debugLevel = 1;

    /**
     * Create n random binary search trees and calculate the average height.
     * 
     * @param n  The number of nodes in each binary tree
     * @param count The number of binary trees to create
     */
    private static void runRandomExperiment(int n, int count) {
        int sum = 0;
        for (int i = 0; i < count; i++) {
            BinarySearchTree tree = new BinarySearchTree();
            createRandomTree(n, tree);
            if (debugLevel > 0) {
                System.out.println("BinarySearchTreeTest: random tree# " + i);
                tree.inorderTreeWalk(tree.getRoot());
                System.out.println();
            }
            sum += tree.getHeight(tree.getRoot());
        }
        System.out.printf("Average height after %d random experiments = %4.2f\n", 
                            count, sum / (double) count);
    }


    /**
     * Create a binary search tree by inserting n random keys in the range [0:n-1]
     * @param n
     * @param tree
     */
    private static void createRandomTree(int n, BinarySearchTree tree) {
        for (int i = 0; i < n; i++) {
            int next = generator.nextInt(n);
            tree.insert(next);
        }
    }


    /**
     * The driver for the test program.
     * 
     * @param args  command line arguments: <tree size> <#experiments> <debugLevel>
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java BinarySearchTree" + 
                                " <treeSize> <#experiments> [<debugLevel=0|1>]");
            System.exit(1);
        }
        int size = Integer.parseInt(args[0]);
        int numExperiments = Integer.parseInt(args[1]);
        if (args.length == 3) debugLevel = Integer.parseInt(args[2]);

        runRandomExperiment(size, numExperiments);
    }
}
