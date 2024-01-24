/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import static it.csi.epay.epaypaweb.util.Util.quote;

import java.io.Serializable;

public class ParseErrorFieldDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer fieldRow;
	private Integer fieldColumn;
	private String fieldName;
	private Object fieldValue;

	public ParseErrorFieldDto(Integer fieldRow, Integer fieldColumn, String fieldName, Object fieldValue) {
		this.fieldName = fieldName;
		this.fieldRow = fieldRow;
		this.fieldColumn = fieldColumn;
		this.fieldValue = fieldValue;
	}

	public Integer getFieldRow() {
		return fieldRow;
	}

	public Integer getFieldColumn() {
		return fieldColumn;
	}

	public String getFieldName() {
		return fieldName;
	}

	public Object getFieldValue() {
		return fieldValue;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("fieldRow:").append(fieldRow).append(COMMA);
		s.append("fieldColumn:").append(fieldColumn).append(COMMA);
		s.append("fieldName:").append(quote(fieldName)).append(COMMA);
		s.append("fieldValue:").append((fieldValue instanceof String) ? quote((String) fieldValue) : fieldValue);
		s.append(" }");
		return s.toString();
	}
}
