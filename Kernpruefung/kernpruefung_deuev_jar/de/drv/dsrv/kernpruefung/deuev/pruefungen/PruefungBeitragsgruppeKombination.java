package de.drv.dsrv.kernpruefung.deuev.pruefungen;

import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;
import de.drv.dsrv.kernpruefung.basis.pruefungen.AbstractPruefung;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.ListenTypDeuev;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.WertelistenVerwaltungDeuev;

/**
 * Validiert ob die Kombination aus zulaessigen
 * Personengruppenschluessel-/Beitragsgruppenschluessel nach Anlage 16 gueltig
 * ist. <br>
 * Um die uebersichtlichkeit zu erhoehen wird die komplexe Datenstruktur im
 * {@link BeitragsgruppenManager} abstrahiert.
 */
public class PruefungBeitragsgruppeKombination extends AbstractPruefung {

	private final transient List<String> werteListe = WertelistenVerwaltungDeuev.getInstance().getWerteliste(
			ListenTypDeuev.A16_BEITRAGSGRUPPEN_KOMBINATIONEN);

	// Abmessungen des Feldes
	private static final int ZUL_LAENGE = 4;

	private final transient Feld<? extends FeldName> feldPersgr;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            das Feld mit den Beitragsgruppen
	 * @param feldPersgr
	 *            das Feld mit den Personengruppen
	 */
	public PruefungBeitragsgruppeKombination(final Feld<? extends FeldName> feld,
			final Feld<? extends FeldName> feldPersgr) {
		super(feld);
		this.feldPersgr = feldPersgr;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = true;

		if (this.getFeld().getTrimmedValue().length() == ZUL_LAENGE) {
			// Aufbereitung des uebergebenen CSV-Strings in mehrere
			// durchsuchbare Listen durch BeitragsgruppenManager-Klasse
			final BeitragsgruppenManager btrsGrpMgr = BeitragsgruppenManager.getInstance(this.werteListe);
			final String persgr = this.feldPersgr.getTrimmedValue();

			if (btrsGrpMgr.persgrVorhanden(persgr)) {
				erfolgreich = new PruefungBeitragsgruppeZulaessig(this.getFeld(), btrsGrpMgr.getZulWerteKV(persgr),
						btrsGrpMgr.getZulWerteRV(persgr), btrsGrpMgr.getZulWerteALV(persgr),
						btrsGrpMgr.getZulWertePV(persgr)).pruefe();
			} else {
				erfolgreich = false;
			}
		} else {
			erfolgreich = false;
		}

		return erfolgreich;
	}
}
