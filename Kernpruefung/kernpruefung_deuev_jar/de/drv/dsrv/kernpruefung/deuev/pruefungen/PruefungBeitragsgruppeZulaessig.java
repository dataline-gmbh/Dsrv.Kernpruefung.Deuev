package de.drv.dsrv.kernpruefung.deuev.pruefungen;

import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;
import de.drv.dsrv.kernpruefung.basis.pruefungen.AbstractPruefung;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;

/**
 * Validiert ob die Kombination das uebergebene Feld den zulaessigen
 * Personengruppenschluessel-/Beitragsgruppenschluesseln entspricht.
 */
public class PruefungBeitragsgruppeZulaessig extends AbstractPruefung {

	// Abmessungen des Feldes
	private static final int INDEX_0 = 0;
	private static final int INDEX_1 = 1;
	private static final int INDEX_2 = 2;
	private static final int INDEX_3 = 3;
	private static final int INDEX_4 = 4;
	private static final int ZUL_LAENGE = 4;

	private final transient List<String> kv;
	private final transient List<String> rv;
	private final transient List<String> alv;
	private final transient List<String> pv;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            das Feld mit den Beitragsgruppen
	 * @param kv
	 *            zulaessige Werte fuer KV
	 * @param rv
	 *            zulaessige Werte fuer RV
	 * @param alv
	 *            zulaessige Werte fuer ALV
	 * @param pv
	 *            zulaessige Werte fuer PV
	 */
	public PruefungBeitragsgruppeZulaessig(final Feld<? extends FeldName> feld, final List<String> kv, final List<String> rv,
			final List<String> alv, final List<String> pv) {
		super(feld);
		this.kv = kv;
		this.rv = rv;
		this.alv = alv;
		this.pv = pv;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		boolean erfolgreich = true;

		if (getFeld().getTrimmedValue().length() == ZUL_LAENGE) {
			final String feldValue = this.getFeld().getTrimmedValue();

			// Krankenversicherung
			final String ersteZiffer = feldValue.substring(INDEX_0, INDEX_1);
			if (!kv.contains(ersteZiffer)) {
				erfolgreich = false;
			}

			// Rentenversicherung
			final String zweiteZiffer = feldValue.substring(INDEX_1, INDEX_2);
			if (!rv.contains(zweiteZiffer)) {
				erfolgreich = false;
			}

			// Arbeitslosenversicherung
			final String dritteZiffer = feldValue.substring(INDEX_2, INDEX_3);
			if (!alv.contains(dritteZiffer)) {
				erfolgreich = false;
			}

			// Pflegeversicherung
			final String vierteZiffer = feldValue.substring(INDEX_3, INDEX_4);
			if (!pv.contains(vierteZiffer)) {
				erfolgreich = false;
			}
		} else {
			erfolgreich = false;
		}

		return erfolgreich;
	}
}
