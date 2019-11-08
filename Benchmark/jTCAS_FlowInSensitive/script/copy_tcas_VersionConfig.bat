ECHO OFF

SET SourceDir=E:\Workspace\Benchmarks\Bekkouche_Benchmark\JTCAS
SET DestDir=E:\Workspace\Benchmarks\Benchmark_AgxFaults\JTCAS

SET file1=tcasVersion.jpf

REM version 2 => 41
FOR /L %%i IN (1,1,41) DO (
	echo %%i
	REM echo %SourceDir%\v%%i\v%%i_%file1% %DestDir%\v%%i\v%%i_%file1%	
	copy %SourceDir%\v%%i\v%%i_%file1% %DestDir%\v%%i\v%%i_%file1%
	
)

ECHO Finished

pause