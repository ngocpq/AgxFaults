ECHO OFF
SET outfile=%1

IF "%~1"=="" SET outfile="..\result_sum.csv"

if exist %outfile% (
	echo Delete old out file: %outfile%
  del %outfile%
)

rem SET file=run_all_test.bat
rem SET file4=run_all_test_sum.bat

echo version1
cd KO
echo Current dir: %CD%	
call run_all_test_sum.bat

cd ..\

echo Current dir: %CD%	
REM version 2 => 41
FOR /L %%i IN (2,1,5) DO (
	echo version%%i
	cd KO%%i
	echo Current dir: %CD%	
	call  run_all_test_sum.bat %outfile%
	cd ..
)

ECHO Finished

pause