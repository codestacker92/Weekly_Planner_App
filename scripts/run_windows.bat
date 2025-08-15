\
@echo off
cd /d %~dp0\..

REM compile
mkdir build\classes 2>nul
javac -d build\classes src\main\java\app\*.java

REM optional: build JAR (works on JDK 9+)
mkdir build\libs 2>nul
jar --create --file build\libs\weekly-planner-app.jar --main-class app.Main -C build\classes . 2>nul

REM run
java -cp build\classes app.Main

pause
