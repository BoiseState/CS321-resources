

Comparing algorithms
--------------------

The BigPower class provides a O(n) brute force algorithm and an O(lg n) recursive doubling
algorithms for computing x ^ n,where x and n can be arbitrarily large. It also compares 
with the built-in pow function in the BigInteger class.

To experiment with the algorithms, use the BigPowerTest class as shown below:

Usage: java BigPowerTest <value> <exponent>

We will start off "small"

```
java BigPowerTest  2 100 
1267650600228229401496703205376
time taken for version 1= 1.0 seconds

1267650600228229401496703205376
time taken for version 2= 0.0 seconds

1267650600228229401496703205376
time taken for pow= 0.0 seconds
```

Then we can venture into something bigger:

```
[amit@fedora-linux power(master)]$ java BigPowerTest 123 123
114374367934617190099880295228066276746218078451850229775887975052369504785666896446606568365201542169649974727730628842345343196581134895919942820874449837212099476648958359023796078549041949007807220625356526926729664064846685758382803707100766740220839267
time taken for version 1= 0.0 seconds

114374367934617190099880295228066276746218078451850229775887975052369504785666896446606568365201542169649974727730628842345343196581134895919942820874449837212099476648958359023796078549041949007807220625356526926729664064846685758382803707100766740220839267
time taken for version 2= 0.0 seconds

114374367934617190099880295228066276746218078451850229775887975052369504785666896446606568365201542169649974727730628842345343196581134895919942820874449837212099476648958359023796078549041949007807220625356526926729664064846685758382803707100766740220839267
time taken for pow= 0.0 seconds
```

Notice these are still small as it takes almost not time to compute this power. Let's go bigger but
we will redirect the output numbers to the file so we can focus on the runtime.

```
java BigPowerTest 1234 3434 > output
time taken for version 1 = 10.0 seconds
time taken for version 2 = 2.0 seconds
time taken for pow = 1.0 seconds
```

And even bigger where the brute force algorithm can no longer keep up:

```
java BigPowerTest 12345 34345 > output
time taken for version 1 = 398.0 seconds

time taken for version 2 = 13.0 seconds

time taken for pow = 14.0 seconds
```

See the file log for a series of tests and plot.pdf for a graph comparing the runtimes.
