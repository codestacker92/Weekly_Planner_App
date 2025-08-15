#!/usr/bin/env bash
cd "$(dirname "$0")/.."

# compile
mkdir -p build/classes
javac -d build/classes src/main/java/app/*.java

# optional: build JAR
mkdir -p build/libs
jar --create --file build/libs/weekly-planner-app.jar --main-class app.Main -C build/classes . 2>/dev/null

# run
java -cp build/classes app.Main

read -n 1 -s -r -p $'\nPress any key to close...\n'
