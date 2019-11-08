ECHO OFF

SET SourceDir=C:\Working\Benchmarks\jTCAS_IncAgelic_agx
SET DestDir=..

SET file1=tcasVersion.jpf

REM version 2 => 41
FOR /L %%i IN (1,1,41) DO (
	echo %%i	
	copy %SourceDir%\v%%i\v%%i_%file1%	%DestDir%\v%%i\v%%i_%file1%
	echo jdart.configs.tcas.faultlocalization.max_mcs_size=3 >> %DestDir%\v%%i\v%%i_%file1%	
)

ECHO Finished

pause