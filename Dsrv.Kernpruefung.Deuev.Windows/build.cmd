@echo off
SET IKVM_PATH=%~dp0\..\ikvm-8.0.5449.1
"%IKVM_PATH%\bin\ikvmc.exe" -target:library "-out:%~dp0\Dsrv.Kernpruefung.Deuev.dll" "%~dp0\..\Kernpruefung\kernpruefung_deuev_jar.jar" -version:%1 -fileversion:%2
