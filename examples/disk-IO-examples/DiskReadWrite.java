import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 
 */

/**
 * The layout of the binary data file is as follows
 * 
 * metadata node node node ...
 * 
 * where metadata has two pieces of information: byte offset to the root node
 * (long - 8 bytes) number of nodes in the data structure (long 8 bytes)
 * 
 * @author amit
 *
 */
public class DiskReadWrite
{

    private long nextDiskAddress = 0;
    private RandomAccessFile dataFile;
    private FileChannel file;
    private ByteBuffer buffer;

    private int METADATA_SIZE = 8;
    private long root = 8; // offset to the root node

    public DiskReadWrite(File fileName) throws IOException {
	try {
	    dataFile = new RandomAccessFile(fileName, "rw");
	    file = dataFile.getChannel();
	    buffer = ByteBuffer.allocateDirect(Node.NODE_SIZE);
	    if (fileName.exists()) {
		readMetaData();
	    } else {
		writeMetaData();
	    }
	} catch (FileNotFoundException e) {
	    System.err.println(e);
	}
    }

    /**
     * 
     * A simple Node class used to demo diskRead and diskWrite methods.
     * 
     * @author amit
     *
     */
    private class Node
    {
	private int n;
	private boolean leaf;
	private long left, right;
	private long address;
	// We will store Boolean as 1 byte as its size is not defined in Java
	public static final int NODE_SIZE = Integer.BYTES + 1 + 2 * Long.BYTES;;
	
	public Node() {
	    this(true);
	}
	
	public Node(boolean onDisk) {
	    leaf = false;
	    n = 0;
	    left = right = 0;
	    if (onDisk) {
		nextDiskAddress += NODE_SIZE;
		address = nextDiskAddress;
	    }
	}

	public String toString() {
	    return "Node[n=" + n + ",leaf=" + leaf + ",left-child=" + left + ",right-child = " + right + "]";
	}
    }



    public void readMetaData() throws IOException {
	file.position(0);

	ByteBuffer tmpbuffer = ByteBuffer.allocateDirect(METADATA_SIZE);

	tmpbuffer.clear();
	file.read(tmpbuffer);

	tmpbuffer.flip();
	root = tmpbuffer.getLong();
    }


    public void writeMetaData() throws IOException {
	file.position(0);

	ByteBuffer buffer = ByteBuffer.allocateDirect(METADATA_SIZE);

	buffer.clear();
	buffer.putLong(root);

	buffer.flip();
	file.write(buffer);
    }


    public Node diskRead(long diskAddress) throws IOException {
	file.position(diskAddress);
	buffer.clear();

	file.read(buffer);
	buffer.flip();

	int n = buffer.getInt();
	byte value = buffer.get(); // read a byte
	boolean leaf = false;
	if (value == 1) leaf = true;
	long left = buffer.getLong();
	long right = buffer.getLong();

	Node x = new Node(false); //the false flag is so that it isn't added to the disk file
	x.n = n;
	x.leaf = leaf;
	x.left = left;
	x.right = right;
	
	return x;
    }


    public void diskWrite(Node x) {

    }


    /**
     * @param args
     */
    public static void main(String[] args) {

	String fileName = "test.data";
	try {
	    DiskReadWrite test = new DiskReadWrite(new File(fileName));

	} catch (IOException e) {
	    System.err.println("DiskReadWrite: " + e);
	}
    }

}
