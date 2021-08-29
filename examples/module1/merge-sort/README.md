
Experiments with mergesort
==========================

Add Java VM argument

-Djava.library.path=.

to run using libtiming.so  NativeTiming library.

For extra memroy add Java VM arguments

-Xmx500m -Xms500m


*test1.sh tries various cutoff values for insertion sort to see which one is the best.

*test2.sh compares the sentinel version of merge versus the non-sentinel version.

*test3.sh runs mergesort with the proper cutoff and sentinels to see its performance over a range of input sizes.
