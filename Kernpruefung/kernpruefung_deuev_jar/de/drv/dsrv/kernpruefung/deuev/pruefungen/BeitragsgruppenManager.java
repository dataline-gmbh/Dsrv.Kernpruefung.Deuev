package de.drv.dsrv.kernpruefung.deuev.pruefungen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.drv.dsrv.kernpruefung.basis.utils.Sonderzeichen;


/**
 * Der BeitragsgruppenManager erweitert die uebergebene Werteliste (zeilenweises
 * CSV) mit zulaessigen
 * Personengruppenschluessel-/Beitragsgruppenschluessel/Kombinationen zu einer
 * mehrdimensionalen Tabelle und abstrahiert den Zugriff auf diese.
 * 
 * Nach aussen kann ueber die Personengruppe (Feld PERSGR aus dem baustein DSME)
 * auf die fuer diese jeweils zulaessige Schluessel-Kombination der
 * Beitragsgruppen (KV, RV, ALV und PV) zugegriffen werden.
 */
public class BeitragsgruppenManager {
	
	// Aufbau: Map<PersonenGruppe, Map<SpaltenSchluessel, List<Spaltenwerte>>>
	private static final Map<String, Map<Integer, List<String>>> PERSGR_BTRGGR_KOMBI = new HashMap<String, Map<Integer, List<String>>>();
	
	// SpaltenSchluessel
	private static final int PERSGR = 0;
	private static final int KV = 1;
	private static final int RV = 2;
	private static final int ALV = 3;
	private static final int PV = 4;
	
	private static final BeitragsgruppenManager INSTANCE = new BeitragsgruppenManager();
	
	/**
	 * Erstelle die Liste der zulaessigen
	 * Personengruppenschluessel-/Beitragsgruppenschluessel-Kombinationen.
	 * 
	 * @param string
	 *            CSV-String (Zeile aus dem CSV)
	 */
	private static void erstellePersgrBtrggrKombi(final String string) {
		
		// Map<SpaltenSchluessel, List<Spaltenwerte>> zur Haltung der
		// zulaessigen Werte der verschiedenen Beitragsgruppen (KV, RV, ALV, PV)
		// einer Personengruppe
		final Map<Integer, List<String>> btrgsgrpWerte = new HashMap<Integer, List<String>>();
		
		final int strLen = string.length();
		int lastBreak = 0;
		int spalte = 0;
		for (int i = 0; i < strLen; i++) {
			if (Sonderzeichen.SEMICOLON.equals(string.charAt(i))) {
				final String extrahiert = string.substring(lastBreak, i);
				fuelleBtrgsgruppen(btrgsgrpWerte, spalte, extrahiereKommaSepListe(extrahiert));
				lastBreak = i + 1;
				spalte++;
			} else if (i == strLen - 1) {
				final String extrahiert = string.substring(lastBreak, i + 1);
				fuelleBtrgsgruppen(btrgsgrpWerte, spalte, extrahiereKommaSepListe(extrahiert));
				lastBreak = i;
				spalte++;
			}
		}
		
		// befuelle uebergeordnete Map, die Personengruppe dient als Key
		PERSGR_BTRGGR_KOMBI.put(btrgsgrpWerte.get(PERSGR).get(0), btrgsgrpWerte);
	}
	
	/**
	 * Extrahiere die jeweils zulaessigen Werte, die durch Kommas getrennt
	 * werden.
	 * 
	 * @param string
	 *            the string
	 * @return the list
	 */
	private static List<String> extrahiereKommaSepListe(final String string) {
		final ArrayList<String> subList = new ArrayList<String>();
		final String[] split = string.split(",");
		subList.addAll(Arrays.asList(split));

		return subList;
	}
	
