ECHO OFF
SET SourceDir=E:\Workspace\Benchmarks\TCAS
SET DestDir=E:\Workspace\Benchmarks\jtcas_benchmark


SET DirName=TcasKO

SET FileName=tcas
SET DestDirName=v

SET FailingTestCasesFile=CE_TcasKO
SET DestFailingTestCasesFile=ce_v
REM first version
IF NOT EXIST %DestDir%\%DestDirName%1 (
		echo Make dir %DestDir%\%DestDirName%1\
		mkdir %DestDir%\%DestDirName%1\
)
copy %SourceDir%\%DirName%\%FailingTestCasesFile%.txt %DestDir%\%DestDirName%1\%DestFailingTestCasesFile%1.txt

REM version 2 => 41
FOR /L %%i IN (2,1,41) DO (
	echo %%i
	IF NOT EXIST %DestDir%\%DestDirName%%%i (		
		echo Make dir %DestDir%\%DestDirName%%%i\
		mkdir %DestDir%\%DestDirName%%%i\
	)
	copy %SourceDir%\%DirName%%%i\%FailingTestCasesFile%%%i.txt %DestDir%\%DestDirName%%%i\%DestFailingTestCasesFile%%%i.txt
)

ECHO Finished

pause