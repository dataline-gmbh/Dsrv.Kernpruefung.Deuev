package de.drv.dsrv.kernpruefung.deuev.pruefer.baustein;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.FeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefer.AbstractBausteinPruefer;
import de.drv.dsrv.kernpruefung.basis.pruefer.UngueltigePrueferDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbgb.PruefungGeburtsdatum;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbgb.PruefungGeburtsname;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbgb.PruefungGeburtsort;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbgb.PruefungGeschlecht;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbgb.PruefungNamenszusatz;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbgb.PruefungVorsatzwort;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBGB;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBGB;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBNA;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBVR;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Registriert und liefert alle Pruefungen, die fuer die Felder des Bausteins
 * DBGB definiert wurden.
 */
public class BausteinPrueferDBGB extends AbstractBausteinPruefer<FeldNameDBGB, FehlerNummerDBGB> {

	private final transient Baustein<FeldNameDSME> bausteinDSME;
	private final transient Baustein<FeldNameDBVR> bausteinDBVR;
	private final transient Baustein<FeldNameDBNA> bausteinDBNA;
	private final transient Date verarbDatum;

	/**
	 * Konstruktor.
	 * 
	 * @param baustein
	 *            Baustein, fuer den die Pruefungen registriert werden
	 * @param bausteinDSME
	 *            der Baustein DSME
	 * @param bausteinDBVR
	 *            der Baustein DBVR
	 * @param verarbDatum
	 *            das Verarbeitungsdatum
	 * @param vfmm
	 *            der OPCODE VFMM
	 */
	public BausteinPrueferDBGB(final Baustein<FeldNameDBGB> baustein, final Baustein<FeldNameDSME> bausteinDSME,
			final Baustein<FeldNameDBVR> bausteinDBVR, final Baustein<FeldNameDBNA> bausteinDBNA, final Date verarbDatum) {
		super(baustein);
		this.bausteinDSME = bausteinDSME;
		this.bausteinDBVR = bausteinDBVR;
		this.bausteinDBNA = bausteinDBNA;
		this.verarbDatum = verarbDatum;
	}

	@Override
	public List<FeldPruefung<FeldNameDBGB, FehlerNummerDBGB>> getPruefungen() throws UngueltigePrueferDatenException {

		final List<FeldPruefung<FeldNameDBGB, FehlerNummerDBGB>> pruefList = new ArrayList<FeldPruefung<FeldNameDBGB, FehlerNummerDBGB>>();
		final Baustein<FeldNameDBGB> baustein = this.getBaustein();

		// Felder der Reihenfolge nach auslesen und Pruefungen definieren
		final Feld<FeldNameDBGB> feldGBName = baustein.getFeld(FeldNameDBGB.GB_NAME);
		final Feld<FeldNameDBGB> feldGBVorsatz = baustein.getFeld(FeldNameDBGB.GB_VORSATZWORT);
		final Feld<FeldNameDBGB> feldGBNmZusatz = baustein.getFeld(FeldNameDBGB.GB_NAMENSZUSATZ);
		final Feld<FeldNameDBGB> feldGBDatum = baustein.getFeld(FeldNameDBGB.GEBURTSDATUM);
		final Feld<FeldNameDBGB> feldGeschlecht = baustein.getFeld(FeldNameDBGB.GESCHLECHT);
		final Feld<FeldNameDBGB> feldGBOrt = baustein.getFeld(FeldNameDBGB.GB_ORT);

		if (feldGBDatum == null || feldGeschlecht == null || feldGBOrt == null) {
			throw new UngueltigePrueferDatenException("Pruefung fuer den Baustein " + baustein.getName()
					+ " kann nicht initalisiert werden, einer der Felder ist nicht vorhanden: " + baustein);
		} else {
			pruefList.add(new PruefungGeburtsname(feldGBName, bausteinDBNA));
			pruefList.add(new PruefungVorsatzwort(feldGBVorsatz));
			pruefList.add(new PruefungNamenszusatz(feldGBNmZusatz));
			pruefList.add(new PruefungGeburtsdatum(feldGBDatum, this.bausteinDSME, this.verarbDatum));
			pruefList.add(new PruefungGeschlecht(feldGeschlecht, this.bausteinDSME));
			pruefList.add(new PruefungGeburtsort(feldGBOrt, this.bausteinDSME, this.bausteinDBVR));
		}

		return pruefList;
	}
}
