#Overview of disk IO examples

## DiskReadWrite.java and TreeObject.java
This is the best example to study how to store a data structures (it uses binary search
trees with TreeObjects) in a binary file on the disk. It shows how to use metadata and be 
able to do diskRead and diskWrite operations.

## SaveTable.java, LoadTable.java,  StudentRecord.java
This example shows how to use the built-in serialization in Java to store a hashtable in 
a binary disk file. Note that serialization stores objects in a sequential manner in the
file so it isn't suitable for BTrees or other data structures that require random access to
the object.

## GenerateText.java, GenerateBinary.java,  GenerateBinarySlower.java
These example show how to store the same information in a text file and a binary files. The binary files are more compact and faster to read/write while the text files are easier for
humans to examine. The GenerateBinary.java uses the java.nio package, which is faster than he normal file IO found in java.io package.