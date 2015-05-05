package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumLogischRichtig;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumNachDatum;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDatumSelberMonat;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBKV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKV;

/**
 * Pruefung fuer das Feld Zeitraum-Ende aus dem Baustein DBKV.
 */
public class PruefungZeitraumEnde extends AbstractFeldPruefung<FeldNameDBKV, FehlerNummerDBKV> {

	private final Feld<FeldNameDBKV> feldZeitraumBeginn;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldZeitraumBeginn
	 *            Feld ZeitraumBeginn aus DBKV
	 */
	public PruefungZeitraumEnde(final Feld<FeldNameDBKV> feld, final Feld<FeldNameDBKV> feldZeitraumBeginn) {
		super(feld);

		this.feldZeitraumBeginn = feldZeitraumBeginn;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch kv050 = new PruefungNumerisch(getFeld(), true);
		addPruefung(kv050, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV050));

		final PruefungDatumLogischRichtig kv052 = new PruefungDatumLogischRichtig(getFeld());
		addPruefung(kv052, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV052));
	}

	@Override
	public void initialisiereFeldUebergreifendePruefungen() throws UngueltigeDatenException {

		final List<FehlerNummerDBKV> fehlernummern = new LinkedList<FehlerNummerDBKV>();
		fehlernummern.add(FehlerNummerDBKV.DBKV054);
		fehlernummern.add(FehlerNummerDBKV.DBKV056);

		if (isPruefbar(fehlernummern, feldZeitraumBeginn)) {
			final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.GERMANY);
			dateFormat.setLenient(false);
			Date datumZeitraumBeginn;
			try {
				datumZeitraumBeginn = dateFormat.parse(feldZeitraumBeginn.getTrimmedValue());

				final PruefungDatumNachDatum kv054 = new PruefungDatumNachDatum(getFeld(), datumZeitraumBeginn);
				addPruefungFeldUebergreifend(kv054, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV054));

				final PruefungDatumSelberMonat kv056 = new PruefungDatumSelberMonat(getFeld(), datumZeitraumBeginn);
				addPruefungFeldUebergreifend(kv056, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV056));
			} catch (final ParseException e) {
				LOGGER.log(Level.FINE, e.toString());
			}
		}
	}
}
