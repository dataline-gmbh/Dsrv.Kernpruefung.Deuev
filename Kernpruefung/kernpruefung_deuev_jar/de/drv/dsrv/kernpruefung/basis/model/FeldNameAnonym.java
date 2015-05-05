package de.drv.dsrv.kernpruefung.basis.model;

/**
 * Repraesentiert den Namen eines anonymen Feldes. Diese Komponente wird
 * verwendet, um Pruefungen auf Feldern ohne speziellen Typ ausfuehren zu
 * koennen.
 */
public class FeldNameAnonym implements FeldName {

	@Override
	public String getName() {
		return "";
	}

	@Override
	public String getKurzName() {
		return "";
	}


}
