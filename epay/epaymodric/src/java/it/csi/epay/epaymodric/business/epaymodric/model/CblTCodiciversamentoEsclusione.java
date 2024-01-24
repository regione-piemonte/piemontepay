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
 * The persistent class for the cbl_t_codiciversamento_esclusione database table.
 * 
 */
@Entity
@Table(name="cbl_t_codiciversamento_esclusione",schema="epaymodric")
@NamedQuery(name="CblTCodiciversamentoEsclusione.findAll", query="SELECT c FROM CblTCodiciversamentoEsclusione c")
public class CblTCodiciversamentoEsclusione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CBL_T_CODICIVERSAMENTO_ESCLUSIONE_ID_GENERATOR", sequenceName="epaymodric.CBL_T_CODICIVERSAMENTO_ESCLUSIONE_ID_SEQ" ,schema="epaymodric", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CBL_T_CODICIVERSAMENTO_ESCLUSIONE_ID_GENERATOR")
	private Long id;

	@Column(name="codice_versamento")
	private String codiceVersamento;

	@Column(name="id_ente")
	private String idEnte;

	private String motivazione;

	public CblTCodiciversamentoEsclusione() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodiceVersamento() {
		return this.codiceVersamento;
	}

	public void setCodiceVersamento(String codiceVersamento) {
		this.codiceVersamento = codiceVersamento;
	}

	public String getIdEnte() {
		return this.idEnte;
	}

	public void setIdEnte(String idEnte) {
		this.idEnte = idEnte;
	}

	public String getMotivazione() {
		return this.motivazione;
	}

	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
	}

}
