#!/bin/sh

case $# in
0) echo "Usage: " `basename $0` " <datafile> "; exit 1;;
esac

datafile=$1

echo
echo "Checking your dump files against sample dump files..."
echo

for i in 1 2 3 4 5 6 7 8 9 10 11 12 16 20 31
do
	echo -n "Test-$i: Comparing $datafile.dump.$i"
	diff -w $datafile.dump.$i results/dumpfiles/$datafile.dump.$i
	if test "$?" = "0"
	then
		echo "----> Test-$i PASSED!"
	fi
done
echo



