echo off

SET outfile=%1

echo Compile java files
javac -g -deprecation -source 8 *.java

IF "%~1"=="" SET outfile="..\ffl_result_sum.csv"

if not exist %outfile% (
	ECHO Version,TC,MCS_Agx,MCS_allPath,Agx_MaxSat_Time,Agx_MaxSat_MaxTime,Agx_MaxSat_AvgTime,Agx_MaxSat_Count,Agx_CheckPath_Time,Agx_CheckPath_MaxTime,Agx_CheckPath_AvgTime,Agx_CheckPath_Count,Agx_Total_Time,AllPath_MaxSat_Time,AllPath_MaxSat_MaxTime,AllPath_MaxSat_avgTime,AllPath_MaxSat_count >>  %outfile%
)

SET JPF_JAR=E:\Workspace\AutomatedDebugging\jpf-core\build\RunJPF.jar

for %%f in (config\*.jpf) do (
	ECHO Running file %%~f ...
	java -cp . -jar "%JPF_JAR%" +classpath="%CD%" +shell.port=4242 %%~f  1>>  %outfile% 2>log\%%~nf_error.txt
 )	

echo Finished!


echo OFF
