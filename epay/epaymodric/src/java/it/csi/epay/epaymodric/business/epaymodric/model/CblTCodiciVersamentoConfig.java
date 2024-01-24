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
 * The persistent class for the cbl_t_codici_versamento_config database table.
 * 
 */
@Entity
@Table(name="cbl_t_codici_versamento_config",schema="epaymodric")
@NamedQuery(name="CblTCodiciVersamentoConfig.findAll", query="SELECT c FROM CblTCodiciVersamentoConfig c")
public class CblTCodiciVersamentoConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CBL_T_CODICI_VERSAMENTO_CONFIG_ID_GENERATOR", sequenceName="CBL_T_CODICI_VERSAMENTO_ID_GENERATOR")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CBL_T_CODICI_VERSAMENTO_CONFIG_ID_GENERATOR")
	private Long id;

	@Column(name="codice_versamento")
	private String codiceVersamento;

	@Column(name="flg_applicazione_epay")
	private Boolean flgApplicazioneEpay;

	@Column(name="id_ente")
	private String idEnte;

	private String note;
	
	public CblTCodiciVersamentoConfig() {
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

	public Boolean getFlgApplicazioneEpay() {
		return this.flgApplicazioneEpay;
	}

	public void setFlgApplicazioneEpay(Boolean flgApplicazioneEpay) {
		this.flgApplicazioneEpay = flgApplicazioneEpay;
	}

	public String getIdEnte() {
		return this.idEnte;
	}

	public void setIdEnte(String idEnte) {
		this.idEnte = idEnte;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
