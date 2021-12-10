#!/bin/sh

case $# in
0) echo "Usage: " `basename $0` " <datafile> "; exit 1;;
esac

datafile=$1
datafileName=`basename $datafile`
for i in 1 2 3 4 5 6 7 8 9 10 11 12 16 20 31
do
	time java GeneBankCreateBTree --degree=0 --gbkfile=$datafile --length=$i --cachesize=500 --debug=1
	mv dump $datafileName.dump.$i
done

