ECHO OFF

cd ../


FOR /L %%i IN (1,1,5) DO (
	echo %%i
	del /Q /F KO%%i\config\*	
)

ECHO Finished

pause