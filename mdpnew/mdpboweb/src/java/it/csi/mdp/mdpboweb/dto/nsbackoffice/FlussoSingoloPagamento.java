/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class FlussoSingoloPagamento
		extends
			it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoRiversamento
		implements
			java.io.Serializable {

	/// Field [idFlusso]
	private int _idFlusso = 0;

	public void setIdFlusso(int val) {
		_idFlusso = val;
	}

	public int getIdFlusso() {
		return _idFlusso;
	}

	/// Field [iuv]
	private java.lang.String _iuv = null;

	public void setIuv(java.lang.String val) {
		_iuv = val;
	}

	public java.lang.String getIuv() {
		return _iuv;
	}

	/// Field [identificativounivocoriscossione]
	private java.lang.String _identificativounivocoriscossione = null;

	public void setIdentificativounivocoriscossione(java.lang.String val) {
		_identificativounivocoriscossione = val;
	}

	public java.lang.String getIdentificativounivocoriscossione() {
		return _identificativounivocoriscossione;
	}

	/// Field [singoloimportopagato]
	private double _singoloimportopagato = 0;

	public void setSingoloimportopagato(double val) {
		_singoloimportopagato = val;
	}

	public double getSingoloimportopagato() {
		return _singoloimportopagato;
	}

	/// Field [codiceesitosingolopagamento]
	private java.lang.String _codiceesitosingolopagamento = null;

	public void setCodiceesitosingolopagamento(java.lang.String val) {
		_codiceesitosingolopagamento = val;
	}

	public java.lang.String getCodiceesitosingolopagamento() {
		return _codiceesitosingolopagamento;
	}

	/// Field [dataesitosingolopagamento]
	private java.lang.String _dataesitosingolopagamento = null;

	public void setDataesitosingolopagamento(java.lang.String val) {
		_dataesitosingolopagamento = val;
	}

	public java.lang.String getDataesitosingolopagamento() {
		return _dataesitosingolopagamento;
	}

	/// Field [applicationId]
	private java.lang.String _applicationId = null;

	public void setApplicationId(java.lang.String val) {
		_applicationId = val;
	}

	public java.lang.String getApplicationId() {
		return _applicationId;
	}

	/// Field [applicationname]
	private java.lang.String _applicationname = null;

	public void setApplicationname(java.lang.String val) {
		_applicationname = val;
	}

	public java.lang.String getApplicationname() {
		return _applicationname;
	}

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public FlussoSingoloPagamento() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R-812671355) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
