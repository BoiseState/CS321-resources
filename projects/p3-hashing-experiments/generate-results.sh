#!/bin/bash

function header(){
	output=$1
	for i in {1..80} 
	do
		echo -n "-" >> $output
	done
	echo >> $output
}

echo
echo "Compiling the source code"
echo
javac *.java


echo
echo "Running tests on random data...with debug level 1: output in results-random.txt"
echo
echo > results-random.txt
for f in 0.5 0.6 0.7 0.8 0.9 0.95 0.99
do
	header "results-random.txt"
	echo "Running java HashtableExperiment dataSource = 1 loadFactor = $f"
	java HashtableExperiment 1 $f >> results-random.txt
	header "results-random.txt"
	echo >> results-random.txt
done

echo
echo "Running tests on Date data...with debug level 1: output in results-date.txt"
echo
echo > results-date.txt
for f in 0.5 0.6 0.7 0.8 0.9 0.95 0.99
do
	header "results-date.txt"
	echo "Running java HashtableExperiment dataSource = 2 loadFactor = $f"
	java HashtableExperiment 2 $f >> results-date.txt
	header "results-date.txt"
	echo >> results-date.txt
done

echo
echo "Running tests on word-list data...with debug level 1: output in results-word-list.txt"
echo
echo > results-word-list.txt
for f in 0.5 0.6 0.7 0.8 0.9 0.95 0.99
do
	header "results-word-list.txt"
	echo "Running java HashtableExperiment dataSource = 3 loadFactor = $f"
	java HashtableExperiment 3 $f >> results-word-list.txt
	header "results-word-list.txt"
	echo >> results-word-list.txt
done

