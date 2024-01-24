/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class Lingua implements java.io.Serializable {

	/// Field [idGateway]
	private java.lang.String _idGateway = null;

	public void setIdGateway(java.lang.String val) {
		_idGateway = val;
	}

	public java.lang.String getIdGateway() {
		return _idGateway;
	}

	/// Field [idLinguaMdp]
	private java.lang.String _idLinguaMdp = null;

	public void setIdLinguaMdp(java.lang.String val) {
		_idLinguaMdp = val;
	}

	public java.lang.String getIdLinguaMdp() {
		return _idLinguaMdp;
	}

	/// Field [idLinguaGateway]
	private java.lang.String _idLinguaGateway = null;

	public void setIdLinguaGateway(java.lang.String val) {
		_idLinguaGateway = val;
	}

	public java.lang.String getIdLinguaGateway() {
		return _idLinguaGateway;
	}

	/// Field [descLingua]
	private java.lang.String _descLingua = null;

	public void setDescLingua(java.lang.String val) {
		_descLingua = val;
	}

	public java.lang.String getDescLingua() {
		return _descLingua;
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
	public Lingua() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R-1916769276) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
