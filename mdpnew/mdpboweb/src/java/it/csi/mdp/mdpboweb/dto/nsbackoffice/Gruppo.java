/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class Gruppo implements java.io.Serializable {

	/// Field [idgroup]
	private java.lang.String _idgroup = null;

	public void setIdgroup(java.lang.String val) {
		_idgroup = val;
	}

	public java.lang.String getIdgroup() {
		return _idgroup;
	}

	/// Field [description]
	private java.lang.String _description = null;

	public void setDescription(java.lang.String val) {
		_description = val;
	}

	public java.lang.String getDescription() {
		return _description;
	}

	/// Field [idRuolo]
	private java.lang.String _idRuolo = null;

	public void setIdRuolo(java.lang.String val) {
		_idRuolo = val;
	}

	public java.lang.String getIdRuolo() {
		return _idRuolo;
	}

	/// Field [applicazioni]
	private java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione> _applicazioni = new java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione>();

	public void setApplicazioni(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione> val) {
		_applicazioni = val;
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione> getApplicazioni() {
		return _applicazioni;
	}

	/// Field [utenti]
	private java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Utente> _utenti = new java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Utente>();

	public void setUtenti(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Utente> val) {
		_utenti = val;
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Utente> getUtenti() {
		return _utenti;
	}

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public Gruppo() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R1329291591) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
