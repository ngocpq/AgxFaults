ECHO OFF

echo current dir: %CD%
echo begin deleting all log and result files

for %%f in (config\*.jpf) do (
	ECHO deleting file: %%~f_error.txt ...
	del /Q /F log\%%~nf_error.txt	
	del /Q /F result\result_*_%%~nf*.txt	
)	


ECHO Finished

pause