package de.drv.dsrv.kernpruefung.deuev.pruefer.baustein;

import java.util.ArrayList;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.FeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefer.AbstractBausteinPruefer;
import de.drv.dsrv.kernpruefung.basis.pruefer.UngueltigePrueferDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbks.PruefungKennzeichenKS;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbks.PruefungVersicherungsarten;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbks.PruefungVknr;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBKS;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKS;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Registriert und liefert alle Pruefungen, die fuer die Felder des Bausteins
 * DBKS definiert wurden.
 */
public class BausteinPrueferDBKS extends AbstractBausteinPruefer<FeldNameDBKS, FehlerNummerDBKS> {

	private final Baustein<FeldNameDSME> bausteinDsme;
	private final Baustein<FeldNameDBME> bausteinDbme;
	private final String vfmm;

	/**
	 * Konstruktor. Nimmt alle Werte entgegen, die fuer die Feld-Pruefungen in
	 * dem Baustein benoetigt werden, und speichert diese ab.
	 * 
	 * @param baustein
	 *            - Baustein DBKS
	 * @param bausteinDsme
	 *            - (externer) Baustein DSME
	 * @param bausteinDbme
	 *            - (externer) Baustein DBME
	 * @param vfmm
	 *            - Opcode (Verfahrensmerkmale)
	 */
	public BausteinPrueferDBKS(final Baustein<FeldNameDBKS> baustein, final Baustein<FeldNameDSME> bausteinDsme,
			final Baustein<FeldNameDBME> bausteinDbme, final String vfmm) {
		super(baustein);

		this.bausteinDsme = bausteinDsme;
		this.bausteinDbme = bausteinDbme;
		this.vfmm = vfmm;
	}

	@Override
	public List<FeldPruefung<FeldNameDBKS, FehlerNummerDBKS>> getPruefungen()
			throws UngueltigePrueferDatenException {

		final List<FeldPruefung<FeldNameDBKS, FehlerNummerDBKS>> pruefList = new ArrayList<FeldPruefung<FeldNameDBKS, FehlerNummerDBKS>>();
		final Baustein<FeldNameDBKS> baustein = getBaustein();

		// Felder der Reihenfolge nach auslesen und Pruefungen definieren
		final Feld<FeldNameDBKS> feldKennzks = baustein.getFeld(FeldNameDBKS.KENNZ_KNV_SEE);
		final Feld<FeldNameDBKS> feldVersicherungsarten = baustein.getFeld(FeldNameDBKS.VERSICHERUNGSARTEN);
		final Feld<FeldNameDBKS> feldVknr = baustein.getFeld(FeldNameDBKS.VKNR);

		if ((feldKennzks == null) || (feldVersicherungsarten == null) || (feldVknr == null)) {
			throw new UngueltigePrueferDatenException("Pruefung fuer den Baustein " + baustein.getName()
					+ " kann nicht initalisiert werden, einer der Felder ist nicht vorhanden: " + baustein);
		}

		pruefList.add(new PruefungKennzeichenKS(feldKennzks, bausteinDsme));
		pruefList.add(new PruefungVersicherungsarten(feldVersicherungsarten, feldKennzks, bausteinDbme, bausteinDsme));
		pruefList.add(new PruefungVknr(feldVknr, feldKennzks, bausteinDsme, bausteinDbme,
				vfmm));

		return pruefList;
	}

}
