@echo off
SET IKVM_PATH=%~dp0\..\ikvm-8.0.5449.1
"%IKVM_PATH%\bin\ikvmc.exe" -version:%1 -fileversion:%2 -target:library "-out:%~dp0\Dsrv.Kernpruefung.Deuev.dll" "%~dp0\..\Kernpruefung\kernpruefung_deuev_jar.jar" "%~dp0\..\Kernpruefung\kernpruefung_deuev_adapter.jar"
REM -debug "-srcpath:%~dp0\..\Kernpruefung\kernpruefung_deuev_jar"