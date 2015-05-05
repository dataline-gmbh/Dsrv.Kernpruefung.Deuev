package de.drv.dsrv.kernpruefung.basis.fehler;

import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.BausteinName;
import de.drv.dsrv.kernpruefung.basis.model.DatensatzName;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.FehlerNummer;
import de.drv.dsrv.kernpruefung.basis.wertelisten.FehlertexteManager;
import de.drv.dsrv.kernpruefung.basis.wertelisten.WertelistenException;

/**
 * Abstrakte Klasse fuer den FehlerBausteinGenerator, der die Fehlerbausteine
 * erstellt, die an den Datensatz gehaengt werden.
 */
public abstract class AbstractFehlerBausteinGenerator {

	/**
	 * Position, an der Fehler-Kennzeichnung im Datenbaustein beginnt.
	 */
	public static final int INDEX_FEHLER_KENNZ = 61;
	/**
	 * Position, an der Fehler-Kennzeichnung im Datenbaustein endet.
	 */
	public static final int INDEX_ANZAHL_FEHLER = 62;

	private transient List<Fehler<? extends FehlerNummer>> fehlerListe;
	private final transient FehlertexteManager fehlertexteManager;
	private final transient int maxLaenge;

	/**
	 * Konstruktor.
	 * 
	 * @param fehlerListe
	 *            Liste der Fehler, von der Pruefung erstellt wurde
	 * @param fehlertexteDateiname
	 *            Pfad zu den Fehlertexten
	 * @param maxLaenge
	 *            Maximale Laenge eines Fehlertextes.
	 */
	public AbstractFehlerBausteinGenerator(final List<Fehler<? extends FehlerNummer>> fehlerListe,
			final String fehlertexteDateiname, final int maxLaenge) {
		this.fehlerListe = fehlerListe;
		this.maxLaenge = maxLaenge;
		fehlertexteManager = FehlertexteManager.getInstance(fehlertexteDateiname);
	}

	/**
	 * Erstellt einen Fehlerbaustein, falls in der Pruefung Fehler aufgetreten
	 * sind, und haengt diesen Fehlerbaustein an den ursprueglichen Datensatz.
	 * Sollten Hinweise und keine Fehler auftreten, dann werden die Hinweise als
	 * Fehlerbausteine zurueckgegeben.
	 * 
	 * @param datensatzName
	 *            Der DatensatzName, zugehoerig zum entsprechenden
	 *            SimpleDatensatz.
	 * 
	 * @return SimpleDatensatz mit dem erzeugten Fehlerbaustein, falls Fehler in
	 *         der Pruefung aufgetreten sind.
	 * @throws FehlerTextNichtVorhandenException
	 *             wird ausgeloest, wenn zu einem Fehler kein Fehlertext in der
	 *             Wertelistendatei hinterlegt ist
	 * @throws WertelistenException
	 *             wird ausgeloest, wenn die Datei mit den Fehlertexten nicht
	 *             geladen werden kann
	 */
	public abstract String generiereFehlerbausteine(final DatensatzName datensatzName)
			throws FehlerTextNichtVorhandenException, WertelistenException;

	/**
	 * Erzeugt aus dem uebergebenen Fehler einen Fehlerbaustein.
	 * 
	 * @param fehler
	 *            Fehler, der im Fehlerbaustein enthalten sein soll
	 * @return Fehlerbaustein mit dem uebergebenen Fehler
	 * @throws FehlerTextNichtVorhandenException
	 *             wird ausgeloest, falls zu dem uebergebenen Fehler keine
	 *             Fehlerbeschreibung in den Fehlertexten enthalten ist
	 */
	protected String zuFehlerbaustein(final Fehler<? extends FehlerNummer> fehler)
			throws FehlerTextNichtVorhandenException, WertelistenException {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(BausteinName.DBFE.name());
		final String interneNummer = fehler.getFehlerNummer().getNummer();
		final String fehlertext = fehlertexteManager.getFehlertext(interneNummer);
		if (fehlertext == null) {
			throw new FehlerTextNichtVorhandenException("Fehlertext für die Fehlernummer " + interneNummer + " fehlt.");
		} else {
			stringBuilder.append(fehlertext);

			// Wenn der Fehlertext zu lang ist, auf die Laenge zuschneiden
			if (stringBuilder.length() > maxLaenge) {
				stringBuilder = new StringBuilder(stringBuilder.toString().substring(0, maxLaenge));
			}

			// Fehlertext auf die vorgegebene Laenge anpassen
			while (stringBuilder.length() < maxLaenge) {
				stringBuilder.append(" ");
			}
		}
		return stringBuilder.toString();
	}

	/**
	 * @return Fehlerliste
	 */
	public List<Fehler<? extends FehlerNummer>> getFehlerListe() {
		return fehlerListe;
	}

}
