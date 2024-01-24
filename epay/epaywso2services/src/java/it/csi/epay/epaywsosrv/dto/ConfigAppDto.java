/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.dto;

import java.io.Serializable;

public class ConfigAppDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private AppDto appDto;
	private EnteDto enteDto;
	private CodVersDto codVersDto;
	private EndpointDto endpointDto;

	public AppDto getAppDto() {
		return appDto;
	}

	public void setAppDto(AppDto appDto) {
		this.appDto = appDto;
	}

	public EnteDto getEnteDto() {
		return enteDto;
	}

	public void setEnteDto(EnteDto enteDto) {
		this.enteDto = enteDto;
	}

	public CodVersDto getCodVersDto() {
		return codVersDto;
	}

	public void setCodVersDto(CodVersDto codVersDto) {
		this.codVersDto = codVersDto;
	}

	public EndpointDto getEndpointDto() {
		return endpointDto;
	}

	public void setEndpointDto(EndpointDto endpointDto) {
		this.endpointDto = endpointDto;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("appDto:").append(appDto).append(COMMA);
		s.append("enteDto").append(enteDto).append(COMMA);
		s.append("codVersDto:").append(codVersDto).append(COMMA);
		s.append("endpointDto:").append(endpointDto);
		s.append(" }");
		return s.toString();
	}

}
