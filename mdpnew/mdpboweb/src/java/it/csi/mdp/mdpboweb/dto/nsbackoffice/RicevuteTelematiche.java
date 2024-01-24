/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class RicevuteTelematiche implements java.io.Serializable {

	/// Field [id]
	private int _id = 0;

	public void setId(int val) {
		_id = val;
	}

	public int getId() {
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

	/// Field [transactionId]
	private java.lang.String _transactionId = null;

	public void setTransactionId(java.lang.String val) {
		_transactionId = val;
	}

	public java.lang.String getTransactionId() {
		return _transactionId;
	}

	/// Field [insertDate]
	private java.lang.String _insertDate = null;

	public void setInsertDate(java.lang.String val) {
		_insertDate = val;
	}

	public java.lang.String getInsertDate() {
		return _insertDate;
	}

	/// Field [lastUpdate]
	private java.lang.String _lastUpdate = null;

	public void setLastUpdate(java.lang.String val) {
		_lastUpdate = val;
	}

	public java.lang.String getLastUpdate() {
		return _lastUpdate;
	}

	/// Field [dataMsgRicevuta]
	private java.lang.String _dataMsgRicevuta = null;

	public void setDataMsgRicevuta(java.lang.String val) {
		_dataMsgRicevuta = val;
	}

	public java.lang.String getDataMsgRicevuta() {
		return _dataMsgRicevuta;
	}

	/// Field [idMsgRicevuta]
	private java.lang.String _idMsgRicevuta = null;

	public void setIdMsgRicevuta(java.lang.String val) {
		_idMsgRicevuta = val;
	}

	public java.lang.String getIdMsgRicevuta() {
		return _idMsgRicevuta;
	}

	/// Field [tipoFirma]
	private java.lang.String _tipoFirma = null;

	public void setTipoFirma(java.lang.String val) {
		_tipoFirma = val;
	}

	public java.lang.String getTipoFirma() {
		return _tipoFirma;
	}

	/// Field [iuv]
	private java.lang.String _iuv = null;

	public void setIuv(java.lang.String val) {
		_iuv = val;
	}

	public java.lang.String getIuv() {
		return _iuv;
	}

	/// Field [idEsitoPagamento]
	private java.lang.String _idEsitoPagamento = null;

	public void setIdEsitoPagamento(java.lang.String val) {
		_idEsitoPagamento = val;
	}

	public java.lang.String getIdEsitoPagamento() {
		return _idEsitoPagamento;
	}

	/// Field [descEsitoPagamento]
	private java.lang.String _descEsitoPagamento = null;

	public void setDescEsitoPagamento(java.lang.String val) {
		_descEsitoPagamento = val;
	}

	public java.lang.String getDescEsitoPagamento() {
		return _descEsitoPagamento;
	}

	/// Field [idMsgRichiesta]
	private java.lang.String _idMsgRichiesta = null;

	public void setIdMsgRichiesta(java.lang.String val) {
		_idMsgRichiesta = val;
	}

	public java.lang.String getIdMsgRichiesta() {
		return _idMsgRichiesta;
	}

	/// Field [insertDateDa]
	private java.lang.String _insertDateDa = null;

	public void setInsertDateDa(java.lang.String val) {
		_insertDateDa = val;
	}

	public java.lang.String getInsertDateDa() {
		return _insertDateDa;
	}

	/// Field [insertDateA]
	private java.lang.String _insertDateA = null;

	public void setInsertDateA(java.lang.String val) {
		_insertDateA = val;
	}

	public java.lang.String getInsertDateA() {
		return _insertDateA;
	}

	/// Field [lastUpdateDa]
	private java.lang.String _lastUpdateDa = null;

	public void setLastUpdateDa(java.lang.String val) {
		_lastUpdateDa = val;
	}

	public java.lang.String getLastUpdateDa() {
		return _lastUpdateDa;
	}

	/// Field [lastUpdateA]
	private java.lang.String _lastUpdateA = null;

	public void setLastUpdateA(java.lang.String val) {
		_lastUpdateA = val;
	}

	public java.lang.String getLastUpdateA() {
		return _lastUpdateA;
	}

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public RicevuteTelematiche() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R-1320268774) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
