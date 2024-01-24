/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the cbl_t_email database table.
 * 
 */
@Entity
@Table(name="cbl_t_email",schema="epaymodric")
@NamedQuery(name="CblTEmail.findAll", query="SELECT c FROM CblTEmail c")
public class CblTEmail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CBL_T_EMAIL_ID_GENERATOR", sequenceName="epaymodric.CBL_T_EMAIL_ID_SEQ" ,schema="epaymodric", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CBL_T_EMAIL_ID_GENERATOR")
	private Long id;

	private String body;

	private Timestamp data;

	private String error;

	@Column(name="id_ente")
	private String idEnte;

	private String recipient;

	private Long retry;

	private String stato;

	private String subject;

	public CblTEmail() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Timestamp getData() {
		return this.data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public String getError() {
		return this.error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getIdEnte() {
		return this.idEnte;
	}

	public void setIdEnte(String idEnte) {
		this.idEnte = idEnte;
	}

	public String getRecipient() {
		return this.recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public Long getRetry() {
		return this.retry;
	}

	public void setRetry(Long retry) {
		this.retry = retry;
	}

	public String getStato() {
		return this.stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}
