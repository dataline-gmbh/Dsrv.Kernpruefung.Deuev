# DEÜV-Kernprüfung der Deutschen Rentenversicherung (DRV)

[![Build-Status](https://img.shields.io/teamcity/https/build.service-dataline.de:8081/s/ThirdPartyLibraries_DeVKernprFungJava.svg?label=TeamCity)](https://build.service-dataline.de:8081/viewType.html?buildTypeId=ThirdPartyLibraries_DeVKernprFungJava&guest=1)

Das Original-Archiv kann auf der Web-Seite der 
[Deutschen Rentenversicherung](http://www.deutsche-rentenversicherung.de/) unter den
[Experten-Informationen](http://www.deutsche-rentenversicherung.de/Allgemein/de/Inhalt/3_Infos_fuer_Experten/02_arbeitgeber_steuerberater/04_meldeverfahren_deuev/07_downloads/downloads_index.html)
gefunden und heruntergeladen werden.

# Nutzung

Die Packages werden genau so verwendet wie in Java. Es existieren unterschiedliche NuGet-Pakete für die
unterschiedlichen Versionen:

Version | NuGet-Paket
--------|--------------
1.6.1   | [![Dsrv.Kernpruefung.Deuev-1.6](https://img.shields.io/nuget/v/Dsrv.Kernpruefung.Deuev-1.6.svg)](https://www.nuget.org/packages/Dsrv.Kernpruefung.Deuev-1.6/)
1.7.0   | [![Dsrv.Kernpruefung.Deuev-1.7](https://img.shields.io/nuget/v/Dsrv.Kernpruefung.Deuev-1.7.svg)](https://www.nuget.org/packages/Dsrv.Kernpruefung.Deuev-1.7/)

Wenn man in einer Anwendung beide verwenden möchte, dann muss man das wie folgt machen:

```csharp
extern alias deuev16;
extern alias deuev17;

using adapter16 = deuev16::de.drv.dsrv.kernpruefung.adapter;
using adapter17 = deuev17::de.drv.dsrv.kernpruefung.adapter;

namespace Test
{
	class Program
	{
		static void Main()
		{
			// Nutzung der Kernprüfung Version 1.6.1
			var pruefung16 = new adapter16.impl.KernpruefungAufrufImpl();
			
			//// ... Nutzung ...

			// Nutzung der Kernprüfung Version 1.7.0
			var pruefung17 = new adapter17.impl.KernpruefungAufrufImpl();
			
			//// ... Nutzung ...
		}
	}
}
```
