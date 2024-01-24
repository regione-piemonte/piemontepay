/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class Transazione implements java.io.Serializable {

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

	/// Field [codStato]
	private long _codStato = 0;

	public void setCodStato(long val) {
		_codStato = val;
	}

	public long getCodStato() {
		return _codStato;
	}

	/// Field [descrStato]
	private java.lang.String _descrStato = null;

	public void setDescrStato(java.lang.String val) {
		_descrStato = val;
	}

	public java.lang.String getDescrStato() {
		return _descrStato;
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

	/// Field [dataInizio]
	private java.lang.String _dataInizio = null;

	public void setDataInizio(java.lang.String val) {
		_dataInizio = val;
	}

	public java.lang.String getDataInizio() {
		return _dataInizio;
	}

	/// Field [dataUltimaOperazione]
	private java.lang.String _dataUltimaOperazione = null;

	public void setDataUltimaOperazione(java.lang.String val) {
		_dataUltimaOperazione = val;
	}

	public java.lang.String getDataUltimaOperazione() {
		return _dataUltimaOperazione;
	}

	/// Field [idTransazione]
	private java.lang.String _idTransazione = null;

	public void setIdTransazione(java.lang.String val) {
		_idTransazione = val;
	}

	public java.lang.String getIdTransazione() {
		return _idTransazione;
	}

	/// Field [idPayment]
	private java.lang.String _idPayment = null;

	public void setIdPayment(java.lang.String val) {
		_idPayment = val;
	}

	public java.lang.String getIdPayment() {
		return _idPayment;
	}

	/// Field [descrPayment]
	private java.lang.String _descrPayment = null;

	public void setDescrPayment(java.lang.String val) {
		_descrPayment = val;
	}

	public java.lang.String getDescrPayment() {
		return _descrPayment;
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

	/// Field [codNuovoStato]
	private long _codNuovoStato = 0;

	public void setCodNuovoStato(long val) {
		_codNuovoStato = val;
	}

	public long getCodNuovoStato() {
		return _codNuovoStato;
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

	/// Field [finishDate]
	private java.lang.String _finishDate = null;

	public void setFinishDate(java.lang.String val) {
		_finishDate = val;
	}

	public java.lang.String getFinishDate() {
		return _finishDate;
	}

	/// Field [basketId]
	private java.lang.String _basketId = null;

	public void setBasketId(java.lang.String val) {
		_basketId = val;
	}

	public java.lang.String getBasketId() {
		return _basketId;
	}

	/// Field [merchantId]
	private java.lang.String _merchantId = null;

	public void setMerchantId(java.lang.String val) {
		_merchantId = val;
	}

	public java.lang.String getMerchantId() {
		return _merchantId;
	}

	/// Field [paymentid]
	private java.lang.String _paymentid = null;

	public void setPaymentid(java.lang.String val) {
		_paymentid = val;
	}

	public java.lang.String getPaymentid() {
		return _paymentid;
	}

	/// Field [payurl]
	private java.lang.String _payurl = null;

	public void setPayurl(java.lang.String val) {
		_payurl = val;
	}

	public java.lang.String getPayurl() {
		return _payurl;
	}

	/// Field [pgresultcode]
	private java.lang.String _pgresultcode = null;

	public void setPgresultcode(java.lang.String val) {
		_pgresultcode = val;
	}

	public java.lang.String getPgresultcode() {
		return _pgresultcode;
	}

	/// Field [startDate]
	private java.lang.String _startDate = null;

	public void setStartDate(java.lang.String val) {
		_startDate = val;
	}

	public java.lang.String getStartDate() {
		return _startDate;
	}

	/// Field [oldstate]
	private long _oldstate = 0;

	public void setOldstate(long val) {
		_oldstate = val;
	}

	public long getOldstate() {
		return _oldstate;
	}

	/// Field [errore]
	private java.lang.String _errore = null;

	public void setErrore(java.lang.String val) {
		_errore = val;
	}

	public java.lang.String getErrore() {
		return _errore;
	}

	/// Field [dataFine]
	private java.lang.String _dataFine = null;

	public void setDataFine(java.lang.String val) {
		_dataFine = val;
	}

	public java.lang.String getDataFine() {
		return _dataFine;
	}

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public Transazione() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R307512660) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
