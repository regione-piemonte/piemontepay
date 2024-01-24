/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class Applicazione implements java.io.Serializable {

	/// Field [idApplicazione]
	private java.lang.String _idApplicazione = null;

	public void setIdApplicazione(java.lang.String val) {
		_idApplicazione = val;
	}

	public java.lang.String getIdApplicazione() {
		return _idApplicazione;
	}

	/// Field [nomeApplicazione]
	private java.lang.String _nomeApplicazione = null;

	public void setNomeApplicazione(java.lang.String val) {
		_nomeApplicazione = val;
	}

	public java.lang.String getNomeApplicazione() {
		return _nomeApplicazione;
	}

	/// Field [referenteCSI]
	private java.lang.String _referenteCSI = null;

	public void setReferenteCSI(java.lang.String val) {
		_referenteCSI = val;
	}

	public java.lang.String getReferenteCSI() {
		return _referenteCSI;
	}

	/// Field [dataInizioValidita]
	private java.lang.String _dataInizioValidita = null;

	public void setDataInizioValidita(java.lang.String val) {
		_dataInizioValidita = val;
	}

	public java.lang.String getDataInizioValidita() {
		return _dataInizioValidita;
	}

	/// Field [dataFineValidita]
	private java.lang.String _dataFineValidita = null;

	public void setDataFineValidita(java.lang.String val) {
		_dataFineValidita = val;
	}

	public java.lang.String getDataFineValidita() {
		return _dataFineValidita;
	}

	/// Field [cliente]
	private java.lang.String _cliente = null;

	public void setCliente(java.lang.String val) {
		_cliente = val;
	}

	public java.lang.String getCliente() {
		return _cliente;
	}

	/// Field [progetto]
	private java.lang.String _progetto = null;

	public void setProgetto(java.lang.String val) {
		_progetto = val;
	}

	public java.lang.String getProgetto() {
		return _progetto;
	}

	/// Field [emailEsercente]
	private java.lang.String _emailEsercente = null;

	public void setEmailEsercente(java.lang.String val) {
		_emailEsercente = val;
	}

	public java.lang.String getEmailEsercente() {
		return _emailEsercente;
	}

	/// Field [note]
	private java.lang.String _note = null;

	public void setNote(java.lang.String val) {
		_note = val;
	}

	public java.lang.String getNote() {
		return _note;
	}

	/// Field [applicationurlback]
	private java.lang.String _applicationurlback = null;

	public void setApplicationurlback(java.lang.String val) {
		_applicationurlback = val;
	}

	public java.lang.String getApplicationurlback() {
		return _applicationurlback;
	}

	/// Field [applicationurlresponseko]
	private java.lang.String _applicationurlresponseko = null;

	public void setApplicationurlresponseko(java.lang.String val) {
		_applicationurlresponseko = val;
	}

	public java.lang.String getApplicationurlresponseko() {
		return _applicationurlresponseko;
	}

	/// Field [applicationurlresponseok]
	private java.lang.String _applicationurlresponseok = null;

	public void setApplicationurlresponseok(java.lang.String val) {
		_applicationurlresponseok = val;
	}

	public java.lang.String getApplicationurlresponseok() {
		return _applicationurlresponseok;
	}

	/// Field [mail2Buyerko]
	private boolean _mail2Buyerko = false;

	public void setMail2Buyerko(boolean val) {
		_mail2Buyerko = val;
	}

	public boolean getMail2Buyerko() {
		return _mail2Buyerko;
	}

	/// Field [mail2Buyerok]
	private boolean _mail2Buyerok = false;

	public void setMail2Buyerok(boolean val) {
		_mail2Buyerok = val;
	}

	public boolean getMail2Buyerok() {
		return _mail2Buyerok;
	}

	/// Field [mail2Esercko]
	private boolean _mail2Esercko = false;

	public void setMail2Esercko(boolean val) {
		_mail2Esercko = val;
	}

	public boolean getMail2Esercko() {
		return _mail2Esercko;
	}

	/// Field [mail2Esercok]
	private boolean _mail2Esercok = false;

	public void setMail2Esercok(boolean val) {
		_mail2Esercok = val;
	}

	public boolean getMail2Esercok() {
		return _mail2Esercok;
	}

	/// Field [mail2Sysok]
	private boolean _mail2Sysok = false;

	public void setMail2Sysok(boolean val) {
		_mail2Sysok = val;
	}

	public boolean getMail2Sysok() {
		return _mail2Sysok;
	}

	/// Field [mail2Sysko]
	private boolean _mail2Sysko = false;

	public void setMail2Sysko(boolean val) {
		_mail2Sysko = val;
	}

	public boolean getMail2Sysko() {
		return _mail2Sysko;
	}

	/// Field [numeroVerde]
	private java.lang.String _numeroVerde = null;

	public void setNumeroVerde(java.lang.String val) {
		_numeroVerde = val;
	}

	public java.lang.String getNumeroVerde() {
		return _numeroVerde;
	}

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public Applicazione() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R-522841187) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
