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
 * The persistent class for the flusso_singolo_pagamento database table.
 * 
 */
@Entity
@Table(name="flusso_singolo_pagamento")
@NamedQuery(name="FlussoSingoloPagamento.findAll", query="SELECT f FROM FlussoSingoloPagamento f")
public class FlussoSingoloPagamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FLUSSO_SINGOLO_PAGAMENTO_ID_GENERATOR", sequenceName="FLUSSO_SINGOLO_PAGAMENTO_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FLUSSO_SINGOLO_PAGAMENTO_ID_GENERATOR")
	private Integer id;

	private String codiceesitosingolopagamento;

	private Timestamp dataesitosingolopagamento;

	private Timestamp datainserimento;

	private Timestamp datamodifica;

	@Column(name="id_flusso")
	private Integer idFlusso;

	private String identificativounivocoriscossione;

	private Integer indicedatisingolopagamento;

	private String iuv;

	private BigDecimal singoloimportopagato;

	//bi-directional many-to-one association to Application
	@ManyToOne(fetch=FetchType.LAZY)
	private Application application;

	public FlussoSingoloPagamento() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodiceesitosingolopagamento() {
		return this.codiceesitosingolopagamento;
	}

	public void setCodiceesitosingolopagamento(String codiceesitosingolopagamento) {
		this.codiceesitosingolopagamento = codiceesitosingolopagamento;
	}

	public Timestamp getDataesitosingolopagamento() {
		return this.dataesitosingolopagamento;
	}

	public void setDataesitosingolopagamento(Timestamp dataesitosingolopagamento) {
		this.dataesitosingolopagamento = dataesitosingolopagamento;
	}

	public Timestamp getDatainserimento() {
		return this.datainserimento;
	}

	public void setDatainserimento(Timestamp datainserimento) {
		this.datainserimento = datainserimento;
	}

	public Timestamp getDatamodifica() {
		return this.datamodifica;
	}

	public void setDatamodifica(Timestamp datamodifica) {
		this.datamodifica = datamodifica;
	}

	public Integer getIdFlusso() {
		return this.idFlusso;
	}

	public void setIdFlusso(Integer idFlusso) {
		this.idFlusso = idFlusso;
	}

	public String getIdentificativounivocoriscossione() {
		return this.identificativounivocoriscossione;
	}

	public void setIdentificativounivocoriscossione(String identificativounivocoriscossione) {
		this.identificativounivocoriscossione = identificativounivocoriscossione;
	}

	public Integer getIndicedatisingolopagamento() {
		return this.indicedatisingolopagamento;
	}

	public void setIndicedatisingolopagamento(Integer indicedatisingolopagamento) {
		this.indicedatisingolopagamento = indicedatisingolopagamento;
	}

	public String getIuv() {
		return this.iuv;
	}

	public void setIuv(String iuv) {
		this.iuv = iuv;
	}

	public BigDecimal getSingoloimportopagato() {
		return this.singoloimportopagato;
	}

	public void setSingoloimportopagato(BigDecimal singoloimportopagato) {
		this.singoloimportopagato = singoloimportopagato;
	}

	public Application getApplication() {
		return this.application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

}
