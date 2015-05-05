package de.drv.dsrv.kernpruefung.deuev.pruefer.baustein;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.FeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefer.AbstractBausteinPruefer;
import de.drv.dsrv.kernpruefung.basis.pruefer.UngueltigePrueferDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbaz.PruefungArtDerZeit;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbaz.PruefungKennzStorno;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbaz.PruefungZeitraumBeginn;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbaz.PruefungZeitraumEnde;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBAZ;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBAZ;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSAE;

/**
 * Registriert und liefert alle Pruefungen, die fuer die Felder des Bausteins
 * DBAZ definiert wurden.
 */
public class BausteinPrueferDBAZ extends AbstractBausteinPruefer<FeldNameDBAZ, FehlerNummerDBAZ> {

	private final Date verarbDatum;
	private final Baustein<FeldNameDSAE> bausteinDsae;
	private final String vfmm;

	/**
	 * Konstruktor.
	 * 
	 * @param baustein
	 *            Baustein, fuer den die Pruefungen registriert werden
	 * @param bausteinDsae
	 *            Baustein DSAE
	 * @param verarbDatum
	 *            Verarbeitungsdatum aus den Eingabedaten
	 * @param vfmm
	 *            Verfahrensmerkmale
	 */
	public BausteinPrueferDBAZ(final Baustein<FeldNameDBAZ> baustein, final Baustein<FeldNameDSAE> bausteinDsae,
			final Date verarbDatum, final String vfmm) {
		// @formatter:on

		super(baustein);
		this.bausteinDsae = bausteinDsae;
		this.verarbDatum = verarbDatum;
		this.vfmm = vfmm;
	}

	@Override
	public List<FeldPruefung<FeldNameDBAZ, FehlerNummerDBAZ>> getPruefungen() throws UngueltigePrueferDatenException {

		final List<FeldPruefung<FeldNameDBAZ, FehlerNummerDBAZ>> pruefList = new ArrayList<FeldPruefung<FeldNameDBAZ, FehlerNummerDBAZ>>();
		final Baustein<FeldNameDBAZ> baustein = this.getBaustein();

		// Felder der Reihenfolge nach auslesen und Pruefungen definieren
		final Feld<FeldNameDBAZ> feldKennzStorno = baustein.getFeld(FeldNameDBAZ.KENNZ_STORNO);
		final Feld<FeldNameDBAZ> feldLeat = baustein.getFeld(FeldNameDBAZ.ART_DER_ZEIT);
		final Feld<FeldNameDBAZ> feldZrbg = baustein.getFeld(FeldNameDBAZ.ZEITRAUM_BEGINN);
		final Feld<FeldNameDBAZ> feldZren = baustein.getFeld(FeldNameDBAZ.ZEITRAUM_ENDE);

		if ((feldKennzStorno == null) || (feldLeat == null) || (feldZrbg == null) || (feldZren == null)) {
			throw new UngueltigePrueferDatenException("Pruefung fuer den Baustein " + baustein.getName()
					+ " kann nicht initalisiert werden, eines der Felder ist nicht vorhanden: " + baustein);
		} else {
			pruefList.add(new PruefungKennzStorno(feldKennzStorno));
			pruefList.add(new PruefungArtDerZeit(feldLeat, this.bausteinDsae, this.vfmm));
			pruefList.add(new PruefungZeitraumBeginn(feldZrbg, feldKennzStorno, feldLeat, this.bausteinDsae,
					this.verarbDatum));
			pruefList.add(new PruefungZeitraumEnde(feldZren, feldZrbg, feldKennzStorno, feldLeat, this.verarbDatum));
		}

		return pruefList;
	}
}
