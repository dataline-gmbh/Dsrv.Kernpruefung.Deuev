@echo off
SET BUILD=%1
SET BASE_PATH=%~dp0
SET IKVM_PATH=%BASE_PATH%\ikvm-8.0.5449.1
"%IKVM_PATH%\bin\ikvmc.exe" -version:1.6.1 -fileversion:1.6.1.%BUILD% -target:library "-out:%BASE_PATH%\NuGet\1.6\Dsrv.Kernpruefung.Deuev.dll" "%BASE_PATH%\Kernpruefung\kernpruefung_deuev_1_6_1_jar.jar" "%BASE_PATH%\Kernpruefung\kernpruefung_deuev_adapter_1_1_1.jar"
