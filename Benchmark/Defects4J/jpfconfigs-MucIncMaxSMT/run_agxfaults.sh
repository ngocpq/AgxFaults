#!/bin/bash

#config_file=/defects4j_agxfaults/jpfconfigs/Chart/Chart1/Chart1_t1.test2947660.jpf
config_file=$1

echo "Run AgxFaults: " ${config_file}

log_file=${config_file}.log
error_log_file=${config_file}.err

#JPF_HOME="/home/ngocpq/jpf-home"
JPF_HOME="${JPF_HOME}"
export PATH="$PATH:${JPF_HOME}/jpf-core/build"


JPF_JAR=${JPF_HOME}/jpf-core/build/RunJPF.jar

echo "JPF was started at " $(date +"%Y.%m.%d %H:%M:%S")

#timeout 10m java -jar ${JPF_JAR} ${config_file} 2>&1 | tee 1>$log_file
timeout 10m java -jar ${JPF_JAR} ${config_file} 2>${error_log_file} 1>${log_file}

echo "JPF was stopped at " $(date +"%Y.%m.%d %H:%M:%S")
