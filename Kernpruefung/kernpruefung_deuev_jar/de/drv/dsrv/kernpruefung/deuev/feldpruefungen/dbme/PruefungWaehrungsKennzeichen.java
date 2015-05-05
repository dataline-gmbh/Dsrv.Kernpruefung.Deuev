package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbme;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumNachDatum;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumVorDatum;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;

/**
 * Pruefung fuer das Feld Waehrungskennzeichen.
 */
public class PruefungWaehrungsKennzeichen extends AbstractFeldPruefung<FeldNameDBME, FehlerNummerDBME> {
	
	private static final String EURO = "E";
	private static final String DMARK = "D";
	
	private static final List<String> ERLBT_ZCHN_082 = Arrays.asList("", "D", "E");
	
	// @formatter:off
	private static final Date DAT_084 = new GregorianCalendar(1999, 0, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DAT_086 = new GregorianCalendar(2001, 11, 31).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	// @formatter:on
	
	private final transient Feld<FeldNameDBME> feldDBMEZrbg;
	private final transient Feld<FeldNameDBME> feldDBMEZren;
	
	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            das Feld Waehrungskennzeichen
	 * @param feldDBMEZrbg
	 *            das Feld Zeitraum-Beginn aus dem Baustein DBME
	 * @param feldDBMEZren
	 *            das Feld Zeitraum-Ende aus dem Baustein DBME
	 */
	public PruefungWaehrungsKennzeichen(final Feld<FeldNameDBME> feld, final Feld<FeldNameDBME> feldDBMEZrbg,
			final Feld<FeldNameDBME> feldDBMEZren) {
		super(feld);
		this.feldDBMEZrbg = feldDBMEZrbg;
		this.feldDBMEZren = feldDBMEZren;
	}
	
	private void addPruefungFeldUebergreifend084(final Feld<FeldNameDBME> feldDBMEZeitraum) {
		final PruefungDatumNachDatum me084 = new PruefungDatumNachDatum(feldDBMEZeitraum, DAT_084);
		this.addPruefungFeldUebergreifend(me084, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME084));
	}
	
	private void addPruefungFeldUebergreifend086(final Feld<FeldNameDBME> feldDBMEZeitraum) {
		final PruefungDatumVorDatum me086 = new PruefungDatumVorDatum(feldDBMEZeitraum, DAT_086);
		this.addPruefungFeldUebergreifend(me086, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME086));
	}
	
	/**
	 * Initialisiere die felduebergreifenden Pruefungen.
	 * 
	 * @throws UngueltigeDatenException
	 */
	@Override
	public void initialisiereFeldUebergreifendePruefungen() throws UngueltigeDatenException {
		// Pruefungen auf Zeiten nur durchfuehren, wenn vorhanden
		if (this.isPruefbar(Arrays.asList(FehlerNummerDBME.DBME084, FehlerNummerDBME.DBME086), this.feldDBMEZrbg,
				this.feldDBMEZren) && this.zeitraeumeNichtLeerUndNummerisch()) {
			final String feldValue = this.getFeld().getTrimmedValue();
			final boolean zrbgEmtptyNummeric = StringUtils.isEmptyNumeric(this.feldDBMEZrbg.getTrimmedValue());
			
			if (EURO.equals(feldValue)) {
				if (zrbgEmtptyNummeric) {
					this.addPruefungFeldUebergreifend084(this.feldDBMEZren);
				} else {
					this.addPruefungFeldUebergreifend084(this.feldDBMEZrbg);
				}
			}
			
			if (DMARK.equals(feldValue)) {
				if (zrbgEmtptyNummeric) {
					this.addPruefungFeldUebergreifend086(this.feldDBMEZren);
				} else {
					this.addPruefungFeldUebergreifend086(this.feldDBMEZrbg);
				}
			}
		}
	}
	
	/**
	 * Initialisiere die feldinternen Pruefungen.
	 * 
	 * @throws UngueltigeDatenException
	 */
	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungInList me082 = new PruefungInList(ERLBT_ZCHN_082, this.getFeld(), true);
		this.addPruefung(me082, new Fehler<FehlerNummerDBME>(FehlerNummerDBME.DBME082));
	}
	
	/**
	 * Validiert, das die Zeitraeume (Zeitraum-Beginn und Zeitraum-Ende) nicht
	 * leer und nummerisch sind.
	 * 
	 * @return true, if successful
	 * @throws UngueltigeDatenException
	 */
	private boolean zeitraeumeNichtLeerUndNummerisch() throws UngueltigeDatenException {
		return StringUtils.isNotEmpty(this.feldDBMEZrbg.getTrimmedValue())
				&& StringUtils.isNotEmpty(this.feldDBMEZren.getTrimmedValue())
				&& StringUtils.isNumeric(this.feldDBMEZrbg.getTrimmedValue())
				&& StringUtils.isNumeric(this.feldDBMEZren.getTrimmedValue());
	}
}
