/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class IbanEnteApplication implements java.io.Serializable {

	/// Field [id]
	private java.lang.String _id = null;

	public void setId(java.lang.String val) {
		_id = val;
	}

	public java.lang.String getId() {
		return _id;
	}

	/// Field [applicationId]
	private java.lang.String _applicationId = null;

	public void setApplicationId(java.lang.String val) {
		_applicationId = val;
	}

	public java.lang.String getApplicationId() {
		return _applicationId;
	}

	/// Field [idEnte]
	private java.lang.String _idEnte = null;

	public void setIdEnte(java.lang.String val) {
		_idEnte = val;
	}

	public java.lang.String getIdEnte() {
		return _idEnte;
	}

	/// Field [tipoversamento]
	private java.lang.String _tipoversamento = null;

	public void setTipoversamento(java.lang.String val) {
		_tipoversamento = val;
	}

	public java.lang.String getTipoversamento() {
		return _tipoversamento;
	}

	/// Field [identificativopsp]
	private java.lang.String _identificativopsp = null;

	public void setIdentificativopsp(java.lang.String val) {
		_identificativopsp = val;
	}

	public java.lang.String getIdentificativopsp() {
		return _identificativopsp;
	}

	/// Field [iban]
	private java.lang.String _iban = null;

	public void setIban(java.lang.String val) {
		_iban = val;
	}

	public java.lang.String getIban() {
		return _iban;
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

	/// Field [attivo]
	private java.lang.String _attivo = null;

	public void setAttivo(java.lang.String val) {
		_attivo = val;
	}

	public java.lang.String getAttivo() {
		return _attivo;
	}

	/// Field [dataPippo]
	private java.lang.String _dataPippo = null;

	public void setDataPippo(java.lang.String val) {
		_dataPippo = val;
	}

	public java.lang.String getDataPippo() {
		return _dataPippo;
	}

	/// Field [ibanOld]
	private java.lang.String _ibanOld = null;

	public void setIbanOld(java.lang.String val) {
		_ibanOld = val;
	}

	public java.lang.String getIbanOld() {
		return _ibanOld;
	}

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public IbanEnteApplication() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R-1907678266) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
