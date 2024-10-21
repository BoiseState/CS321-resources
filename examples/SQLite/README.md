# How to use SQLite Databases from Java

First, we need the jar file that contains SQLite executable as well the JDBC classes. The latest
version is provided in this folder and is named:

```
sqlite-jdbc-3.46.1.3.jar
```

This contains versions for MS Windows, Linux and Mac OS X. 

## How to run sample code

A sample program has been provided: `Sample.java`.  Compile and run it as follows:

```
>javac Sample.java
>java -classpath ".;sqlite-jdbc-3.46.1.3.jar" Sample   # in Windows

or

>java -classpath ".:sqlite-jdbc-3.46.1.3.jar" Sample   # in Mac or Linux
name = leo
id = 1
name = yui
id = 2
```

---

## References

1. See the [SQLite JDBC tutorial](https://www.sqlitetutorial.net/sqlite-java/) site for more information.

2. [SQLite Home Page](https://www.sqlite.org/index.html)
