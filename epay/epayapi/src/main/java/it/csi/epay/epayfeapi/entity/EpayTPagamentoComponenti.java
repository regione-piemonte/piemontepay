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
@Table ( name = "epay_t_pagamento_componenti" )
@SuppressWarnings ( "unused" )
public class EpayTPagamentoComponenti implements Serializable {

	private static final long serialVersionUID = -811318281310578279L;

	@Id
	@SequenceGenerator ( name = "EPAY_T_PAGAMENTO_COMPONENTI_IDCOMPONENTE_GENERATOR", allocationSize = 1,
					sequenceName = "EPAY_T_PAGAMENTO_COMPONENTI_ID_COMPONENTE_SEQ" )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "EPAY_T_PAGAMENTO_COMPONENTI_IDCOMPONENTE_GENERATOR" )
	@Column ( name = "id_componente", unique = true, nullable = false )
	private Long idComponente;

	@Column ( nullable = false, length = 140 )
	private String causale;

	@Column ( name = "dati_specifici_riscossione", nullable = false, length = 140 )
	private String datiSpecificiRiscossione;

	@Column ( name = "anno_accertamento" )
	private Integer annoAccertamento;

	@Column ( name = "numero_accertamento", length = 35 )
	private String numeroAccertamento;

	@Column ( nullable = false, precision = 10, scale = 2 )
	private BigDecimal importo;

	@Column ( nullable = false )
	private Integer progressivo;

	@Column ( name = "utente_ultima_modifica", nullable = false, length = 100 )
	private String utenteUltimaModifica;

	@Column ( name = "codice_tributo" )
	private String codiceTributo;

	@ManyToOne
	@JoinColumn ( name = "id_pagamento", nullable = false )
	private EpayTPagamento epayTPagamento;

	public EpayTPagamentoComponenti () {
	}

	public Long getIdComponente () {
		return idComponente;
	}

	public void setIdComponente ( Long idComponente ) {
		this.idComponente = idComponente;
	}

	public String getCausale () {
		return causale;
	}

	public void setCausale ( String causale ) {
		this.causale = causale;
	}

	public String getDatiSpecificiRiscossione () {
		return datiSpecificiRiscossione;
	}

	public void setDatiSpecificiRiscossione ( String datiSpecificiRiscossione ) {
		this.datiSpecificiRiscossione = datiSpecificiRiscossione;
	}

	public Integer getAnnoAccertamento () {
		return annoAccertamento;
	}

	public void setAnnoAccertamento ( Integer annoAccertamento ) {
		this.annoAccertamento = annoAccertamento;
	}

	public String getNumeroAccertamento () {
		return numeroAccertamento;
	}

	public void setNumeroAccertamento ( String numeroAccertamento ) {
		this.numeroAccertamento = numeroAccertamento;
	}

	public BigDecimal getImporto () {
		return importo;
	}

	public void setImporto ( BigDecimal importo ) {
		this.importo = importo;
	}

	public Integer getProgressivo () {
		return progressivo;
	}

	public void setProgressivo ( Integer progressivo ) {
		this.progressivo = progressivo;
	}

	public String getUtenteUltimaModifica () {
		return utenteUltimaModifica;
	}

	public void setUtenteUltimaModifica ( String utenteUltimaModifica ) {
		this.utenteUltimaModifica = utenteUltimaModifica;
	}

	public EpayTPagamento getEpayTPagamento () {
		return epayTPagamento;
	}

	public void setEpayTPagamento ( EpayTPagamento epayTPagamento ) {
		this.epayTPagamento = epayTPagamento;
	}

	public String getCodiceTributo () {
		return codiceTributo;
	}

	public void setCodiceTributo ( String codiceTributo ) {
		this.codiceTributo = codiceTributo;
	}

	@Override
	public String toString () {
		return "{ " +
			"idComponente:" + idComponente +
			", causale:" + causale +
			", datiSpecificiRiscossione:" + datiSpecificiRiscossione +
			", annoAccertamento:" + annoAccertamento +
			", numeroAccertamento:" + numeroAccertamento +
			", importo:" + importo +
			", progressivo:" + progressivo +
			", utenteUltimaModifica:" + utenteUltimaModifica +
			", codiceTributo:" + codiceTributo +
			// non esporre epayTPagamento
			" }";
	}
}
