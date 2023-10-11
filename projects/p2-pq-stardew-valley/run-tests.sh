#!/bin/bash

if ! test -f MyLifeInStardew.java
then
	echo "MyLifeInStardew.java file not found! Did you have import the correctly named version?"
	exit 1
fi

javac  MyLifeInStardew.java

if ! test -f MyLifeInStardew.class
then
	echo "MyLifeInStardew.class file not found!  Check that it compiles correctly."
	exit 1
fi


for i in 1 2 3 4 5 6
do
	echo "==============================================================================="
	cmd=$(cat test-cases/in$i) 
	result=$?
	if test "$result" != 0
	then
		echo " ++++ Test $i failed to run!!!! See difference in output in file out$i-diff.txt ++++ "
	else
	echo "Running Test $i:" $cmd " "
	$cmd >& out$i
	dos2unix out$i >& /dev/null
	diff -B -w out$i test-cases/out$i > out$i-diff.txt
	result=$?
	if test "$result" = 0
	then
		echo " ---- Test $i output matches! ---- "
	else
		echo " ++++ Test $i failed!!!! See difference in output in file out$i-diff.txt ++++ "
	fi
	fi
done

echo "==============================================================================="
