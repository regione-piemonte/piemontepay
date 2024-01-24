/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the application_log database table.
 * 
 */
@Entity
@Table(name="application_log")
@NamedQuery(name="ApplicationLog.findAll", query="SELECT a FROM ApplicationLog a")
public class ApplicationLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="APPLICATION_LOG_SEQ_GENERATOR", sequenceName="APPLICATION_LOG_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="APPLICATION_LOG_SEQ_GENERATOR")
	private Long seq;

	private String applicationname;

	private String cliente;

	private String esercemail;

	private String id;

	private String note;

	private String numeroverde;

	private String progetto;

	@Column(name="redirect_newmdp")
	private BigDecimal redirectNewmdp;

	private String referentecsi;

	@Column(name="valido_al")
	private Timestamp validoAl;

	@Column(name="valido_dal")
	private Timestamp validoDal;

	public ApplicationLog() {
	}

	public Long getSeq() {
		return this.seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getApplicationname() {
		return this.applicationname;
	}

	public void setApplicationname(String applicationname) {
		this.applicationname = applicationname;
	}

	public String getCliente() {
		return this.cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getEsercemail() {
		return this.esercemail;
	}

	public void setEsercemail(String esercemail) {
		this.esercemail = esercemail;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNumeroverde() {
		return this.numeroverde;
	}

	public void setNumeroverde(String numeroverde) {
		this.numeroverde = numeroverde;
	}

	public String getProgetto() {
		return this.progetto;
	}

	public void setProgetto(String progetto) {
		this.progetto = progetto;
	}

	public BigDecimal getRedirectNewmdp() {
		return this.redirectNewmdp;
	}

	public void setRedirectNewmdp(BigDecimal redirectNewmdp) {
		this.redirectNewmdp = redirectNewmdp;
	}

	public String getReferentecsi() {
		return this.referentecsi;
	}

	public void setReferentecsi(String referentecsi) {
		this.referentecsi = referentecsi;
	}

	public Timestamp getValidoAl() {
		return this.validoAl;
	}

	public void setValidoAl(Timestamp validoAl) {
		this.validoAl = validoAl;
	}

	public Timestamp getValidoDal() {
		return this.validoDal;
	}

	public void setValidoDal(Timestamp validoDal) {
		this.validoDal = validoDal;
	}

}
