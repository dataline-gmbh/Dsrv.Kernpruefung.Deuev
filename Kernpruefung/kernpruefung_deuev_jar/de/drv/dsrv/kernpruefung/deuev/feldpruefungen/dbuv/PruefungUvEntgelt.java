package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbuv;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNotInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.FeldPruefungStornierungDsme;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBUV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBUV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSME;

/**
 * Pruefung fuer das Feld UV-Entgelt aus dem Baustein DBUV.
 */
public class PruefungUvEntgelt extends AbstractFeldPruefung<FeldNameDBUV, FehlerNummerDBUV> {

	private static final List<String> UVGD_GRUNDSTELLUNG = Arrays.asList("A07", "A08", "A09", "B01", "B02", "B03",
			"B04", "B05", "B06", "B09", "C01");

	private static final String GD_SONDERMELDUNG = "91";
	private static final List<String> ERLAUBTE_WERTE_91 = Arrays.asList("000000");

	private final Feld<FeldNameDBUV> feldUvgd;

	private final Baustein<FeldNameDSME> bausteinDsme;
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
	 * @param bausteinDsme
	 *            Baustein DSME
	 */
	public PruefungUvEntgelt(final Feld<FeldNameDBUV> feld, final Feld<FeldNameDBUV> feldUvgd,
			final Baustein<FeldNameDBME> bausteinDbme, final Baustein<FeldNameDSME> bausteinDsme) {
		super(feld);

		this.feldUvgd = feldUvgd;
		this.bausteinDsme = bausteinDsme;
		this.bausteinDbme = bausteinDbme;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch uv180 = new PruefungNumerisch(getFeld(), true);
		addPruefung(uv180, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV180));
	}

	@Override
	public void initialisiereBausteinUebergreifendePruefungen() throws UngueltigeDatenException {
		final List<FehlerNummerDBUV> fehlernummern = new LinkedList<FehlerNummerDBUV>();
		fehlernummern.add(FehlerNummerDBUV.DBUV182);
		fehlernummern.add(FehlerNummerDBUV.DBUV183);
		fehlernummern.add(FehlerNummerDBUV.DBUV184);
		fehlernummern.add(FehlerNummerDBUV.DBUV185);

		if (bausteinDbme == null || isPruefbar(fehlernummern, bausteinDbme.getFeld(FeldNameDBME.KENNZ_STORNO))) {

			final FeldPruefungStornierungDsme stornierung = new FeldPruefungStornierungDsme(bausteinDbme);
			if (!stornierung.isStornierungDbme()) {
				if (isPruefbar(FehlerNummerDBUV.DBUV182, bausteinDsme.getFeld(FeldNameDSME.ABGABEGRUND))
						&& bausteinDsme.getFeld(FeldNameDSME.ABGABEGRUND).getTrimmedValue()
								.equals(GD_SONDERMELDUNG)) {
					final PruefungNotInList uv182 = new PruefungNotInList(ERLAUBTE_WERTE_91, getFeld());
					addPruefungBausteinUebergreifend(uv182, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV182));
				}

				final List<FehlerNummerDBUV> fehlernummernUvgd = new LinkedList<FehlerNummerDBUV>();
				fehlernummernUvgd.add(FehlerNummerDBUV.DBUV183);
				fehlernummernUvgd.add(FehlerNummerDBUV.DBUV184);
				fehlernummernUvgd.add(FehlerNummerDBUV.DBUV185);

				if (isPruefbar(fehlernummernUvgd, feldUvgd)) {
					if (UVGD_GRUNDSTELLUNG.contains(feldUvgd.getTrimmedValue())) {
						final PruefungInList uv183 = new PruefungInList(ERLAUBTE_WERTE_91, getFeld());
						addPruefungBausteinUebergreifend(uv183, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV183));
					}

					if ("000000".equals(getFeld().getTrimmedValue())) {
						final PruefungNichtLeer uv184a = new PruefungNichtLeer(feldUvgd);
						addPruefungBausteinUebergreifend(uv184a, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV184));

						final PruefungInList uv184 = new PruefungInList(UVGD_GRUNDSTELLUNG, feldUvgd, true);
						addPruefungBausteinUebergreifend(uv184, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV184));
					} else if (feldUvgd.getTrimmedValue().length() > 0) {
						final PruefungNotInList uv185 = new PruefungNotInList(UVGD_GRUNDSTELLUNG, feldUvgd);
						addPruefungBausteinUebergreifend(uv185, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV185));
					}
				}
			}
		}
	}
}
