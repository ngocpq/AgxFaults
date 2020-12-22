echo "Patches info:"

logFile=jpfconfigs/*/*/config/*.log
outputLogFile=jpfconfigs/*/*/output/*.log

resultFile=jpfconfigs/*/*/output/*.out
confFile=./jpfconfigs/*/*/config/*.jpf
errLogFile=jpfconfigs/*/*/config/*.err


#error trace size
grep -r -m 1 "ERROR_TRACE_SIZE_INST_COUNTER: " ${logFile} | tee ./output_analysis/ERROR_TRACE_SIZE_INST_COUNTER.txt
grep -r -m 1 "ERROR_TRACE_SIZE_LINE_COUNTER: " ${logFile} | tee ./output_analysis/ERROR_TRACE_SIZE_LINE_COUNTER.txt
grep -r -m 1 "ERROR_TRACE_INST_DISTINC_COUNTER: " ${logFile} | tee ./output_analysis/ERROR_TRACE_INST_DISTINC_COUNTER.txt
grep -r -m 1 "ERROR_TRACE_LINE_DISTINCT_COUNTER: " ${logFile} | tee ./output_analysis/ERROR_TRACE_LINE_DISTINCT_COUNTER.txt
grep -r -m 1 "ERROR_TRACE_METHOD_COUNTER: " ${logFile} | tee ./output_analysis/ERROR_TRACE_METHOD_COUNTER.txt

#execution time


grep -r -m 1 "JDART-run: " ${logFile} | tee ./output_analysis/time-JDART-run.txt
grep -r -m 1 "checkSAT: " ${logFile} | tee ./output_analysis/time-checkSAT.txt
grep -r -m 1 "JPF-boot: " ${logFile} | tee ./output_analysis/time-JPF-boot.txt

grep -r -m 1 "findNextAbstractAgxCorrectTrace: " ${logFile} | tee ./output_analysis/time-findNextAbstractAgxCorrectTrace.txt
grep -r -m 1 "addAtMostOne" ${logFile} | tee ./output_analysis/time-addAtMostOne.txt
grep -r -m 1 "processUnsat" ${logFile} | tee ./output_analysis/time-processUnsat.txt
grep -r -m 1 "processSAT" ${logFile} | tee ./output_analysis/time-processSAT.txt
