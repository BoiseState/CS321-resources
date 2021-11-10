
/**
 * Shows an extended example using bitwise operators and DNA sequences.
 * The extended example shows how to pack the bases in a DNA sequence 
 * into a long and how to extract the bases back out. This will result in around 8x savings in memory
 * (and on disk as well)
 * @author amit
 *
 */
public class BitwiseShiftDemo
{
    public static void main(String[] args) {

	long sequence = 0;
	
	String dnaSequence = "gatcctccat";
	dnaSequence = dnaSequence.toUpperCase();
	int seqLength = dnaSequence.length();
	System.out.println("original sequence: " + dnaSequence);
	
	for (int i = 0; i < seqLength; i++) {
	    sequence = sequence << 2;
	    switch (dnaSequence.charAt(i)) {
		case 'A':
		    sequence = sequence | DNA.A;
		    break;
		case 'C':
		    sequence = sequence | DNA.C;
		    break;
		case 'T':
		    sequence = sequence | DNA.T;
		    break;
		case 'G':
		    sequence = sequence | DNA.G;
		    break;
	    }   
	}
	
	System.out.println(Long.toString(sequence, 2));
	
	long value = sequence;
	String seqString = "";
	int next = 0;
	int length = 0;
	
	while (value > 0) {
	    //extract the least significant two bits -- which is the DNA base
	    next = (int) value & 0b11; 
	    switch (next) {
		case 0: seqString = "A" + seqString; break;
		case 3: seqString = "T" + seqString; break;    
		case 1: seqString = "C" + seqString; break;   
		case 2: seqString = "G" + seqString; break;
	    }
	    value = value >>> 2;
	    length++;
	    if (length == seqLength) break;
	}
	System.out.println("extracted sequence: " + seqString);
    }

}
