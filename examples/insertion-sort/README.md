
Experiments with Insertion sort
===============================

Usage: java InsertionSortTest <input size> [<seed>]

The script run-tests.sh runs insertion sort over a range of input sizes to see the growth of run time
versus input size. How much does the time increase when we double the input size?

Here is a typical output of the script:

```
InsertionSortTest:  Sorting    10000 elements took 0.03 secs

InsertionSortTest:  Sorting    20000 elements took 0.11 secs   3.67 times slower than previous value

InsertionSortTest:  Sorting    40000 elements took 0.33 secs  3.00 times slower than previous value

InsertionSortTest:  Sorting    80000 elements took 1.38 secs  4.18 times slower than previous value

InsertionSortTest:  Sorting   160000 elements took 5.41 secs  3.92 times slower than previous value

InsertionSortTest:  Sorting   320000 elements took 22.14 secs  4.09 times slower than previous value

InsertionSortTest:  Sorting   640000 elements took 87.83 secs  3.97 times slower than previous value
```

From the output, we can see that when we double the input size, the time taken increases by a factor
of about 4, which is consistent with the O(n^2) time complexity of insertion sort.

