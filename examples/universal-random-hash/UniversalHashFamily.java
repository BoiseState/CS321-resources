
import java.security.SecureRandom;

/**
 * Shows how to generate a universal random hashing function that changes for
 * each run. Note that while this is more secure than using a simple hash
 * function like k mod m, the linear formula ((ax + b) (mod p)) is vulnerable to
 * algebraic exploitation if an attacker can observe the outputs or the behavior
 * of your system.
 * 
 * <a> Linear Vulnerability</b> The formula is purely linear. If an attacker can
 * deduce just a few inputs and their resulting hash locations (either through
 * error messages, timing side-channels, or by observing which keys end up in
 * the same bucket), they can set up a simple system of linear equations:
 * 
 * h1 = (a x1 + b) mod p h2 = (a x2 + b) mod p
 * 
 * Using basic modular arithmetic, the attacker can solve for your "secret" a
 * and b variables. Once they know a and b, your universal hash family is
 * completely compromised, and they can generate millions of perfect collisions
 * to crash your server.
 *
 * <b> hashCode() flaw</b>The line long x = key.hashCode() & 0xFFFFFFFFL; passes
 * the key's default Java hash code into the formula. Because Java's native
 * .hashCode() algorithms are deterministic and public (for example,
 * String.hashCode() uses a completely static polynomial formula), the attacker
 * already knows the exact values of x. This makes solving the linear equations
 * trivial.
 * 
 * Note that Java does not use a universal hash function because it uses a
 * balanced tree to to make sure that hashing doesn't degrade to a O(n)
 * worst-case performance. The balanced tree guarantees a O(lg n) worst-case
 * performance.
 * 
 */
public class UniversalHashFamily
{
    private final long a;
    private final long b;
    // Mersenne Prime 2^31 - 1 (fits inside a standard signed primitive)
    private final long prime = 2147483647L;

    public UniversalHashFamily()
    {
	// Secure for security-sensitive applications
	SecureRandom cryptoRand = new SecureRandom();

	// 'a' must be non-zero and less than the prime
	this.a = cryptoRand.nextLong(1, prime);
	this.b = cryptoRand.nextLong(0, prime);
    }


    /**
     * Hashes an object dynamically into a variable table size.
     * 
     * @param key
     *                      The object to hash
     * @param tableSize
     *                      The current capacity of your array/buckets
     */
    public int hash(Object key, int tableSize)
    {
	if (key == null) return 0;
	if (tableSize <= 0) {
	    throw new IllegalArgumentException("Table size must be greater than 0");
	}

	// Convert the signed 32-bit java hashCode to an unsigned 64-bit long
	long x = key.hashCode() & 0xFFFFFFFFL;

	// Carter-Wegman formula: ((ax + b) % p)
	long hashValue = ((a * x) + b) % prime;

	// Final mapping to the dynamic table size argument
	return Math.abs((int) hashValue) % tableSize;
    }
}
