
#include "ExternalBinarySearch.h"

/*
RecordPtr extBinarySearch(FILE *dataFile, int key):
	Search for a record with the input key value in the specified binary file
	on the disk. Uses binary search to minimize the number of disk reads.

	Input: 
			dataFile  file pointer to an open file.
			key       intgere key value to use for searching

	Output: Pointer to a record structure if search is successful
			otherwise a NULL pointer .
			
	Side Effects: None
*/
RecordPtr extBinarySearch(FILE *dataFile, unsigned long int key)
{
	/* the search window is low..high if we view the dataFile as an
	   array of record strutures */
	long int low = 0;
	long int mid = 0;
	long int high = 0;
	long int pos = 0; /* offset into the file, in bytes */
	RecordPtr record;
	char *buffer;

	if (dataFile == NULL) return NULL;

	/* figure out the size of the file in bytes */
	fseek(dataFile, 0, SEEK_END);
	high = ftell(dataFile);
	/* set high to number of records in the file */
	high = high/sizeof(Record) - 1;

	if (DEBUG >= 2) {
		fprintf(stderr, "data file has %ld records\n", high);
	}

	record = (RecordPtr) malloc(sizeof(Record));
	while (low <= high) {
		mid = (low+high)/2;
		pos = mid * sizeof(Record); /* calculate byte offset in the file */
		fseek(dataFile, pos, SEEK_SET);
		fread(record, sizeof(Record), 1, dataFile);
		if (DEBUG >= 2) {
			buffer = toString(record);
			fprintf(stderr, "low = %ld mid = %ld high = %ld\n", low, mid, high);
			fprintf(stderr, "====>record: %s", buffer);
			/*free(buffer);*/
		}
		if (record->key == key) {
			return record;
		} else if (record->key < key) {
			low = mid + 1;
		} else { /* record->key > key */
			high = mid - 1;
		}
	}

	free(record);
	return NULL;
}
