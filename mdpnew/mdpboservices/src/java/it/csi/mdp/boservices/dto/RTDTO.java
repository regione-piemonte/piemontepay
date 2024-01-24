/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.boservices.dto;

import java.sql.Timestamp;
import java.util.Date;

public class RTDTO extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3371818928282681618L;

	private Integer id;
	
	private String applicationId;
	
	private String transactionId;
	
	private Date insertDate;
	
	private Date lastUpdate;

	private Date dataMsgRicevuta;
	
	private String idMsgRicevuta;

	private byte[] rtData;
	
	private String tipoFirma;
	
	private String iuv;

	private Integer idEsitoPagamento;

	private String descEsitoPagamento;

	private String idMsgRichiesta;
	
	
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
	 * @return the applicationId
	 */
	public String getApplicationId() {
		return applicationId;
	}

	/**
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * @return the insertDate
	 */
	public Date getInsertDate() {
		return insertDate;
	}

	/**
	 * @param insertDate the insertDate to set
	 */
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	/**
	 * @return the lastUpdate
	 */
	public Date getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @param lastUpdate the lastUpdate to set
	 */
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	/**
	 * @return the dataMsgRicevuta
	 */
	public Date getDataMsgRicevuta() {
		return dataMsgRicevuta;
	}

	/**
	 * @param dataMsgRicevuta the dataMsgRicevuta to set
	 */
	public void setDataMsgRicevuta(Date dataMsgRicevuta) {
		this.dataMsgRicevuta = dataMsgRicevuta;
	}

	/**
	 * @return the idMsgRicevuta
	 */
	public String getIdMsgRicevuta() {
		return idMsgRicevuta;
	}

	/**
	 * @param idMsgRicevuta the idMsgRicevuta to set
	 */
	public void setIdMsgRicevuta(String idMsgRicevuta) {
		this.idMsgRicevuta = idMsgRicevuta;
	}

	/**
	 * @return the tipoFirma
	 */
	public String getTipoFirma() {
		return tipoFirma;
	}

	/**
	 * @param tipoFirma the tipoFirma to set
	 */
	public void setTipoFirma(String tipoFirma) {
		this.tipoFirma = tipoFirma;
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
	 * @return the idEsitoPagamento
	 */
	public Integer getIdEsitoPagamento() {
		return idEsitoPagamento;
	}

	/**
	 * @param idEsitoPagamento the idEsitoPagamento to set
	 */
	public void setIdEsitoPagamento(Integer idEsitoPagamento) {
		this.idEsitoPagamento = idEsitoPagamento;
	}

	/**
	 * @return the descEsitoPagamento
	 */
	public String getDescEsitoPagamento() {
		return descEsitoPagamento;
	}

	/**
	 * @param descEsitoPagamento the descEsitoPagamento to set
	 */
	public void setDescEsitoPagamento(String descEsitoPagamento) {
		this.descEsitoPagamento = descEsitoPagamento;
	}

	/**
	 * @return the idMsgRichiesta
	 */
	public String getIdMsgRichiesta() {
		return idMsgRichiesta;
	}

	/**
	 * @param idMsgRichiesta the idMsgRichiesta to set
	 */
	public void setIdMsgRichiesta(String idMsgRichiesta) {
		this.idMsgRichiesta = idMsgRichiesta;
	}

	/**
	 * @return the rtDataBin
	 */
	public byte[] getRtData() {
		return rtData;
	}

	/**
	 * @param rtDataBin the rtDataBin to set
	 */
	public void setRtData(byte[] rtData) {
		this.rtData = rtData;
	}
	
	
}
