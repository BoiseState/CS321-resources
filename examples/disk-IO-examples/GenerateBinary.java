
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

/**
 * Illustrates how to write a binary file efficiently using java.nio stuff.
 * 
 * @author amit
 * 
 */

public class GenerateBinary
{
    final static int MAX_KEY = 1000000;
    final static int MAX_DOUBLE = 2148;
    final static int RECORD_SIZE = 28;
    final static int NUM_OF_RECORDS_PER_BUFFER = 146;
    final static int BUFFER_SIZE = RECORD_SIZE * NUM_OF_RECORDS_PER_BUFFER;

    private static void generateFile(int n, long seed, FileChannel fout) throws IOException {
	int i;
	int key;
	double value1;
	double value2;
	double value3;
	Random generator = new Random(seed);
	// make buffer be a multiple of the record size
	ByteBuffer buff = ByteBuffer.allocateDirect(RECORD_SIZE * NUM_OF_RECORDS_PER_BUFFER);

	for (i = 0; i < n; i++) {
	    /* key = generator.nextInt(MAX_KEY); */
	    key = 2 * i;
	    value1 = generator.nextDouble() * MAX_DOUBLE;
	    value2 = generator.nextDouble() * MAX_DOUBLE;
	    value3 = generator.nextDouble() * MAX_DOUBLE;
	    buff.putInt(key);
	    buff.putDouble(value1);
	    buff.putDouble(value2);
	    buff.putDouble(value3);
	    if (!buff.hasRemaining()) {
		buff.flip();
		fout.write(buff);
		buff.clear();
	    }
	}
	if (buff.position() > 0) {
	    buff.flip();
	    fout.write(buff);
	    buff.clear();
	}
    }


    public static void main(String argv[]) {
	int n;
	long seed = 0;

	if (argv.length < 1) {
	    System.err.println("Usage: java GenerateBinary <n> [<seed>]");
	    System.exit(1);
	}

	n = Integer.parseInt(argv[0]);
	if (argv.length == 2) {
	    seed = Long.parseLong(argv[1]);
	}

	try {
	    RandomAccessFile out = new RandomAccessFile("data.bin", "rw");
	    FileChannel fout = out.getChannel();
	    fout.truncate(0); // truncate the file
	    generateFile(n, seed, fout);
	    fout.close();
	    out.close();
	} catch (IOException e) {
	    System.err.println(e);
	    System.exit(1);
	}
	System.exit(0);
    }
}
