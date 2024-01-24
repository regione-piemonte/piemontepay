/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class Gateway implements java.io.Serializable {

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

	/// Field [providerGateway]
	private java.lang.String _providerGateway = null;

	public void setProviderGateway(java.lang.String val) {
		_providerGateway = val;
	}

	public java.lang.String getProviderGateway() {
		return _providerGateway;
	}

	/// Field [serviceJNDIName]
	private java.lang.String _serviceJNDIName = null;

	public void setServiceJNDIName(java.lang.String val) {
		_serviceJNDIName = val;
	}

	public java.lang.String getServiceJNDIName() {
		return _serviceJNDIName;
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

	/// Field [defaultPaymentUrl]
	private java.lang.String _defaultPaymentUrl = null;

	public void setDefaultPaymentUrl(java.lang.String val) {
		_defaultPaymentUrl = val;
	}

	public java.lang.String getDefaultPaymentUrl() {
		return _defaultPaymentUrl;
	}

	/// Field [returnUrl]
	private java.lang.String _returnUrl = null;

	public void setReturnUrl(java.lang.String val) {
		_returnUrl = val;
	}

	public java.lang.String getReturnUrl() {
		return _returnUrl;
	}

	/// Field [receiptUrl]
	private java.lang.String _receiptUrl = null;

	public void setReceiptUrl(java.lang.String val) {
		_receiptUrl = val;
	}

	public java.lang.String getReceiptUrl() {
		return _receiptUrl;
	}

	/// Field [KoUrl]
	private java.lang.String _KoUrl = null;

	public void setKoUrl(java.lang.String val) {
		_KoUrl = val;
	}

	public java.lang.String getKoUrl() {
		return _KoUrl;
	}

	/// Field [PaymentlUrl]
	private java.lang.String _PaymentlUrl = null;

	public void setPaymentlUrl(java.lang.String val) {
		_PaymentlUrl = val;
	}

	public java.lang.String getPaymentlUrl() {
		return _PaymentlUrl;
	}

	/// Field [abilitazione]
	private boolean _abilitazione = false;

	public void setAbilitazione(boolean val) {
		_abilitazione = val;
	}

	public boolean getAbilitazione() {
		return _abilitazione;
	}

	/// Field [idGatewayxClone]
	private java.lang.String _idGatewayxClone = null;

	public void setIdGatewayxClone(java.lang.String val) {
		_idGatewayxClone = val;
	}

	public java.lang.String getIdGatewayxClone() {
		return _idGatewayxClone;
	}

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public Gateway() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R-1991719718) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
