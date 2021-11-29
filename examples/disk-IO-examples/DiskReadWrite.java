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
 *   metadata
 *   node
 *   node
 *   node
 * ...
 * 
 * where metadata has two pieces of information: 
 *   byte offset to the root node (long - 8 bytes)
 *   number of nodes in the data structure (long 8 bytes)
 * 
 * @author amit
 *
 */
public class DiskReadWrite
{
    
    private int nextDiskAddress = 0;
    private RandomAccessFile dataFile;
    private FileChannel file;
    private ByteBuffer buffer;
    
    private int METADATA_SIZE = 8;
    private long root = 8; //offset to the root node
    
    
    public DiskReadWrite(File fileName) throws IOException {
	try {
	    dataFile = new RandomAccessFile(fileName, "rw");
	    file = dataFile.getChannel();
	    buffer = ByteBuffer.allocateDirect(Node.getDiskSize());
	    if (fileName.exists()) {
		readMetaData();
	    } else {
		writeMetaData();
	    }    
	} catch (FileNotFoundException e) {
	    System.err.println(e);
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

    public Node diskRead() {
	return null;
    }
    
    public void diskWrite(Node x) {
	
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
	// TODO Auto-generated method stub

	String fileName = "test.data";
	try {
	    DiskReadWrite test = new DiskReadWrite(new File(fileName));
	    Node x = new Node(10, true, 0, 0);
	    test.diskWrite(x);
	    
	} catch (IOException e) {
	    System.err.println("DiskReadWrite: " + e);
	}
    }

}
