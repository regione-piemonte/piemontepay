/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.dto;

import java.io.Serializable;

import it.csi.epay.epaywsosrv.enumeration.TipoRichiestaEnum;

public class EndpointAppDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private TipoRichiestaEnum tipoRichiestaEnum;
	private AppDto appDto;
	private EndpointDto endpointDto;

	public TipoRichiestaEnum getTipoRichiestaEnum() {
		return tipoRichiestaEnum;
	}

	public void setTipoRichiestaEnum(TipoRichiestaEnum tipoRichiestaEnum) {
		this.tipoRichiestaEnum = tipoRichiestaEnum;
	}

	public AppDto getAppDto() {
		return appDto;
	}

	public void setAppDto(AppDto appDto) {
		this.appDto = appDto;
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
		s.append("tipoRichiestaEnum:").append(tipoRichiestaEnum).append(COMMA);
		s.append("appDto:").append(appDto).append(COMMA);
		s.append("endpointDto:").append(endpointDto);
		s.append(" }");
		return s.toString();
	}

}
