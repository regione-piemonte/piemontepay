/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.dto;

import java.io.Serializable;

import it.csi.epay.epaywsosrv.enumeration.SortTypeEnum;

public class CriterioOrdinamentoDto<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	private T columnNameEnum;
	private SortTypeEnum sortTypeEnum;

	public CriterioOrdinamentoDto(T columnNameEnum) {
		this(columnNameEnum, SortTypeEnum.ASC);
	}

	public CriterioOrdinamentoDto(T columnNameEnum, SortTypeEnum sortTypeEnum) {
		this.columnNameEnum = columnNameEnum;
		this.sortTypeEnum = sortTypeEnum;
	}

	public T getColumnNameEnum() {
		return columnNameEnum;
	}

	public SortTypeEnum getSortTypeEnum() {
		return sortTypeEnum;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("columnNameEnum:").append(columnNameEnum).append(COMMA);
		s.append("sortTypeEnum:").append(sortTypeEnum);
		s.append(" }");
		return s.toString();
	}

}
