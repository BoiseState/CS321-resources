#!/bin/sh

function header(){
	output=$1
	for i in {1..80} 
	do
		echo -n "-" >> $output
	done
	echo >> $output
}

echo
echo "Compiling the source code"
echo
javac *.java

if ! test -f HashtableTest.class
then
	echo
	echo "HashtableTest.class not found! Not able to test!! "
	echo
	exit 1
fi

echo
echo "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
echo "Running test for word-list for varying load factors"
echo "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
echo


debugLevel=1
dos2unix test-cases/* >& /dev/null

for load in 0.5 0.6 0.7 0.8 0.9 0.95 0.99
do
	echo "Running java HashtableTest dataSource = 3 loadFactor = $load "
	java HashtableTest 3 $load $debugLevel  >> /dev/null
	dos2unix linear-dump.txt double-dump.txt  >& /dev/null

	echo
	diff linear-dump.txt test-cases/word-list-$load-linear-dump.txt > diff-linear-$load.out
	if test "$?" = 0
	then
		echo "Test PASSED for linear probing and load = $load"
		/bin/rm -f diff-linear-$load.out
	else
		echo "==> Test FAILED for linear probing load = $load!! "
		echo "       Check the file diff-linear-$load.out for differences"
	fi

	diff double-dump.txt test-cases/word-list-$load-double-dump.txt > diff-double-$load.out
	if test "$?" = 0
	then
		echo "Test PASSED for double probing and load = $load"
		/bin/rm -f diff-double-$load.out
	else
		echo "==> Test FAILED for double probing load = $load!! "
		echo "       Check the file diff-double-$load.out for differences"
	fi
	echo
done

