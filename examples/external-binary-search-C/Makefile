
CC=gcc
#CFLAGS=-Wall -g  -DDEBUG=2
CFLAGS=-Wall -O -DDEBUG=0

all: TestBsearch gendata


gendata: gendata.o
	$(CC) $(CFLAGS)  -o $@ $<

TestBsearch: TestBsearch.o ExternalBinarySearch.o Record.o common.h timing.o
	$(CC) $(CFLAGS)  -o $@ TestBsearch.o ExternalBinarySearch.o Record.o timing.o

clean:
	rm --force *.o a.out TestBsearch data.bin gendata
