/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.common;

public class UserInfo implements java.io.Serializable {

	/// Field [nome]
	private java.lang.String _nome = null;

	public void setNome(java.lang.String val) {
		_nome = val;
	}

	public java.lang.String getNome() {
		return _nome;
	}

	/// Field [cognome]
	private java.lang.String _cognome = null;

	public void setCognome(java.lang.String val) {
		_cognome = val;
	}

	public java.lang.String getCognome() {
		return _cognome;
	}

	/// Field [codFisc]
	private java.lang.String _codFisc = null;

	public void setCodFisc(java.lang.String val) {
		_codFisc = val;
	}

	public java.lang.String getCodFisc() {
		return _codFisc;
	}

	/// Field [ente]
	private java.lang.String _ente = null;

	public void setEnte(java.lang.String val) {
		_ente = val;
	}

	public java.lang.String getEnte() {
		return _ente;
	}

	/// Field [ruolo]
	private java.lang.String _ruolo = null;

	public void setRuolo(java.lang.String val) {
		_ruolo = val;
	}

	public java.lang.String getRuolo() {
		return _ruolo;
	}

	/// Field [idIride]
	private java.lang.String _idIride = null;

	public void setIdIride(java.lang.String val) {
		_idIride = val;
	}

	public java.lang.String getIdIride() {
		return _idIride;
	}

	/// Field [codRuolo]
	private java.lang.String _codRuolo = null;

	public void setCodRuolo(java.lang.String val) {
		_codRuolo = val;
	}

	public java.lang.String getCodRuolo() {
		return _codRuolo;
	}

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public UserInfo() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R-579808706) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
