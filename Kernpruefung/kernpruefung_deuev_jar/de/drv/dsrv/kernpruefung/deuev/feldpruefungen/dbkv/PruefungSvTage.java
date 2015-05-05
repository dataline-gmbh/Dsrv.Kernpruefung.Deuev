package de.drv.dsrv.kernpruefung.deuev.feldpruefungen.dbkv;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import de.drv.dsrv.kernpruefung.basis.feldpruefung.AbstractFeldPruefung;
import de.drv.dsrv.kernpruefung.basis.model.Fehler;
import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungInterval;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNotInList;
import de.drv.dsrv.kernpruefung.basis.pruefungen.PruefungNumerisch;
import de.drv.dsrv.kernpruefung.basis.pruefungen.UngueltigeDatenException;
import de.drv.dsrv.kernpruefung.deuev.model.fehler.FehlerNummerDBKV;
import de.drv.dsrv.kernpruefung.deuev.model.feld.FeldNameDBKV;

/**
 * Pruefung fuer das Feld SV-Tage aus dem Baustein DBKV.
 */
public class PruefungSvTage extends AbstractFeldPruefung<FeldNameDBKV, FehlerNummerDBKV> {

	private static final List<String> GRUNDSTELLUNG = Arrays.asList("00");
	private final Feld<FeldNameDBKV> feldKennzst;
	private final Feld<FeldNameDBKV> feldLaufendesEntgelt;
	private final Feld<FeldNameDBKV> feldLaufendesEntgeltKv;
	private final Feld<FeldNameDBKV> feldLaufendesEntgeltRv;
	private final Feld<FeldNameDBKV> feldLaufendesEntgeltAv;

	/**
	 * Fuegt fuer jede (externe) Fehlernummer des Feldes die entsprechenden
	 * Pruefungen der Pruefliste hinzu.
	 * 
	 * @param feld
	 *            Das zu pruefende Feld.
	 * @param feldKennzst
	 *            Feld Kennzeichen Storno aus DBKV
	 * @param feldLaufendesEntgelt
	 *            Feld laufendes Entgelt aus DBKV
	 */
	public PruefungSvTage(final Feld<FeldNameDBKV> feld, final Feld<FeldNameDBKV> feldKennzst, final Feld<FeldNameDBKV> feldLaufendesEntgelt, final Feld<FeldNameDBKV> feldLaufendesEntgeltKv, final Feld<FeldNameDBKV> feldLaufendesEntgeltRv,
			final Feld<FeldNameDBKV> feldLaufendesEntgeltAv) {
		super(feld);

		this.feldKennzst = feldKennzst;
		this.feldLaufendesEntgelt = feldLaufendesEntgelt;
		this.feldLaufendesEntgeltKv = feldLaufendesEntgeltKv;
		this.feldLaufendesEntgeltRv = feldLaufendesEntgeltRv;
		this.feldLaufendesEntgeltAv = feldLaufendesEntgeltAv;
	}

	@Override
	public void initialisierePruefungen() throws UngueltigeDatenException {
		final PruefungNumerisch kv030 = new PruefungNumerisch(getFeld(), true);
		addPruefung(kv030, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV030));

		final PruefungInterval kv032 = new PruefungInterval(0, 30, getFeld());
		addPruefung(kv032, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV032));
	}

	@Override
	public void initialisiereFeldUebergreifendePruefungen() throws UngueltigeDatenException {
		if (isPruefbar(FehlerNummerDBKV.DBKV034, feldLaufendesEntgelt, feldKennzst, feldLaufendesEntgeltKv, feldLaufendesEntgeltRv, feldLaufendesEntgeltAv)) {
			try {
				final int laufendesEntgelt = Integer.parseInt(feldLaufendesEntgelt.getTrimmedValue());
				if ((feldKennzst.getTrimmedValue().compareTo("N") == 0) && (laufendesEntgelt > 0)) {
					final PruefungNotInList kv034 = new PruefungNotInList(GRUNDSTELLUNG, getFeld());
					addPruefungFeldUebergreifend(kv034, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV034));
				}
				
				final int laufendesEntgeltKv = Integer.parseInt(feldLaufendesEntgeltKv.getTrimmedValue());
				final int laufendesEntgeltRv = Integer.parseInt(feldLaufendesEntgeltRv.getTrimmedValue());
				final int laufendesEntgeltAv = Integer.parseInt(feldLaufendesEntgeltAv.getTrimmedValue());
				if ((laufendesEntgeltKv > 0) || (laufendesEntgeltRv > 0) || (laufendesEntgeltAv > 0)) {
					final PruefungNotInList kv036 = new PruefungNotInList(GRUNDSTELLUNG, getFeld());
					addPruefungFeldUebergreifend(kv036, new Fehler<FehlerNummerDBKV>(FehlerNummerDBKV.DBKV036));
				}
			} catch (final NumberFormatException e) {
				LOGGER.log(Level.FINE, e.toString());
			}
		}
	}
}
