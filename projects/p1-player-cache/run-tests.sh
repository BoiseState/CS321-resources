#!/bin/bash

javac *.java

if  test -e Player-List50.data  -a -e Player-List1000.data -a -e Player-List100000.data
then
	echo
	echo "Input data files found. Proceeding with the test!"
	echo
else
	echo
	echo "Generating input data files: Player-List50.data, Player-List1000.data, and Player-List100000.data!"
	echo
	javac PlayerGenerator.java
	java PlayerGenerator 50 3.0 0 123
	java PlayerGenerator 1000 15.0 0 123
	java PlayerGenerator 100000 2500.0 0 123
fi


for i in 1 2 3 4 5 6
do
	cmd=$(cat test-cases/in$i)
	echo "======================================================================="
	echo "Running Test $i:" $cmd " "
	$cmd > out$i
	diff -w -I -B '^Time.*' out$i test-cases/out$i
	result=$?
	if test "$result" = 0
	then
		echo " ---- Test $i output matches! ---- "
	fi
done

echo "======================================================================="
