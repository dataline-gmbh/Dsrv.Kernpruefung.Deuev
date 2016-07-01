@echo off
SET PACKAGE_VERSION=1.7
SET FULL_PACKAGE_VERSION=1.7.0
SET JAVA_PACKAGE_VERSION=1_7
SET BUILD=%1
SET BASE_PATH=%~dp0
SET IKVM_PATH=%BASE_PATH%\ikvm-8.0.5449.1
"%IKVM_PATH%\bin\ikvmc.exe" -version:%FULL_PACKAGE_VERSION% -fileversion:%FULL_PACKAGE_VERSION%.%BUILD% -target:library "-out:%BASE_PATH%\NuGet\%PACKAGE_VERSION%\Dsrv.Kernpruefung.Deuev-%PACKAGE_VERSION%.dll" "%BASE_PATH%\Kernpruefung\kernpruefung_deuev_%JAVA_PACKAGE_VERSION%_jar.jar" "%BASE_PATH%\Kernpruefung\kernpruefung_deuev_adapter_1_1_1.jar"
