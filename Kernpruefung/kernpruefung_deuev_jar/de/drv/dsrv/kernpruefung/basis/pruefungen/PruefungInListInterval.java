package de.drv.dsrv.kernpruefung.basis.pruefungen;

import java.util.List;

import de.drv.dsrv.kernpruefung.basis.model.Feld;
import de.drv.dsrv.kernpruefung.basis.model.FeldName;

/**
 * Validiert, ob der uebergebene Wert in einem der Intervalle enthalten ist. Die
 * Intervalle, welche einen gueltigen Wertebereich kennzeichnen, werden dabei in
 * einer Liste angegeben.<br/>
 * <br/>
 * <b>Beachte:</b> Fuehrende Nullen bei der Angabe eines Wertes im Feld werden
 * bei der Pruefung ignoriert. <br/>
 * <br/>
 * Auch Intervalle [x,x] bzw. einzelne Werte koennen geprueft werden, indem im
 * Listenparameter ein einzelner Wert an den Array uebergeben wird.
 */
public class PruefungInListInterval extends AbstractPruefung {

	private final transient List<int[]> pruefIntervalle;

	public PruefungInListInterval(final List<int[]> pruefIntervalle, Feld<? extends FeldName> feld) {
		super(feld);
		this.pruefIntervalle = pruefIntervalle;
	}

	@Override
	public boolean pruefe() throws UngueltigeDatenException {

		boolean ergebnis = false;

		if (getFeld() != null && getFeld().getTrimmedValue() != null) {

			for (int[] aktuellesIntervall : pruefIntervalle) {

				final int minBereich = aktuellesIntervall[0];
				int maxBereich = minBereich;

				if (aktuellesIntervall.length > 1) {
					maxBereich = aktuellesIntervall[1];
				}

				final PruefungInterval aktuelleIntervallPruefung = new PruefungInterval(minBereich, maxBereich,
						this.getFeld());
				ergebnis = ergebnis || aktuelleIntervallPruefung.pruefe();
			}

		} else {
			throw new UngueltigeDatenException(
					"Fehler in der Pruefung >Wert ist in einem der Intervalle der Intervall-Liste enthalten< "
							+ "Wert des Feldes fehlt.");
		}


		return ergebnis;
	}

}
