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
 * The persistent class for the application_temp database table.
 * 
 */
@Entity
@Table(name="application_temp")
@NamedQuery(name="ApplicationTemp.findAll", query="SELECT a FROM ApplicationTemp a")
public class ApplicationTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ApplicationTempPK id;

	private String applicationname;

	private String cliente;

	private String esercemail;

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

	public ApplicationTemp() {
	}

	public ApplicationTempPK getId() {
		return this.id;
	}

	public void setId(ApplicationTempPK id) {
		this.id = id;
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
