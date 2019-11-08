ECHO OFF

cd ../
echo current dir: %CD%
echo begin deleting all log and result files

FOR /L %%i IN (1,1,5) DO (
	echo %%i
	del /Q /F KO%%i\log\*
	del /Q /F KO%%i\result\*
)

ECHO Finished

pause