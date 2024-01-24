/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;

public class PaginazioneDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer numeroPagina;
	private Integer numeroRighePerPagina;

	public PaginazioneDto(Integer numeroPagina, Integer numeroRighePerPagina) {
		this.numeroPagina = numeroPagina;
		this.numeroRighePerPagina = numeroRighePerPagina;
	}

	public Integer getNumeroPagina() {
		return numeroPagina;
	}

	public Integer getNumeroRighePerPagina() {
		return numeroRighePerPagina;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("numeroPagina:").append(numeroPagina).append(COMMA);
		s.append("numeroRighePerPagina:").append(numeroRighePerPagina);
		s.append(" }");
		return s.toString();
	}

}