	/**
	 * Fuelle die btrgsgrpWerte-Map (Map<SpaltenSchluessel, List<Spaltenwerte>>)
	 * mit den extrahierten zulaessigen Werten. Die SpaltenSchluessel
	 * entsprechen den durch nummerische Konstanten codieren Beitragsgruppen KV,
	 * RV, ALV und PV.
	 * 
	 * @param btrgsgruppenWerte
	 *            die BeitragsgruppenWerte-Map
	 * @param spalte
	 *            die Spalte in der Map
	 * @param extrahierteListe
	 *            die extrahierte liste
	 */
	private static void fuelleBtrgsgruppen(final Map<Integer, List<String>> btrgsgruppenWerte,
			final int spalte, final List<String> extrahierteListe) {
		switch (spalte) {
		case PERSGR:
			btrgsgruppenWerte.put(PERSGR, extrahierteListe);
			break;
		case KV:
			btrgsgruppenWerte.put(KV, extrahierteListe);
			break;
		case RV:
			btrgsgruppenWerte.put(RV, extrahierteListe);
			break;
		case ALV:
			btrgsgruppenWerte.put(ALV, extrahierteListe);
			break;
		case PV:
			btrgsgruppenWerte.put(PV, extrahierteListe);
			break;
		default:
			break;
		}
	}
	
	/**
	 * Singleton Instance fuer BeitragsgruppenHelper.
	 * 
	 * @param csvListe
	 *            die csv-Werteliste
	 * 
	 * @return single instance des BeitragsgruppenHelper
	 */
	public static synchronized BeitragsgruppenManager getInstance(final List<String> csvListe) {
		initPersgrBtrggrKombiList(csvListe);
		return INSTANCE;
	}
	
	/**
	 * Erstellt die Map der zulaessigen
	 * Personengruppenschluessel-/Beitragsgruppenschluessel, falls dies noch
	 * nicht geschehen ist.
	 * 
	 * @param csvListe
	 *            die csv-Werteliste
	 */
	private static void initPersgrBtrggrKombiList(final List<String> csvListe) {
		if (PERSGR_BTRGGR_KOMBI.isEmpty()) {
			for (final String string : csvListe) {
				erstellePersgrBtrggrKombi(string);
			}
		}
	}
	
	/**
	 * Pruefe, ob die uebergebene Personengruppe in der Liste der zulaessigen
	 * vorhanden ist.
	 * 
	 * @param persGrp
	 *            die Personengruppe
	 * @return true, wenn vorhanden
	 */
	public boolean persgrVorhanden(final String persGrp) {
		return PERSGR_BTRGGR_KOMBI.containsKey(persGrp);
	}

	/**
	 * Hole die zulaessigen Werte der uebergebenen Personengruppe fuer ALV.
	 * 
	 * @param persGrp
	 *            die Personengruppe
	 * @return die Liste der zulaessigen ALV-Schuessel
	 */
	public List<String> getZulWerteALV(final String persGrp) {
		return PERSGR_BTRGGR_KOMBI.get(persGrp).get(ALV);
	}
	
	/**
	 * Hole die zulaessigen Werte der uebergebenen Personengruppe fuer KV.
	 * 
	 * @param persGrp
	 *            die Personengruppe
	 * @return die Liste der zulaessigen KV-Schuessel
	 */
	public List<String> getZulWerteKV(final String persGrp) {
		return PERSGR_BTRGGR_KOMBI.get(persGrp).get(KV);
	}
	
	/**
	 * Hole die zulaessigen Werte der uebergebenen Personengruppe fuer PV.
	 * 
	 * @param persGrp
	 *            die Personengruppe
	 * @return die Liste der zulaessigen PV-Schuessel
	 */
	public List<String> getZulWertePV(final String persGrp) {
		return PERSGR_BTRGGR_KOMBI.get(persGrp).get(PV);
	}
	
	/**
	 * Hole die zulaessigen Werte der uebergebenen Personengruppe fuer RV.
	 * 
	 * @param persGrp
	 *            die Personengruppe
	 * @return die Liste der zulaessigen RV-Schuessel
	 */
	public List<String> getZulWerteRV(final String persGrp) {
		return PERSGR_BTRGGR_KOMBI.get(persGrp).get(RV);
	}
	
}
