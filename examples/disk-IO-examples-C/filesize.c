
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv)
{
	long int size;
	FILE *fin;

	if (argc !=2 ) {
		fprintf(stderr, "Usage: %s <filename>\n", argv[0]);
		exit(1);
	}

	fin = fopen(argv[1], "r");
	if (!fin) {
		perror("filesize:");
	}
	fseek(fin, 0, SEEK_END);
	size = ftell(fin);
	printf("Size of the file %s = %lf MB\n", argv[1],  (double) size/(1024*1024));
	exit(0);
}
