package de.drv.dsrv.kernpruefung.deuev.pruefer.baustein;

import java.util.ArrayList;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.FeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefer.AbstractBausteinPruefer;
import de.drv.dsrv.kernpruefung.basis.pruefer.UngueltigePrueferDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dban.PruefungAnschriftenzusatz;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dban.PruefungHausnummer;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dban.PruefungLaenderkennzeichen;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dban.PruefungPLZ;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dban.PruefungStrasse;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dban.PruefungWohnort;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBAN;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBAN;

/**
 * Registriert und liefert alle Pruefungen, die fuer die Felder des Bausteins
 * DBAN definiert wurden.
 */
public class BausteinPrueferDBAN extends AbstractBausteinPruefer<FeldNameDBAN, FehlerNummerDBAN> {

	private final String vfmm;

	/**
	 * Konstruktor.
	 * 
	 * @param baustein
	 *            Baustein, fuer den die Pruefungen registriert werden
	 * @param vfmm
	 *            Opcode (Verfahrensmerkmale)
	 */
	public BausteinPrueferDBAN(final Baustein<FeldNameDBAN> baustein, final String vfmm) {
		super(baustein);

		this.vfmm = vfmm;
	}

	@Override
	public List<FeldPruefung<FeldNameDBAN, FehlerNummerDBAN>> getPruefungen()
			throws UngueltigePrueferDatenException {

		final List<FeldPruefung<FeldNameDBAN, FehlerNummerDBAN>> pruefList = new ArrayList<FeldPruefung<FeldNameDBAN, FehlerNummerDBAN>>();
		final Baustein<FeldNameDBAN> baustein = this.getBaustein();

		// Felder der Reihenfolge nach auslesen und Pruefungen definieren
		final Feld<FeldNameDBAN> feldLdkz = baustein.getFeld(FeldNameDBAN.LAENDER_KENNZ);
		final Feld<FeldNameDBAN> feldPLZ = baustein.getFeld(FeldNameDBAN.PLZ);
		final Feld<FeldNameDBAN> feldOrt = baustein.getFeld(FeldNameDBAN.WOHNORT);
		final Feld<FeldNameDBAN> feldStr = baustein.getFeld(FeldNameDBAN.STRASSE);
		final Feld<FeldNameDBAN> feldNr = baustein.getFeld(FeldNameDBAN.HAUS_NR);
		final Feld<FeldNameDBAN> feldAdrzu = baustein.getFeld(FeldNameDBAN.ADR_ZUSATZ);

		if ((feldLdkz == null) || (feldPLZ == null) || (feldOrt == null) || (feldStr == null) || (feldNr == null)
				|| (feldAdrzu == null)) {
			throw new UngueltigePrueferDatenException("Pruefung fuer den Baustein " + baustein.getName()
					+ " kann nicht initalisiert werden, eines der Felder ist nicht vorhanden: " + baustein);
		} else {
			pruefList.add(new PruefungLaenderkennzeichen(feldLdkz, vfmm));
			pruefList.add(new PruefungPLZ(feldPLZ, feldLdkz));
			pruefList.add(new PruefungWohnort(feldOrt, feldLdkz));
			pruefList.add(new PruefungStrasse(feldStr, feldLdkz));
			pruefList.add(new PruefungHausnummer(feldNr));
			pruefList.add(new PruefungAnschriftenzusatz(feldAdrzu));
		}

		return pruefList;
	}

}
