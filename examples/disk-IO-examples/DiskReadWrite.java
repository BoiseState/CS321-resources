import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import java.util.ArrayList;
import java.util.Random;

/**
 * This is a fairly complete example showing how to manipulate a binary search tree 
 * stored on the disk as a external data structure in a binary file. The layout of the 
 * binary search tree in the data file is as follows:
 * 
 * metadata
 * node
 * node 
 * node 
 * ...
 * 
 * where metadata has one piece of information: byte offset to the root node.
 * The byte offset is a long value, so it takes 8 bytes (Long.BYTES).
 * 
 * This example is for demonstration purposes and and has only gone through a few smoke tests.
 * 
 * First create a binary search tree on disk with the command:
 * <tt> java DiskReadWrite --create </tt>
 * 
 * Then dump the tree to show we can read the tree from the disk. Use the following command to 
 * print out the tree.
 * 
 * <tt> java DiskReadWrite --dump </tt>
 * 
 * @author amit
 *
 */
public class DiskReadWrite {

    private int METADATA_SIZE = Long.BYTES;
    private long nextDiskAddress = METADATA_SIZE;
    private FileChannel file;
    private ByteBuffer buffer;
    private int nodeSize;

    private long rootAddress = METADATA_SIZE; // offset to the root node
    private Node root; // the root node in memory


