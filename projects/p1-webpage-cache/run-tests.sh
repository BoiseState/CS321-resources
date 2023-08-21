#!/bin/bash

for i in 1 2 3 4 5 6 7 8
do
	cmd=$(cat test-cases/in$i)
	echo "======================================================================="
	echo "Running Test $i:" $cmd " "
	$cmd > out$i
	timing=$(grep -i Time out$i)
	echo "          ===> "  $timing
	dos2unix out$i >& /dev/null
	diff -B -w -I '^Time.*' -I "--" -I "~~" out$i test-cases/out$i
	result=$?
	if test "$result" = 0
	then
		echo " ---- Test $i output matches! ---- "
	else
		echo " ---- Test $i FAILED!! ---- "
	fi
done

echo "======================================================================="
