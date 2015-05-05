package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Validiert, ob das uebergebene Feld nur aus den erlaubten Zeichen (pattern)
 * besteht. Kann z.B. gebraucht werden, wenn nur Grossbuchstaben ohne Umlaute
 * erlaubt sind ([A-Z]). Fehlernummer DSME082.
 */
public class PruefungPattern extends AbstractPruefung {

	private final String pattern;
	private final boolean sollTrimmen;

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld
	 * @param pattern
	 *            Pattern mit den erlaubten Zeichen
	 */
	public PruefungPattern(final Feld<? extends FeldName> feld, final String pattern) {
		this(feld, pattern, true);
	}

	/**
	 * Konstruktor.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld
	 * @param pattern
	 *            Pattern mit den erlaubten Zeichen
	 * @param sollTrimmen
	 *            <code>true</code> legt fest, dass die Pruefung auf dem
	 *            getrimmten Feldwert durchgefuehrt wird. <code>false</code>
	 *            legt fest, dass die Pruefung anhand des ungetrimmten
	 *            Feldwertes durchgefuehrt wird.
	 */
	public PruefungPattern(final Feld<? extends FeldName> feld, final String pattern, boolean sollTrimmen) {
		super(feld);
		this.sollTrimmen = sollTrimmen;
		this.pattern = pattern;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {
		if (getFeld() == null || getFeld().getTrimmedValue() == null || pattern == null) {
			throw new UngueltigeDatenException("Mind. 1 der uebergebenen Parameter ist null.");
		}

		final String matchString = (sollTrimmen) ? getFeld().getTrimmedValue() : getFeld().getValue();

		final Pattern p = Pattern.compile(pattern);
		final Matcher m = p.matcher(matchString);

		return m.matches();
	}

}
