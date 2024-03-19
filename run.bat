@echo off

set CLASSPATH=.;C:\java_programs

if [%1%] == [] goto error 

rmdir /S /Q classes
mkdir classes
javac -d classes %1%.java
java -cp classes %1%
goto end

:error
echo "error: run <program_name>"

:end