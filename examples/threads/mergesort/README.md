# MergeSort

To compile and run 
```console
  javac MergeSort.java
  java MergeSort <num_elements> [-p [cutoff_level]]
```
Example:
```console
  javac MergeSort.java
  java MergeSort 100000 -p 6
```

num_elements: Required arguement that declares # of elements to be sorted

-p: Enables parallel mergesort

cutoff_level: Declares # of levels of threads 

If the cutoff_level is not declared, then the value will be set to the deafult value.
