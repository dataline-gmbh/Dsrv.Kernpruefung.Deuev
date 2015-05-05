package de.drv.dsrv.kernpruefung.basis.model;


/**
 * Repraesentiert Daten eines Fehlers, der von einer Pruefung gemeldet wird.
 * 
 * @param <T>
 *            Konkreter Typ der Fehlernummern, die fuer einen konkreten Baustein
 *            definiert wurde, vom Typ {@link FehlerNummer}.
 */
public class Fehler<T extends FehlerNummer> {

	private final transient T fehlerNummer;
	private transient String fehlerText;
	private FehlerArt fehlerArt;

	/**
	 * Konstruktor.
	 * 
	 * @param fehlerNummer
	 *            die Nummer des Fehlers
	 */
	public Fehler(final T fehlerNummer) {
		this.fehlerNummer = fehlerNummer;
		this.fehlerArt = FehlerArt.FEHLER;
	}

	/**
	 * Liefert programminterne Nummer der Fehlernummer.
	 * 
	 * @return programminterne Nummer der Fehlernummer
	 */
	public FehlerNummer getFehlerNummer() {
		return fehlerNummer;
	}

	/**
	 * Liefert Fehlertext zu der angegebenen Fehlernummer.
	 * 
	 * @return Fehlertext zu der angegebenen Fehlernummer
	 */
	public String getFehlerText() {
		return fehlerText;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fehlerNummer == null) ? 0 : fehlerNummer.hashCode());
		result = prime * result + ((fehlerText == null) ? 0 : fehlerText.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Fehler<?> other = (Fehler<?>) obj;
		if (fehlerNummer == null) {
			if (other.fehlerNummer != null) {
				return false;
			}
		} else if (!fehlerNummer.equals(other.fehlerNummer)) {
			return false;
		}
		if (fehlerText == null) {
			if (other.fehlerText != null) {
				return false;
			}
		} else if (!fehlerText.equals(other.fehlerText)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Fehler [fehlerNummer=" + fehlerNummer + ", fehlerText=" + fehlerText + "]";
	}

	/**
	 * Hier kann gesetzt werden, ob es sich um einen Fehler oder einen Hinweis
	 * handelt. In der Klasse Hinweis wird diese Methode automatisch aufgerufen.
	 * 
	 * @param fehlerArt
	 */
	public void setFehlerArt(final FehlerArt fehlerArt) {
		this.fehlerArt = fehlerArt;
	}

	/**
	 * Gibt "FEHLER" oder "HINWEIS" zurueck.
	 * 
	 * @return "FEHLER" oder "HINWEIS"
	 */
	public FehlerArt getFehlerArt() {
		return this.fehlerArt;
	}
}
