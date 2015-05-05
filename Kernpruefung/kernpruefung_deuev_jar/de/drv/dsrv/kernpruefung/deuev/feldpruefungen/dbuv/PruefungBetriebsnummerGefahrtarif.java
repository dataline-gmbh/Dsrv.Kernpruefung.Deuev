package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbuv;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungGleicherString;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.FeldPruefungStornierungDsme;
import de.drv.dsrv.kernpruefung.deuev.feldpruefungen.utils.DateUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBUV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBME;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBUV;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.ListenTypDeuev;
import de.drv.dsrv.kernpruefung.deuev.wertelisten.WertelistenVerwaltungDeuev;

/**
 * Pruefung fuer das Feld Betriebsnummer Gefahrtarif aus dem Baustein DBUV.
 */
public class PruefungBetriebsnummerGefahrtarif extends AbstractFeldPruefung<FeldNameDBUV, FehlerNummerDBUV> {

	// @formatter:off
	// 31.12.2013
	private static final Date ENDE_2013 = new GregorianCalendar(2013, 11, 31).getTime(); // SUPPRESS
																							// CHECKSTYLE
																							// MagicNumber
	// @formatter:on

	private static final List<String> UVG_GRUNDSTELLUNG = Arrays.asList("A07", "A08", "A09", "B01", "B02", "B03", "C01", "C06");
	private static final List<String> UVG_GRUNDSTELLUNG2 = Arrays.asList("A07", "A08", "A09");
	private static final List<String> UNGLEICH_BBNR_UV = Arrays.asList("14066582", "15087927", "29036720", "42884688", "44888436", "62279404", "67350937", "87661138", "87661183", "63800761");

	private final Feld<FeldNameDBUV> feldUvgd;
	private final Feld<FeldNameDBUV> feldBbnrUv;
	private final List<String> bbnrAnlage20;
	private final Baustein<FeldNameDBME> bausteinDbme;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldUvgd
	 *            Feld UV-Grund aus DBUV
	 * @param feldUvgd
	 *            Feld BBNR-UV aus DBUV
	 * @param bausteinDbme
	 *            Baustein DBME
	 */
	public PruefungBetriebsnummerGefahrtarif(final Feld<FeldNameDBUV> feld, final Feld<FeldNameDBUV> feldUvgd, final Feld<FeldNameDBUV> feldBbnrUv, final Baustein<FeldNameDBME> bausteinDbme) {
		super(feld);

		this.feldUvgd = feldUvgd;
		this.feldBbnrUv = feldBbnrUv;
		this.bbnrAnlage20 = WertelistenVerwaltungDeuev.getInstance().getWerteliste(ListenTypDeuev.A20_BETRIEBSNUMMER_UVTRAEGER);
		this.bausteinDbme = bausteinDbme;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		if (getFeld().getTrimmedValue().length() > 0) {
			final PruefungInList uv140 = new PruefungInList(bbnrAnlage20, getFeld());
			addPruefung(uv140, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV140));
		}
	}

	@Override
	public void initialisiereBausteinUebergreifendePruefungen() throws UngueltigeDatenException {

		final List<FehlerNummerDBUV> fehlernummern = new LinkedList<FehlerNummerDBUV>();
		fehlernummern.add(FehlerNummerDBUV.DBUV142);
		fehlernummern.add(FehlerNummerDBUV.DBUV144);
		fehlernummern.add(FehlerNummerDBUV.DBUV146);

		if (bausteinDbme == null || isPruefbar(fehlernummern, bausteinDbme.getFeld(FeldNameDBME.KENNZ_STORNO), feldUvgd)) {
			final FeldPruefungStornierungDsme stornierung = new FeldPruefungStornierungDsme(bausteinDbme);
			if (!stornierung.isStornierungDbme()) {
				if (getFeld().getTrimmedValue().length() == 0) {
					final PruefungNichtLeer uv142a = new PruefungNichtLeer(feldUvgd);
					addPruefungBausteinUebergreifend(uv142a, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV142));

					final PruefungInList uv142 = new PruefungInList(UVG_GRUNDSTELLUNG, feldUvgd, true);
					addPruefungBausteinUebergreifend(uv142, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV142));
				}

				if (UVG_GRUNDSTELLUNG2.contains(feldUvgd.getTrimmedValue())) {
					final PruefungLaenge uv144 = new PruefungLaenge(0, getFeld());
					addPruefungBausteinUebergreifend(uv144, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV144));
				} else {
					pruefungDbuv146();
				}
			}
		}
	}

	private void pruefungDbuv146() {
		if (bausteinDbme != null) {
			final Feld<FeldNameDBME> feldDbmeZrbg = bausteinDbme.getFeld(FeldNameDBME.ZEITRAUM_BEGINN);
			if (isPruefbar(FehlerNummerDBUV.DBUV146, feldBbnrUv, feldDbmeZrbg) && getFeld().getTrimmedValue().length() != 0) {
				final Date zrbg = DateUtils.parseDate(feldDbmeZrbg);
				if (ENDE_2013.before(zrbg) && !UNGLEICH_BBNR_UV.contains(feldBbnrUv.getTrimmedValue())) {
					final PruefungGleicherString uv146 = new PruefungGleicherString(getFeld(), feldBbnrUv.getTrimmedValue());
					addPruefungBausteinUebergreifend(uv146, new Fehler<FehlerNummerDBUV>(FehlerNummerDBUV.DBUV146));
				}
			}
		}
	}
}
