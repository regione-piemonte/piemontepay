/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import static it.csi.epay.epaypaweb.util.Util.quote;

import java.io.Serializable;

public class EsitoDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codEsito;
	private String desEsito;

	public EsitoDto(String codEsito, String desEsito) {
		this.codEsito = codEsito;
		this.desEsito = desEsito;
	}

	public String getCodEsito() {
		return codEsito;
	}

	public void setCodEsito(String codEsito) {
		this.codEsito = codEsito;
	}

	public String getDesEsito() {
		return desEsito;
	}

	public void setDesEsito(String desEsito) {
		this.desEsito = desEsito;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("codEsito:").append(quote(codEsito)).append(COMMA);
		s.append("desEsito:").append(quote(desEsito));
		s.append(" }");
		return s.toString();
	}
}
