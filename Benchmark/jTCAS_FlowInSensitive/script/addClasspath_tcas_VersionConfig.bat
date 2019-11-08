ECHO OFF

SET SourceDir=E:\Workspace\Benchmarks\jtcas_benchmark
SET DestDir=E:\Workspace\Benchmarks\Bekkouche_Benchmark\JTCAS

SET file1=tcasVersion.jpf
SET file2=RunTCAS.java
SET file3=run_all_test.bat
SET file4=run_all_test_sum.bat

REM version 2 => 41
FOR /L %%i IN (1,1,41) DO (
	echo %%i	
	echo.>> %DestDir%\v%%i\v%%i_%file1%	
	echo +classpath=${config_path}/>> %DestDir%\v%%i\v%%i_%file1%	
	echo sourcepath=${config_path}>> %DestDir%\v%%i\v%%i_%file1%		
)

ECHO Finished

pause