
JFLAGS=

# Recognize files with .class and .java extensions
.SUFFIXES: .class .java
#
# # This is a rule to convert a file with .java extension
# # into a file with a .class extension. The macro $< just
# # supplies the name of the file (without the extension)
# # that invoked this rule.
#
.java.class:
	javac $(JFLAGS) $<

CC=gcc
JDK_HOME=/usr/java/default/
INCLUDE=-I$(JDK_HOME)/include -I$(JDK_HOME)/include/linux
CFLAGS= -fPIC -shared -c $(INCLUDE)


TestNativeTiming: libtiming.so TestNativeTiming.class NativeTiming.class
	
libtiming.so: NativeTiming.o
	ld -shared NativeTiming.o  -o $@
	LD_LIBRARY_PATH=.:$$LD_LIBRARY_PATH; export LD_LIBRARY_PATH

clean:
	/bin/rm -f libtiming.so NativeTiming.o *.class
