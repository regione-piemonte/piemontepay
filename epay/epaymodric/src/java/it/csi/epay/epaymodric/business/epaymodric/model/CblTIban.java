/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the cbl_t_iban database table.
 * 
 */
@Entity
@Table(name="cbl_t_iban",schema="epaymodric")
@NamedQuery(name="CblTIban.findAll", query="SELECT c FROM CblTIban c")
public class CblTIban implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CBL_T_IBAN_ID_GENERATOR", sequenceName="epaymodric.CBL_T_IBAN_ID_SEQ" ,schema="epaymodric", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CBL_T_IBAN_ID_GENERATOR")
	private Long id;

	private String iban;

	@Column(name="id_applicativo_mdp")
	private String idApplicativoMdp;

	@Column(name="id_ente")
	private String idEnte;

	public CblTIban() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIban() {
		return this.iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getIdApplicativoMdp() {
		return this.idApplicativoMdp;
	}

	public void setIdApplicativoMdp(String idApplicativoMdp) {
		this.idApplicativoMdp = idApplicativoMdp;
	}

	public String getIdEnte() {
		return this.idEnte;
	}

	public void setIdEnte(String idEnte) {
		this.idEnte = idEnte;
	}

}
