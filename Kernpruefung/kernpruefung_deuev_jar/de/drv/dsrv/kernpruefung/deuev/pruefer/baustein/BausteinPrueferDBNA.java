package de.drv.dsrv.kernpruefung.deuev.pruefer.baustein;

import java.util.ArrayList;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.FeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefer.AbstractBausteinPruefer;
import de.drv.dsrv.kernpruefung.basis.pruefer.UngueltigePrueferDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbna.PruefungFamilienname;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbna.PruefungKennzAendBer;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbna.PruefungNamenszusatz;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbna.PruefungTitel;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbna.PruefungVorname;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbna.PruefungVorsatzwort;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBNA;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBNA;

/**
 * Registriert und liefert alle Pruefungen, die fuer die Felder des Bausteins
 * DBAN definiert wurden.
 */
public class BausteinPrueferDBNA extends AbstractBausteinPruefer<FeldNameDBNA, FehlerNummerDBNA> {

	private final transient String vfmm;

	/**
	 * Konstruktor.
	 * 
	 * @param baustein
	 *            Baustein, fuer den die Pruefungen registriert werden
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmale)
	 */
	public BausteinPrueferDBNA(final Baustein<FeldNameDBNA> baustein, final String vfmm) {
		super(baustein);
		this.vfmm = vfmm;
	}

	@Override
	public List<FeldPruefung<FeldNameDBNA, FehlerNummerDBNA>> getPruefungen() throws UngueltigePrueferDatenException {

		final List<FeldPruefung<FeldNameDBNA, FehlerNummerDBNA>> pruefList = new ArrayList<FeldPruefung<FeldNameDBNA, FehlerNummerDBNA>>();
		final Baustein<FeldNameDBNA> baustein = this.getBaustein();

		// Felder der Reihenfolge nach auslesen und Pruefungen definieren
		final Feld<FeldNameDBNA> feldFName = baustein.getFeld(FeldNameDBNA.FAMILIENNAME);
		final Feld<FeldNameDBNA> feldVName = baustein.getFeld(FeldNameDBNA.VORNAME);
		final Feld<FeldNameDBNA> feldVorsatz = baustein.getFeld(FeldNameDBNA.VORSATZWORT);
		final Feld<FeldNameDBNA> feldNmZusatz = baustein.getFeld(FeldNameDBNA.NAMENSZUSATZ);
		final Feld<FeldNameDBNA> feldTitel = baustein.getFeld(FeldNameDBNA.TITEL);
		final Feld<FeldNameDBNA> feldKennzAend = baustein.getFeld(FeldNameDBNA.KENNZ_AEND_BER);

		if (feldFName == null || feldVName == null || feldKennzAend == null) {
			throw new UngueltigePrueferDatenException("Pruefung fuer den Baustein " + baustein.getName()
					+ " kann nicht initalisiert werden, einer der Felder ist nicht vorhanden: " + baustein);
		} else {
			pruefList.add(new PruefungFamilienname(feldFName));
			pruefList.add(new PruefungVorname(feldVName, feldFName, this.vfmm));
			pruefList.add(new PruefungVorsatzwort(feldVorsatz));
			pruefList.add(new PruefungNamenszusatz(feldNmZusatz));
			pruefList.add(new PruefungTitel(feldTitel));
			pruefList.add(new PruefungKennzAendBer(feldKennzAend, this.vfmm));
		}

		return pruefList;
	}

}
