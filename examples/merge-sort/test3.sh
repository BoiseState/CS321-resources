#!/bin/sh

seed=123

echo
echo "Comparing merge sort with insertion sort"
echo

for i in  100000 200000 300000 400000 500000 1000000 
do
	(cd ../insertion-sort/; java InsertionSortTest $i $seed)

	java MergeSortTest $i 0 $seed
done

echo
