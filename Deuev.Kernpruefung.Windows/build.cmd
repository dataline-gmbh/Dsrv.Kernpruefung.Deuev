@echo off
SET IKVM_PATH=%~dp0\..\ikvm-8.1.5561
"%IKVM_PATH%\bin\ikvmc.exe" -target:library "-out:%~dp0\Deuev.Kernpruefung.dll" "%~dp0\..\Kernpruefung\kernpruefung_deuev_jar.jar" -version:%1 -fileversion:%2
