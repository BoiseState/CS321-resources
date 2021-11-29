
/**

 example of creating/writing to a  binary file
 example of appending to a  binary file
 example of reading from a  binary file
 example of random access read/write

 @author Amit Jain

*/

import java.io.*;

public class RandomAccessExample
{
    public static void main(String[] args) throws IOException {
	// write 128 integers out to a file
	File datafile = new File("data");
	RandomAccessFile fout = new RandomAccessFile(datafile, "rw");

	for (int i = 0; i < 128; i++) {
	    fout.writeInt(i);
	}
	fout.close();

	// now append another 128 integers
	System.out.println("file size before  appending = " + datafile.length() + " bytes");

	// the overloaded constructor with a second argument "true"
	// specifies append instead of create
	RandomAccessFile fa = new RandomAccessFile("data", "rw");
	fa.seek(datafile.length()); // seek to the end of the file
	for (int i = 0; i < 128; i++) {
	    fa.writeInt(i);
	}
	fa.close();

	System.out.println("file size after appending  = " + datafile.length() + " bytes");

	// random access read/write to the file
	RandomAccessFile data = new RandomAccessFile("data", "rw");

	data.seek(data.length()); // seek to the end of the file
	data.writeInt(128);
	System.out.println("after random access write = " + datafile.length() + " bytes");
	data.seek(4);
	// the integer at that offset should be 1
	System.out.println("integer read from offset 516 was:" + data.readInt());

	data.seek(0);
	for (int i = 0; i < 257; i++) {
	    int value = data.readInt();
	    System.out.print(value + " ");
	    if (i % 10 == 0) System.out.println();
	}
	System.out.println();
	data.close();

    }
}
