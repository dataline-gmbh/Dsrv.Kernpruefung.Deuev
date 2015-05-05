package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Validiert, ob die uebergeben Felder (Datum) im selben Jahr liegen. Das Datum
 * im Wert der Felder muss im Format yyyyMMdd uebergeben werden.
 */
public class PruefungDatumGleichesJahr extends AbstractPruefung {

	private static final int YEARSTART = 0;
	private static final int YEAREND = 4;
	private static final int LAENGE = 8;

	private final Feld<? extends FeldName> feld2;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 * @param feld2
	 *            Feld mit dem evtl. gleichem Jahr
	 * @throws UngueltigeDatenException
	 */
	public PruefungDatumGleichesJahr(final Feld<? extends FeldName> feld, final Feld<? extends FeldName> feld2)
			throws UngueltigeDatenException {
		super(feld);
		this.feld2 = feld2;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {

		boolean erfolgreich = false;

		if (this.getFeld() != null && getFeld().getTrimmedValue() != null && feld2 != null
				&& feld2.getTrimmedValue() != null) {

			if (getFeld().getTrimmedValue().length() >= LAENGE
					&& feld2.getTrimmedValue().length() >= LAENGE
					&& getFeld().getTrimmedValue().substring(YEARSTART, YEAREND)
							.equals(feld2.getTrimmedValue().substring(YEARSTART, YEAREND))) {
				erfolgreich = true;
			}
		} else {
			throw new UngueltigeDatenException("Fehler in der Pruefung >Uebergebenes Datum liegt im gleichen Jahr< "
					+ "Wert des Feldes fehlt oder nicht initialisierter Wert Verarbeitungsdatum.");
		}

		return erfolgreich;
	}

}
