
/**
 * A utility class that provides methods to encode a DNA string into a long, decode
 * from the long back to a DNA string, and get the complement of a DNA string encoded 
 * as a long. Storing DNA strings as binary encoded long values will result in  
 * 8x savings in memory (and on disk as well) as well as faster processing time.
 *  
 * @author amit
 *
 */

public class SequenceUtils
{
    /**
     * Converts a DNA sequence provided as a string of A,C, T and G into a long by
     * encoding into a binary number. It is case insensitive.
     * 
     * @param dna The DNA sequence of bases A, C, T, and G
     * @return
     */
    public static long dnaStringToLong(String dna) {
        if (dna.length() > 31) {
            throw new IllegalArgumentException("DNA string longer than 31");
        }
        dna = dna.toLowerCase();
        long encoding = 0;

        for (int i = 0; i < dna.length(); i++) {
            char next = dna.charAt(i);
            switch (next) {
                case 'a':
                    encoding = encoding | DNA.A;
                    break;
                case 'c':
                    encoding = encoding | DNA.C;
                    break;
                case 'g':
                    encoding = encoding | DNA.G;
                    break;
                case 't':
                    encoding = encoding | DNA.T;
                    break;
                default:
                    break;
            }
            if (i == dna.length() - 1) break;
            encoding = encoding << 2;
        }
        return encoding;
    }


    /**
     * Converts a long representing a DNA string (of up to length 31) back into a
     * DNA string of A,C, D and G by decoding the binary number stored as a long.
     * 
     * @param sequence  the long that encodes a DNA string
     * @param seqLength the length of the sequence to decode
     * @throws IllegalArgumentException
     * @return
     */
    public static String longToDnaString(long sequence, int seqLength) throws IllegalArgumentException {
        if (seqLength > 31) throw new IllegalArgumentException("seqlength must be <= 31");

        StringBuilder buffer = new StringBuilder(32);
        for (int i = 0; i < seqLength; i++) {
            // extract the least significant two bits -- which is the next DNA base
            int next = (int) sequence & 0b11; // mask out the 2 least significant bits
            switch (next) {
                case DNA.A:
                    buffer.append('a');
                    break;
                case DNA.C:
                    buffer.append('c');
                    break;
                case DNA.G:
                    buffer.append('g');
                    break;
                case DNA.T:
                    buffer.append('t');
                    break;
                default:
                    break;
            }
            sequence = sequence >>> 2;
        }
        String value = buffer.reverse().toString();
        return value;
    }
    
    
    /**
     * Returns the complement of a DNA string encoded as a long. For example, the string 
     * ATCG will become TAGC since A and T are complementary bases and so are C and G.
     * 
     * @param sequence The DNA sequence to complement (encoded as a long value).
     * @param seqLength The length of the sequence.
     * @return
     */
    public static long getComplement(long sequence, int seqLength)
    {
        long mask = (1<<(2*seqLength)) - 1;
        return sequence ^ mask; // use xor operator to invert the bits
    }



    /**
     * The driver for demonstrating the usage of the methods in the class.
     * 
     * @param args does not take commend line arguments.
     * 
     * @throws IllegalArgumentException may throw an exception if argument passed is incorrect
     */
    public static void main(String[] args) throws IllegalArgumentException {

        String dnaSequence = "gatcctccat";
        int seqLength = dnaSequence.length();
        System.out.println("original sequence: " + dnaSequence);

        long sequence = dnaStringToLong(dnaSequence);
        // the Long.toString(sequence, 2) prints as binary, use 16 to print in hexadecimal
        System.out.println("Sequence (encoded as a long): " + sequence + " " + Long.toString(sequence, 2));
        
        String seqString = longToDnaString(sequence, seqLength);
        System.out.println("decoded sequence: " + seqString);
        System.out.println();
        
        System.out.println("complementary sequence: ");
        System.out.println(" " + dnaSequence);
        long complement = getComplement(sequence, seqLength);
        System.out.println(" " + longToDnaString(complement, seqLength));
    }
}
