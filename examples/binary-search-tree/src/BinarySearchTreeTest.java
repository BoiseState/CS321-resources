import java.util.Random;

public class BinarySearchTreeTest {
	
	private static Random generator = new Random();
	
	private static void createRandomTree(int n, BinarySearchTree tree) {
		for (int i = 0; i < n; i++) {
			int next = generator.nextInt(n);
			tree.insert(next);
		}
	}

	public static void main(String[] args) {
		
		if (args.length  == 0) {
			System.out.println("Usage: jhava BinarySearchTree <n>");
			System.exit(1);
		}
		int n = Integer.parseInt(args[0]);

		BinarySearchTree tree = new BinarySearchTree();
		
		createRandomTree(n, tree);
		
		//tree.inorderTreeWalk(tree.getRoot());
		
		System.out.println("height of tree = " + tree.getHeight(tree.getRoot()));
	}

}
