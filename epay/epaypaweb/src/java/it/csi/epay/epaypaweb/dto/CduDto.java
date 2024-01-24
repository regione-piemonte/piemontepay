/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import static it.csi.epay.epaypaweb.util.Util.quote;

import java.io.Serializable;

public class CduDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String cod;

    private CategoriaCduDto categoria;
	private String des;

    public CduDto ( Integer id, String cod, CategoriaCduDto categoria ) {
		this.id = id;
		this.cod = cod;
		this.categoria = categoria;
	}

	public Integer getId() {
		return id;
	}

	public String getCod() {
		return cod;
	}

    public CategoriaCduDto getCategoria () {
		return categoria;
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
		s.append("cod:").append(quote(cod)).append(COMMA);
		s.append("categoria:").append(categoria).append(COMMA);
		s.append("des:").append(quote(des));
		s.append(" }");
		return s.toString();
	}

}
