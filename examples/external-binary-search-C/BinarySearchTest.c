
#include <stdlib.h>
#include <stdio.h>
#include "common.h"
#include "Record.h"
#include "ExternalBinarySearch.h"

char *program;
const int MAX_KEY = 100000;

double getMilliseconds();

void conduct_random_searches(FILE *dataFile, long int n)
{
	long int i;
	unsigned long int key;
	Record *record;
	char *buffer;

	for (i=0; i<n; i++) {
		key = (unsigned long int) random() % MAX_KEY;
		if (DEBUG >= 1) {
				printf("\nSearching for key = %ld\n\n", key);
		}
		record = extBinarySearch(dataFile, key);
		if (DEBUG >= 1) {
			if (record) {
				buffer = toString(record);
				printf("%s\n", buffer);
				printf("found: record with key %ld\n", key);
				free(record);
				free(buffer);
			} else {
				printf("not found: record with key %ld\n", key);
			}
		}
	}
}

int main (int argc, char **argv)
{
	int n;
	long int count;
	FILE *dataFile;
	double startTime, totalTime;

	program = argv[0];

	if (argc != 3) {
		fprintf(stderr, "Usage: %s <data file name> <number of searches>\n",
				program);
		exit(1);
	}
	n = atoi(argv[2]);
	dataFile = fopen(argv[1], "r");
	if (!dataFile) {
		perror(program);
		exit(1);
	}
	fseek(dataFile, 0, SEEK_END);
	count = ftell(dataFile);
	count = count/sizeof(Record);
	if (DEBUG >= 1) {
		   fprintf(stderr, "data file has %ld records\n", count);
	}

	startTime = getMilliseconds();
	conduct_random_searches(dataFile, n);
	totalTime = getMilliseconds() - startTime;
	printf("Elapsed time for %d searches = %8.2f seconds\n", n, totalTime/1000.0);

	fclose(dataFile);
	exit(0);
}

/* vim: set ts=4: */
