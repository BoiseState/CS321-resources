import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;

/**
 * To be truly secure against a malicious adversary, we must use a non-linear,
 * cryptographically strong pseudorandom function (PRF).
 * 
 * Because Java does not include SipHash out of the box, the cleanest way to do
 * this using 100% built-in Java features is to leverage the native
 * cryptographic MAC (Message Authentication Code) framework (javax.crypto.Mac).
 * This framework uses hardware-accelerated, cryptographically secure non-linear
 * hashing (like HmacSHA256) that cannot be broken by linear algebra.
 * 
 * <b>Immune to Linear Algebra</b>: Because HMAC-SHA256 is non-linear, an
 * attacker cannot reverse-engineer your secretKey even if they discover
 * thousands of input/output pairs.
 * 
 * <b>Bypasses .hashCode()</b>: This method reads the actual data bytes of your
 * keys directly, preventing attackers from exploiting known flaws in standard
 * Java hash codes.
 * 
 */
public class CryptographicallySecureHash {
    private final byte[] secretKey;

    public CryptographicallySecureHash() {
        // Generate a 128-bit unguessable key using operating system entropy
        SecureRandom cryptoRand = new SecureRandom();
        this.secretKey = new byte[16];
        cryptoRand.nextBytes(this.secretKey);
    }

    /**
     * Securely hashes any string key into a dynamic table size.
     */
    public int hash(String key, int tableSize) {
        if (key == null) return 0;
        if (tableSize <= 0) throw new IllegalArgumentException("Invalid table size");

        try {
            // Use HMAC-SHA256 (Built into Java)
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec keySpec = new SecretKeySpec(this.secretKey, "HmacSHA256");
            mac.init(keySpec);

            // Compute the non-linear cryptographic hash of the input string
            byte[] rawHmac = mac.doFinal(key.getBytes(StandardCharsets.UTF_8));

            // Extract the first 4 bytes as a 32-bit signed integer
            int hashValue = ((rawHmac[0] & 0xFF) << 24) |
                            ((rawHmac[1] & 0xFF) << 16) |
                            ((rawHmac[2] & 0xFF) << 8)  |
                            (rawHmac[3] & 0xFF);

            // Map safely to your dynamic bucket size
            return Math.abs(hashValue) % tableSize;

        } catch (Exception e) {
            // Fallback to a safe runtime exception
            throw new IllegalStateException("Crypto framework failure", e);
        }
    }
}
