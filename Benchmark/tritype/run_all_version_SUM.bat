ECHO OFF
SET outfile=%1

IF "%~1"=="" SET outfile="..\ffl_result_sum.csv"

if exist %outfile% (
	echo Delete old out file: %outfile%
  del %outfile%
)

rem SET file=run_all_test.bat
rem SET file4=run_all_test_sum.bat

echo version1
cd v1
echo Current dir: %CD%	
call run_all_test_sum.bat %outfile% true

cd ..\

echo Current dir: %CD%	
FOR /L %%i IN (1,1,8) DO (
	echo version%%i
	cd v%%i
	echo Current dir: %CD%	
	call  run_all_test_sum.bat %outfile%
	cd ..
)

ECHO Finished

pause