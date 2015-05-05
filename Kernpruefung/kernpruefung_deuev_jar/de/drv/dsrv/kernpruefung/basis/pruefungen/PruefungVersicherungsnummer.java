package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;
import de.drv.dsrv.kernpruefung.basis.model.FeldNameAnonym;
import de.drv.dsrv.kernpruefung.basis.utils.Pruefziffer;

/**
 * Validiert eine Versicherungsnummer.<br/>
 * Die folgenden Richtlinien werden zu diesem Zweck abgeprueft:<br/>
 * <br/>
 * - Die Zeichenkette hat eine Laenge von 12 Zeichen.<br/>
 * - Die Stellen 1 bis 8 bestehen aus Ziffern<br/>
 * - Die Stelle 9 besteht aus einem Grossbuchstaben<br/>
 * - Die Stellen 10 bis 12 bestehen aus Ziffern<br/>
 * - Die Stellen 1 bis 2 beinhalten eine gueltige Bereichsnummer (Vergleich mit
 * Datei)<br/>
 * - Die Stellen 2 bis 8 beinhalten ein gueltiges Geburtsdatum.<br/>
 * - Die Stellen 11 bis 12 beinhalten eine gueltige Pruefziffer.
 */
public class PruefungVersicherungsnummer extends AbstractPruefung {

	private static final int INDEX_NUMMER_START_A = 0;
	private static final int INDEX_NUMMER_ENDE_A = 8;

	private static final int INDEX_BUCHSTABE_START = INDEX_NUMMER_ENDE_A;
	private static final int INDEX_BUCHSTABE_ENDE = 9;

	private static final int INDEX_NUMMER_START_B = INDEX_BUCHSTABE_ENDE;
	private static final int INDEX_NUMMER_ENDE_B = 12;

	private static final int INDEX_BEREICHSNUMMER_START = 0;
	private static final int INDEX_BEREICHSNUMMER_ENDE = 2;

	private static final int INDEX_GEBURTSDATUM_BEGINN = INDEX_BEREICHSNUMMER_ENDE;
	private static final int INDEX_GEBURTSDATUM_ENDE = 8;

	private static final int INDEX_PRUEFZIFFER_START = 11;
	private static final int INDEX_PRUEFZIFFER_ENDE = 12;

	private static final int LAENGE_VSNR = 12;
	private Feld<? extends FeldName> feldGeburtsdatum;
	private final List<String> valideBereichsnummern;

	public PruefungVersicherungsnummer(final Feld<? extends FeldName> feld, List<String> valideBereichsnummern) {
		super(feld);

		this.valideBereichsnummern = valideBereichsnummern;

	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {

		if (getFeld() != null && getFeld().getTrimmedValue() != null) {
			final String feldValue = getFeld().getTrimmedValue();

			if (feldValue.length() != LAENGE_VSNR) {

				return false;

			} else {

				// Pruefung der Belegung mit Ziffern am Anfang der VSNR
				final Feld<FeldNameAnonym> numerisch18 = new Feld<FeldNameAnonym>(feldValue.substring(
						INDEX_NUMMER_START_A,
						INDEX_NUMMER_ENDE_A));
				final PruefungNumerisch pruefeBelegungAnfang = new PruefungNumerisch(numerisch18);

				if (!pruefeBelegungAnfang.pruefe()) {
					return false;
				}

				// Pruefung der Belegung mit einem Buchstaben in der Mitte der
				// VSNR
				final Feld<FeldNameAnonym> grossbuchstabe = new Feld<FeldNameAnonym>(feldValue.substring(
						INDEX_BUCHSTABE_START, INDEX_BUCHSTABE_ENDE));
				final PruefungPattern pruefebelegungMitte = new PruefungPattern(grossbuchstabe, "[A-Z]");

				if (!pruefebelegungMitte.pruefe()) {
					return false;
				}

				// Pruefung der Belegung mit Ziffern am Ende der VSNR
				final Feld<FeldNameAnonym> numerisch1012 = new Feld<FeldNameAnonym>(feldValue.substring(
						INDEX_NUMMER_START_B, INDEX_NUMMER_ENDE_B));
				final PruefungNumerisch pruefeBelegungEnde = new PruefungNumerisch(numerisch1012);

				if (!pruefeBelegungEnde.pruefe()) {
					return false;
				}

				// Vorbereitung zur Pruefung der Inhalte. Das Geburtsdatum muss
				// sich
				// aus gueltigen
				// Werten zusammensetzen.
				feldGeburtsdatum = new Feld<FeldNameAnonym>(feldValue.substring(INDEX_GEBURTSDATUM_BEGINN,
						INDEX_GEBURTSDATUM_ENDE));

				// Pruefung, ob es eine VSNR ist
				if (valideBereichsnummern.contains(feldValue.substring(INDEX_BEREICHSNUMMER_START,
						INDEX_BEREICHSNUMMER_ENDE))) {
					if (!pruefungenVsnr(feldValue)) {
						return false;
					}

				} else {
					return false;
				}
			}

		} else {
			throw new UngueltigeDatenException("Fehler in der Pruefung >Versicherungsnummer< "
					+ "Wert des Feldes fehlt.");
		}

		return true;
	}

	/**
	 * Pruefungen, falls eine Versicherungsnummer vorliegt.
	 * 
	 * @throws UngueltigeDatenException
	 */
	private boolean pruefungenVsnr(String feldValue) throws UngueltigeDatenException {

		final PruefungVersicherungsnummerGeburtsdatum pruefeGeburtsdatum = new PruefungVersicherungsnummerGeburtsdatum(
				feldGeburtsdatum);
		if (!pruefeGeburtsdatum.pruefe()) {
			return false;
		}

		final int errechnetePruefziffer = Pruefziffer.berechnePruefzifferDerVersicherungsnummer(feldValue);
		final Feld<FeldNameAnonym> pruefziffer = new Feld<FeldNameAnonym>(feldValue.substring(INDEX_PRUEFZIFFER_START,
				INDEX_PRUEFZIFFER_ENDE));
		final PruefungInList pruefeAufPruefziffer = new PruefungInList(Arrays.asList("" + errechnetePruefziffer),
				pruefziffer);

		if (!pruefeAufPruefziffer.pruefe()) {
			return false;
		}

		return true;
	}
}
