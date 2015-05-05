package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.utils.DateUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBKV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKV;

/**
 * Pruefung fuer das Feld Beitragsbemessungsgrundlage Kurzarbeitergeld aus dem
 * Baustein DBKV.
 */
public class PruefungBeitragsBemGrundlageKurzArbeitgergeld extends AbstractFeldPruefung<FeldNameDBKV, FehlerNummerDBKV> {

	private static final Date ENDE_2014 = new GregorianCalendar(2014, 11, 31).getTime();

	private static final List<String> GRUNDSTELLUNG = Arrays.asList("00000000");
	
	private final Feld<FeldNameDBKV> feldZeitraumBeginn;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungBeitragsBemGrundlageKurzArbeitgergeld(final Feld<FeldNameDBKV> feld, final Feld<FeldNameDBKV> feldZeitraumBeginn) {
		super(feld);
		
		this.feldZeitraumBeginn = feldZeitraumBeginn;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch kv080 = new PruefungNumerisch(getFeld(), true);
		addPruefung(kv080, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV080));
	}

	@Override
	public void initialisiereFeldUebergreifendePruefungen() throws UngueltigeDatenException {
		final Date zeitraumBeginn = DateUtils.parseDate(feldZeitraumBeginn);
		if (isPruefbar(FehlerNummerDBKV.DBKV082, feldZeitraumBeginn) && zeitraumBeginn.after(ENDE_2014)) {
			final PruefungInList kv082 = new PruefungInList(GRUNDSTELLUNG, getFeld());
			addPruefungFeldUebergreifend(kv082, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV082));
		}
	}

}
