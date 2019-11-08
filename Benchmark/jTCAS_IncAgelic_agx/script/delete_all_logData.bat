ECHO OFF

cd ../
echo current dir: %CD%
echo begin deleting all log files

FOR /L %%i IN (1,1,41) DO (
	echo %%i	
	del /Q /F v%%i\log\*
)

ECHO Finished

pause