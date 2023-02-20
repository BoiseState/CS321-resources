
Experiments with mergesort
==========================

Run the MergeSortTest class to run and test merge sort.

* test1.sh tries various cutoff values for insertion sort to see which one is the best.
* test2.sh runs merge sort for various input sizes
* test3:sh compares  merge sort [Theta(n lg n)] and insertion sort [Theta(n^2)]

 | input size | insertion sort  | merge sort| speedup | 
 | ----------: | --------: | -----: | -----: | 
 | 100,000    |  0.69s   | 0.01s |   69  | 
 | 200,000    |  2.80s   | 0.02s |  140  | 
 | 300,000    |  6.47s   | 0.03s |  215  | 
 | 400,000    | 11.89s   | 0.05s |  237  |  
 | 500,000    | 19.35s   | 0.06s |  322  | 
 | 1000,000   | 81.40s   | 0.12s |  678  | 
 | 10,000,000 | 8140.00s | 1.27s | 6522  |  

 The insertion sort time for 10M is projected. For 100M elements, merge sort is projected to
 be around 62,000 times faster!!
