package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dban;

import java.util.Arrays;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBeginntEndetBuchstabeZiffer;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungBuchstabenZiffernZeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungMehrfachGleichesSonderOderLeerzeichen;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.basis.utils.Sonderzeichen;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBAN;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBAN;

/**
 * Pruefung fuer das Feld Hausnummer aus dem Baustein DBAN.
 */
public class PruefungHausnummer extends AbstractFeldPruefung<FeldNameDBAN, FehlerNummerDBAN> {

	private static final List<Character> ERLAUBTE_ZEICHEN = Arrays.asList(Sonderzeichen.COMMA, Sonderzeichen.SPACE,
			Sonderzeichen.HYPHEN_MINUS, Sonderzeichen.SOLIDUS, Sonderzeichen.FULL_STOP);

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungHausnummer(final Feld<FeldNameDBAN> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		if (getFeld().getTrimmedValue().length() > 0) {
			final PruefungMehrfachGleichesSonderOderLeerzeichen an170 = new PruefungMehrfachGleichesSonderOderLeerzeichen(
					getFeld());
			addPruefung(an170, new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN170));

			final PruefungBuchstabenZiffernZeichen an174 = new PruefungBuchstabenZiffernZeichen(ERLAUBTE_ZEICHEN,
					getFeld());
			addPruefung(an174, new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN174));

			final PruefungBeginntEndetBuchstabeZiffer an176 = new PruefungBeginntEndetBuchstabeZiffer(getFeld());
			addPruefung(an176, new Fehler<FehlerNummerDBAN>(FehlerNummerDBAN.DBAN176));
		}
	}

}
