/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class TipologiaCommissione implements java.io.Serializable {

	/// Field [commissionId]
	private java.lang.String _commissionId = null;

	public void setCommissionId(java.lang.String val) {
		_commissionId = val;
	}

	public java.lang.String getCommissionId() {
		return _commissionId;
	}

	/// Field [commissionDescription]
	private java.lang.String _commissionDescription = null;

	public void setCommissionDescription(java.lang.String val) {
		_commissionDescription = val;
	}

	public java.lang.String getCommissionDescription() {
		return _commissionDescription;
	}

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public TipologiaCommissione() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R-1922962508) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
