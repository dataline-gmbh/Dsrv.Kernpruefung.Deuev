package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtGleicherString;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.utils.DateUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBKV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Pruefung fuer das Feld Kennzeichen Gleitzone aus dem Baustein DBKV.
 */
public class PruefungKennzeichenGleitzone extends AbstractFeldPruefung<FeldNameDBKV, FehlerNummerDBKV> {

	private static final Date ENDE_2014 = new GregorianCalendar(2014, 11, 31).getTime();

	private static final List<String> ZULAESSIGE_WERTE = Arrays.asList("0", "1");
	private static final List<String> UNZULAESSIGE_WERTE_094 = Arrays.asList("102", "121", "122", "123", "103", "105", "107", "111", "127", "127", "141", "144");

	private static final List<String> GRUNDSTELLUNG = Arrays.asList("0");

	private final Feld<FeldNameDBKV> feldZeitraumBeginn;

	private final Feld<FeldNameDBKV> feldKennzst;
	private final Baustein<FeldNameDSME> bausteinDsme;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldKennzst
	 *            Feld KennzeichenStorno aus DBKV
	 */
	public PruefungKennzeichenGleitzone(final Feld<FeldNameDBKV> feld, final Feld<FeldNameDBKV> feldKennzst, final Feld<FeldNameDBKV> feldZeitraumBeginn, final Baustein<FeldNameDSME> bausteinDsme) {
		super(feld);

		this.feldKennzst = feldKennzst;
		this.bausteinDsme = bausteinDsme;
		this.feldZeitraumBeginn = feldZeitraumBeginn;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch kv090 = new PruefungNumerisch(getFeld(), true);
		addPruefung(kv090, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV090));
	}

	@Override
	public void initialisiereFeldUebergreifendePruefungen() throws UngueltigeDatenException {
		if (isPruefbar(FehlerNummerDBKV.DBKV092, feldKennzst) && feldKennzst.getTrimmedValue().compareTo("N") == 0) {
			final PruefungInList kv092 = new PruefungInList(ZULAESSIGE_WERTE, getFeld());
			addPruefungFeldUebergreifend(kv092, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV092));
		}

		final Date zeitraumBeginn = DateUtils.parseDate(feldZeitraumBeginn);
		if (isPruefbar(FehlerNummerDBKV.DBKV096, feldZeitraumBeginn) && zeitraumBeginn.after(ENDE_2014)) {
			final PruefungInList kv082 = new PruefungInList(GRUNDSTELLUNG, getFeld());
			addPruefungFeldUebergreifend(kv082, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV096));
		}
	}

	@Override
	public void initialisiereBausteinUebergreifendePruefungen() {
		final Feld<FeldNameDSME> feldPersgr = bausteinDsme.getFeld(FeldNameDSME.PERSONENGRUPPE);

		if (isPruefbar(FehlerNummerDBKV.DBKV094, feldPersgr) && UNZULAESSIGE_WERTE_094.contains(feldPersgr.getTrimmedValue())) {
			final PruefungNichtGleicherString kv094 = new PruefungNichtGleicherString(getFeld(), "1");
			addPruefungBausteinUebergreifend(kv094, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV094));
		}
	}
}
