#!/bin/sh

cutoff=40
for n in  100000 200000 400000 500000 5000000 10000000 20000000 30000000
do
	java QuickSortTest $n 
done
