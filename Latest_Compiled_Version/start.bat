@echo off
setlocal

title HabboRP - Arcturus Morningstar

set "ROOT=D:\laragon\www\habborp"
set "EMU_RUN=%ROOT%\Arcturus-Morningstar-Extended\Latest_Compiled_Version"
set "JAR=Habbo-4.2.20-jar-with-dependencies.jar"

echo.
echo ==========================================
echo  HabboRP - Arrancar emulador
echo ==========================================
echo.

if not exist "%EMU_RUN%" (
    echo [ERROR] No existe la carpeta:
    echo %EMU_RUN%
    pause
    exit /b 1
)

if not exist "%EMU_RUN%\%JAR%" (
    echo [ERROR] No existe el JAR:
    echo %EMU_RUN%\%JAR%
    pause
    exit /b 1
)

if not exist "%EMU_RUN%\config.ini" (
    echo [ERROR] No existe config.ini:
    echo %EMU_RUN%\config.ini
    pause
    exit /b 1
)

where java >nul 2>nul
if errorlevel 1 (
    echo [ERROR] Java no esta disponible en PATH.
    pause
    exit /b 1
)

cd /d "%EMU_RUN%"

echo Ejecutando:
echo java -jar "%JAR%"
echo.

java -jar "%JAR%"

echo.
echo El emulador se ha cerrado.
pause
