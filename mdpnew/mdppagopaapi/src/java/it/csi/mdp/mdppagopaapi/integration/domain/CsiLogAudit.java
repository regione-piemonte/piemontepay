/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the csi_log_audit database table.
 * 
 */
@Entity
@Table(name="csi_log_audit")
@NamedQuery(name="CsiLogAudit.findAll", query="SELECT c FROM CsiLogAudit c")
public class CsiLogAudit implements Serializable {
	private static final long serialVersionUID = 1L;

	private String applicationid;

	private String codappmodify;

	private String codfisc;

	@Column(name="data_ora")
	private Timestamp dataOra;

	private String descrizione;

	private String gatewayid;

	@Column(name="id_app")
	private String idApp;

	private String idaction;

	@Column(name="ip_address")
	private String ipAddress;

	@Column(name="key_oper")
	private String keyOper;

	@Column(name="ogg_oper")
	private String oggOper;

	private String operazione;

	private String transactionid;

	@Id
	@SequenceGenerator(name="CSI_LOG_AUDIT_KEY_GENERATOR", sequenceName="CSI_LOG_AUDIT_UNIQUEID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSI_LOG_AUDIT_KEY_GENERATOR")
	private Integer uniqueid;

	private Integer utente;

	public CsiLogAudit() {
	}

	public String getApplicationid() {
		return this.applicationid;
	}

	public void setApplicationid(String applicationid) {
		this.applicationid = applicationid;
	}

	public String getCodappmodify() {
		return this.codappmodify;
	}

	public void setCodappmodify(String codappmodify) {
		this.codappmodify = codappmodify;
	}

	public String getCodfisc() {
		return this.codfisc;
	}

	public void setCodfisc(String codfisc) {
		this.codfisc = codfisc;
	}

	public Timestamp getDataOra() {
		return this.dataOra;
	}

	public void setDataOra(Timestamp dataOra) {
		this.dataOra = dataOra;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getGatewayid() {
		return this.gatewayid;
	}

	public void setGatewayid(String gatewayid) {
		this.gatewayid = gatewayid;
	}

	public String getIdApp() {
		return this.idApp;
	}

	public void setIdApp(String idApp) {
		this.idApp = idApp;
	}

	public String getIdaction() {
		return this.idaction;
	}

	public void setIdaction(String idaction) {
		this.idaction = idaction;
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

	public String getTransactionid() {
		return this.transactionid;
	}

	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}

	public Integer getUniqueid() {
		return this.uniqueid;
	}

	public void setUniqueid(Integer uniqueid) {
		this.uniqueid = uniqueid;
	}

	public Integer getUtente() {
		return this.utente;
	}

	public void setUtente(Integer utente) {
		this.utente = utente;
	}

}
