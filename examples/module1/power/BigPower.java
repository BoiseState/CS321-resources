

import java.math.*;

public class BigPower
{
	/**
	 Compute x^n using the naive algorithm. O(n) multilications.
	 @param x  The base value, unlimited precision using BigInteger type.
	 @param n  The exponent, an integer.
	 @return   The computed power as a BigInteger
	 */
	public static BigInteger power_brute_force(BigInteger x, int n)
	{
		BigInteger temp = BigInteger.ONE;
		for (int i=1; i<=n; i++)
			temp = temp.multiply(x);
		return temp;
	}

    /**
	 Compute x^n using recursive doubling technique. O(lg n) multilications.
	 @param x  The base value, unlimited precision using BigInteger type.
	 @param n  The exponent, an integer.
	 @return   The computed power as a BigInteger
	 */
   	public static BigInteger power_recursive_doubling(BigInteger x, int n)
    {
        BigInteger temp = x;
        BigInteger result = BigInteger.ONE;
		while (n != 0) {
            if ((n & 1) == 1) 
                result = result.multiply(temp);
			if ((n = n >>>1) != 0)
            	temp = temp.multiply(temp);
        }
        return result;
	}

}
