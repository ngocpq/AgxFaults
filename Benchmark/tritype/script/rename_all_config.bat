echo OFF

cd ..\

echo Current dir: %CD%	
FOR /L %%i IN (1,1,41) DO (
	echo version%%i
	cd v%%i
	echo Current dir: %CD%	
	
	for %%f in (config\*.jpf) do (
		ECHO Rename file %%~f ...
		RENAME config\%%~nf.jpf v%%i_%%~nf.jpf
		RENAME config\%%~nf.jpf_check.txt v%%i_%%~nf.jpf_check.txt
	)	
	cd ..\
)

echo Finished!
pause