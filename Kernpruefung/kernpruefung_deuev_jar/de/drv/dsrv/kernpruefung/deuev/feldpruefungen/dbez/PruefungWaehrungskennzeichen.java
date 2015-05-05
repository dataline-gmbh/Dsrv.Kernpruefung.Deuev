package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbez;

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
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBEZ;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBEZ;

/**
 * Pruefung fuer das Feld Waehrungskennzeichen aus dem Baustein DBEZ.
 */
public class PruefungWaehrungskennzeichen extends AbstractFeldPruefung<FeldNameDBEZ, FehlerNummerDBEZ> {

	private static final List<String> ZULAESSIGE_WERTE = Arrays.asList("D", "E");

	// @formatter:off
	private static final Date JAN_2002 = new GregorianCalendar(2002, 0, 1).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	private static final Date DEZ_2001 = new GregorianCalendar(2001, 11, 31).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	// @formatter:on

	private final Feld<FeldNameDBEZ> feldZeitraumBeginn;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldZeitraumBeginn
	 *            Feld ZeitraumBeginn aus dem Baustein DBEZ.
	 */
	public PruefungWaehrungskennzeichen(final Feld<FeldNameDBEZ> feld, final Feld<FeldNameDBEZ> feldZeitraumBeginn) {
		super(feld);

		this.feldZeitraumBeginn = feldZeitraumBeginn;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		if (StringUtils.isNotBlank(getFeld().getTrimmedValue())) {
			final PruefungInList ez082 = new PruefungInList(ZULAESSIGE_WERTE, getFeld(), true);
			addPruefung(ez082, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ082));
		}
	}

	@Override
	public void initialisiereFeldUebergreifendePruefungen() throws UngueltigeDatenException {
		if (isPruefbar(Arrays.asList(FehlerNummerDBEZ.DBEZ084, FehlerNummerDBEZ.DBEZ086), feldZeitraumBeginn)) {
			if ("E".equals(getFeld().getTrimmedValue())) {
				final PruefungDatumNachDatum ez084 = new PruefungDatumNachDatum(feldZeitraumBeginn, JAN_2002);
				addPruefungFeldUebergreifend(ez084, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ084));

			} else if ("D".equals(getFeld().getTrimmedValue())) {
				final PruefungDatumVorDatum ez086 = new PruefungDatumVorDatum(feldZeitraumBeginn, DEZ_2001);
				addPruefungFeldUebergreifend(ez086, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ086));
			}
		}
	}
}
