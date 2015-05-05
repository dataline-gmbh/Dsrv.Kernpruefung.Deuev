package de.drv.dsrv.kernpruefung.basis.pruefungen;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Validiert, ob die uebergeben Felder (Datum) im gleichen Monat liegen.
 * Verglichen werden ausschliesslich die Monate. Das Datum im Wert der Felder
 * muss im Format yyyyMMdd uebergeben werden.
 */
public class PruefungDatumGleicherMonat extends AbstractPruefung {

	private static final int MONTHSTART = 4;
	private static final int MONTHEND = 6;
	private static final int LAENGE = 8;

	private final Feld<? extends FeldName> feld2;

	/**
	 * 
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 * @param feld2
	 *            Feld mit dem evtl. gleichen Monat
	 * @throws UngueltigeDatenException
	 */
	public PruefungDatumGleicherMonat(final Feld<? extends FeldName> feld, final Feld<? extends FeldName> feld2)
			throws UngueltigeDatenException {
		super(feld);
		this.feld2 = feld2;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {

		boolean erfolgreich = false;

		if (this.getFeld() != null && getFeld().getTrimmedValue() != null && this.feld2 != null
				&& feld2.getTrimmedValue() != null) {

			if (getFeld().getTrimmedValue().length() >= LAENGE
					&& feld2.getTrimmedValue().length() >= LAENGE
					&& getFeld().getTrimmedValue().substring(MONTHSTART, MONTHEND)
							.equals(feld2.getTrimmedValue().substring(MONTHSTART, MONTHEND))) {
				erfolgreich = true;
			}
		} else {
			throw new UngueltigeDatenException("Fehler in der Pruefung >Uebergebenes Datum liegt im gleichen Monat< "
					+ "Wert des Feldes fehlt oder nicht initialisierter Wert Verarbeitungsdatum.");
		}

		return erfolgreich;
	}

}
