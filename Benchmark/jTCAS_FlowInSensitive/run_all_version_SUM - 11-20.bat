ECHO OFF
SET outfile=%1

IF "%~1"=="" SET outfile="..\ffl_result_sum_11_20.csv"

if exist %outfile% (
	echo Delete old out file: %outfile%
  del %outfile%
)

echo Current dir: %CD%	
FOR /L %%i IN (11,1,20) DO (
	echo version%%i
	cd v%%i
	echo Current dir: %CD%	
	call  run_all_test_sum.bat %outfile%
	cd ..
)

ECHO Finished

pause