package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbaz;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.feldpruefung.FeldPruefungErgebnis;
import de.drv.dsrv.kernpruefung.basis.model.Baustein;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBAZ;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBAZ;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSAE;

/**
 * Pruefung fuer das Feld Art der Zeit aus dem Baustein DBAZ.
 */
public class PruefungArtDerZeit extends AbstractFeldPruefung<FeldNameDBAZ, FehlerNummerDBAZ> {

	private static final List<String> ZULAESSIGE_ZIFFERN = Arrays.asList("40", "41", "42", "43", "44", "45", "46",
			"51", "52", "54");
	private static final List<String> ZULAESSIG_DSAE = Arrays.asList("50", "51", "52", "53", "54", "55", "56", "57",
			"58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73",
			"74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93",
			"94", "95", "96", "97", "98", "99");
	private static final List<String> LEAT_BUNDESAGENTUR_FUER_ARBEIT = Arrays.asList("40", "41", "42", "43", "44", "45", "46");
	private static final List<String> LEAT_KRANKENKASSE = Arrays.asList("51", "52", "54");
	private static final List<String> LEAT_KOMMUNEN = Arrays.asList("41", "46");
	private static final List<String> VFMM_KRANKENKASSE = Arrays.asList("KVTWL", "KVTRV");

	private final Baustein<FeldNameDSAE> bausteinDsae;
	private final String vfmm;

	private final FeldPruefungErgebnis<FehlerNummerDBAZ> ergebnis;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld
	 * @param bausteinDsae
	 *            Baustein DSAE
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmale)
	 */
	public PruefungArtDerZeit(final Feld<FeldNameDBAZ> feld, final Baustein<FeldNameDSAE> bausteinDsae,
			final String vfmm) {
		super(feld);

		this.bausteinDsae = bausteinDsae;
		this.vfmm = vfmm;

		ergebnis = new FeldPruefungErgebnis<FehlerNummerDBAZ>();
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNichtLeer az020a = new PruefungNichtLeer(getFeld());
		addPruefung(az020a, new Fehler<FehlerNummerDBAZ>(FehlerNummerDBAZ.DBAZ020));

		final PruefungNumerisch az020b = new PruefungNumerisch(getFeld(), true);
		addPruefung(az020b, new Fehler<FehlerNummerDBAZ>(FehlerNummerDBAZ.DBAZ020));

		final PruefungInList az022 = new PruefungInList(ZULAESSIGE_ZIFFERN, getFeld());
		addPruefung(az022, new Fehler<FehlerNummerDBAZ>(FehlerNummerDBAZ.DBAZ022));

		if ("BATRV".equals(vfmm)) {
			final PruefungInList az026 = new PruefungInList(LEAT_BUNDESAGENTUR_FUER_ARBEIT, getFeld());
			addPruefung(az026, new Fehler<FehlerNummerDBAZ>(FehlerNummerDBAZ.DBAZ026));

		} else if ("KTTRV".equals(vfmm)) {
			final PruefungInList az027 = new PruefungInList(LEAT_KOMMUNEN, getFeld());
			addPruefung(az027, new Fehler<FehlerNummerDBAZ>(FehlerNummerDBAZ.DBAZ027));

		} else if (VFMM_KRANKENKASSE.contains(vfmm)) {
			final PruefungInList az028 = new PruefungInList(LEAT_KRANKENKASSE, getFeld());
			addPruefung(az028, new Fehler<FehlerNummerDBAZ>(FehlerNummerDBAZ.DBAZ028));

		}
	}

	@Override
	public void initialisiereBausteinUebergreifendePruefungen() throws UngueltigeDatenException {
		if (isPruefbar(FehlerNummerDBAZ.DBAZ024, bausteinDsae.getFeld(FeldNameDSAE.VSNR))
				&& getFeld().getTrimmedValue().compareTo("52") == 0) {
			final Feld<FeldNameDSAE> feldDsaeVsnr = bausteinDsae.getFeld(FeldNameDSAE.VSNR);
			final String feldDsaeVsnrSeriennummer = feldDsaeVsnr.getTrimmedValue().substring(9, 11);
			if (!ZULAESSIG_DSAE.contains(feldDsaeVsnrSeriennummer)) {
				ergebnis.addFehler(new Fehler<FehlerNummerDBAZ>(FehlerNummerDBAZ.DBAZ024));
			}
		}
	}


	@Override
	public FeldPruefungErgebnis<FehlerNummerDBAZ> pruefeBausteinUebergreifend() throws UngueltigeDatenException {
		FeldPruefungErgebnis<FehlerNummerDBAZ> ergebnis = super.pruefeBausteinUebergreifend();
		if (ergebnis == null || ergebnis.isErfolgreichInklHinweis()) {
			ergebnis = this.ergebnis;
		}

		return ergebnis;
	}
}
