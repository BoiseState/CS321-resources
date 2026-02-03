#!/bin/sh

echo
echo "Running merge sort with insertion sort cutoff=200"
cutoff=100
for i in  1000000 2000000 3000000 5000000 10000000 20000000 30000000 50000000
do
	java MergeSortTest $i 200
done

echo
