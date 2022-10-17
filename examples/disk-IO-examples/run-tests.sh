#!/bin/bash

case $# in
0) echo "Usage:  $0  <n> "; exit 1;; 
esac

n=$1
seed=123

echo
echo "Generating data as binary file using java.nio package"
time java GenerateBinary $n $seed

echo
echo "Generating data as binary file using java.io package"
time java GenerateBinarySlower $n $seed


echo
echo "Generating data as text file using java.io package"
time java GenerateText $n $seed
