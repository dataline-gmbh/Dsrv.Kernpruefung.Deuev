package de.drv.dsrv.kernpruefung.deuev.pruefer.baustein;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.FeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Datensatz;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefer.AbstractBausteinPruefer;
import de.drv.dsrv.kernpruefung.basis.pruefer.UngueltigePrueferDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbme.PruefungBeitragsgruppe;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbme.PruefungEntgelt;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbme.PruefungKennzGleitzone;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbme.PruefungKennzStorno;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbme.PruefungKennzeichenMehrfach;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbme.PruefungKennzeichenRechtskreis;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbme.PruefungTaetigkeitsSchluessel;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbme.PruefungWaehrungsKennzeichen;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbme.PruefungZahlTage;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbme.PruefungZeitraumBeginn;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbme.PruefungZeitraumEnde;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBGB;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Registriert und liefert alle Pruefungen, die fuer die Felder des Bausteins
 * DBME definiert wurden.
 */
public class BausteinPrueferDBME extends AbstractBausteinPruefer<FeldNameDBME, FehlerNummerDBME> {

	private final transient Baustein<FeldNameDSME> bausteinDSME;
	private final transient Baustein<FeldNameDBGB> bausteinDBGB;
	private final transient Date verarbDatum;
	private final transient Datensatz datensatz;
	private final transient String vfmm;

	/**
	 * Konstruktor.
	 * 
	 * @param baustein
	 *            Baustein, fuer den die Pruefungen registriert werden
	 * @param bausteinDSME
	 *            Baustein DSME
	 * @param bausteinDBGB
	 *            Baustein DBGB
	 * @param verarbDatum
	 *            Verarbeitungsdatum aus den Eingabedaten
	 * @param datensatz
	 * @param vfmm
	 *            Verfahrensmerkmale
	 */
	public BausteinPrueferDBME(final Baustein<FeldNameDBME> baustein, final Baustein<FeldNameDSME> bausteinDSME,
			final Baustein<FeldNameDBGB> bausteinDBGB, final Date verarbDatum, final Datensatz datensatz,
			final String vfmm) {
		super(baustein);
		this.bausteinDSME = bausteinDSME;
		this.bausteinDBGB = bausteinDBGB;
		this.verarbDatum = verarbDatum;
		this.datensatz = datensatz;
		this.vfmm = vfmm;
	}

	@Override
	public List<FeldPruefung<FeldNameDBME, FehlerNummerDBME>> getPruefungen() throws UngueltigePrueferDatenException {

		final List<FeldPruefung<FeldNameDBME, FehlerNummerDBME>> pruefList = new ArrayList<FeldPruefung<FeldNameDBME, FehlerNummerDBME>>();
		final Baustein<FeldNameDBME> baustein = this.getBaustein();

		final Feld<FeldNameDBME> feldKennzSt = baustein.getFeld(FeldNameDBME.KENNZ_STORNO);
		final Feld<FeldNameDBME> feldKennzGle = baustein.getFeld(FeldNameDBME.KENNZ_GLEITZONE);
		final Feld<FeldNameDBME> feldZrbg = baustein.getFeld(FeldNameDBME.ZEITRAUM_BEGINN);
		final Feld<FeldNameDBME> feldZren = baustein.getFeld(FeldNameDBME.ZEITRAUM_ENDE);
		final Feld<FeldNameDBME> feldZltg = baustein.getFeld(FeldNameDBME.ZAHL_TAGE);
		final Feld<FeldNameDBME> feldWg = baustein.getFeld(FeldNameDBME.WAEHRUNGS_KENNZ);
		final Feld<FeldNameDBME> feldEg = baustein.getFeld(FeldNameDBME.ENTGELT);
		final Feld<FeldNameDBME> feldBygr = baustein.getFeld(FeldNameDBME.BEITRAGS_GRUPPE);
		final Feld<FeldNameDBME> feldTtsc = baustein.getFeld(FeldNameDBME.TAETIGKEITS_SC);
		final Feld<FeldNameDBME> feldKennzRk = baustein.getFeld(FeldNameDBME.KENNZ_RECHTSKREIS);
		final Feld<FeldNameDBME> feldKennzMf = baustein.getFeld(FeldNameDBME.KENNZ_MEHRFACH);

		if ((feldKennzSt == null) || (feldKennzGle == null) || (feldZrbg == null) || (feldZren == null)
				|| (feldZltg == null) || (feldWg == null) || (feldEg == null) || (feldBygr == null)
				|| (feldTtsc == null) || (feldKennzRk == null) || (feldKennzMf == null)) {
			throw new UngueltigePrueferDatenException("Pruefung fuer den Baustein " + baustein.getName()
					+ " kann nicht initalisiert werden, einer der Felder ist nicht vorhanden: " + baustein);
		} else {
			pruefList.add(new PruefungKennzStorno(feldKennzSt, this.bausteinDSME));
			pruefList.add(new PruefungKennzGleitzone(feldKennzGle, feldKennzSt, this.bausteinDSME, this.vfmm,
					this.verarbDatum));
			pruefList.add(new PruefungZeitraumBeginn(feldZrbg, feldKennzSt, feldKennzGle, this.datensatz,
					this.verarbDatum, this.vfmm));
			pruefList.add(new PruefungZeitraumEnde(feldZren, feldZrbg, feldKennzSt, feldKennzGle, this.verarbDatum,
					this.datensatz));
			pruefList.add(new PruefungZahlTage(feldZltg, this.bausteinDSME));
			pruefList.add(new PruefungWaehrungsKennzeichen(feldWg, feldZrbg, feldZren));
			pruefList.add(new PruefungEntgelt(feldEg, feldBygr, feldKennzRk, feldZrbg, feldZren, feldKennzSt, feldWg,
					this.bausteinDSME, this.vfmm));
			pruefList.add(new PruefungBeitragsgruppe(feldBygr, feldKennzSt, feldKennzGle, feldZrbg, feldZren,
					this.bausteinDSME, this.bausteinDBGB, this.verarbDatum, this.vfmm));
			pruefList.add(new PruefungTaetigkeitsSchluessel(feldTtsc, feldZrbg, feldZren, feldKennzSt,
					this.bausteinDSME));
			pruefList.add(new PruefungKennzeichenRechtskreis(feldKennzRk, feldZrbg, feldZren, this.bausteinDSME));
			pruefList.add(new PruefungKennzeichenMehrfach(feldKennzMf, this.vfmm));
		}

		return pruefList;
	}
}
