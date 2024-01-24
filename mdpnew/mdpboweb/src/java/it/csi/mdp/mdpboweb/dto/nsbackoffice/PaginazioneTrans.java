/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class PaginazioneTrans implements java.io.Serializable {

	/// Field [totale]
	private int _totale = 0;

	public void setTotale(int val) {
		_totale = val;
	}

	public int getTotale() {
		return _totale;
	}

	/// Field [numeroPagina]
	private int _numeroPagina = 0;

	public void setNumeroPagina(int val) {
		_numeroPagina = val;
	}

	public int getNumeroPagina() {
		return _numeroPagina;
	}

	/// Field [dimensionePagina]
	private int _dimensionePagina = 0;

	public void setDimensionePagina(int val) {
		_dimensionePagina = val;
	}

	public int getDimensionePagina() {
		return _dimensionePagina;
	}

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public PaginazioneTrans() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R-363010407) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
