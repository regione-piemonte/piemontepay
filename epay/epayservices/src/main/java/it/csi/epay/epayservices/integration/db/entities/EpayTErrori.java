/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import java.io.Serializable;
import java.sql.Timestamp;

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
 * The persistent class for the epay_t_errori database table.
 * 
 */
@Entity
@Table(name="epay_t_errori")
public class EpayTErrori implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EPAY_T_ERRORI_ID_GENERATOR", allocationSize=1, sequenceName="EPAY_T_ERRORI_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EPAY_T_ERRORI_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(nullable=false)
	private Timestamp data;

	@Column(name="desc_correzione", length=500)
	private String descCorrezione;

	@Column(nullable=false, length=500)
	private String descrizione;

	@Column(name="id_pagamento")
	private Long idPagamento;

	@Column(name="id_registro_versamento")
	private Long idRegistroVersamento;

	@Column(name="id_transazione")
	private Long idTransazione;

	@Column(length=25)
	private String iuv;
	
	@Column(nullable=false, length=200)
	private String applicativo;

	//bi-directional many-to-one association to EpayDStatoErrore
	@ManyToOne
	@JoinColumn(name="id_stato_errore", nullable=false)
	private EpayDStatoErrore epayDStatoErrore;

	public EpayTErrori() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getData() {
		return this.data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public String getDescCorrezione() {
		return this.descCorrezione;
	}

	public void setDescCorrezione(String descCorrezione) {
		this.descCorrezione = descCorrezione;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Long getIdPagamento() {
		return this.idPagamento;
	}

	public void setIdPagamento(Long idPagamento) {
		this.idPagamento = idPagamento;
	}

	public Long getIdRegistroVersamento() {
		return this.idRegistroVersamento;
	}

	public void setIdRegistroVersamento(Long idRegistroVersamento) {
		this.idRegistroVersamento = idRegistroVersamento;
	}

	public Long getIdTransazione() {
		return this.idTransazione;
	}

	public void setIdTransazione(Long idTransazione) {
		this.idTransazione = idTransazione;
	}

	public String getIuv() {
		return this.iuv;
	}

	public void setIuv(String iuv) {
		this.iuv = iuv;
	}

	public EpayDStatoErrore getEpayDStatoErrore() {
		return this.epayDStatoErrore;
	}

	public void setEpayDStatoErrore(EpayDStatoErrore epayDStatoErrore) {
		this.epayDStatoErrore = epayDStatoErrore;
	}
	
	/**
	 * @return the applicativo
	 */
	public String getApplicativo() {
		return applicativo;
	}

	/**
	 * @param applicativo the applicativo to set
	 */
	public void setApplicativo(String applicativo) {
		this.applicativo = applicativo;
	}
}
