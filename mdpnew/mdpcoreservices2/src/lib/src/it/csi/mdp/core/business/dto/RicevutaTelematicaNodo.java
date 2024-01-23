/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class RicevutaTelematicaNodo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2672575428405866965L;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Date getDataMsgRicevuta() {
		return dataMsgRicevuta;
	}

	public void setDataMsgRicevuta(Date dataMsgRicevuta) {
		this.dataMsgRicevuta = dataMsgRicevuta;
	}

	public String getIdMsgRicevuta() {
		return idMsgRicevuta;
	}

	public void setIdMsgRicevuta(String idMsgRicevuta) {
		this.idMsgRicevuta = idMsgRicevuta;
	}

	public byte[] getRtData() {
		return rtData;
	}

	public void setRtData(byte[] rtData) {
		this.rtData = rtData;
	}

	public String getTipoFirma() {
		return tipoFirma;
	}

	public void setTipoFirma(String tipoFirma) {
		this.tipoFirma = tipoFirma;
	}

	public String getIuv() {
		return iuv;
	}

	public void setIuv(String iuv) {
		this.iuv = iuv;
	}

	public Integer getIdEsitoPagamento() {
		return idEsitoPagamento;
	}

	public void setIdEsitoPagamento(Integer idEsitoPagamento) {
		this.idEsitoPagamento = idEsitoPagamento;
	}

	public String getDescEsitoPagamento() {
		return descEsitoPagamento;
	}

	public void setDescEsitoPagamento(String descEsitoPagamento) {
		this.descEsitoPagamento = descEsitoPagamento;
	}

	public String getIdMsgRichiesta() {
		return idMsgRichiesta;
	}

	public void setIdMsgRichiesta(String idMsgRichiesta) {
		this.idMsgRichiesta = idMsgRichiesta;
	}
}
