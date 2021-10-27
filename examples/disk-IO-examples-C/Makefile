
CC=gcc
CFLAGS=-Wall -O


all: gentxt genbin filesize 


gentxt: generate_text.o
	$(CC) $(CFLAGS)  -o $@ $<

genbin: generate_binary.o
	$(CC) $(CFLAGS)  -o $@ $<

filesize: filesize.o
	$(CC) $(CFLAGS)  -o $@ $<


clean:
	rm --force *.o a.out genbin gentxt  data* filesize

