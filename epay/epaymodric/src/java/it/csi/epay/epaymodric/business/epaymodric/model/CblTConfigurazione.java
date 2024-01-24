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
 * The persistent class for the cbl_t_configurazione database table.
 * 
 */
@Entity
@Table(name="cbl_t_configurazione",schema="epaymodric")
@NamedQuery(name="CblTConfigurazione.findAll", query="SELECT c FROM CblTConfigurazione c")
public class CblTConfigurazione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CBL_T_CONFIGURAZIONE_ID_GENERATOR", sequenceName="epaymodric.CBL_T_CONFIGURAZIONE_ID_SEQ" ,schema="epaymodric", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CBL_T_CONFIGURAZIONE_ID_GENERATOR")
	private Long id;

	@Column(name="id_ente")
	private String idEnte;

	@Column(name="nome_attributo")
	private String nomeAttributo;

	private String valore;

	public CblTConfigurazione() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdEnte() {
		return this.idEnte;
	}

	public void setIdEnte(String idEnte) {
		this.idEnte = idEnte;
	}

	public String getNomeAttributo() {
		return this.nomeAttributo;
	}

	public void setNomeAttributo(String nomeAttributo) {
		this.nomeAttributo = nomeAttributo;
	}

	public String getValore() {
		return this.valore;
	}

	public void setValore(String valore) {
		this.valore = valore;
	}

}
