#!/bin/sh

echo
echo "Running merge sort to determine insertion sort cutoff"
echo


for size in 2000000 3000000 5000000 6000000 10000000
do
	echo
	echo "Starting test for " $size " elements"
	echo
	for cutoff in  10 20 30 40 50 100 200 500 1000
	do
		echo -n "cutoff = " $cutoff ": "
		java MergeSortTest $size $cutoff
	done
	echo
done

echo