    /**
     * Create a binary search tree on disk if it doesn't exist, otherwise read the
     * metadata for a tree that already exists on disk.
     * 
     * @param fileName
     * @throws IOException
     */
    @SuppressWarnings("resource")
    public DiskReadWrite(File fileName) throws IOException {
        
        Node r = new Node(null, false); //dummy root node, not on disk
        nodeSize = r.getDiskSize();
        buffer = ByteBuffer.allocateDirect(nodeSize);

        try {
            if (!fileName.exists()) {
                fileName.createNewFile();
                RandomAccessFile dataFile = new RandomAccessFile(fileName, "rw");
                file = dataFile.getChannel();
                writeMetaData();
            } else {
                RandomAccessFile dataFile = new RandomAccessFile(fileName, "rw");
                file = dataFile.getChannel();
                readMetaData();
                root = diskRead(rootAddress);
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
    private class Node {
        private long address; //byte offset in disk file
        private int n; //for demo purposes, we don't really use it
        private TreeObject key;
        private boolean leaf; 
        private long parent, left, right; //these are byte offsets in the data file

        public Node(TreeObject key, boolean onDisk) {
            n = 0;
            this.key = key;
            leaf = false;
            left = right = parent = 0; //0 represents null for disk offsets
            if (onDisk) {
                address = nextDiskAddress;
                nextDiskAddress += nodeSize;  //update pointer for next node on disk
            }
        }
        
        /**
         * Calculate the size of a node as stored on disk (in bytes).
         * 
         * @return the size of a node on disk
         */
        public int getDiskSize() {
            // We will store boolean as 1 byte as its size is not defined in Java
            return Integer.BYTES + TreeObject.getDiskSize() + 1 + 3 * Long.BYTES;

        }
    }


    /**
     * Read the metadata from the data file.
     * @throws IOException
     */
    public void readMetaData() throws IOException {
        file.position(0);

        ByteBuffer tmpbuffer = ByteBuffer.allocateDirect(METADATA_SIZE);

        tmpbuffer.clear();
        file.read(tmpbuffer);

        tmpbuffer.flip();
        rootAddress = tmpbuffer.getLong();
    }
    

    /**
     * Write the metadata to the data file.
     * @throws IOException
     */
    public void writeMetaData() throws IOException {
        file.position(0);

        ByteBuffer tmpbuffer = ByteBuffer.allocateDirect(METADATA_SIZE);

        tmpbuffer.clear();
        tmpbuffer.putLong(rootAddress);

        tmpbuffer.flip();
        file.write(tmpbuffer);
    }


    /**
     * Reads a node from the disk and returns a Node object built from it.
     * @param diskAddress the byte offset for the node in the data file
     * @return the Node object
     * @throws IOException
     */
    public Node diskRead(long diskAddress) throws IOException {
        if (diskAddress == 0) return null;
        
        file.position(diskAddress);
        buffer.clear();

        file.read(buffer);
        buffer.flip();

        int n = buffer.getInt();

        long value = buffer.getLong();
        long frequency = buffer.getLong();
        TreeObject key = new TreeObject(value, frequency);

        byte flag = buffer.get(); // read a byte
        boolean leaf = false;
        if (flag == 1)
            leaf = true;

        long parent = buffer.getLong();
        long left = buffer.getLong();
        long right = buffer.getLong();

        Node x = new Node(key, false); // the false flag is so that it isn't added to the disk file
        x.n = n;
        x.leaf = leaf;
        x.parent = parent;
        x.left = left;
        x.right = right;
        x.address = diskAddress;

        return x;
    }

    /**
     * Writes a node to the disk at the specified disk offset *in the Node object).
     * @param x the Node to write
     * @throws IOException
     */
    public void diskWrite(Node x) throws IOException {
        file.position(x.address);
        buffer.clear();

        buffer.putInt(x.n);
        buffer.putLong(x.key.getValue());
        buffer.putLong(x.key.getFrequency());

        if (x.leaf) 
            buffer.put((byte)1);
        else
            buffer.put((byte)0);
        buffer.putLong(x.parent);
        buffer.putLong(x.left);
        buffer.putLong(x.right);

        buffer.flip();
        file.write(buffer);
    }


    /**
     * Cleanup at the end. Writes the root node and metadata and closes the data file.
     * @throws IOException
     */
    public void finishUp() throws IOException {
        diskWrite(root);
        writeMetaData();
        file.close();
    }

    /**
     * Insert a new object into the external binary search tree. This follows the algorithm 
     * from the book at page 321 of CLRS Introduction to Algorithms book, except for adding disk read/writes.
     * 
     * @param obj the object t insert
     * @throws IOException
     */
    public void insert(TreeObject obj) throws IOException {
        Node z = new Node(obj, true); //a new node on disk and in memory
        Node y = null;
        Node x = root;

        while (x != null) {
            y = x;
            if (z.key.compareTo(x.key) < 0) 
                x = diskRead(x.left);
            else
                x = diskRead(x.right);
        }
        if (y == null) {
            root = z;
            rootAddress = z.address;
        } else if (z.key.compareTo(y.key) < 0) {
            y.left = z.address;
            diskWrite(y);
        } else {
            y.right = z.address;
            diskWrite(y);
        }
        diskWrite(z); // z is new so it is always written
    }
    
    /**
     * Prints the tree using an inorder traversal.
     * @throws IOException
     */
    public void print() throws IOException {
        inorderTreeWalk(root);
    }
    
    /**
     * Recursive inorder traversal. The usual procedure except for the inclusion of disk reads.
     * 
     * @param x the starting node
     * @throws IOException
     */
    private void inorderTreeWalk(Node x) throws IOException {
        if (x != null) {
            inorderTreeWalk(diskRead(x.left));
            System.out.println(x.key);
            inorderTreeWalk(diskRead(x.right));
        }
    }

    public ArrayList<Long> arrayWalk() throws IOException {
        ArrayList<Long> result = new ArrayList<>();

        arrayWalk(root, result);
        return result;
    }

    private void arrayWalk(Node x, ArrayList<Long> result) throws IOException {
        if (x != null) {
            arrayWalk(diskRead(x.left), result);
            result.add(x.key.getValue());
            arrayWalk(diskRead(x.right), result);
        }
    }


    /**
     * Create an external binary search for some arbitrary objects.
     * @param file  the data file where the tree will be stored
     * @throws IOException
     */
    public static void createTree(File file) throws IOException {
        DiskReadWrite test = new DiskReadWrite(file);

        int n = 1000;
        long seed = 1234;
        Random generator = new Random(seed);
        for (int i = 1; i <= n; i++) {
            TreeObject next = new TreeObject(generator.nextInt(n), 1);
            test.insert(next);
        }
        test.print();
        test.finishUp(); // needed to make sure file is written and closed up
    }
    
    /**
     * Dump the binary search tree (inorder traversal) from the give binary data file.
     * @param file
     * @throws IOException
     */
    public static void dumpTree(File file) throws IOException {
        DiskReadWrite test = new DiskReadWrite(file);
        test.print();
    }
    

    /**
     * The main program that takes a command line argument to either create or to dump an external binary search tree.
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {

        File file = new File("bst.data");
        if (args.length != 1) {
            System.err.println("Usage: java DiskReadWrite [--create|--dump]");
            System.exit(1);
        }
        if (args[0].equals("--create")) {
            createTree(file);
            System.out.println("DiskReadWrite: created a binary search tree in bst.data binary file");
        } else if (args[0].equals("--dump")) {
            dumpTree(file);
            System.out.println("DiskReadWrite: dumping a binary search tree from the file bst.data");
        } else {
            System.err.println("DiskReadWrite: unknown option: " + args[0]);
        }
    }

}
