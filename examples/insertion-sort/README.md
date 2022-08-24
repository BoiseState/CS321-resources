
Experiments with Insertion sort
===============================

Usage: java InsertionSortTest <input size> [<seed>]

The script test1.sh runs insertion sort over a range of input sizes to see the growth of run time
versus input size.


Native timing
=============


To time more precisely (on a server with multiple users), we have to use the timing function from
the Operating System (we need C to access that).

Generate the native C library (libtiming.so) with the command:

make

To run using libtiming.so  NativeTiming library.

java -Djava.library.path=../../timing/ InsertionSortTest <input size> [<seed>]


