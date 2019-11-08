ECHO OFF

SET file=run_all_test.bat
rem SET file4=run_all_test_sum.bat

REM version 1 => 41
FOR /L %%i IN (1,1,8) DO (
	echo %%i
	cd v%%i
	echo Current dir: %CD%	
	run_all_test.bat
	cd ..
)

ECHO Finished

pause