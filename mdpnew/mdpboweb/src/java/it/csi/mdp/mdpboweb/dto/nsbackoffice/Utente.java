/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class Utente implements java.io.Serializable {

	/// Field [idUtente]
	private java.lang.String _idUtente = null;

	public void setIdUtente(java.lang.String val) {
		_idUtente = val;
	}

	public java.lang.String getIdUtente() {
		return _idUtente;
	}

	/// Field [descrUtente]
	private java.lang.String _descrUtente = null;

	public void setDescrUtente(java.lang.String val) {
		_descrUtente = val;
	}

	public java.lang.String getDescrUtente() {
		return _descrUtente;
	}

	/// Field [codFisc]
	private java.lang.String _codFisc = null;

	public void setCodFisc(java.lang.String val) {
		_codFisc = val;
	}

	public java.lang.String getCodFisc() {
		return _codFisc;
	}

	/// Field [email]
	private java.lang.String _email = null;

	public void setEmail(java.lang.String val) {
		_email = val;
	}

	public java.lang.String getEmail() {
		return _email;
	}

	/// Field [pwdservizio]
	private java.lang.String _pwdservizio = null;

	public void setPwdservizio(java.lang.String val) {
		_pwdservizio = val;
	}

	public java.lang.String getPwdservizio() {
		return _pwdservizio;
	}

	/// Field [idGruppo]
	private java.lang.String _idGruppo = null;

	public void setIdGruppo(java.lang.String val) {
		_idGruppo = val;
	}

	public java.lang.String getIdGruppo() {
		return _idGruppo;
	}

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public Utente() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R-403167237) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
