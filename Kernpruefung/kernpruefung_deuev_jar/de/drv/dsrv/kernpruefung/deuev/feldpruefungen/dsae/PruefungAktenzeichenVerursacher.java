package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsae;

import java.util.Arrays;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungGrossbuchstabeDeutsch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungLaenge;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNotInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSAE;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSAE;

/**
 * Pruefung fuer das Feld Aktenzeichen-Verursacher aus dem Baustein DSAE.
 */
public class PruefungAktenzeichenVerursacher extends AbstractFeldPruefung<FeldNameDSAE, FehlerNummerDSAE> {

	private static final String BATRV = "BATRV";
	private static final int LAENGE_BATRV = 15;
	private final String vfmm;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld
	 * @param vfmm
	 *            OpCode (Verfahrensmerkmale)
	 */
	public PruefungAktenzeichenVerursacher(final Feld<FeldNameDSAE> feld, final String vfmm) {
		super(feld);

		this.vfmm = vfmm;
	}

	@Override
	public void initialisierePruefungen() {
		if (BATRV.compareTo(vfmm) == 0) {
			if (getFeld().getTrimmedValue().length() < LAENGE_BATRV) {
				final PruefungNichtLeer ae160Leer = new PruefungNichtLeer(getFeld());
				addPruefung(ae160Leer, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE160));

				final PruefungLaenge ae160Laenge = new PruefungLaenge(15, getFeld());
				addPruefung(ae160Laenge, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE160));
			} else {
				final Feld<FeldNameDSAE> numerisch1 = new Feld<FeldNameDSAE>(getFeld().getTrimmedValue()
						.substring(0, 8));
				final PruefungNumerisch ae160Numerisch1 = new PruefungNumerisch(numerisch1);
				addPruefung(ae160Numerisch1, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE160));

				final PruefungNotInList ae160Nullen1 = new PruefungNotInList(Arrays.asList("00000000"), numerisch1);
				addPruefung(ae160Nullen1, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE160));

				final Feld<FeldNameDSAE> numerisch2 = new Feld<FeldNameDSAE>(getFeld().getTrimmedValue().substring(9,
						15));
				final PruefungNumerisch ae160Numerisch2 = new PruefungNumerisch(numerisch2);
				addPruefung(ae160Numerisch2, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE160));

				final PruefungNotInList ae160Nullen2 = new PruefungNotInList(Arrays.asList("000000"), numerisch2);
				addPruefung(ae160Nullen2, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE160));

				final Feld<FeldNameDSAE> grossbuchstabe = new Feld<FeldNameDSAE>(getFeld().getTrimmedValue().substring(
						8, 9));
				
				// TODO Damit Java wie Cobol läuft, muss spezielle Prüfung für alle deutschen Großbuchstaben verwendet werden.
				final PruefungGrossbuchstabeDeutsch ae160Grossbuchstabe = new PruefungGrossbuchstabeDeutsch(grossbuchstabe);
				addPruefung(ae160Grossbuchstabe, new Fehler<FehlerNummerDSAE>(FehlerNummerDSAE.DSAE160));
			}
		}
	}
}
