import java.util.Random;

public class BinarySearchTreeTest {
	
	private static Random generator = new Random();
	
	private static void runRandomExperiment(int n, int count) {
	    int sum = 0;
	    for (int i = 0; i < count; i++) {
		BinarySearchTree tree = new BinarySearchTree();
		    createRandomTree(n, tree);
		    sum += tree.getHeight(tree.getRoot());
	    }
	    System.out.printf("Average height after %d random experiments = %4.2f\n", n, sum/(double)count);
	    
	}
	
	private static void createRandomTree(int n, BinarySearchTree tree) {
		for (int i = 0; i < n; i++) {
			int next = generator.nextInt(n);
			tree.insert(next);
		}
	}

	public static void main(String[] args) {
		
		if (args.length != 2) {
			System.out.println("Usage: java BinarySearchTree <tree size> <#random experiments");
			System.exit(1);
		}
		int size = Integer.parseInt(args[0]);
		int numExperiments = Integer.parseInt(args[1]);

		BinarySearchTree tree = new BinarySearchTree();
		
		//tree.inorderTreeWalk(tree.getRoot());
		
		runRandomExperiment(size, numExperiments);
	}

}
