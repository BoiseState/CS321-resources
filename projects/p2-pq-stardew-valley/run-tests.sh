#!/bin/bash

javac  MyLifeInStardew.java

for i in 1 2 3 4 5 6
do
	cmd=$(cat test-cases/in$i)
	echo "======================================================================="
	echo "Running Test $i:" $cmd " "
	$cmd > out$i
	diff -w out$i test-cases/out$i
	result=$?
	if test "$result" = 0
	then
		echo " ---- Test $i output matches! ---- "
	fi
done

echo "======================================================================="
