/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import static it.csi.epay.epaypaweb.util.Util.quote;

import java.io.Serializable;

public class ProfiloDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String des;

	public ProfiloDto(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("id:").append(id).append(COMMA);
		s.append("des:").append(quote(des));
		s.append(" }");
		return s.toString();
	}

}
