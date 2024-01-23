/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class GiornaleEvento implements Serializable {
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3497491443980704263L;

	private Integer id;
	
	private Timestamp insertDate;
	
	private Timestamp lastUpdate;
	
	private Timestamp dataoraevento;
	
	private String identificativodominio;
	
	private String iuv ;
	
	private String codiceContesto;
	
	private String idPsp;
	
	private String tipoversamento;
	
	private String componente;

	private String categoriaevento;
	
	private String tipoevento;
	
	private String sottotipoevento;
	
	private String identificativofruitore;
	
	private String identificativoerogatore;
	
	private String identificativostazioneintermediariopa;
	
	private String idIntPsp;
	
	private String canalepagamento;
	
	private String parametrispecificiinterfaccia;
	
	private String esito;

	private String applicationId;
	
	private String transactionId;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the insertDate
	 */
	public Timestamp getInsertDate() {
		return insertDate;
	}

	/**
	 * @param insertDate the insertDate to set
	 */
	public void setInsertDate(Timestamp insertDate) {
		this.insertDate = insertDate;
	}

	/**
	 * @return the lastUpdate
	 */
	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @param lastUpdate the lastUpdate to set
	 */
	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	/**
	 * @return the dataoraevento
	 */
	public Timestamp getDataoraevento() {
		return dataoraevento;
	}

	/**
	 * @param dataoraevento the dataoraevento to set
	 */
	public void setDataoraevento(Timestamp dataoraevento) {
		this.dataoraevento = dataoraevento;
	}

	/**
	 * @return the identificativodominio
	 */
	public String getIdentificativodominio() {
		return identificativodominio;
	}

	/**
	 * @param identificativodominio the identificativodominio to set
	 */
	public void setIdentificativodominio(String identificativodominio) {
		this.identificativodominio = identificativodominio;
	}

	/**
	 * @return the iuv
	 */
	public String getIuv() {
		return iuv;
	}

	/**
	 * @param iuv the iuv to set
	 */
	public void setIuv(String iuv) {
		this.iuv = iuv;
	}

	/**
	 * @return the codiceContesto
	 */
	public String getCodiceContesto() {
		return codiceContesto;
	}

	/**
	 * @param codiceContesto the codiceContesto to set
	 */
	public void setCodiceContesto(String codiceContesto) {
		this.codiceContesto = codiceContesto;
	}

	/**
	 * @return the idPsp
	 */
	public String getIdPsp() {
		return idPsp;
	}

	/**
	 * @param idPsp the idPsp to set
	 */
	public void setIdPsp(String idPsp) {
		this.idPsp = idPsp;
	}

	/**
	 * @return the tipoversamento
	 */
	public String getTipoversamento() {
		return tipoversamento;
	}

	/**
	 * @param tipoversamento the tipoversamento to set
	 */
	public void setTipoversamento(String tipoversamento) {
		this.tipoversamento = tipoversamento;
	}

	/**
	 * @return the categoriaevento
	 */
	public String getCategoriaevento() {
		return categoriaevento;
	}

	/**
	 * @param categoriaevento the categoriaevento to set
	 */
	public void setCategoriaevento(String categoriaevento) {
		this.categoriaevento = categoriaevento;
	}

	/**
	 * @return the tipoevento
	 */
	public String getTipoevento() {
		return tipoevento;
	}

	/**
	 * @param tipoevento the tipoevento to set
	 */
	public void setTipoevento(String tipoevento) {
		this.tipoevento = tipoevento;
	}

	/**
	 * @return the sottotipoevento
	 */
	public String getSottotipoevento() {
		return sottotipoevento;
	}

	/**
	 * @param sottotipoevento the sottotipoevento to set
	 */
	public void setSottotipoevento(String sottotipoevento) {
		this.sottotipoevento = sottotipoevento;
	}

	/**
	 * @return the identificativofruitore
	 */
	public String getIdentificativofruitore() {
		return identificativofruitore;
	}

	/**
	 * @param identificativofruitore the identificativofruitore to set
	 */
	public void setIdentificativofruitore(String identificativofruitore) {
		this.identificativofruitore = identificativofruitore;
	}

	/**
	 * @return the identificativoerogatore
	 */
	public String getIdentificativoerogatore() {
		return identificativoerogatore;
	}

	/**
	 * @param identificativoerogatore the identificativoerogatore to set
	 */
	public void setIdentificativoerogatore(String identificativoerogatore) {
		this.identificativoerogatore = identificativoerogatore;
	}

	/**
	 * @return the identificativostazioneintermediariopa
	 */
	public String getIdentificativostazioneintermediariopa() {
		return identificativostazioneintermediariopa;
	}

	/**
	 * @param identificativostazioneintermediariopa the identificativostazioneintermediariopa to set
	 */
	public void setIdentificativostazioneintermediariopa(String identificativostazioneintermediariopa) {
		this.identificativostazioneintermediariopa = identificativostazioneintermediariopa;
	}

	/**
	 * @return the idIntPsp
	 */
	public String getIdIntPsp() {
		return idIntPsp;
	}

	/**
	 * @param idIntPsp the idIntPsp to set
	 */
	public void setIdIntPsp(String idIntPsp) {
		this.idIntPsp = idIntPsp;
	}

	/**
	 * @return the canalepagamento
	 */
	public String getCanalepagamento() {
		return canalepagamento;
	}

	/**
	 * @param canalepagamento the canalepagamento to set
	 */
	public void setCanalepagamento(String canalepagamento) {
		this.canalepagamento = canalepagamento;
	}

	/**
	 * @return the parametrispecificiinterfaccia
	 */
	public String getParametrispecificiinterfaccia() {
		return parametrispecificiinterfaccia;
	}

	/**
	 * @param parametrispecificiinterfaccia the parametrispecificiinterfaccia to set
	 */
	public void setParametrispecificiinterfaccia(String parametrispecificiinterfaccia) {
		this.parametrispecificiinterfaccia = parametrispecificiinterfaccia;
	}

	/**
	 * @return the esito
	 */
	public String getEsito() {
		return esito;
	}

	/**
	 * @param esito the esito to set
	 */
	public void setEsito(String esito) {
		this.esito = esito;
	}

	/**
	 * @return the applicationId
	 */
	public String getApplicationId() {
		return applicationId;
	}

	/**
	 * @return the componente
	 */
	public String getComponente() {
		return componente;
	}

	/**
	 * @param componente the componente to set
	 */
	public void setComponente(String componente) {
		this.componente = componente;
	}

	/**
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}


	
	
	
	
}
