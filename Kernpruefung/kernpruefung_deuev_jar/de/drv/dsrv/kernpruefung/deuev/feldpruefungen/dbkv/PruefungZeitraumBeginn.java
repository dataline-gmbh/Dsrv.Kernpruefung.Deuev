package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumLogischRichtig;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumNachDatum;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.utils.DateUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBKV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Pruefung fuer das Feld Zeitraum-Beginn aus dem Baustein DBKV.
 */
public class PruefungZeitraumBeginn extends AbstractFeldPruefung<FeldNameDBKV, FehlerNummerDBKV> {

	private static final Date ANFANG_2012 = new GregorianCalendar(2012, 0, 1).getTime();
	private static final Date ENDE_2014 = new GregorianCalendar(2014, 11, 31, 23, 59, 59).getTime();
	private static final Date Anfang_2015 = new GregorianCalendar(2015, 0, 1).getTime();

	private final Baustein<FeldNameDSME> bausteinDsme;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungZeitraumBeginn(final Feld<FeldNameDBKV> feld, final Baustein<FeldNameDSME> bausteinDsme) {
		super(feld);
		this.bausteinDsme = bausteinDsme;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch kv040 = new PruefungNumerisch(getFeld(), true);
		addPruefung(kv040, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV040));

		final PruefungDatumLogischRichtig kv042 = new PruefungDatumLogischRichtig(getFeld());
		addPruefung(kv042, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV042));

		final PruefungDatumNachDatum kv044 = new PruefungDatumNachDatum(getFeld(), ANFANG_2012);
		addPruefung(kv044, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV044));
	}
	
	@Override
	public void initialisiereBausteinUebergreifendePruefungen() {
		final Feld<FeldNameDSME> feldEd = bausteinDsme.getFeld(FeldNameDSME.DATUM_ERSTELLUNG);
		final String tmpEd = (feldEd.getTrimmedValue().length() > 14 ) ? feldEd.getTrimmedValue().substring(0, 14) : feldEd.getTrimmedValue();
		final DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.GERMANY);
		final Date erstellungsDatum = DateUtils.parseDate(tmpEd, dateFormat);
		if (isPruefbar(FehlerNummerDBKV.DBKV046, feldEd) && erstellungsDatum.after(ENDE_2014)) {
			final PruefungDatumNachDatum kv046 = new PruefungDatumNachDatum(getFeld(), Anfang_2015);
			addPruefungBausteinUebergreifend(kv046, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV046));
		}
	}
}
