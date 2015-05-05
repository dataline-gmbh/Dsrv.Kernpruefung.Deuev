package de.drv.dsrv.kernpruefung.basis.model;

/**
 * Definiert Methoden fuer die Fehlernummernkreise.
 */
public interface FehlerNummer {

	/**
	 * Liefert programminterne Nummer der Fehlernummer.
	 * 
	 * @return programminterne Nummer der Fehlernummer
	 */
	String getNummer();

	/**
	 * Wrapper fuer die Funktion <code>valueOf</code>.
	 * 
	 * @param fehlerNummer
	 * @return Enumeration passend zur fehlerNummer.
	 */
	FehlerNummer getValueOf(final String fehlerNummer);
}
