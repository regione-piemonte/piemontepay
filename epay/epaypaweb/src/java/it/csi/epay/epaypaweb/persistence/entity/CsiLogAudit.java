/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="csi_log_audit")
@NamedQueries({
	@NamedQuery ( name="CsiLogAudit.findAll",			query="SELECT cla FROM CsiLogAudit cla"),
	@NamedQuery ( name="CsiLogAudit.countAll",			query="SELECT COUNT(cla) FROM CsiLogAudit cla" ),
	@NamedQuery ( name="CsiLogAudit.findByIdLog",		query="SELECT cla FROM CsiLogAudit cla WHERE cla.idLog = :idLog" ),
	@NamedQuery ( name="CsiLogAudit.findByIdApp",		query="SELECT cla FROM CsiLogAudit cla WHERE cla.idApp = :idApp" ),
	@NamedQuery ( name="CsiLogAudit.findByIpAddress", 	query="SELECT cla FROM CsiLogAudit cla WHERE cla.ipAddress = :ipAddress" ),
	@NamedQuery ( name="CsiLogAudit.findByUtente", 		query="SELECT cla FROM CsiLogAudit cla WHERE cla.utente = :utente" ),
	@NamedQuery ( name="CsiLogAudit.findByOperazione",	query="SELECT cla FROM CsiLogAudit cla WHERE cla.utente = :operazione" ),
	@NamedQuery ( name="CsiLogAudit.findByOggOper",		query="SELECT cla FROM CsiLogAudit cla WHERE cla.oggOper = :oggOper" ),
	@NamedQuery ( name="CsiLogAudit.findByKeyOper",		query="SELECT cla FROM CsiLogAudit cla WHERE cla.keyOper = :keyOper" )
})
public class CsiLogAudit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
	@Column(name="id_log")
	private Long idLog;
	
	@Column(name="data_ora")
	private Timestamp dataOra;
	
	@Column(name="id_app")
	private String idApp;
	
	@Column(name="ip_address")
	private String ipAddress;
	
	@Column(name="utente")
	private String utente;
	
	@Column(name="operazione")
	private String operazione;
	
	@Column(name="ogg_oper")
	private String oggOper;
	
	@Column(name="key_oper")
	private String keyOper;
	
	//uni-directional many-to-one association to AuditAction
	@ManyToOne
	@JoinColumn(name="idaction")
	private AuditAction action;
	
	@Column(name="descrizione")
	private String descrizione;
	
	@Column(name="codappmodify")
	private String codiceApplicazione;

	@Column(name="codfisc")
	private String codiceFiscaleOperatore;

	public CsiLogAudit() {
	}

	public Long getIdLog() {
		return idLog;
	}

	public void setIdLog(Long idLog) {
		this.idLog = idLog;
	}

	public Timestamp getDataOra() {
		return dataOra;
	}

	public void setDataOra(Timestamp dataOra) {
		this.dataOra = dataOra;
	}

	public String getIdApp() {
		return idApp;
	}

	public void setIdApp(String idApp) {
		this.idApp = idApp;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getUtente() {
		return utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

	public String getOperazione() {
		return operazione;
	}

	public void setOperazione(String operazione) {
		this.operazione = operazione;
	}

	public String getOggOper() {
		return oggOper;
	}

	public void setOggOper(String oggOper) {
		this.oggOper = oggOper;
	}

	public String getKeyOper() {
		return keyOper;
	}

	public void setKeyOper(String keyOper) {
		this.keyOper = keyOper;
	}

	public AuditAction getAction() {
		return action;
	}

	public void setAction(AuditAction action) {
		this.action = action;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCodiceApplicazione() {
		return codiceApplicazione;
	}

	public void setCodiceApplicazione(String codiceApplicazione) {
		this.codiceApplicazione = codiceApplicazione;
	}

	public String getCodiceFiscaleOperatore() {
		return codiceFiscaleOperatore;
	}

	public void setCodiceFiscaleOperatore(String codiceFiscaleOperatore) {
		this.codiceFiscaleOperatore = codiceFiscaleOperatore;
	}

	@Override
	public String toString() {
		return "CsiLogAudit [idLog=" + idLog + ", dataOra=" + dataOra + ", idApp=" + idApp + ", ipAddress=" + ipAddress
				+ ", utente=" + utente + ", operazione=" + operazione + ", oggOper=" + oggOper + ", keyOper=" + keyOper
				+ ", action=" + action + ", descrizione=" + descrizione + ", codiceApplicazione=" + codiceApplicazione
				+ ", codiceFiscaleOperatore=" + codiceFiscaleOperatore + "]";
	}
}
