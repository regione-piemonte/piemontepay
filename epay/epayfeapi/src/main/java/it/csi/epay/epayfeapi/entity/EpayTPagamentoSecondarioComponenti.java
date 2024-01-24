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
@Table ( name = "epay_t_pagamento_secondario_componenti" )
@SuppressWarnings ( "unused" )
public class EpayTPagamentoSecondarioComponenti implements Serializable {

	private static final long serialVersionUID = 6572029153482321096L;

	@Id
	@SequenceGenerator ( name = "EPAY_T_PAGAMENTO_SECONDARIO_COMPONENTI_ID_GENERATOR", allocationSize = 1,
					sequenceName = "EPAY_T_PAGAMENTO_SECONDARIO_COMPONENTI_ID_SEQ" )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "EPAY_T_PAGAMENTO_SECONDARIO_COMPONENTI_ID_GENERATOR" )
	@Column ( name = "id_componente_secondaria", unique = true, nullable = false )
	private Long idComponente;

	@Column ( name = "anno_accertamento" )
	private Integer annoAccertamento;

	@Column ( nullable = false, length = 140 )
	private String causale;

	@Column ( name = "dati_specifici_riscossione", nullable = false, length = 140 )
	private String datiSpecificiRiscossione;

	@Column ( nullable = false, precision = 10, scale = 2 )
	private BigDecimal importo;

	@Column ( name = "numero_accertamento", nullable = false, length = 35 )
	private String numeroAccertamento;

	@Column ( nullable = false )
	private Integer progressivo;

	@Column ( name = "utente_ultima_modifica", nullable = false, length = 100 )
	private String utenteUltimaModifica;

	@Column ( name = "codice_tributo" )
	private String codiceTributo;

	@ManyToOne
	@JoinColumn ( name = "id_pagamento_secondario" )
	private EpayTPagamentoSecondario epayTPagamentoSecondario;

	public EpayTPagamentoSecondarioComponenti () {
	}

	public Long getIdComponente () {
		return idComponente;
	}

	public void setIdComponente ( Long idComponente ) {
		this.idComponente = idComponente;
	}

	public Integer getAnnoAccertamento () {
		return annoAccertamento;
	}

	public void setAnnoAccertamento ( Integer annoAccertamento ) {
		this.annoAccertamento = annoAccertamento;
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

	public BigDecimal getImporto () {
		return importo;
	}

	public void setImporto ( BigDecimal importo ) {
		this.importo = importo;
	}

	public String getNumeroAccertamento () {
		return numeroAccertamento;
	}

	public void setNumeroAccertamento ( String numeroAccertamento ) {
		this.numeroAccertamento = numeroAccertamento;
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

	public String getCodiceTributo () {
		return codiceTributo;
	}

	public void setCodiceTributo ( String codiceTributo ) {
		this.codiceTributo = codiceTributo;
	}

	public EpayTPagamentoSecondario getEpayTPagamentoSecondario () {
		return epayTPagamentoSecondario;
	}

	public void setEpayTPagamentoSecondario ( EpayTPagamentoSecondario epayTPagamentoSecondario ) {
		this.epayTPagamentoSecondario = epayTPagamentoSecondario;
	}

	@Override
	public String toString () {
		return "{ " +
			"idComponente:" + idComponente +
			", annoAccertamento:" + annoAccertamento +
			", causale:" + causale +
			", datiSpecificiRiscossione:" + datiSpecificiRiscossione +
			", importo:" + importo +
			", numeroAccertamento:" + numeroAccertamento +
			", progressivo:" + progressivo +
			", utenteUltimaModifica:" + utenteUltimaModifica +
			", codiceTributo:" + codiceTributo +
			// non esporre epayTPagamentoSecondarioComponentis
			" }";
	}
}
