#!/bin/sh

echo "x=2" "n=23238"
java TestPower  2 23238 > /dev/null

echo "x=34764" "n=2323"
java TestPower  34764 23238 > /dev/null

echo "x=34764" "n=23238"
java TestPower  34764 23238 > /dev/null

echo "x=753232" "n=222"
java TestPower  753232 222 > /dev/null
 
echo "x=753232" "n=2228"
java TestPower  753232 2228 > /dev/null
 
echo "x=7532327" "n=2228"
java TestPower  7532327 2228 > /dev/null
 
echo "x=75323275" "n=2228"
java TestPower  75323275 2228 > /dev/null
 
echo "x=75323275" "n=11223"
java TestPower  75323275 11223 > /dev/null

echo "x=75323275999" "n=11223"
java TestPower  75323275999 11223 > /dev/null

