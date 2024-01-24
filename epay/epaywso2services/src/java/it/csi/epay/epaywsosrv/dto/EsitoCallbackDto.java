/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.dto;

import static it.csi.epay.epaywsosrv.util.Util.quote;

import java.io.Serializable;

public class EsitoCallbackDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String cod;
	private String det;

	public EsitoCallbackDto(String cod, String det) {
		this.cod = cod;
		this.det = det;
	}

	public String getCod() {
		return cod;
	}

	public String getDet() {
		return det;
	}
	
	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("cod:").append(quote(cod)).append(COMMA);
		s.append("det:").append(quote(det));
		s.append(" }");
		return s.toString();
	}

}
