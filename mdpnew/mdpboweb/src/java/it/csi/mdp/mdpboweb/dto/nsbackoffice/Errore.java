/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class Errore implements java.io.Serializable {

	/// Field [idTransazione]
	private java.lang.String _idTransazione = null;

	public void setIdTransazione(java.lang.String val) {
		_idTransazione = val;
	}

	public java.lang.String getIdTransazione() {
		return _idTransazione;
	}

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

	/// Field [cercaDataInizio]
	private java.lang.String _cercaDataInizio = null;

	public void setCercaDataInizio(java.lang.String val) {
		_cercaDataInizio = val;
	}

	public java.lang.String getCercaDataInizio() {
		return _cercaDataInizio;
	}

	/// Field [cercaDataFine]
	private java.lang.String _cercaDataFine = null;

	public void setCercaDataFine(java.lang.String val) {
		_cercaDataFine = val;
	}

	public java.lang.String getCercaDataFine() {
		return _cercaDataFine;
	}

	/// Field [dataTransazione]
	private java.lang.String _dataTransazione = null;

	public void setDataTransazione(java.lang.String val) {
		_dataTransazione = val;
	}

	public java.lang.String getDataTransazione() {
		return _dataTransazione;
	}

	/// Field [idGateway]
	private java.lang.String _idGateway = null;

	public void setIdGateway(java.lang.String val) {
		_idGateway = val;
	}

	public java.lang.String getIdGateway() {
		return _idGateway;
	}

	/// Field [descrGateway]
	private java.lang.String _descrGateway = null;

	public void setDescrGateway(java.lang.String val) {
		_descrGateway = val;
	}

	public java.lang.String getDescrGateway() {
		return _descrGateway;
	}

	/// Field [importoTransazione]
	private double _importoTransazione = 0;

	public void setImportoTransazione(double val) {
		_importoTransazione = val;
	}

	public double getImportoTransazione() {
		return _importoTransazione;
	}

	/// Field [commissione]
	private double _commissione = 0;

	public void setCommissione(double val) {
		_commissione = val;
	}

	public double getCommissione() {
		return _commissione;
	}

	/// Field [numeroPagina]
	private int _numeroPagina = 0;

	public void setNumeroPagina(int val) {
		_numeroPagina = val;
	}

	public int getNumeroPagina() {
		return _numeroPagina;
	}

	/// Field [transPerPagina]
	private int _transPerPagina = 0;

	public void setTransPerPagina(int val) {
		_transPerPagina = val;
	}

	public int getTransPerPagina() {
		return _transPerPagina;
	}

	/// Field [totaleTransazioniTrovate]
	private int _totaleTransazioniTrovate = 0;

	public void setTotaleTransazioniTrovate(int val) {
		_totaleTransazioniTrovate = val;
	}

	public int getTotaleTransazioniTrovate() {
		return _totaleTransazioniTrovate;
	}

	/// Field [idErrore]
	private java.lang.String _idErrore = null;

	public void setIdErrore(java.lang.String val) {
		_idErrore = val;
	}

	public java.lang.String getIdErrore() {
		return _idErrore;
	}

	/// Field [dettaglioErrore]
	private java.lang.String _dettaglioErrore = null;

	public void setDettaglioErrore(java.lang.String val) {
		_dettaglioErrore = val;
	}

	public java.lang.String getDettaglioErrore() {
		return _dettaglioErrore;
	}

	/// Field [buyerName]
	private java.lang.String _buyerName = null;

	public void setBuyerName(java.lang.String val) {
		_buyerName = val;
	}

	public java.lang.String getBuyerName() {
		return _buyerName;
	}

	/// Field [buyerNameCF]
	private java.lang.String _buyerNameCF = null;

	public void setBuyerNameCF(java.lang.String val) {
		_buyerNameCF = val;
	}

	public java.lang.String getBuyerNameCF() {
		return _buyerNameCF;
	}

	/// Field [testoErroreCompleto]
	private java.lang.String _testoErroreCompleto = null;

	public void setTestoErroreCompleto(java.lang.String val) {
		_testoErroreCompleto = val;
	}

	public java.lang.String getTestoErroreCompleto() {
		return _testoErroreCompleto;
	}

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public Errore() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R-43206817) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
