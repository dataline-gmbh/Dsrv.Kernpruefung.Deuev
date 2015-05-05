package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.utils.Zeichen;

/**
 * Validiert, ob der uebergebene Wert mit einem aus der Liste der erlaubten
 * Werte uebereinstimmt.<br/>
 * Zudem kann auch validiert werden, ob der uebergebene Wert keinem Wert der
 * Liste entspricht. (Negativtest) <br/>
 * Fehlernummer: NA050.
 */
public class PruefungInListVosa extends AbstractPruefung {

	private final transient List<String> erlaubteWerte;

	/**
	 * Konstruktor.
	 * 
	 * @param erlaubteWerte
	 *            Liste mit zulaessigen Werten
	 * @param feld
	 *            Feld mit dem zu pruefenden Wert
	 */
	public PruefungInListVosa(final List<String> erlaubteWerte, final Feld<?> feld) {
		super(feld);
		this.erlaubteWerte = erlaubteWerte;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {

		boolean erfolgreich = false;

		if (getFeld() != null && getFeld().getTrimmedValue() != null && erlaubteWerte != null) {
			final String feldValue = getFeld().getTrimmedValue().toUpperCase();
			final int indexErstesUngültigesZeichen = getIndexOfErstemUngültigenZeichen(feldValue);
			final String tmpFeld = feldValue.substring(0, indexErstesUngültigesZeichen);;
			
			if (tmpFeld != null && erlaubteWerte.contains(tmpFeld)) {
				erfolgreich = true;
			}
		} else {
			throw new UngueltigeDatenException(
					"Fehler in der Pruefung >Wert stimmt mit einem aus der Liste erlaubte Werte ueberein< "
							+ "Wert des Feldes fehlt oder nicht initialisierte Liste der erlaubten Werte.");
		}

		return erfolgreich ^ false;
	}
	
	private int getIndexOfErstemUngültigenZeichen(final String feldValue){
		int index = feldValue.length();
		
		for (int j = 0; j < feldValue.length(); j++) {
			if (Zeichen.ALL_ALPHABETIC_CHARACTERS_VOSA.indexOf(feldValue.charAt(j)) < 0) {
				index = j;
				break;
			}
		}
		
		return index;
	}
}
