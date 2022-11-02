
/**
 * Shows an extended example using bitwise operators and DNA sequences. The
 * extended example shows how to pack the bases in a DNA sequence into a long
 * and how to extract the bases back out. This will result in around 8x savings
 * in memory (and on disk as well).
 * 
 * Feel free to use these methods in your project, one for encoding a DNA string
 * into a long and the second for decoding a long into a DNA string.
 * 
 * @author amit
 *
 */

public class BitwiseShiftDemo
{
    /**
     * Converts a DNA sequence provide as a string of A,C, D and G into a long by
     * encoding into a binary number.
     * 
     * @param dnaSequence
     *            the DNA string to encode
     * @return the encoded long representing the DNA string
     */
    public static long encode(String dnaSequence) {
        int seqLength = dnaSequence.length();

        long encoding = 0;
        for (int i = 0; i < seqLength; i++) {
            encoding = encoding << 2;
            switch (dnaSequence.charAt(i)) {
                case 'a':
                    encoding = encoding | DNA.A;
                    break;
                case 'c':
                    encoding = encoding | DNA.C;
                    break;
                case 't':
                    encoding = encoding | DNA.T;
                    break;
                case 'g':
                    encoding = encoding | DNA.G;
                    break;
            }
        }
        return encoding;
    }


    /**
     *
     * Converts a long representing a DNA string (of up to length 31) back into a
     * DNA string of A,C, D and G by decoding the binary number stored as a long.
     * 
     * @param sequence
     *            the long that encodes a DNA string
     * @param seqLength
     *            the length of the sequence to decode
     * @return the decode DNA string
     * @throws IllegalArgumentException may throw an exception if argument passed is incorrect
     */
    public static String decode(long sequence, int seqLength) throws IllegalArgumentException {
        if (seqLength > 31) throw new IllegalArgumentException("seqlength must be <= 31");

        long value = sequence;
        String seqString = "";
        int next = 0;
        int length = 0;

        while (value > 0) {
            // extract the least significant two bits -- which is the DNA base
            next = (int) value & 0b11; // mask out the 2 least significant bits
            switch (next) {
                case 0:
                    seqString = "a" + seqString;
                    break;
                case 3:
                    seqString = "t" + seqString;
                    break;
                case 1:
                    seqString = "c" + seqString;
                    break;
                case 2:
                    seqString = "g" + seqString;
                    break;
            }
            value = value >>> 2;
            length++;
            if (length == seqLength) break;
        }
        return seqString;

    }


    /**
     * The driver for demonstrating encoding and decoding of DNA bases into and from a long.
     * @param args does not take commend line arguments
     * @throws IllegalArgumentException may throw an exception if argument passed is incorrect
     */
    public static void main(String[] args) throws IllegalArgumentException {

        String dnaSequence = "gatcctccat";
        int seqLength = dnaSequence.length();
        System.out.println("original sequence: " + dnaSequence);

        long sequence = encode(dnaSequence);
        // the Long.toString(sequence, 2) prints as binary, use 16 to print in
        // hexadecimal
        System.out.println(sequence + " " + Long.toString(sequence, 2));

        String seqString = decode(sequence, seqLength);
        System.out.println("extracted sequence: " + seqString);
    }
}
