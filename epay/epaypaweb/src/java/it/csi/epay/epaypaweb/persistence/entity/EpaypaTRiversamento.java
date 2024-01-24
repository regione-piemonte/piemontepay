/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the epaypa_t_dati_riversamento database table.
 * 
 */
@Entity
@Table(name="epaypa_t_dati_riversamento")
@NamedQueries({
	@NamedQuery(
			name = "EpaypaTRiversamento.findAllByIdFlusso",
			query = "SELECT e FROM EpaypaTRiversamento e WHERE epaypaTRendicontazione.idFlusso = :idFlusso"),
	@NamedQuery(
			name = "EpaypaTRiversamento.findAll",
			query = "SELECT e FROM EpaypaTRiversamento e")
})
public class EpaypaTRiversamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_dati_riversamento")
	private Long idRiversamento;

	@Column(name="cod_esito")
	private String codEsito;

	@Column(name="dt_esito")
	private Timestamp dtEsito;

	@Column(name="importo_pagato")
	private BigDecimal importoPagato;

	@Column(name="indice_dati_rt")
	private Integer indiceDatiRt;

	private String iur;

	private String iuv;

	//bi-directional many-to-one association to EpaypaTRendicontazione
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_flusso", updatable=false, insertable=false, nullable=false)
	private EpaypaTRendicontazione epaypaTRendicontazione;
	
	public EpaypaTRiversamento() {
	}

	public Long getIdRiversamento() {
		return this.idRiversamento;
	}

	public void setIdRiversamento(Long idRiversamento) {
		this.idRiversamento = idRiversamento;
	}

	public String getCodEsito() {
		return this.codEsito;
	}

	public void setCodEsito(String codEsito) {
		this.codEsito = codEsito;
	}

	public Timestamp getDtEsito() {
		return this.dtEsito;
	}

	public void setDtEsito(Timestamp dtEsito) {
		this.dtEsito = dtEsito;
	}

	public BigDecimal getImportoPagato() {
		return this.importoPagato;
	}

	public void setImportoPagato(BigDecimal importoPagato) {
		this.importoPagato = importoPagato;
	}

	public Integer getIndiceDatiRt() {
		return this.indiceDatiRt;
	}

	public void setIndiceDatiRt(Integer indiceDatiRt) {
		this.indiceDatiRt = indiceDatiRt;
	}

	public String getIur() {
		return this.iur;
	}

	public void setIur(String iur) {
		this.iur = iur;
	}

	public String getIuv() {
		return this.iuv;
	}

	public void setIuv(String iuv) {
		this.iuv = iuv;
	}

	public EpaypaTRendicontazione getEpaypaTRendicontazione() {
		return this.epaypaTRendicontazione;
	}

	public void setEpaypaTRendicontazione(EpaypaTRendicontazione epaypaTRendicontazione) {
		this.epaypaTRendicontazione = epaypaTRendicontazione;
	}

}
