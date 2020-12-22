echo "Patches info:"

logFile=jpfconfigs/*/*/config/*.log

#grep -r -m 1 -B 1 "[INFO] Total # MCS found:" ${logFile} | grep "Variant id: " > firstSolution.txt

#grep -r -m 1 -A 1 "Patch 1" ${logFile} | grep "VARIANT_ID=" > firstSolution_2.txt

#grep -r -c "isSolution: true" ${logFile} > countSolution.txt

echo "output status:"
#grep -r "[INFO] Total # MCS found:" ${logFile}

grep -r -m 1 "loading class java.lang.ClassNotFoundException from jar" ${logFile} | tee ./output_analysis/ClassNotFoundException.txt

grep -r -m 1 "Successful AgxPath " ${logFile} | tee ./output_analysis/SuccessfulAgxPath.txt

grep -r -m 1 "Completed Analyses:" ${logFile} | tee ./output_analysis/CompletedAnalyses.txt
grep -r -m 1 "Completed Analyses:" ${logFile} | grep "Completed Analyses: 0$" | tee ./output_analysis/CompletedAnalysesNO.txt
grep -r -m 1 "Completed Analyses:" ${logFile} | grep -v "Completed Analyses: 0$" | tee ./output_analysis/CompletedAnalysesOK.txt

grep -r -m 1 "Total # MCS found:" ${logFile} | tee ./output_analysis/TotalMcsFound.txt

grep -r -m 1 "JPF error stack trace " ${logFile} | tee ./output_analysis/ExceptionInJPF.txt

grep -r -m 1 ", OK, Node: " ${logFile} | tee ./output_analysis/FinishedOkPath.txt

grep -r -m 1 "JDART-run: " ${logFile} | tee ./output_analysis/Runtime.txt
grep -r -m 1 "JDART-run: " ${logFile} | tee ./output_analysis/Runtime.txt

#grep -r "NR_FAILING_VALIDATION_PROCESS=" * 

############################################
resultFile=jpfconfigs/*/*/output/*.out
grep -r -m "mcs_" ${resultFile} | tee ./output_analysis/ResultMcs.txt

grep -r -A 1 "mcs_.*1$" ${resultFile} |tee ./output_analysis/ResultMcs_size1.txt
grep -r -A 2 "mcs_.*2$" ${resultFile} |tee ./output_analysis/ResultMcs_size2.txt
grep -r -A 3 "mcs_.*3$" ${resultFile} |tee ./output_analysis/ResultMcs_size3.txt

grep -r -c "mcs_" ${resultFile} |grep -v "txt:0$" | tee ./output_analysis/ResultMcsCount.txt

grep -r "mcs_" ${resultFile} |grep -v "0$" | tee ./output_analysis/ResultMcsCount2.txt

grep -r -A 1 "mcs_" ${resultFile} |grep "#" | tee ./output_analysis/reportedMCSLine.txt

#files_with_mcs=./output_analysis/resultMcsFiles.txt
#grep -l -r "mcs_" ${resultFile} | tee ${files_with_mcs}
#files_with_mcs="$(cat ./output_analysis/reportedMCSLine.txt)"

############################################
confFile=./jpfconfigs/*/*/*/*.jpf
grep -r -m 1 "testmethod=" ${confFile} | grep -r -c "testmethod=" | grep ".jpf:1"  > ./output_analysis/CountTestConfig.txt


############################################
outputLogFile=jpfconfigs/*/*/output/*.log

grep -r -m 1 "unsat core size:" ${outputLogFile} | tee ./output_analysis/ErrTraceUnsatCoreSize.txt
grep -r -m 1 "unsat core size:" ${outputLogFile} | grep "size: 0$" | tee ./output_analysis/ErrTraceUnsatCoreSize0.txt
grep -r -m 1 "unsat core size:" ${outputLogFile} | grep -v "size: 0$" | tee ./output_analysis/ErrTraceUnsatCoreFound.txt

grep -r -m 1 " relax_vars size: " ${outputLogFile} | grep -v "size: 0$" | tee ./output_analysis/ErrTraceUnsatCoreFound_relaxVarSize.txt

errLogFile=jpfconfigs/*/*/config/*.err
grep -r -m 1 "Exception in thread " ${errLogFile} | tee ./output_analysis/RuntimeException.txt
grep -r -A 1 "Exception in thread " ${errLogFile} | tee ./output_analysis/RuntimeExceptionDetail.txt
