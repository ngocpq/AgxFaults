echo off
SET outfile=%1

echo Compile java files
javac -g -deprecation -source 8 *.java

IF "%~1"=="" SET /p testid="enter test id: "
SET outfile="result_%testid%.txt"
SET configFile="config\%testid%.jpf"

if exist %outfile% (
	echo Delete old log file: %outfile%
  del %outfile%
)

ECHO Running file %configFile% ...
java -cp . -jar E:\Workspace\AutomatedDebugging\jpf-core\build\RunJPF.jar +classpath="%CD%";"E:\Workspace\AutomatedDebugging\jdart\build\jpf-jdart.jar" +shell.port=4242 %configFile%  1>>  %outfile% 2> %configFile%_error.txt


echo Finished!