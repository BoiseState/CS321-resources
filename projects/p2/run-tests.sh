#!/bin/bash

for i in 1 2 3 4 5 
do
	cmd=$(cat test-cases/in$i)
	echo "Running  " $cmd
	$cmd > out$i
	diff -w out$i test-cases/out$i
	result=$?
	if test "$result" = 0
	then
		echo " ---- Test $i output matches!"
	fi
done
