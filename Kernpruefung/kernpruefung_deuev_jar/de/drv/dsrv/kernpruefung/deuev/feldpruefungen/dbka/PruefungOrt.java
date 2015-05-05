package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbka;

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
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBKA;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKA;

/**
 * Pruefung fuer das Feld Ort aus dem Baustein DBKA.
 */
public class PruefungOrt extends AbstractFeldPruefung<FeldNameDBKA, FehlerNummerDBKA> {

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
	public PruefungOrt(final Feld<FeldNameDBKA> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNichtLeer ka120 = new PruefungNichtLeer(getFeld());
		addPruefung(ka120, new Fehler<FehlerNummerDBKA>(FehlerNummerDBKA.DBKA120));

		final PruefungMehrfachGleichesSonderOderLeerzeichen ka122 = new PruefungMehrfachGleichesSonderOderLeerzeichen(
				getFeld());
		addPruefung(ka122, new Fehler<FehlerNummerDBKA>(FehlerNummerDBKA.DBKA122));

		final PruefungDreiGleicheAnfangsBuchstaben ka124 = new PruefungDreiGleicheAnfangsBuchstaben(getFeld());
		addPruefung(ka124, new Fehler<FehlerNummerDBKA>(FehlerNummerDBKA.DBKA124));

		final PruefungBeginntMitBuchstabe ka126 = new PruefungBeginntMitBuchstabe(getFeld());
		addPruefung(ka126, new Fehler<FehlerNummerDBKA>(FehlerNummerDBKA.DBKA126));

		final PruefungMindestensZweiBuchstaben ka128 = new PruefungMindestensZweiBuchstaben(getFeld());
		addPruefung(ka128, new Fehler<FehlerNummerDBKA>(FehlerNummerDBKA.DBKA128));

		final PruefungBuchstabenZeichen ka130 = new PruefungBuchstabenZeichen(ERLAUBTE_ZEICHEN, getFeld());
		addPruefung(ka130, new Fehler<FehlerNummerDBKA>(FehlerNummerDBKA.DBKA130));

		// Geht auch mit Ziffer, da an diesem Punkt keine Ziffern mehr vorkommen
		// konnen (siehe DBKA130).
		final PruefungVorPunktBuchstabeZiffer ka132 = new PruefungVorPunktBuchstabeZiffer(getFeld());
		addPruefung(ka132, new Fehler<FehlerNummerDBKA>(FehlerNummerDBKA.DBKA132));

		final PruefungEndetMitBuchstabeZeichen ka134 = new PruefungEndetMitBuchstabeZeichen(getFeld(), ENDET_MIT);
		addPruefung(ka134, new Fehler<FehlerNummerDBKA>(FehlerNummerDBKA.DBKA134));
	}
}
