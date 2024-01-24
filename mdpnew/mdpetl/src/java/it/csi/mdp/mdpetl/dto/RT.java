/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class RT implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2672575428405866965L;

	private Integer id;
	
	private String applicationId;
	
	private String transactionId;
	
	private Timestamp insertDate;
	
	private Timestamp lastUpdate;

	private Timestamp dataMsgRicevuta;
	
	private String idMsgRicevuta;

	private byte[] rtData;
	
	private String tipoFirma;
	
	private String iuv;

	private Integer idEsitoPagamento;

	private String descEsitoPagamento;
	
	private String idMsgRichiesta;
	
	private String statoInvioFruitore;
	
	private Timestamp dataInvioFruitore;
	
	private String sogenteInvioFruitore;
	
	private double commissioniApplicatePSP;
	
	private Integer idRr;
	
	
    public Integer getIdRr () {
        return idRr;
    }

    
    public void setIdRr ( Integer idRr ) {
        this.idRr = idRr;
    }

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
	 * @return the dataMsgRicevuta
	 */
	public Timestamp getDataMsgRicevuta() {
		return dataMsgRicevuta;
	}

	/**
	 * @param dataMsgRicevuta the dataMsgRicevuta to set
	 */
	public void setDataMsgRicevuta(Timestamp dataMsgRicevuta) {
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
	 * @return the rtData
	 */
	public byte[] getRtData() {
		return rtData;
	}

	/**
	 * @param rtData the rtData to set
	 */
	public void setRtData(byte[] rtData) {
		this.rtData = rtData;
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

	public String getStatoInvioFruitore() {
		return statoInvioFruitore;
	}

	public void setStatoInvioFruitore(String statoInvioFruitore) {
		this.statoInvioFruitore = statoInvioFruitore;
	}

	public Timestamp getDataInvioFruitore() {
		return dataInvioFruitore;
	}

	public void setDataInvioFruitore(Timestamp dataInvioFruitore) {
		this.dataInvioFruitore = dataInvioFruitore;
	}

	public String getSogenteInvioFruitore() {
		return sogenteInvioFruitore;
	}

	public void setSogenteInvioFruitore(String sogenteInvioFruitore) {
		this.sogenteInvioFruitore = sogenteInvioFruitore;
	}

	public double getCommissioniApplicatePSP() {
		return commissioniApplicatePSP;
	}

	public void setCommissioniApplicatePSP(double commissioniApplicatePSP) {
		this.commissioniApplicatePSP = commissioniApplicatePSP;
	}
	
	
}
