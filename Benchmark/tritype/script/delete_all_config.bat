ECHO OFF

cd ../
echo current dir: %CD%
echo begin deleting all config files

FOR /L %%i IN (1,1,8) DO (
	echo %%i
	del /Q /F v%%i\config\*	
)

ECHO Finished

pause