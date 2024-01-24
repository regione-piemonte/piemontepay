/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class GiornaleEventi implements java.io.Serializable {

	/// Field [id]
	private long _id = 0;

	public void setId(long val) {
		_id = val;
	}

	public long getId() {
		return _id;
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

	/// Field [dataoraevento]
	private java.lang.String _dataoraevento = null;

	public void setDataoraevento(java.lang.String val) {
		_dataoraevento = val;
	}

	public java.lang.String getDataoraevento() {
		return _dataoraevento;
	}

	/// Field [identificativodominio]
	private java.lang.String _identificativodominio = null;

	public void setIdentificativodominio(java.lang.String val) {
		_identificativodominio = val;
	}

	public java.lang.String getIdentificativodominio() {
		return _identificativodominio;
	}

	/// Field [iuv]
	private java.lang.String _iuv = null;

	public void setIuv(java.lang.String val) {
		_iuv = val;
	}

	public java.lang.String getIuv() {
		return _iuv;
	}

	/// Field [codiceContesto]
	private java.lang.String _codiceContesto = null;

	public void setCodiceContesto(java.lang.String val) {
		_codiceContesto = val;
	}

	public java.lang.String getCodiceContesto() {
		return _codiceContesto;
	}

	/// Field [idPsp]
	private java.lang.String _idPsp = null;

	public void setIdPsp(java.lang.String val) {
		_idPsp = val;
	}

	public java.lang.String getIdPsp() {
		return _idPsp;
	}

	/// Field [tipoversamento]
	private java.lang.String _tipoversamento = null;

	public void setTipoversamento(java.lang.String val) {
		_tipoversamento = val;
	}

	public java.lang.String getTipoversamento() {
		return _tipoversamento;
	}

	/// Field [componente]
	private java.lang.String _componente = null;

	public void setComponente(java.lang.String val) {
		_componente = val;
	}

	public java.lang.String getComponente() {
		return _componente;
	}

	/// Field [categoriaevento]
	private java.lang.String _categoriaevento = null;

	public void setCategoriaevento(java.lang.String val) {
		_categoriaevento = val;
	}

	public java.lang.String getCategoriaevento() {
		return _categoriaevento;
	}

	/// Field [tipoevento]
	private java.lang.String _tipoevento = null;

	public void setTipoevento(java.lang.String val) {
		_tipoevento = val;
	}

	public java.lang.String getTipoevento() {
		return _tipoevento;
	}

	/// Field [sottotipoevento]
	private java.lang.String _sottotipoevento = null;

	public void setSottotipoevento(java.lang.String val) {
		_sottotipoevento = val;
	}

	public java.lang.String getSottotipoevento() {
		return _sottotipoevento;
	}

	/// Field [identificativofruitore]
	private java.lang.String _identificativofruitore = null;

	public void setIdentificativofruitore(java.lang.String val) {
		_identificativofruitore = val;
	}

	public java.lang.String getIdentificativofruitore() {
		return _identificativofruitore;
	}

	/// Field [identificativoerogatore]
	private java.lang.String _identificativoerogatore = null;

	public void setIdentificativoerogatore(java.lang.String val) {
		_identificativoerogatore = val;
	}

	public java.lang.String getIdentificativoerogatore() {
		return _identificativoerogatore;
	}

	/// Field [identificativostazioneintermediariopa]
	private java.lang.String _identificativostazioneintermediariopa = null;

	public void setIdentificativostazioneintermediariopa(java.lang.String val) {
		_identificativostazioneintermediariopa = val;
	}

	public java.lang.String getIdentificativostazioneintermediariopa() {
		return _identificativostazioneintermediariopa;
	}

	/// Field [idIntPsp]
	private java.lang.String _idIntPsp = null;

	public void setIdIntPsp(java.lang.String val) {
		_idIntPsp = val;
	}

	public java.lang.String getIdIntPsp() {
		return _idIntPsp;
	}

	/// Field [canalepagamento]
	private java.lang.String _canalepagamento = null;

	public void setCanalepagamento(java.lang.String val) {
		_canalepagamento = val;
	}

	public java.lang.String getCanalepagamento() {
		return _canalepagamento;
	}

	/// Field [parametrispecificiinterfaccia]
	private java.lang.String _parametrispecificiinterfaccia = null;

	public void setParametrispecificiinterfaccia(java.lang.String val) {
		_parametrispecificiinterfaccia = val;
	}

	public java.lang.String getParametrispecificiinterfaccia() {
		return _parametrispecificiinterfaccia;
	}

	/// Field [esito]
	private java.lang.String _esito = null;

	public void setEsito(java.lang.String val) {
		_esito = val;
	}

	public java.lang.String getEsito() {
		return _esito;
	}

	/// Field [applicationId]
	private java.lang.String _applicationId = null;

	public void setApplicationId(java.lang.String val) {
		_applicationId = val;
	}

	public java.lang.String getApplicationId() {
		return _applicationId;
	}

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public GiornaleEventi() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R-1416741470) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
