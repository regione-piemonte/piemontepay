/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.sql.Timestamp;


public class TracciaturaNotify implements Serializable {


	private static final long serialVersionUID = 1785134700639155696L;

	private Long idNotifica;

	private Long idPagamento;

	private String esitoStatoInvioNotify;

	private Timestamp dataInivioNotify;

	private String messageUuid;

	private String bulkId;
	

	public Long getIdNotifica() {
		return idNotifica;
	}

	public void setIdNotifica(Long idNotifica) {
		this.idNotifica = idNotifica;
	}

	public Long getIdPagamento() {
		return idPagamento;
	}

	public void setIdPagamento(Long idPagamento) {
		this.idPagamento = idPagamento;
	}

	public String getEsitoStatoInvioNotify() {
		return esitoStatoInvioNotify;
	}

	public void setEsitoStatoInvioNotify(String esitoStatoInvioNotify) {
		this.esitoStatoInvioNotify = esitoStatoInvioNotify;
	}

	public Timestamp getDataInivioNotify() {
		return dataInivioNotify;
	}

	public void setDataInivioNotify(Timestamp dataInivioNotify) {
		this.dataInivioNotify = dataInivioNotify;
	}

	public String getMessageUuid() {
		return messageUuid;
	}

	public void setMessageUuid(String messageUuid) {
		this.messageUuid = messageUuid;
	}

	public String getBulkId() {
		return bulkId;
	}

	public void setBulkId(String bulkId) {
		this.bulkId = bulkId;
	}




}
	
	
