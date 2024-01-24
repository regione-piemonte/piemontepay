/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;


@Entity
@Table ( name = "epay_t_dati_singoli_versamenti" )
@SuppressWarnings ( "unused" )
public class EpayTDatiSingoliVersamenti implements Serializable {

	private static final long serialVersionUID = 4679406437854010276L;

	@Id
	@SequenceGenerator ( name = "EPAY_T_DATI_SINGOLI_VERSAMENTI_ID_GENERATOR", allocationSize = 1, sequenceName = "EPAY_T_DATI_SINGOLI_VERSAMENTI_ID_SEQ" )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "EPAY_T_DATI_SINGOLI_VERSAMENTI_ID_GENERATOR" )
	@Column ( unique = true, nullable = false )
	private Long id;

	@Column ( name = "dati_specifici_riscossione", nullable = false, length = 140 )
	private String datiSpecificiRiscossione;

	@Column ( name = "descrizione_causale", length = 100 )
	private String descrizioneCausale;

	@Column ( nullable = false, precision = 10, scale = 2 )
	private BigDecimal importo;

	@ManyToOne
	@JoinColumn ( name = "id_pagamento", nullable = false )
	private EpayTPagamento epayTPagamento;

	public EpayTDatiSingoliVersamenti () {
	}

	public Long getId () {
		return id;
	}

	public void setId ( Long id ) {
		this.id = id;
	}

	public String getDatiSpecificiRiscossione () {
		return datiSpecificiRiscossione;
	}

	public void setDatiSpecificiRiscossione ( String datiSpecificiRiscossione ) {
		this.datiSpecificiRiscossione = datiSpecificiRiscossione;
	}

	public String getDescrizioneCausale () {
		return descrizioneCausale;
	}

	public void setDescrizioneCausale ( String descrizioneCausale ) {
		this.descrizioneCausale = descrizioneCausale;
	}

	public BigDecimal getImporto () {
		return importo;
	}

	public void setImporto ( BigDecimal importo ) {
		this.importo = importo;
	}

	public EpayTPagamento getEpayTPagamento () {
		return epayTPagamento;
	}

	public void setEpayTPagamento ( EpayTPagamento epayTPagamento ) {
		this.epayTPagamento = epayTPagamento;
	}

	@Override
	public String toString () {
		return "{ " +
			"id:" + id +
			", datiSpecificiRiscossione:" + datiSpecificiRiscossione +
			", descrizioneCausale:" + descrizioneCausale +
			", importo:" + importo +
			// non esporre epayTPagamento
			" }";
	}
}
