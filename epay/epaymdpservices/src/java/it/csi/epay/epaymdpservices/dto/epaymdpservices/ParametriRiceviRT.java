/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymdpservices.dto.epaymdpservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ParametriRiceviRT", namespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParametriRiceviRT {
	
	public String applicationId;
	public String transactionId;
	public String dataOraMsgRicevuta;//formato [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss]
	public String idMsgRicevuta;
	public String tipoFirma;
	public String iuv;
	public String codEsitoPagamento;
	public String descEsitoPagamento;
	public String idMsgRichiesta;
	public byte[] rtData;//Documento XML della RT in formato base64
	public String timestamp;//Formato ddmmyyyy-hh:mm:ss:ms
	public String mac;
	
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
	public String getDataOraMsgRicevuta() {
		return dataOraMsgRicevuta;
	}
	public void setDataOraMsgRicevuta(String dataOraMsgRicevuta) {
		this.dataOraMsgRicevuta = dataOraMsgRicevuta;
	}
	public String getIdMsgRicevuta() {
		return idMsgRicevuta;
	}
	public void setIdMsgRicevuta(String idMsgRicevuta) {
		this.idMsgRicevuta = idMsgRicevuta;
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
	public String getCodEsitoPagamento() {
		return codEsitoPagamento;
	}
	public void setCodEsitoPagamento(String codEsitoPagamento) {
		this.codEsitoPagamento = codEsitoPagamento;
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
	public byte[] getRtData() {
		return rtData;
	}
	public void setRtData(byte[] rtData) {
		this.rtData = rtData;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}

	
}
