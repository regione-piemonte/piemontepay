/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.bo;

import java.io.Serializable;
import java.sql.Timestamp;

public class LogAudit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer uniqueid;

	private String codappmodify;

	private String codfisc;

	private Timestamp dataOra;

	private String descrizione;

	private String idApp;

	private String ipAddress;

	private String keyOper;

	private String oggOper;

	private String operazione;

	private Integer utente;

	private ActionAudit auditaction;

	public LogAudit(Integer uniqueid, String codappmodify, Timestamp dataOra, String descrizione, Integer utente,
			ActionAudit auditaction) {
		super();
		this.uniqueid = uniqueid;
		this.codappmodify = codappmodify;
		this.dataOra = dataOra;
		this.descrizione = descrizione;
		this.utente = utente;
		this.auditaction = auditaction;
	}

	public LogAudit() {
		
	}

	public Integer getUniqueid() {
		return uniqueid;
	}

	public void setUniqueid(Integer uniqueid) {
		this.uniqueid = uniqueid;
	}

	public String getCodappmodify() {
		return codappmodify;
	}

	public void setCodappmodify(String codappmodify) {
		this.codappmodify = codappmodify;
	}

	public String getCodfisc() {
		return codfisc;
	}

	public void setCodfisc(String codfisc) {
		this.codfisc = codfisc;
	}

	public Timestamp getDataOra() {
		return dataOra;
	}

	public void setDataOra(Timestamp dataOra) {
		this.dataOra = dataOra;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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

	public String getKeyOper() {
		return keyOper;
	}

	public void setKeyOper(String keyOper) {
		this.keyOper = keyOper;
	}

	public String getOggOper() {
		return oggOper;
	}

	public void setOggOper(String oggOper) {
		this.oggOper = oggOper;
	}

	public String getOperazione() {
		return operazione;
	}

	public void setOperazione(String operazione) {
		this.operazione = operazione;
	}

	public Integer getUtente() {
		return utente;
	}

	public void setUtente(Integer utente) {
		this.utente = utente;
	}

	public ActionAudit getAuditaction() {
		return auditaction;
	}

	public void setAuditaction(ActionAudit auditaction) {
		this.auditaction = auditaction;
	}

	@Override
	public String toString() {
		return "LogAudit [uniqueid=" + uniqueid + ", codappmodify=" + codappmodify + ", codfisc=" + codfisc
				+ ", dataOra=" + dataOra + ", descrizione=" + descrizione + ", idApp=" + idApp + ", ipAddress="
				+ ipAddress + ", keyOper=" + keyOper + ", oggOper=" + oggOper + ", operazione=" + operazione
				+ ", utente=" + utente + ", auditaction=" + auditaction + "]";
	}
	
}
