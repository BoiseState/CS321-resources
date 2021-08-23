#!/bin/sh

echo "x=2" "n=1000"
java BigPowerTest  2 1000 > /dev/null

echo "x=2" "n=10000"
java BigPowerTest  2 10000 > /dev/null

echo "x=2" "n=50000"
java BigPowerTest  2 50000 > /dev/null

echo "x=2" "n=100000"
java BigPowerTest  2 100000 > /dev/null

echo "x=2" "n=200000"
java BigPowerTest  2 200000 > /dev/null

echo "x=2" "n=500000"
java BigPowerTest  2 500000 > /dev/null

echo "x=2" "n=1000000"
java BigPowerTest  2 1000000 > /dev/null

