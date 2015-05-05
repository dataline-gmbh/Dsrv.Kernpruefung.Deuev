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
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBKV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKV;

/**
 * Pruefung fuer das Feld Beitragsbemessungsgrundlage Entgelt Altersteilzeit aus
 * dem Baustein DBKV.
 */
public class PruefungBeitragsBemGrundlageEntgeltAltersTeilzeit extends
		AbstractFeldPruefung<FeldNameDBKV, FehlerNummerDBKV> {

	private static final Date ENDE_2012 = new GregorianCalendar(2012, 11, 31).getTime();
	private static final Date ENDE_2014 = new GregorianCalendar(2014, 11, 31).getTime();
	
	private final Feld<FeldNameDBKV> feldZrgbKv;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldZrgbKv
	 *            Feld Zeitraumbeginn KV aus DBKV
	 */
	public PruefungBeitragsBemGrundlageEntgeltAltersTeilzeit(final Feld<FeldNameDBKV> feld,
			final Feld<FeldNameDBKV> feldZrgbKv) {
		super(feld);

		this.feldZrgbKv = feldZrgbKv;
	}

	@Override
	public void initialisiereFeldUebergreifendePruefungen() throws UngueltigeDatenException {

		final List<FehlerNummerDBKV> fehlernummern = new LinkedList<FehlerNummerDBKV>();
		fehlernummern.add(FehlerNummerDBKV.DBKV130);
		fehlernummern.add(FehlerNummerDBKV.DBKV132);
		fehlernummern.add(FehlerNummerDBKV.DBKV134);

		if (isPruefbar(fehlernummern, feldZrgbKv)) {
			final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.GERMANY);
			dateFormat.setLenient(false);

			try {
				final Date datumZrgbKv = dateFormat.parse(feldZrgbKv.getTrimmedValue());
				if (datumZrgbKv.after(ENDE_2014)) {
					final PruefungLaenge kv134 = new PruefungLaenge(0, getFeld());
					addPruefungFeldUebergreifend(kv134, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV134));
					
				} else if (datumZrgbKv.after(ENDE_2012)) {
					final PruefungNichtLeer kv130a = new PruefungNichtLeer(getFeld());
					addPruefungFeldUebergreifend(kv130a, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV130));

					final PruefungNumerisch kv130 = new PruefungNumerisch(getFeld(), true);
					addPruefungFeldUebergreifend(kv130, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV130));
				}

				else {
					final PruefungLaenge kv132 = new PruefungLaenge(0, getFeld());
					addPruefungFeldUebergreifend(kv132, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV132));
				}
			} catch (final ParseException e) {
				if (LOGGER.isLoggable(Level.FINE)) {
					LOGGER.log(Level.FINE, e.toString());
				}
			}
		}
	}
}
