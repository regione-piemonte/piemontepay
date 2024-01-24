/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the epay_t_dati_singoli_versamenti database table.
 * 
 */
@Entity
@Table(name="epay_t_dati_singoli_versamenti")
public class EpayTDatiSingoliVersamenti implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EPAY_T_DATI_SINGOLI_VERSAMENTI_ID_GENERATOR", allocationSize=1, sequenceName="EPAY_T_DATI_SINGOLI_VERSAMENTI_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EPAY_T_DATI_SINGOLI_VERSAMENTI_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="dati_specifici_riscossione", nullable=false, length=140)
	private String datiSpecificiRiscossione;

	@Column(name="descrizione_causale", length=100)
	private String descrizioneCausale;

	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal importo;

	//bi-directional many-to-one association to EpayTPagamento
	@ManyToOne
	@JoinColumn(name="id_pagamento", nullable=false)
	private EpayTPagamento epayTPagamento;

	public EpayTDatiSingoliVersamenti() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDatiSpecificiRiscossione() {
		return this.datiSpecificiRiscossione;
	}

	public void setDatiSpecificiRiscossione(String datiSpecificiRiscossione) {
		this.datiSpecificiRiscossione = datiSpecificiRiscossione;
	}

	public String getDescrizioneCausale() {
		return this.descrizioneCausale;
	}

	public void setDescrizioneCausale(String descrizioneCausale) {
		this.descrizioneCausale = descrizioneCausale;
	}

	public BigDecimal getImporto() {
		return this.importo;
	}

	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	public EpayTPagamento getEpayTPagamento() {
		return this.epayTPagamento;
	}

	public void setEpayTPagamento(EpayTPagamento epayTPagamento) {
		this.epayTPagamento = epayTPagamento;
	}

}
