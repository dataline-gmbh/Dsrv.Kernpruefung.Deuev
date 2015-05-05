package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbka;

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
import de.drv.dsrv.kernpruefung.basis.utils.StringUtils;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBKA;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKA;

/**
 * Pruefung fuer das Feld Hausnummer aus dem Baustein DBKA.
 */
public class PruefungHausnummer extends AbstractFeldPruefung<FeldNameDBKA, FehlerNummerDBKA> {

	private static final List<Character> ERLAUBTE_ZEICHEN = Arrays.asList(Sonderzeichen.COMMA, Sonderzeichen.SPACE,
			Sonderzeichen.HYPHEN_MINUS, Sonderzeichen.SOLIDUS, Sonderzeichen.FULL_STOP);

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 */
	public PruefungHausnummer(final Feld<FeldNameDBKA> feld) {
		super(feld);
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		if (StringUtils.isNotBlank(getFeld().getTrimmedValue())) {
			final PruefungMehrfachGleichesSonderOderLeerzeichen bd400 = new PruefungMehrfachGleichesSonderOderLeerzeichen(
					getFeld());
			addPruefung(bd400, new Fehler<FehlerNummerDBKA>(FehlerNummerDBKA.DBKA200));

			final PruefungBuchstabenZiffernZeichen bd402 = new PruefungBuchstabenZiffernZeichen(ERLAUBTE_ZEICHEN,
					getFeld());
			addPruefung(bd402, new Fehler<FehlerNummerDBKA>(FehlerNummerDBKA.DBKA202));

			final PruefungBeginntEndetBuchstabeZiffer bd404 = new PruefungBeginntEndetBuchstabeZiffer(getFeld());
			addPruefung(bd404, new Fehler<FehlerNummerDBKA>(FehlerNummerDBKA.DBKA204));
		}
	}

}
