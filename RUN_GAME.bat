@echo off
setlocal
cd /d "%~dp0"
title WAR CARD GAME
chcp 65001 >nul
set "JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8 -Dstdout.encoding=UTF-8 -Dstderr.encoding=UTF-8"

set "JAVA_BIN="
if exist "%~dp0jdk\bin\java.exe" if exist "%~dp0jdk\bin\javac.exe" set "JAVA_BIN=%~dp0jdk\bin"
if not defined JAVA_BIN if defined JAVA_HOME if exist "%JAVA_HOME%\bin\java.exe" if exist "%JAVA_HOME%\bin\javac.exe" set "JAVA_BIN=%JAVA_HOME%\bin"
if not defined JAVA_BIN if exist "%LOCALAPPDATA%\Programs\Eclipse Adoptium\jdk-21.0.12.101-hotspot\bin\java.exe" if exist "%LOCALAPPDATA%\Programs\Eclipse Adoptium\jdk-21.0.12.101-hotspot\bin\javac.exe" set "JAVA_BIN=%LOCALAPPDATA%\Programs\Eclipse Adoptium\jdk-21.0.12.101-hotspot\bin"
if not defined JAVA_BIN for /d %%D in ("%LOCALAPPDATA%\Programs\Eclipse Adoptium\jdk-*") do if exist "%%~fD\bin\java.exe" if exist "%%~fD\bin\javac.exe" set "JAVA_BIN=%%~fD\bin"
if not defined JAVA_BIN for /d %%D in ("%ProgramFiles%\Eclipse Adoptium\jdk-*") do if exist "%%~fD\bin\java.exe" if exist "%%~fD\bin\javac.exe" set "JAVA_BIN=%%~fD\bin"
if not defined JAVA_BIN (
    where java >nul 2>&1
    if not errorlevel 1 (
        where javac >nul 2>&1
        if not errorlevel 1 set "JAVA_BIN=PATH"
    )
)
if not defined JAVA_BIN (
    echo ERROR: No compatible JDK was found.
    echo Put a Windows JDK in the jdk folder, or install JDK 21 or newer.
    pause
    exit /b 1
)
if "%JAVA_BIN%"=="PATH" (
    set "JAVA_EXE=java"
    set "JAVAC_EXE=javac"
) else (
    set "JAVA_EXE=%JAVA_BIN%\java.exe"
    set "JAVAC_EXE=%JAVA_BIN%\javac.exe"
)
echo Java runtime:
"%JAVA_EXE%" -version
if errorlevel 1 goto :error
if not exist "src\cardgame\WarCardGame.java" (
    echo ERROR: Missing src\cardgame\WarCardGame.java
    goto :error
)
if not exist "input.txt" (
    if exist "src\input.txt" (
        copy /y "src\input.txt" "input.txt" >nul
    ) else (
        echo ERROR: input.txt was not found.
        goto :error
    )
)
if not exist "out" mkdir "out"
echo.
echo Compiling...
"%JAVAC_EXE%" -encoding UTF-8 -d out src\cardgame\*.java
if errorlevel 1 goto :error
echo.
echo Starting War Card Game...
echo.
"%JAVA_EXE%" -cp out cardgame.WarCardGame
echo.
pause
exit /b 0
:error
echo.
echo The game could not start. Review the error above.
pause
exit /b 1
