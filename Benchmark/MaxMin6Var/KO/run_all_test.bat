echo off
SET outfile=%1

echo Compile java files
javac -g -deprecation -source 8 *.java

IF "%~1"=="" SET outfile="result.txt"

if exist %outfile% (
	echo Delete old log file: %outfile%
  del %outfile%
)

if "%2"=="true" (
	REM echo TcID	#Diagnosis	#UnionSet	#LineSet	CorrectInsn	CorrectLine	#InsnInErrorTrace	#TotalRun	#ErrorTraceSize	SumMaxSAT Time	Avg.MaxSATTime	TotalRunningTime	DiagnosisLine	DiagnosisInsn >>  %outfile%
	echo vId,TcID,#Diagnosis,#UnionSet,#LineSet,CorrectInsn,CorrectLine,#InsnInErrorTrace,#TotalRun,#ErrorTraceSize,SumMaxSAT Time,Avg.MaxSAT time,BuildCFGTime,Total running time,DiagnosisLine,DiagnosisInsn >>  %outfile%
)
for %%f in (config\*.jpf) do (
	ECHO Running file %%~f ...
  java -cp . -jar E:\Workspace\AutomatedDebugging\jpf-core\build\RunJPF.jar +classpath="%CD%";"E:\Workspace\AutomatedDebugging\jdart\build\jpf-jdart.jar" +shell.port=4242 %%~f  1>>  %outfile% 2>%%~f_error.txt
 )	

echo Finished!