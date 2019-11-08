ECHO OFF

SET SourceDir=E:\Workspace\Benchmarks\jtcas_benchmark
SET DestDir=E:\Workspace\Benchmarks\Bekkouche_Benchmark\JTCAS

SET file1=tcas.java
SET file2=RunTCAS.java
SET file3=run_all_test.bat
SET file4=run_all_test_sum.bat

REM version 2 => 41
FOR /L %%i IN (1,1,41) DO (
	echo %%i
	copy %SourceDir%\v%%i\%file1% %DestDir%\v%%i\%file1%	
)

ECHO Finished

pause