/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the epay_t_tracciatura_notify_cittafacile database table.
 *
 */
@Entity
@Table ( name = "epay_t_tracciatura_notify_cittafacile" )

public class EpayTTracciaturaNotifyCittaFacile implements Serializable {

	private static final long serialVersionUID = -1622928461967295045L;

	@Id
    @SequenceGenerator ( name = "EPAY_T_TRACCIATURA_NOTIFY_CITTAFACILE_ID_NOTIFICA_GENERATOR", allocationSize = 1, 
    sequenceName = "EPAY_T_TRACCIATURA_NOTIFY_CITTAFACILE_ID_NOTIFICA_SEQ" )
    @GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "EPAY_T_TRACCIATURA_NOTIFY_CITTAFACILE_ID_NOTIFICA_GENERATOR" )
    @Column ( name = "id_notifica", unique = true, nullable = false )
    private Long idNotifica;
	 
	@ManyToOne
	@JoinColumn ( name = "id_pagamento", nullable = false )
	private EpayTPagamento epayTPagamento;
	

    @Column ( name = "esito_stato_invio_notify", length = 10 )
    private String esitoStatoInvioNotify;
    
    @Column ( name = "data_inivio_notify" )
    private Timestamp dataInivioNotify;
    
    @Column ( name = "message_uuid", length = 50 )
    private String messageUuid;
    
    
    @Column ( name = "bulk_id", length = 50 )
    private String bulkId;
    
	
	

public Long getIdNotifica() {
	return idNotifica;
}

public void setIdNotifica(Long idNotifica) {
	this.idNotifica = idNotifica;
}


public EpayTPagamento getEpayTPagamento() {
	return epayTPagamento;
}

public void setEpayTPagamento(EpayTPagamento epayTPagamento) {
	this.epayTPagamento = epayTPagamento;
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
