ECHO OFF


FOR /L %%i IN (1,1,41) DO (
	echo %%i
	cd v%%i
	echo Current dir: %CD%	
	echo Compile java files
	javac -g -deprecation -source 8 *.java
	cd ..
)

ECHO Finished

pause