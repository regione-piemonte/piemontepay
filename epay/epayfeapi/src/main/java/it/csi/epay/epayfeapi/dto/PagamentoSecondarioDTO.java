/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings ( "unused" )
public class PagamentoSecondarioDTO implements Serializable {

	private static final long serialVersionUID = -7611137639999810866L;

	private Long idPagamento;

	private Long idPagamentoSecondario;

	private BigDecimal importoComplessivo;

	private TipoPagamentoDTO tipoPagamento;

	private String identificativoDominio;

	private List<PagamentoComponentiDTO> componenti;

	private String causale;

	public Long getIdPagamento () {
		return idPagamento;
	}

	public void setIdPagamento ( Long idPagamento ) {
		this.idPagamento = idPagamento;
	}

	public Long getIdPagamentoSecondario () {
		return idPagamentoSecondario;
	}

	public void setIdPagamentoSecondario ( Long idPagamentoSecondario ) {
		this.idPagamentoSecondario = idPagamentoSecondario;
	}

	public BigDecimal getImportoComplessivo () {
		return importoComplessivo;
	}

	public void setImportoComplessivo ( BigDecimal importoComplessivo ) {
		this.importoComplessivo = importoComplessivo;
	}

	public TipoPagamentoDTO getTipoPagamento () {
		return tipoPagamento;
	}

	public void setTipoPagamento ( TipoPagamentoDTO tipoPagamento ) {
		this.tipoPagamento = tipoPagamento;
	}

	public String getIdentificativoDominio () {
		return identificativoDominio;
	}

	public void setIdentificativoDominio ( String identificativoDominio ) {
		this.identificativoDominio = identificativoDominio;
	}

	public List<PagamentoComponentiDTO> getComponenti () {
		if ( componenti == null ) {
			componenti = new ArrayList<> ();
		}
		return componenti;
	}

	public void setComponenti ( List<PagamentoComponentiDTO> componenti ) {
		this.componenti = componenti;
	}

	public String getCausale () {
		return causale;
	}

	public void setCausale ( String causale ) {
		this.causale = causale;
	}
}
