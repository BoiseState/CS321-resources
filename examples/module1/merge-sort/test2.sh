#!/bin/sh

echo
echo "Running merge sort without a sentinel"
cutoff=40
for i in  1000000 2000000 3000000 5000000 10000000 
do
	java MergeSortTest $i 40 nosentinel
done

echo

echo "Running merge sort with a sentinel"
for i in  1000000 2000000 3000000 5000000 10000000
do
	java MergeSortTest $i 40 sentinel
done
echo
