
Experiments with mergesort
==========================

You will need a copy of the timing library in the file libtiming.so (in the ../../timing folder).
If you run the make command in this folder, it will generate the library and copy it over.

Then define LD_LIBRARY_PATH in the shell as follows:

```
export LD_LIBRARY_PATH=.
```

before running the program. You can also define that in the java VM arguments (for running in an
IDE) as follows:

```
-Djava.library.path=.
```


*test1.sh tries various cutoff values for insertion sort to see which one is the best.

*test2.sh compares the sentinel version of merge versus the non-sentinel version.

*test3.sh runs mergesort with the proper cutoff and sentinels to see its performance over a range of input sizes.
