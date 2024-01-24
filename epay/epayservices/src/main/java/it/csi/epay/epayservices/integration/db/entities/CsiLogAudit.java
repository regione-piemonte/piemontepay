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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the csi_log_audit database table.
 * 
 */
@Entity
@Table(name="csi_log_audit")
public class CsiLogAudit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSI_LOG_AUDIT_IDLOG_GENERATOR", allocationSize=1, sequenceName="CSI_LOG_AUDIT_ID_LOG_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSI_LOG_AUDIT_IDLOG_GENERATOR")
	@Column(name="id_log", unique=true, nullable=false)
	private Long idLog;

	@Column(name="data_ora", nullable=false)
	private Timestamp dataOra;

	@Column(name="id_app", nullable=false, length=100)
	private String idApp;

	@Column(name="ip_address", length=40)
	private String ipAddress;

	@Column(name="key_oper", nullable=false, length=500)
	private String keyOper;

	@Column(name="ogg_oper", nullable=false, length=500)
	private String oggOper;

	@Column(nullable=false, length=50)
	private String operazione;

	@Column(nullable=false, length=100)
	private String utente;

	public CsiLogAudit() {
	}

	public Long getIdLog() {
		return this.idLog;
	}

	public void setIdLog(Long idLog) {
		this.idLog = idLog;
	}

	public Timestamp getDataOra() {
		return this.dataOra;
	}

	public void setDataOra(Timestamp dataOra) {
		this.dataOra = dataOra;
	}

	public String getIdApp() {
		return this.idApp;
	}

	public void setIdApp(String idApp) {
		this.idApp = idApp;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getKeyOper() {
		return this.keyOper;
	}

	public void setKeyOper(String keyOper) {
		this.keyOper = keyOper;
	}

	public String getOggOper() {
		return this.oggOper;
	}

	public void setOggOper(String oggOper) {
		this.oggOper = oggOper;
	}

	public String getOperazione() {
		return this.operazione;
	}

	public void setOperazione(String operazione) {
		this.operazione = operazione;
	}

	public String getUtente() {
		return this.utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

}
