#!/bin/sh

echo
echo "Comapring run times for quicksort and mergesort"
echo

seed=123
for n in 1000000 5000000 10000000 20000000 30000000 50000000
do
	echo
	java QuickSortTest $n  $seed
	(cd ../merge-sort/; java MergeSortTest $n 40 sentinels 123)
done
