package de.drv.dsrv.kernpruefung.basis.feldpruefung;

import java.util.ArrayList;
import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.FehlerArt;
import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;

/**
 * Repraesentiert Ergebnis der Pruefung fuer ein Feld.
 * 
 * @param <T>
 *            Konkreter Typ der Fehlernummern, die fuer einen konkreten Baustein
 *            definiert wurde, vom Typ {@link FehlerNummer}.
 */
public class FeldPruefungErgebnis<T extends FehlerNummer> {

	private List<Fehler<T>> fehlerListe;
	
	/**
	 * Konstruktor. Erstellt die Fehlerliste.
	 */
	public FeldPruefungErgebnis() {
		this.fehlerListe = new ArrayList<Fehler<T>>();
	}

	/**
	 * Gibt an, ob die Pruefung erfolgreich war oder nicht. Dazu wird die Liste
	 * der Fehler ausgewertet. Sollte ein Hinweis in der Liste sein, zaehlt
	 * dieser als Fehler.
	 * 
	 * @return <code>true</code>, wenn die Fehlerliste nicht initialisert oder
	 *         leer ist
	 */
	public boolean isErfolgreichInklHinweis() {
		return (this.fehlerListe == null || this.fehlerListe.isEmpty());
	}

	/**
	 * Gibt an, ob die Pruefung erfolgreich war oder nicht. Dazu wird die Liste
	 * der Fehler ausgewertet. Sollte ein Hinweis in der Liste sein, zaehlt
	 * dieser NICHT als Fehler.
	 * 
	 * @return <code>true</code>, wenn die Fehlerliste nicht initialisert, leer
	 *         ist oder nur Hinweise enthaelt.
	 */
	public boolean isErfolgreichExklHinweis() {
		boolean erfolgreich = isErfolgreichInklHinweis();

		if (!erfolgreich) {
			erfolgreich = true;

			for (Fehler<T> fehler : fehlerListe) {
				if (fehler.getFehlerArt() == FehlerArt.FEHLER) {
					erfolgreich = false;
					break;
				}
			}
		}

		return erfolgreich;
	}

	/**
	 * Gibt die Liste der Fehler zurueck.
	 * 
	 * @return Liste der Fehler
	 */
	public List<Fehler<T>> getFehlerListe() {
		return fehlerListe;
	}

	/**
	 * Traegt die Liste der Fehler ein.
	 * 
	 * @param fehlerListe
	 *            Liste der Fehler
	 */
	public void setFehlerListe(final List<Fehler<T>> fehlerListe) {
		this.fehlerListe = fehlerListe;
	}

	/**
	 * Fuegt einen Fehler der Liste hinzu.
	 * 
	 * @param fehler
	 *            Fehler, welcher der Liste hinzugefuegt wird
	 */
	public void addFehler(final Fehler<T> fehler) {
		if (this.fehlerListe == null) {
			this.fehlerListe = new ArrayList<Fehler<T>>();
		}

		this.fehlerListe.add(fehler);
	}

	@Override
	public String toString() {
		return "PruefungErgebnis [fehlerListe=" + fehlerListe + "]";
	}
}
