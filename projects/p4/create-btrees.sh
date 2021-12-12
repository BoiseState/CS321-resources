#!/bin/sh

case $# in
0) echo "Usage: " `basename $0` " <datafile> "; exit 1;;
esac

datafile=$1
datafileName=`basename $datafile`
for i in 1 2 3 4 5 6 7 8 9 10 11 12 16 20 31
do
	time java -jar build/libs/GeneBankCreateBTree.jar 0 0 $datafile $i 500 1
	mv dump $datafileName.dump.$i
done

