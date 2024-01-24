/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class SingoloStatoRPT implements java.io.Serializable {

	/// Field [data]
	private java.lang.String _data = null;

	public void setData(java.lang.String val) {
		_data = val;
	}

	public java.lang.String getData() {
		return _data;
	}

	/// Field [stato]
	private java.lang.String _stato = null;

	public void setStato(java.lang.String val) {
		_stato = val;
	}

	public java.lang.String getStato() {
		return _stato;
	}

	/// Field [descrizione]
	private java.lang.String _descrizione = null;

	public void setDescrizione(java.lang.String val) {
		_descrizione = val;
	}

	public java.lang.String getDescrizione() {
		return _descrizione;
	}

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public SingoloStatoRPT() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R-43283372) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
