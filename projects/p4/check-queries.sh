#!/bin/sh

case $# in
0) echo "Usage: " `basename $0` " <datafile> "; exit 1;;
esac


datafile=$1
for i in 1 2 3 4 10 16 20 31
do
	echo
	echo "Running queryfile " query$i "on $datafile.btree.data.$i.0"
	echo
	time java -jar build/libs/GeneBankSearchBTree.jar 0 0 $datafile.btree.data.$i.0 $i queries/query$i 1  > queries/query$i-$datafile.out
done
echo

for i in 1 2 3 4 10 16 20 31
do
	diff -w queries/query$i-$datafile.out results/queries/query$i-$datafile.out
	if test "$?" = "0"
	then
		echo "----> Query-Test-$i PASSED!"
	else
		echo "----> Query-Test-$i FAILED@$#!"
	fi

done
echo


do
done

