import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Test 1: Random Data Insertion (Average Case) Both the deterministic table and
 * the universal table handle randomly distributed integers seamlessly. Because
 * the data layout has no malice or bias, the maximum bucket length (or
 * collision chain) remains low and balanced in both implementations.
 * 
 * Test 2: Adversarial Data Attack (Worst Case) The script generates a malicious
 * list of keys specifically engineered to exploit the predictable math of the
 * fixed hash function.
 * 
 * Fixed Hash fails: It forces every single key into the exact same bucket slot.
 * The table collapses into an O(N) linear search, simulating an algorithmic
 * denial-of-service attack.
 * 
 * Universal Hash succeeds: Because the universal hash assigns its internal
 * random multipliers (\(a\) and \(b\)) at startup, the attacker's engineered
 * keys fail to trigger a cascading collision. It seamlessly distributes the
 * adversarial data, preserving uniform \(O(1)\) performance metrics.
 * 
 */
public class AttackDemo {

    public static class FixedHashTable {
        /**
         *    Fixed Hash Table Implementation: for demo purposes.
         */
        private final int slots;
        private final List<List<Integer>> table;

        public FixedHashTable(int slots) {
            this.slots = slots;
            this.table = new ArrayList<>(slots);
            for (int i = 0; i < slots; i++) this.table.add(new ArrayList<>());
        }

        public int hash(int key) {
            return Math.abs((key * 12345)) % slots;
        }

        public void insert(int key) {
            table.get(hash(key)).add(key);
        }

        public int getMaxCollisionChain() {
            return table.stream().mapToInt(List::size).max().orElse(0);
        }
    }

    
    /**
     * Universal Random Hash Table Implementation: for demo purposes
     */
    public static class UniversalHashTable {
        private final int slots;
        private final long prime;
        private final long a, b;
        private final List<List<Integer>> table;

	public UniversalHashTable(int slots, long prime)
	{
	    this.slots = slots;
	    this.prime = prime;
	    this.table = new ArrayList<>(slots);
	    for (int i = 0; i < slots; i++)
		this.table.add(new ArrayList<>());

	    // Secure for security-sensitive applications
	    SecureRandom cryptoRand = new SecureRandom();

	    // 'a' must be non-zero and less than the prime
	    this.a = cryptoRand.nextLong(1, prime);
	    this.b = cryptoRand.nextLong(0, prime);
	}

        public int hash(int key) {
            // Formula: ((a * x + b) % prime) % slots
            long val = ((long) a * key + b) % prime;
            if (val < 0) val += prime; // Safety shield for negative values
            return (int) (val % slots);
        }

        public void insert(int key) {
            table.get(hash(key)).add(key);
        }

        public int getMaxCollisionChain() {
            return table.stream().mapToInt(List::size).max().orElse(0);
        }
    }

    public static void main(String[] args) {
        int slots = 1000;
        int itemsCount = 500;
        // Mersenne Prime 2^31 - 1 (fits inside a standard signed primitive)
        final long prime = 2147483647L;

        System.out.println("--- Scenario 1: Inserting Normal Random Data ---");
        FixedHashTable fixedTableNormal = new FixedHashTable(slots);
        UniversalHashTable universalTableNormal = new UniversalHashTable(slots, prime);
        Random dataGen = new Random();

        for (int i = 0; i < itemsCount; i++) {
            int normalKey = dataGen.nextInt(1000000);
            fixedTableNormal.insert(normalKey);
            universalTableNormal.insert(normalKey);
        }
        System.out.println("Fixed Max Chain: " + fixedTableNormal.getMaxCollisionChain());
        System.out.println("Universal Max Chain: " + universalTableNormal.getMaxCollisionChain());

        System.out.println("\n--- Scenario 2: Inserting Malicious Adversarial Data ---");
        FixedHashTable fixedTableAttack = new FixedHashTable(slots);
        UniversalHashTable universalTableAttack = new UniversalHashTable(slots, prime);

        // Attacker forces keys to intentionally target index '0' on the fixed multiplier
        int attackKey = 0;
        for (int i = 0; i < itemsCount; i++) {
            fixedTableAttack.insert(attackKey);
            universalTableAttack.insert(attackKey);
            attackKey += 200; // Step size engineered to always yield index 0
        }
        System.out.println("Fixed Max Chain: " + fixedTableAttack.getMaxCollisionChain() + " (ALL COLLIDED)");
        System.out.println("Universal Max Chain: " + universalTableAttack.getMaxCollisionChain() + " (NEUTRALIZED)");
    }
}
