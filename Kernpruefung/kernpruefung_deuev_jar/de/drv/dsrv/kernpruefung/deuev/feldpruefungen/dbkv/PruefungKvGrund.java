package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungGleicherString;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.utils.DateUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBKV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKV;

/**
 * Pruefung fuer das Feld KV-Grund aus dem Baustein DBKV.
 */
public class PruefungKvGrund extends AbstractFeldPruefung<FeldNameDBKV, FehlerNummerDBKV> {

	// @formatter:off
	// 31.12.2014
	private static final Date ENDE_2014 = new GregorianCalendar(2014, 11, 31).getTime();
	
	private static final List<String> ZULAESSIGE_WERTE = Arrays.asList("00", "01", "02");

	private final Feld<FeldNameDBKV> feldZeitraumBeginn;
	
	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungKvGrund(final Feld<FeldNameDBKV> feld, final Feld<FeldNameDBKV> feldZeitraumBeginn) {
		super(feld);
		
		this.feldZeitraumBeginn = feldZeitraumBeginn;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch kv020 = new PruefungNumerisch(getFeld(), true);
		addPruefung(kv020, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV020));

		final PruefungInList kv022 = new PruefungInList(ZULAESSIGE_WERTE, getFeld());
		addPruefung(kv022, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV022));
	}

	@Override
	public void initialisiereFeldUebergreifendePruefungen() throws UngueltigeDatenException {
		final Date zrbg = DateUtils.parseDate(feldZeitraumBeginn);
		if (isPruefbar(FehlerNummerDBKV.DBKV096, feldZeitraumBeginn) && zrbg.after(ENDE_2014)) {
			final PruefungGleicherString kv024 = new PruefungGleicherString(getFeld(), "00");
			addPruefungFeldUebergreifend(kv024, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV024));
		}
	}
}
