@echo off

REM adds current directory(.) and (classes) folder to classpath
set CLASSPATH=.;classes

if [%1] == [] goto error 

rmdir /S /Q classes
mkdir classes
javac -d classes %1.java
java -cp %CLASSPATH% %1
goto end

:error
echo "error: run <program_name>"

:end