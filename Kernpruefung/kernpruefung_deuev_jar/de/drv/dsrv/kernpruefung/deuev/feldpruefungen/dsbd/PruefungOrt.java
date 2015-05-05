package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dsbd;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBeginntMitBuchstabe;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBuchstabenZeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungDreiGleicheAnfangsBuchstaben;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungEndetMitBuchstabeZeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungMehrfachGleichesSonderOderLeerzeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungMindestensZweiBuchstaben;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNichtLeer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungVorPunktBuchstabeZiffer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.Sonderzeichen;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDSBD;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDSBD;

/**
 * Pruefung fuer das Feld Ort aus dem Baustein DSBD.
 */
public class PruefungOrt extends AbstractFeldPruefung<FeldNameDSBD, FehlerNummerDSBD> {

	private static final List<Character> ERLAUBTE_ZEICHEN = Arrays.asList(Sonderzeichen.FULL_STOP, Sonderzeichen.COMMA,
			Sonderzeichen.SPACE, Sonderzeichen.HYPHEN_MINUS, Sonderzeichen.SOLIDUS, Sonderzeichen.LEFT_PARENTHESIS,
			Sonderzeichen.RIGHT_PARENTHESIS);

	private static final List<Character> ENDET_MIT = Arrays.asList(Sonderzeichen.FULL_STOP,
			Sonderzeichen.RIGHT_PARENTHESIS);

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungOrt(final Feld<FeldNameDSBD> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNichtLeer bd320 = new PruefungNichtLeer(getFeld());
		addPruefung(bd320, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD320));

		final PruefungMehrfachGleichesSonderOderLeerzeichen bd322 = new PruefungMehrfachGleichesSonderOderLeerzeichen(
				getFeld());
		addPruefung(bd322, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD322));

		final PruefungDreiGleicheAnfangsBuchstaben bd324 = new PruefungDreiGleicheAnfangsBuchstaben(getFeld());
		addPruefung(bd324, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD324));

		final PruefungBeginntMitBuchstabe bd326 = new PruefungBeginntMitBuchstabe(getFeld());
		addPruefung(bd326, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD326));

		final PruefungMindestensZweiBuchstaben bd328 = new PruefungMindestensZweiBuchstaben(getFeld());
		addPruefung(bd328, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD328));

		final PruefungBuchstabenZeichen bd330 = new PruefungBuchstabenZeichen(ERLAUBTE_ZEICHEN, getFeld());
		addPruefung(bd330, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD330));

		// Geht auch mit Ziffer, da an diesem Punkt keine Ziffern mehr vorkommen
		// konnen (siehe DSBD330).
		final PruefungVorPunktBuchstabeZiffer bd332 = new PruefungVorPunktBuchstabeZiffer(getFeld());
		addPruefung(bd332, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD332));

		final PruefungEndetMitBuchstabeZeichen bd334 = new PruefungEndetMitBuchstabeZeichen(getFeld(), ENDET_MIT);
		addPruefung(bd334, new Fehler<FehlerNummerDSBD>(FehlerNummerDSBD.DSBD334));
	}

}
