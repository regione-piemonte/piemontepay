/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class Enti implements java.io.Serializable {

	/// Field [enteId]
	private java.lang.String _enteId = null;

	public void setEnteId(java.lang.String val) {
		_enteId = val;
	}

	public java.lang.String getEnteId() {
		return _enteId;
	}

	/// Field [partitaIva]
	private java.lang.String _partitaIva = null;

	public void setPartitaIva(java.lang.String val) {
		_partitaIva = val;
	}

	public java.lang.String getPartitaIva() {
		return _partitaIva;
	}

	/// Field [descrizione]
	private java.lang.String _descrizione = null;

	public void setDescrizione(java.lang.String val) {
		_descrizione = val;
	}

	public java.lang.String getDescrizione() {
		return _descrizione;
	}

	/// Field [attivo]
	private java.lang.String _attivo = null;

	public void setAttivo(java.lang.String val) {
		_attivo = val;
	}

	public java.lang.String getAttivo() {
		return _attivo;
	}

	/// Field [enteIdSelezionato]
	private java.lang.String _enteIdSelezionato = null;

	public void setEnteIdSelezionato(java.lang.String val) {
		_enteIdSelezionato = val;
	}

	public java.lang.String getEnteIdSelezionato() {
		return _enteIdSelezionato;
	}

	/// Field [stato]
	private java.lang.String _stato = null;

	public void setStato(java.lang.String val) {
		_stato = val;
	}

	public java.lang.String getStato() {
		return _stato;
	}

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public Enti() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R-740198048) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
