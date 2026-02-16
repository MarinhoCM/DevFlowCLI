@echo off
setlocal

echo =====================================
echo Installing DevFlow CLI...
echo =====================================

set INSTALL_DIR=%USERPROFILE%\devflow

if not exist "%INSTALL_DIR%" (
    mkdir "%INSTALL_DIR%"
)

copy /Y "target\devflow-1.0.0.jar" "%INSTALL_DIR%\devflow.jar"

echo @echo off > "%INSTALL_DIR%\devflow.cmd"
echo java --enable-native-access=ALL-UNNAMED -jar "%%~dp0devflow.jar" %%* >> "%INSTALL_DIR%\devflow.cmd"

setx PATH "%PATH%;%INSTALL_DIR%" >nul

echo.
echo DevFlow instalado com sucesso!
echo Reinicie o terminal e use: devflow --help
echo.

pause
