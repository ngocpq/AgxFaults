ECHO OFF

cd ../
echo current dir: %CD%
echo begin deleting all log and result files

FOR /L %%i IN (1,1,8) DO (
	echo %%i
	del /Q /F v%%i\log\*
	del /Q /F v%%i\result\*
)

ECHO Finished

pause