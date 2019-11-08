ECHO OFF


FOR /L %%i IN (1,1,41) DO (
	echo %%i
	del /Q /F v%%i\config\t1579*	
	del /Q /F v%%i\config\t1582*
	del /Q /F v%%i\config\t1584*
	del /Q /F v%%i\config\t1586*
	del /Q /F v%%i\config\t1587*
	del /Q /F v%%i\config\t1589*
	del /Q /F v%%i\config\t1594*
	del /Q /F v%%i\config\t1596*
	del /Q /F v%%i\config\t1602*
	del /Q /F v%%i\config\t1605*
	del /Q /F v%%i\config\t1606*
	del /Q /F v%%i\config\t1607*
)

ECHO Finished

pause