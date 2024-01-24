/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class Divisa implements java.io.Serializable {

	/// Field [idGateway]
	private java.lang.String _idGateway = null;

	public void setIdGateway(java.lang.String val) {
		_idGateway = val;
	}

	public java.lang.String getIdGateway() {
		return _idGateway;
	}

	/// Field [idDivisaMdp]
	private java.lang.String _idDivisaMdp = null;

	public void setIdDivisaMdp(java.lang.String val) {
		_idDivisaMdp = val;
	}

	public java.lang.String getIdDivisaMdp() {
		return _idDivisaMdp;
	}

	/// Field [idDivisaGateway]
	private java.lang.String _idDivisaGateway = null;

	public void setIdDivisaGateway(java.lang.String val) {
		_idDivisaGateway = val;
	}

	public java.lang.String getIdDivisaGateway() {
		return _idDivisaGateway;
	}

	/// Field [descDivisa]
	private java.lang.String _descDivisa = null;

	public void setDescDivisa(java.lang.String val) {
		_descDivisa = val;
	}

	public java.lang.String getDescDivisa() {
		return _descDivisa;
	}

	/// Field [chiave]
	private java.lang.String _chiave = null;

	public void setChiave(java.lang.String val) {
		_chiave = val;
	}

	public java.lang.String getChiave() {
		return _chiave;
	}

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public Divisa() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R-40514232) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
