/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class ExtraAttribute implements java.io.Serializable {

	/// Field [chiave]
	private java.lang.String _chiave = null;

	public void setChiave(java.lang.String val) {
		_chiave = val;
	}

	public java.lang.String getChiave() {
		return _chiave;
	}

	/// Field [nome]
	private java.lang.String _nome = null;

	public void setNome(java.lang.String val) {
		_nome = val;
	}

	public java.lang.String getNome() {
		return _nome;
	}

	/// Field [valore]
	private java.lang.String _valore = null;

	public void setValore(java.lang.String val) {
		_valore = val;
	}

	public java.lang.String getValore() {
		return _valore;
	}

	/// Field [idApplicazione]
	private java.lang.String _idApplicazione = null;

	public void setIdApplicazione(java.lang.String val) {
		_idApplicazione = val;
	}

	public java.lang.String getIdApplicazione() {
		return _idApplicazione;
	}

	/// Field [descrizione]
	private java.lang.String _descrizione = null;

	public void setDescrizione(java.lang.String val) {
		_descrizione = val;
	}

	public java.lang.String getDescrizione() {
		return _descrizione;
	}

	/// Field [gatewayId]
	private java.lang.String _gatewayId = null;

	public void setGatewayId(java.lang.String val) {
		_gatewayId = val;
	}

	public java.lang.String getGatewayId() {
		return _gatewayId;
	}

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public ExtraAttribute() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R1658297198) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
