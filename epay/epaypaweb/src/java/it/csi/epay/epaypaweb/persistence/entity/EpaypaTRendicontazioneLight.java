/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the epaypa_t_rendicontazione database table.
 * 
 */
@Entity
@Table(name="epaypa_t_rendicontazione")
public class EpaypaTRendicontazioneLight implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_flusso")
	private Long idFlusso;

	@Column(name="cod_bic_psp")
	private String codBicPsp;

	@Column(name="cod_id_univoco_mittente")
	private String codIdUnivocoMittente;

	@Column(name="denominazione_mittente")
	private String denominazioneMittente;

	@Column(name="dt_ora_flusso")
	private Timestamp dtOraFlusso;

	@Column(name="dt_regolamento")
	private Timestamp dtRegolamento;

	@Column(name="id_flusso_rendicontazione")
	private String idFlussoRendicontazione;

	@Column(name="id_univoco_regolamento")
	private String idUnivocoRegolamento;

	@Column(name="tipo_id_mittente")
	private String tipoIdMittente;

	public EpaypaTRendicontazioneLight() {
	}

	public Long getIdFlusso() {
		return this.idFlusso;
	}

	public void setIdFlusso(Long idFlusso) {
		this.idFlusso = idFlusso;
	}

	public String getCodBicPsp() {
		return this.codBicPsp;
	}

	public void setCodBicPsp(String codBicPsp) {
		this.codBicPsp = codBicPsp;
	}

	public String getCodIdUnivocoMittente() {
		return this.codIdUnivocoMittente;
	}

	public void setCodIdUnivocoMittente(String codIdUnivocoMittente) {
		this.codIdUnivocoMittente = codIdUnivocoMittente;
	}

	public String getDenominazioneMittente() {
		return this.denominazioneMittente;
	}

	public void setDenominazioneMittente(String denominazioneMittente) {
		this.denominazioneMittente = denominazioneMittente;
	}

	public Timestamp getDtOraFlusso() {
		return this.dtOraFlusso;
	}

	public void setDtOraFlusso(Timestamp dtOraFlusso) {
		this.dtOraFlusso = dtOraFlusso;
	}

	public Timestamp getDtRegolamento() {
		return this.dtRegolamento;
	}

	public void setDtRegolamento(Timestamp dtRegolamento) {
		this.dtRegolamento = dtRegolamento;
	}

	public String getIdFlussoRendicontazione() {
		return idFlussoRendicontazione;
	}

	public void setIdFlussoRendicontazione(String idFlussoRendicontazione) {
		this.idFlussoRendicontazione = idFlussoRendicontazione;
	}

	public String getIdUnivocoRegolamento() {
		return this.idUnivocoRegolamento;
	}

	public void setIdUnivocoRegolamento(String idUnivocoRegolamento) {
		this.idUnivocoRegolamento = idUnivocoRegolamento;
	}

	public String getTipoIdMittente() {
		return this.tipoIdMittente;
	}

	public void setTipoIdMittente(String tipoIdMittente) {
		this.tipoIdMittente = tipoIdMittente;
	}

}
