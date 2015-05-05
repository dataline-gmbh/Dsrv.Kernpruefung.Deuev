package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbez;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBEZ;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBEZ;

/**
 * Pruefung fuer das Feld Kennzeichen-Rechtskreis aus dem Baustein DBEZ.
 */
public class PruefungKennzeichenRechtskreis extends AbstractFeldPruefung<FeldNameDBEZ, FehlerNummerDBEZ> {

	private static final List<String> ZULAESSIGE_WERTE = Arrays.asList("W", "O");
	private static final List<String> LEAT_W = Arrays.asList("23", "43", "44");
	private static final List<String> LEAT_O = Arrays.asList("25", "26");

	private final Feld<FeldNameDBEZ> feldLeistungsart;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldLeistungsart
	 *            Feld Leistungsart aus dem Baustein DBEZ.
	 */
	public PruefungKennzeichenRechtskreis(final Feld<FeldNameDBEZ> feld, final Feld<FeldNameDBEZ> feldLeistungsart) {
		super(feld);

		this.feldLeistungsart = feldLeistungsart;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNichtLeer ez160a = new PruefungNichtLeer(getFeld());
		addPruefung(ez160a, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ160));

		final PruefungInList ez160b = new PruefungInList(ZULAESSIGE_WERTE, getFeld(), true);
		addPruefung(ez160b, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ160));
	}

	@Override
	public void initialisiereFeldUebergreifendePruefungen() throws UngueltigeDatenException {
		if (isPruefbar(Arrays.asList(FehlerNummerDBEZ.DBEZ164, FehlerNummerDBEZ.DBEZ166), feldLeistungsart)) {
			if (LEAT_O.contains(feldLeistungsart.getTrimmedValue())) {
				final PruefungInList ez164 = new PruefungInList(Arrays.asList("O"), getFeld(), true);
				addPruefungFeldUebergreifend(ez164, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ164));
			}

			else if (LEAT_W.contains(feldLeistungsart.getTrimmedValue())) {
				final PruefungInList ez166 = new PruefungInList(Arrays.asList("W"), getFeld(), true);
				addPruefungFeldUebergreifend(ez166, new Fehler<FehlerNummerDBEZ>(FehlerNummerDBEZ.DBEZ166));
			}
		}
	}
}
