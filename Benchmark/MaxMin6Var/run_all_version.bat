ECHO OFF

SET file=run_all_test.bat
rem SET file4=run_all_test_sum.bat


echo version1
cd KO
echo Current dir: %CD%	
call run_all_test.bat

cd ..\

REM version 1 => 41
FOR /L %%i IN (2,1,5) DO (
	echo %%i
	cd KO%%i
	echo Current dir: %CD%	
	run_all_test.bat
	cd ..
)

ECHO Finished

pause