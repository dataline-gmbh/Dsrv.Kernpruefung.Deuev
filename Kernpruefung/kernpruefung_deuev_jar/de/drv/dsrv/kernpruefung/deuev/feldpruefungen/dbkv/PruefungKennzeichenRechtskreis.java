package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBKV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKV;

/**
 * Pruefung fuer das Feld Kennzeichen Rechtskreis aus dem Baustein DBKV.
 */
public class PruefungKennzeichenRechtskreis extends AbstractFeldPruefung<FeldNameDBKV, FehlerNummerDBKV> {

	// @formatter:off
	private static final Date ENDE_2012 = new GregorianCalendar(2012, 11, 31).getTime(); // SUPPRESS CHECKSTYLE MagicNumber
	// @formatter:on

	private static final List<String> ERLAUBTE_ZEICHEN_HEUTE = Arrays.asList("W", "O");
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
	public PruefungKennzeichenRechtskreis(final Feld<FeldNameDBKV> feld, final Feld<FeldNameDBKV> feldZrgbKv) {
		super(feld);

		this.feldZrgbKv = feldZrgbKv;
	}

	@Override
	public void initialisiereFeldUebergreifendePruefungen() throws UngueltigeDatenException {

		final List<FehlerNummerDBKV> fehlernummern = new LinkedList<FehlerNummerDBKV>();
		fehlernummern.add(FehlerNummerDBKV.DBKV150);
		fehlernummern.add(FehlerNummerDBKV.DBKV152);

		if (isPruefbar(fehlernummern, feldZrgbKv)) {
			final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.GERMANY);
			dateFormat.setLenient(false);

			try {
				final Date datumZrgbKv = dateFormat.parse(feldZrgbKv.getTrimmedValue());
				if (datumZrgbKv.after(ENDE_2012)) {
					final PruefungNichtLeer kv150a = new PruefungNichtLeer(getFeld());
					addPruefungFeldUebergreifend(kv150a, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV150));

					final PruefungInList kv150 = new PruefungInList(ERLAUBTE_ZEICHEN_HEUTE, getFeld(), true);
					addPruefungFeldUebergreifend(kv150, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV150));
				}

				else {
					final PruefungLaenge kv152 = new PruefungLaenge(0, getFeld());
					addPruefungFeldUebergreifend(kv152, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV152));
				}
			} catch (final ParseException e) {
				LOGGER.log(Level.FINE, e.toString());
			}
		}
	}

}
