package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbuv;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.FeldPruefungStornierungDsme;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBUV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBUV;

/**
 * Pruefung fuer das Feld Arbeitsstunden aus dem Baustein DBUV.
 */
public class PruefungArbeitsstunden extends AbstractFeldPruefung<FeldNameDBUV, FehlerNummerDBUV> {

	private static final List<String> UVGD_KEINE_STORNIERUNG = Arrays.asList("A07", "A08", "A09", "B01", "B02", "B03",
			"B05", "B09");
	private final Feld<FeldNameDBUV> feldUvgd;

	private final Baustein<FeldNameDBME> bausteinDbme;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldUvgd
	 *            Feld UV-Grund aus DBUV
	 * @param bausteinDbme
	 *            Baustein DBME
	 */
	public PruefungArbeitsstunden(final Feld<FeldNameDBUV> feld, final Feld<FeldNameDBUV> feldUvgd,
			final Baustein<FeldNameDBME> bausteinDbme) {
		super(feld);

		this.feldUvgd = feldUvgd;
		this.bausteinDbme = bausteinDbme;
	}

	@Override
	public void initialisiereBausteinUebergreifendePruefungen() throws UngueltigeDatenException {

		final List<FehlerNummerDBUV> fehlernummern = new LinkedList<FehlerNummerDBUV>();
		fehlernummern.add(FehlerNummerDBUV.DBUV200);
		fehlernummern.add(FehlerNummerDBUV.DBUV202);

		if (bausteinDbme != null && isPruefbar(fehlernummern, bausteinDbme.getFeld(FeldNameDBME.KENNZ_STORNO))) {
			final FeldPruefungStornierungDsme stornierung = new FeldPruefungStornierungDsme(bausteinDbme);
			if (!stornierung.isStornierungDbme()) {
				final PruefungNumerisch uv200 = new PruefungNumerisch(getFeld(), true);
				addPruefungBausteinUebergreifend(uv200, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV200));

				if (isPruefbar(FehlerNummerDBUV.DBUV202, feldUvgd)
						&& UVGD_KEINE_STORNIERUNG.contains(feldUvgd.getTrimmedValue())) {
					// 0000 = Grundstellung
					final PruefungInList uv202 = new PruefungInList(Arrays.asList("0000"), getFeld());
					addPruefungBausteinUebergreifend(uv202, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV202));
				}
			}
		}
	}

}
