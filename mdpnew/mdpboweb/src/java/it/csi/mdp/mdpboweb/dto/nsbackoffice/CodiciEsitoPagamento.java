/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class CodiciEsitoPagamento implements java.io.Serializable {

	/// Field [idEsitoPagamento]
	private java.lang.String _idEsitoPagamento = null;

	public void setIdEsitoPagamento(java.lang.String val) {
		_idEsitoPagamento = val;
	}

	public java.lang.String getIdEsitoPagamento() {
		return _idEsitoPagamento;
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
	public CodiciEsitoPagamento() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R1646568457) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
