ECHO OFF

cd ../

REM SET file1=tcasVersion.jpf
REM SET file2=RunTCAS.java
SET file3=run_all_test.bat
SET file4=run_all_test_sum.bat

SET file5=site.properties

FOR /L %%i IN (1,1,41) DO (
	echo %%i
	REM copy .\template\%file1% v%%i\v%%i_%file1%
	REM copy .\template\%file2% v%%i\%file2%
	REM copy /V .\template\%file3% v%%i\%file3%
	REM copy .\template\%file4% v%%i\%file4%
	copy .\template\%file5% v%%i\%file5%
)

ECHO Finished

pause