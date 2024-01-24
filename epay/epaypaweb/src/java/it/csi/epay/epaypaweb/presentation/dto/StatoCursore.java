/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.dto;

import java.util.List;

import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;

public class StatoCursore<T> {
	private Long idFlusso;
	private List<Long> idList;
	private Integer numeroPagina;
	private Integer numeroRighePerPagina;
	private Integer numeroTotaleElementi;
	private List<CriterioOrdinamentoDto<T>> criteriOrdinamento;

	//@formatter:off
	public StatoCursore(
			Long idFlusso,
			List<Long> idList,
			Integer numeroPagina,
			Integer numeroRighePerPagina,
			Integer numeroTotaleElementi,
			List<CriterioOrdinamentoDto<T>> criteriOrdinamento)
	{
		this.idFlusso = idFlusso;
		this.idList = idList;
		this.numeroPagina = numeroPagina;
		this.numeroRighePerPagina = numeroRighePerPagina;
		this.numeroTotaleElementi = numeroTotaleElementi;
		this.criteriOrdinamento = criteriOrdinamento;
	}
	//@formatter:on

	public Long getIdFlusso() {
		return idFlusso;
	}

	public void getIdFlusso(Long idFlusso) {
		this.idFlusso = idFlusso;
	}

	public List<Long> getIdList() {
		return idList;
	}

	public void setIdList(List<Long> idList) {
		this.idList = idList;
	}

	public Integer getNumeroPagina() {
		return numeroPagina;
	}

	public void setNumeroPagina(Integer numeroPagina) {
		this.numeroPagina = numeroPagina;
	}

	public Integer getNumeroRighePerPagina() {
		return numeroRighePerPagina;
	}

	public void setNumeroRighePerPagina(Integer numeroRighePerPagina) {
		this.numeroRighePerPagina = numeroRighePerPagina;
	}

	public Integer getNumeroTotaleElementi() {
		return numeroTotaleElementi;
	}

	public void setNumeroTotaleElementi(Integer numeroTotaleElementi) {
		this.numeroTotaleElementi = numeroTotaleElementi;
	}

	public List<CriterioOrdinamentoDto<T>> getCriteriOrdinamento() {
		return criteriOrdinamento;
	}

	public void setCriteriOrdinamento(List<CriterioOrdinamentoDto<T>> criteriOrdinamento) {
		this.criteriOrdinamento = criteriOrdinamento;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("idFlusso:").append(idFlusso).append(COMMA);
		s.append("idList:").append(idList).append(COMMA);
		s.append("numeroPagina:").append(numeroPagina).append(COMMA);
		s.append("numeroRighePerPagina:").append(numeroRighePerPagina).append(COMMA);
		s.append("numeroTotaleElementi:").append(numeroTotaleElementi).append(COMMA);
		s.append("criteriOrdinamento:").append(criteriOrdinamento);
		s.append(" }");
		return s.toString();
	}

}
