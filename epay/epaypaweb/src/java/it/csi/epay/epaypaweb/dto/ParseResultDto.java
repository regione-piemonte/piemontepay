/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;
import java.util.List;

import it.csi.epay.epaypaweb.enumeration.ParseErrorEnum;

public class ParseResultDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private ParseErrorEnum parseErrorEnum;
	private List<ParseErrorFieldDto> parseErrorFieldList;

	public ParseResultDto(ParseErrorEnum parseErrorEnum, List<ParseErrorFieldDto> parseErrorFieldList) {
		this.parseErrorEnum = parseErrorEnum;
		this.parseErrorFieldList = parseErrorFieldList;
	}

	public ParseErrorEnum getParseErrorEnum() {
		return parseErrorEnum;
	}

	public void setParseErrorEnum(ParseErrorEnum parseErrorEnum) {
		this.parseErrorEnum = parseErrorEnum;
	}

	public List<ParseErrorFieldDto> getParseErrorFieldList() {
		return parseErrorFieldList;
	}

	public void setParseErrorFieldList(List<ParseErrorFieldDto> parseErrorFieldList) {
		this.parseErrorFieldList = parseErrorFieldList;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("parseErrorEnum:").append(parseErrorEnum).append(COMMA);
		s.append("parseErrorFieldList:").append(parseErrorFieldList);
		s.append(" }");
		return s.toString();
	}
}
