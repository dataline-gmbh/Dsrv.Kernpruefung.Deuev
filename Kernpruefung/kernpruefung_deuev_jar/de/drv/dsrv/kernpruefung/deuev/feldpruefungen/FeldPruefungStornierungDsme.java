package de.drv.dsrv.kernpruefung.deuev.feldpruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBSO;

/**
 * Diese Klasse erkennt, ob es sich um eine Stornierung beim Datensatz DSME
 * handelt.
 * 
 */
public class FeldPruefungStornierungDsme {

	private static final String STORNIERUNG = "J";

	private final Feld<FeldNameDBME> feldDbmeKennzSt;
	private final Feld<FeldNameDBSO> feldDbsoKennzStSofort;
	private final Feld<FeldNameDBKV> feldDbkvKennzSt;

	/**
	 * Konstruktor.
	 * 
	 * @param bausteinDbme
	 * @param bausteinDbso
	 * @param bausteinDbkv
	 */
	public FeldPruefungStornierungDsme(final Baustein<FeldNameDBME> bausteinDbme,
			final Baustein<FeldNameDBSO> bausteinDbso, final Baustein<FeldNameDBKV> bausteinDbkv) {

		if (bausteinDbme != null) {
			feldDbmeKennzSt = bausteinDbme.getFeld(FeldNameDBME.KENNZ_STORNO);
		} else {
			feldDbmeKennzSt = new Feld<FeldNameDBME>(FeldNameDBME.KENNZ_STORNO, "invalid");
		}

		if (bausteinDbso != null) {
			feldDbsoKennzStSofort = bausteinDbso.getFeld(FeldNameDBSO.KENNZ_STORNO_SOFORT);
		} else {
			feldDbsoKennzStSofort = new Feld<FeldNameDBSO>(FeldNameDBSO.KENNZ_STORNO_SOFORT, "invalid");
		}

		if (bausteinDbkv != null) {
			feldDbkvKennzSt = bausteinDbkv.getFeld(FeldNameDBKV.KENNZ_STORNO);
		} else {
			feldDbkvKennzSt = new Feld<FeldNameDBKV>(FeldNameDBKV.KENNZ_STORNO, "invalid");
		}
	}

	/**
	 * Konstruktor.
	 * 
	 * @param bausteinDbme
	 */
	public FeldPruefungStornierungDsme(final Baustein<FeldNameDBME> bausteinDbme) {
		this(bausteinDbme, null, null);
	}

	/**
	 * Ueberprueft ob eines der Felder den Wert "J" inne hat. Falls ja liegt
	 * eine Stornierung vor, falls nein keine.
	 * 
	 * @return <code>true</code>, wenn mind 1 KennzSt-Feld gesetzt ist und den
	 *         Wert "J" besitzt.
	 */
	public boolean isStornierung() {
		boolean stornierung;

		// Falls nirgendwo Stornierung gesetzt wurde, liegt keine Stornierung
		// vor.
		if (feldDbmeKennzSt.getValue().equals(STORNIERUNG) || feldDbsoKennzStSofort.getValue().equals(STORNIERUNG)
				|| feldDbkvKennzSt.getValue().equals(STORNIERUNG)) {
			stornierung = true;
		} else {
			stornierung = false;
		}

		return stornierung;
	}

	/**
	 * Validiert nur das Feld KennzSt aus dem Baustein DBME.
	 * 
	 * @return <code>true</code>, wenn das KennzSt-Feld gesetzt ist und den Wert
	 *         "J" besitzt.
	 */
	public boolean isStornierungDbme() {
		return feldDbmeKennzSt.getValue().equals(STORNIERUNG);
	}
}
