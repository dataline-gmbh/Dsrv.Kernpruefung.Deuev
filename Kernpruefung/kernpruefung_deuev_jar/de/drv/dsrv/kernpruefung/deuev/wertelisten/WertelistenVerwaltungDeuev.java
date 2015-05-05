package de.drv.dsrv.kernpruefung.deuev.wertelisten;

import java.util.ArrayList;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.wertelisten.AbstractWertelistenVerwaltung;
import de.drv.dsrv.kernpruefung.basis.wertelisten.ListenTyp;
import de.drv.dsrv.kernpruefung.basis.wertelisten.MapTyp;
import de.drv.dsrv.kernpruefung.basis.wertelisten.WertelistenException;

/**
 * Singleton-Klasse fuer das Verwalten der DEUEV-Wertelisten. Es muss am Anfang
 * 1x ladeListen aufgerufen werden.
 */
public final class WertelistenVerwaltungDeuev extends AbstractWertelistenVerwaltung {

	private static final WertelistenVerwaltungDeuev INSTANCE = new WertelistenVerwaltungDeuev();
	private final List<ListenTyp> speicherortList;
	private final List<MapTyp> speicherortProp;

	/**
	 * Gibt eine Instanz der {@link WertelistenVerwaltungDeuev} zurueck
	 * (Singleton).
	 * 
	 * @return {@link WertelistenVerwaltungDeuev}
	 */
	public static WertelistenVerwaltungDeuev getInstance() {
		return INSTANCE;
	}

	@Override
	public void ladeListen() throws WertelistenException {
		super.ladeListen(speicherortList, speicherortProp);
	}

	private WertelistenVerwaltungDeuev(){
		speicherortProp = new ArrayList<MapTyp>();
		speicherortList = new ArrayList<ListenTyp>();

		for (final ListenTypDeuev lTyp : ListenTypDeuev.values()) {
			speicherortList.add(lTyp);
		}

		for (final MapTypDeuev pTyp : MapTypDeuev.values()) {
			speicherortProp.add(pTyp);
		}
	}
}
