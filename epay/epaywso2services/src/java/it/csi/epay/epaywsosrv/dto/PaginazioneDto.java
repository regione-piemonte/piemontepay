/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.dto;

import java.io.Serializable;

public class PaginazioneDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer numeroDiPagina;
	private Integer numeroRighePerPagina;

	public PaginazioneDto(Integer numeroDiPagina, Integer numeroRighePerPagina) {
		this.numeroDiPagina = numeroDiPagina;
		this.numeroRighePerPagina = numeroRighePerPagina;
	}

	public Integer getNumeroDiPagina() {
		return numeroDiPagina;
	}

	public Integer getNumeroRighePerPagina() {
		return numeroRighePerPagina;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("numeroDiPagina:").append(numeroDiPagina).append(COMMA);
		s.append("numeroRighePerPagina:").append(numeroRighePerPagina);
		s.append(" }");
		return s.toString();
	}

}
