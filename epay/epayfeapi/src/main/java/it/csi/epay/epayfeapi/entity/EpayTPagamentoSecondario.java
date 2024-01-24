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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Table ( name = "epay_t_pagamento_secondario" )
@SuppressWarnings ( "unused" )
public class EpayTPagamentoSecondario implements Serializable {

	private static final long serialVersionUID = 3590098618953851732L;

	@Id
	@SequenceGenerator ( name = "EPAY_T_PAGAMENTO_SECONDARIO_ID_GENERATOR", allocationSize = 1, sequenceName = "EPAY_T_PAGAMENTO_SECONDARIO_ID_SEQ" )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "EPAY_T_PAGAMENTO_SECONDARIO_ID_GENERATOR" )
	@Column ( name = "id_pagamento_secondario", unique = true, nullable = false )
	private Long idPagamentoSecondario;

	private String causale;

	@Column ( name = "importo_complessivo" )
	private BigDecimal importoComplessivo;

	@OneToMany ( mappedBy = "epayTPagamentoSecondario" )
	private List<EpayTPagamentoSecondarioComponenti> epayTPagamentoSecondarioComponentis;

	@ManyToOne
	@JoinColumn ( name = "id_pagamento" )
	private EpayTPagamento epayTPagamento;

	@ManyToOne
	@JoinColumn ( name = "id_tipo_pagamento" )
	private EpayTTipoPagamento epayTTipoPagamento;

	@Column ( name = "identificativo_dominio" )
	private String identificativoDominio;

	public EpayTPagamentoSecondario () {
	}

	public Long getIdPagamentoSecondario () {
		return idPagamentoSecondario;
	}

	public void setIdPagamentoSecondario ( Long idPagamentoSecondario ) {
		this.idPagamentoSecondario = idPagamentoSecondario;
	}

	public String getCausale () {
		return causale;
	}

	public void setCausale ( String causale ) {
		this.causale = causale;
	}

	public BigDecimal getImportoComplessivo () {
		return importoComplessivo;
	}

	public void setImportoComplessivo ( BigDecimal importoComplessivo ) {
		this.importoComplessivo = importoComplessivo;
	}

	public List<EpayTPagamentoSecondarioComponenti> getEpayTPagamentoSecondarioComponentis () {
		return epayTPagamentoSecondarioComponentis;
	}

	public void setEpayTPagamentoSecondarioComponentis ( List<EpayTPagamentoSecondarioComponenti> epayTPagamentoSecondarioComponentis ) {
		this.epayTPagamentoSecondarioComponentis = epayTPagamentoSecondarioComponentis;
	}

	public EpayTPagamento getEpayTPagamento () {
		return epayTPagamento;
	}

	public void setEpayTPagamento ( EpayTPagamento epayTPagamento ) {
		this.epayTPagamento = epayTPagamento;
	}

	public EpayTTipoPagamento getEpayTTipoPagamento () {
		return epayTTipoPagamento;
	}

	public void setEpayTTipoPagamento ( EpayTTipoPagamento epayTTipoPagamento ) {
		this.epayTTipoPagamento = epayTTipoPagamento;
	}

	public String getIdentificativoDominio () {
		return identificativoDominio;
	}

	public void setIdentificativoDominio ( String identificativoDominio ) {
		this.identificativoDominio = identificativoDominio;
	}

	@Override
	public String toString () {
		return "{ " +
			"idPagamentoSecondario:" + idPagamentoSecondario +
			", causale:" + causale +
			", importoComplessivo:" + importoComplessivo +
			// non esporre epayTPagamentoSecondarioComponentis
			// non esporre epayTPagamento
			// non esporre epayTTipoPagamento
			", identificativoDominio:" + identificativoDominio +
			" }";
	}
}
