
/**
 * Demo bitwise operators in Java. Bitwise operators can be used with any integral number type such as byte, short,
 * int, and long.
 * <p>
 * Basic definitions of bitwise operators. For two k-bit numbers, the operators are applied to
 * each pair of corresponding bits:
 * <p>
 * bitwise or :   0 | 0 = 0, 0 | 1 = 1, 1 | 0 = 1, 1 | 1 = 1 <br>
 * bitwise and:   0 &amp; 0 = 0, 0 &amp; 1 = 0, 1 &amp; 0 = 0, 1 &amp; 1 = 1 <br>
 * bitwise xor:   0 ^ 0 = 1, 0 ^ 1 = 1, 1 ^ 0 = 1, 1 ^ 1 = 1 <br>
 * bitwise left shift: x = x &lt;&lt; k  --> shift x left by k bits and fill in with zeros from the right. <br>
 * bitwise signed right shift: x = x &gt;&gt; k --> shift x right by k bits and fill in with the sign bit 
 *                                           from the left. <br>
 * bitwise unsigned (arithmetic)right shift: x = x &gt;&gt;&gt; k --> shift x right by k bits and fill in with 
 *                                                                   zeroes from the left <br>
 * <p>
 * Note: bitwise signed shift and unsigned shift are the same for positive numbers.
 * <p>
 * See <a href="https://www.baeldung.com/java-bitwise-operators">Java Bitwise Operators</a> for a brief tutorial.
 * 
 * @author amit
 *
 */
public class BitwiseOperatorsDemo
{

    /**
     * Print the result of a binary operation nicely.
     * 
     * @param operator
     *            the operator used
     * @param value1
     *            first operand
     * @param value2
     *            second operand
     * @param result
     *            the result of the operation
     */
    private static void printResult(String operator, int value1, int value2, int result) {
        System.out.printf("%s\n %s (%d)\n %s (%d)\n -------\n %s (%d)\n\n", operator, Integer.toString(value1, 2),
                value1, Integer.toString(value2, 2), value2, Integer.toString(result, 2), result);
    }


    /**
     * Print the result of an unary operation nicely.
     * 
     * @param operator
     *            the operator used
     * @param value
     *            first operand
     * @param result
     *            the result of the operation
     */
    private static void printResult(String operator, int value, int result) {
        System.out.printf("%s\n %s (%d)\n -------\n %s (%d)\n\n", operator, Integer.toString(value, 2), value,
                Integer.toString(result, 2), result);
    }


    /**
     * Demo all the bitwise operators in Java.
     * 
     * @param args The command line arguments aren't used.
     */
    public static void main(String[] args) {

        int value1;
        int value2;

        value1 = 0b110; // value is 6 (as a binary constant), use 0x for hexadecimal. 
                        // Upper case 0B or 0X also works.
        value2 = 0b101; // value is 5 (as a binary constant), use 0x for hexadecimal. Upper case 0b or
                        // Upper case 0B or 0X also works.


        int result = value1 | value2; // bitwise OR
        printResult("bitwise or |", value1, value2, result);

        result = value1 & value2; // bitwise AND
        printResult("bitwise and &", value1, value2, result);

        result = value1 ^ value2; // bitwise XOR
        printResult("bitwise xor ^", value1, value2, result);

        // complement operator uses 2's complement
        result = ~value1;
        printResult("2's complement", value1, result);

        // left shift by n bits -- fills in with zeroes
        // left shift by n bits is same as multiplying by 2^n
        result = value1 << 2; // multiply by 2^2 or 4
        printResult("left shift: value1 << 2", value1, result);

        // signed right shift by n bits -- fills in with the sign bit
        // (0 for +ve number and 1 for negative number)
        // right shift by n bits is same as dividing by 2^n
        value1 = 0b1100; // 12
        result = value1 >> 2; // divide by 2^2 or 4
        printResult("signed right shift on +ve number: value1 >> 2", value1, result);

        value1 = -12;
        result = value1 >> 2; // divide by 2^2 or 4
        printResult("signed right shift on -ve number: value1 >> 2", value1, result);

        // unsigned (arithmetic) right shift by n bits -- fills in with zeroes
        // for positive numbers, this behaves the same as signed right shift
        value1 = 0b1100; // 12
        result = value1 >>> 2; // multiply by 2^2 or 4
        printResult("unsigned right shift on +ve number: value1 >>> 2", value1, result);

        // for negative numbers, unsigned right shift loses the sign!
        // this is useful when we are storing data in the primitive type and the
        // sign of the number has no relevance
        value1 = -12;
        result = value1 >>> 2; // divide by 2^2 or 4
        printResult("unsigned right shift on -ve number: value1 >>> 2", value1, result);
    }

}
