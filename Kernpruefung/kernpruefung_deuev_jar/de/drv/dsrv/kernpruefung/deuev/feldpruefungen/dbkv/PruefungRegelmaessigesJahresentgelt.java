package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInterval;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBKV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKV;

/**
 * Pruefung fuer das Feld regelmaessiges Jahresentgelt aus dem Baustein DBKV.
 */
public class PruefungRegelmaessigesJahresentgelt extends AbstractFeldPruefung<FeldNameDBKV, FehlerNummerDBKV> {

	private static final Date ENDE_2012 = new GregorianCalendar(2012, 11, 31).getTime();
	private static final Date ENDE_2014 = new GregorianCalendar(2014, 11, 31).getTime();

	private final Feld<FeldNameDBKV> feldZrgbKv;
	private final Feld<FeldNameDBKV> feldKennzglSv;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldZrgbKv
	 *            Feld Zeitraumbeginn KV aus DBKV
	 * @param feldKennzglSv
	 *            Feld Kennzgle-Sv aus DBKV
	 */
	public PruefungRegelmaessigesJahresentgelt(final Feld<FeldNameDBKV> feld, final Feld<FeldNameDBKV> feldZrgbKv,
			final Feld<FeldNameDBKV> feldKennzglSv) {
		super(feld);

		this.feldZrgbKv = feldZrgbKv;
		this.feldKennzglSv = feldKennzglSv;
	}

	@Override
	public void initialisiereFeldUebergreifendePruefungen() throws UngueltigeDatenException {

		final List<FehlerNummerDBKV> fehlernummern = new LinkedList<FehlerNummerDBKV>();
		fehlernummern.add(FehlerNummerDBKV.DBKV120);
		fehlernummern.add(FehlerNummerDBKV.DBKV122);
		fehlernummern.add(FehlerNummerDBKV.DBKV124);
		fehlernummern.add(FehlerNummerDBKV.DBKV126);

		if (isPruefbar(fehlernummern, feldZrgbKv)) {
			final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.GERMANY);
			dateFormat.setLenient(false);

			try {
				final Date datumZrgbKv = dateFormat.parse(feldZrgbKv.getTrimmedValue());
				if (datumZrgbKv.after(ENDE_2014)) {
					final PruefungLaenge kv126 = new PruefungLaenge(0, getFeld());
					addPruefungFeldUebergreifend(kv126, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV126));
					
				} else if (datumZrgbKv.after(ENDE_2012)) {

					final PruefungNumerisch kv120b = new PruefungNumerisch(getFeld(), true);
					addPruefungFeldUebergreifend(kv120b, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV120));

					if (isPruefbar(FehlerNummerDBKV.DBKV122, feldKennzglSv)
							&& feldKennzglSv.getTrimmedValue().compareTo("1") == 0) {
						final PruefungInterval kv122 = new PruefungInterval(1, 99999999, getFeld());
						addPruefungFeldUebergreifend(kv122, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV122));
					}
					
				} else {
					final PruefungLaenge kv124 = new PruefungLaenge(0, getFeld());
					addPruefungFeldUebergreifend(kv124, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV124));
				}
			} catch (final ParseException e) {
				LOGGER.log(Level.FINE, e.toString());
			}
		}
	}

}
