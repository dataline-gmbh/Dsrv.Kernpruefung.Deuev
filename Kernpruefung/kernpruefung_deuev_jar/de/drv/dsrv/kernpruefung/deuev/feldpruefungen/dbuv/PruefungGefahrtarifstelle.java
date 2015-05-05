package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbuv;

import java.util.LinkedList;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.FeldPruefungStornierungDsme;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBUV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBUV;

/**
 * Pruefung fuer das Feld Gefahrtarifstellte aus dem Baustein DBUV.
 */
public class PruefungGefahrtarifstelle extends AbstractFeldPruefung<FeldNameDBUV, FehlerNummerDBUV> {

	private final Feld<FeldNameDBUV> feldBbnrGts;
	private final Baustein<FeldNameDBME> bausteinDbme;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldBbnrGts
	 *            Feld Betriebsnummer-Gefahrentarif aus DBVU
	 * @param bausteinDbme
	 *            Baustein DBME
	 */
	public PruefungGefahrtarifstelle(final Feld<FeldNameDBUV> feld, final Feld<FeldNameDBUV> feldBbnrGts,
			final Baustein<FeldNameDBME> bausteinDbme) {
		super(feld);

		this.feldBbnrGts = feldBbnrGts;
		this.bausteinDbme = bausteinDbme;
	}

	@Override
	public void initialisiereBausteinUebergreifendePruefungen() throws UngueltigeDatenException {
		final List<FehlerNummerDBUV> fehlernummern = new LinkedList<FehlerNummerDBUV>();
		fehlernummern.add(FehlerNummerDBUV.DBUV160);
		fehlernummern.add(FehlerNummerDBUV.DBUV161);

		if (bausteinDbme == null
				|| isPruefbar(fehlernummern, bausteinDbme.getFeld(FeldNameDBME.KENNZ_STORNO))) {

			final FeldPruefungStornierungDsme stornierung = new FeldPruefungStornierungDsme(bausteinDbme);
			if (!stornierung.isStornierungDbme()) {
				if (feldBbnrGts.getTrimmedValue().length() > 0) {
					final PruefungNichtLeer uv160 = new PruefungNichtLeer(getFeld());
					addPruefungBausteinUebergreifend(uv160, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV160));
				}

				if (getFeld().getTrimmedValue().length() > 0) {
					final PruefungNichtLeer uv161 = new PruefungNichtLeer(feldBbnrGts);
					addPruefungBausteinUebergreifend(uv161, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV161));
				}
			}
		}
	}
}
