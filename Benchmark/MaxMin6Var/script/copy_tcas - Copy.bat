ECHO OFF

cd ../

REM SET file1=tcasVersion.jpf
SET file2=RunTCAS.java
SET file3=run_all_test.bat
SET file4=run_all_test_sum.bat

REM version 2 => 41
FOR /L %%i IN (2,1,41) DO (
	echo %%i
	REM copy .\template\%file1% v%%i\v%%i_%file1%
	copy .\template\%file2% v%%i\%file2%
	copy .\template\%file3% v%%i\%file3%
	copy .\template\%file4% v%%i\%file4%
)

ECHO Finished

pause