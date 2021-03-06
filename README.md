# Dieses Projekt wird nicht mehr unterstützt.

Mit dem 01.01.2019 wurde die bisherige DEÜV-Kernprüfung eingestellt und durch eine [Neuentwicklung der DSRV](https://www.dsrv.info/de/Navigation/20_Unsere_Verfahren/01_Nationaler_Datenaustausch/09_Kernpr%C3%BCfung/Kernpr%C3%BCfung_node.html) ersetzt, die mit diesem Projekt nicht kompatibel ist.

Dieses Projekt wird aus diesem Grund nicht mehr gepflegt, und es wird keine weiteren Updates oder neuen Pakete geben.

----

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
1.8.0   | [![Dsrv.Kernpruefung.Deuev-1.8](https://img.shields.io/nuget/v/Dsrv.Kernpruefung.Deuev-1.8.svg)](https://www.nuget.org/packages/Dsrv.Kernpruefung.Deuev-1.8/)
1.9.0   | [![Dsrv.Kernpruefung.Deuev-1.9](https://img.shields.io/nuget/v/Dsrv.Kernpruefung.Deuev-1.9.svg)](https://www.nuget.org/packages/Dsrv.Kernpruefung.Deuev-1.9/)
1.10.0  | [![Dsrv.Kernpruefung.Deuev-1.10](https://img.shields.io/nuget/v/Dsrv.Kernpruefung.Deuev-1.10.svg)](https://www.nuget.org/packages/Dsrv.Kernpruefung.Deuev-1.10/)
1.11.1  | [![Dsrv.Kernpruefung.Deuev-1.11](https://img.shields.io/nuget/v/Dsrv.Kernpruefung.Deuev-1.11.svg)](https://www.nuget.org/packages/Dsrv.Kernpruefung.Deuev-1.11/)

Wenn man in einer Anwendung 1.7 und 1.8 verwenden möchte, dann muss man das wie folgt machen:

```csharp
extern alias deuev17;
extern alias deuev18;

using adapter17 = deuev17::de.drv.dsrv.kernpruefung.adapter;
using adapter18 = deuev18::de.drv.dsrv.kernpruefung.adapter;

namespace Test
{
	class Program
	{
		static void Main()
		{
			// Nutzung der Kernprüfung Version 1.7.0
			var pruefung17 = new adapter17.impl.KernpruefungAufrufImpl();
			
			//// ... Nutzung ...

			// Nutzung der Kernprüfung Version 1.8.0
			var pruefung18 = new adapter18.impl.KernpruefungAufrufImpl();
			
			//// ... Nutzung ...
		}
	}
}
```

Zusätzlich muss, wenn das neue CSPROJ-Format verwendet wird, dieser Block hinzugefügt werden, der die Aliases den entsprechenden Assemblies zuordnet:

    <Target Name="ChangeAliasesOfStrongNameAssemblies" BeforeTargets="FindReferenceAssembliesForReferences;ResolveReferences">
      <ItemGroup>
        <ReferencePath Condition="'%(FileName)' == 'Dsrv.Kernpruefung.Deuev-1.7'">
          <Aliases>deuev17</Aliases>
        </ReferencePath>
        <ReferencePath Condition="'%(FileName)' == 'Dsrv.Kernpruefung.Deuev-1.8'">
          <Aliases>deuev18</Aliases>
        </ReferencePath>
      </ItemGroup>
    </Target>
