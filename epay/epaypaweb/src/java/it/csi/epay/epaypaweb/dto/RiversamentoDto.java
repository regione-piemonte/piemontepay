/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import static it.csi.epay.epaypaweb.util.Util.date2strdatetime;
import static it.csi.epay.epaypaweb.util.Util.quote;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import it.csi.epay.epaypaweb.enumeration.EsitoRiversamentoEnum;

/** dto facade <-> business <-> persistence */
public class RiversamentoDto extends ElementoFlussoDto implements Serializable {
	private static final long serialVersionUID = 1L;

	// dati del riversamento
	private Long idFlusso;
	private String iuv;
	private String iur;
	private Integer indicePagamento;
	private BigDecimal importoPagato;
	private EsitoRiversamentoEnum esito;
	private Date dataEsito;

	public RiversamentoDto() {
		super();
	}

	public RiversamentoDto(Long id) {
		super(id);
	}

	public Long getIdFlusso() {
		return idFlusso;
	}

	public void setIdFlusso(Long idFlusso) {
		this.idFlusso = idFlusso;
	}

	public String getIUV() {
		return iuv;
	}

	public void setIUV(String iuv) {
		this.iuv = iuv;
	}

	public String getIUR() {
		return iur;
	}

	public void setIUR(String iur) {
		this.iur = iur;
	}

	public Integer getIndicePagamento() {
		return indicePagamento;
	}

	public void setIndicePagamento(Integer indicePagamento) {
		this.indicePagamento = indicePagamento;
	}

	public BigDecimal getImportoPagato() {
		return importoPagato;
	}

	public void setImportoPagato(BigDecimal importoPagato) {
		this.importoPagato = importoPagato;
	}

	public EsitoRiversamentoEnum getEsito() {
		return esito;
	}

	public void setEsito(EsitoRiversamentoEnum esito) {
		this.esito = esito;
	}

	public String getCodAndDesEsito() {
		if (esito != null)
			return esito.getId() + " - " + esito.getDes();
		else
			return "";
	}

	public Date getDataEsito() {
		return dataEsito;
	}

	public void setDataEsito(Date dataEsito) {
		this.dataEsito = dataEsito;
	}

	@Override
	public String toString() {
		return "{ " + toSuperString() + " }";
	}

	public String toSuperString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append(super.toSuperString()).append(COMMA);
		s.append("idFlusso:").append(idFlusso).append(COMMA);
		s.append("iuv:").append(quote(iuv)).append(COMMA);
		s.append("iur:").append(quote(iur)).append(COMMA);
		s.append("indicePagamento:").append(indicePagamento).append(COMMA);
		s.append("importoPagato:").append(importoPagato).append(COMMA);
		s.append("esito:").append(esito).append(COMMA);
		s.append("dataEsito:").append(date2strdatetime(dataEsito));
		return s.toString();
	}

}
