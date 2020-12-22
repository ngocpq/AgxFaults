#!/bin/bash

src_dir=$1
des_dir=$2

echo "Copy jpf files from $src_dir to $des_dir"

echo "Making folder structure..."

find $src_dir -type d | xargs -i mkdir -p ${des_dir}/{}

echo "Copy jpf files..."
find $src_dir -type f | grep -i jpf$| xargs -i cp {} ${des_dir}/{}

echo "Finished!"
