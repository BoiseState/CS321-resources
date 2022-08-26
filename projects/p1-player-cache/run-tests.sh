#!/bin/bash

if  test -e Player-List50.data  -a -e Player-List1000.data -a -e Player-List100000.data
then
	echo
	echo "Input data files found. Proceeding with the test!"
	echo
else
	echo
	echo "Expected input data files: Player-List50.data, Player-List1000.data, and Player-List100000.data not found!"
	echo "Please generate all the data files as explained in the project specifications."
	echo "!!!BAILING out!!!"
	echo
	exit 1
fi


for i in 1 2 3 4 5 6
do
	cmd=$(cat test-cases/in$i)
	echo "======================================================================="
	echo "Running Test $i:" $cmd " "
	$cmd > out$i
	diff -w -I '^Time.*' out$i test-cases/out$i
	result=$?
	if test "$result" = 0
	then
		echo " ---- Test $i output matches! ---- "
	fi
done

echo "======================================================================="
