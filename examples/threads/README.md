# Intro to Threads and Parallelism

Shows basic usage and benefits of threads in Java.

### Example 1:  hellothreads

```
java hellothreads.Dance
```

### Example2: widgetmaker

```
time java widgetmaker.WidgetMaker 100 
time java FasterWidgetMaker 100 8
```

The faster widget maker makes 100 widgets sequentially and then with 8 threads -- see how 
much faster it works on your system!  We had 8 cores on the system we tried it on and it ran 
about 6 times faster with 6 threads. Then we tried 16, 24, 32, and 64 threads. The best 
speedup was around 14x with 32 threads!


### Example 3: parallel mergesort

First run sequentially 

```
time mergesort 30000000
```

Then run in parallel.

```
time mergesort 30000000 --useThreads 3
```

This creates one thread for each recursive call, up to 3 levels deep. So, for k levels, it uses a
total of 2^k - 1 threads.
