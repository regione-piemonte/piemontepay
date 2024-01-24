/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.dto;

import static it.csi.epay.epaywsosrv.util.Util.date2strdatetime;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/** dto facade <-> business <-> persistence */
public class RichiestaDto extends RichiestaBaseDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private EsitoRichiestaDto esitoRichiestaDto;
	private List<EsitoInvioDto> esitoInvioDtoList;
	private Date dataCallback;
	private EsitoCallbackDto esitoCallbackDto;
	private String contenutoCallback;

	public RichiestaDto() {
	}

	public RichiestaDto(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public EsitoRichiestaDto getEsitoRichiestaDto() {
		return esitoRichiestaDto;
	}

	public void setEsitoRichiestaDto(EsitoRichiestaDto esitoRichiestaDto) {
		this.esitoRichiestaDto = esitoRichiestaDto;
	}

	public List<EsitoInvioDto> getEsitoInvioDtoList() {
		return esitoInvioDtoList;
	}

	public void setEsitoInvioDtoList(List<EsitoInvioDto> esitoInvioDtoList) {
		this.esitoInvioDtoList = esitoInvioDtoList;
	}

	public Date getDataCallback() {
		return dataCallback;
	}

	public void setDataCallback(Date dataCallback) {
		this.dataCallback = dataCallback;
	}

	public EsitoCallbackDto getEsitoCallbackDto() {
		return esitoCallbackDto;
	}

	public void setEsitoCallbackDto(EsitoCallbackDto esitoCallbackDto) {
		this.esitoCallbackDto = esitoCallbackDto;
	}

	public String getContenutoCallback() {
		return contenutoCallback;
	}

	public void setContenutoCallback(String contenutoCallback) {
		this.contenutoCallback = contenutoCallback;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("id:").append(id).append(COMMA);
		s.append(super.toString()).append(COMMA);
		s.append("esitoRichiestaDto:").append(esitoRichiestaDto).append(COMMA);
		s.append("esitoInvioDtoList:").append(esitoInvioDtoList).append(COMMA);
		s.append("dataCallback:").append(date2strdatetime(dataCallback)).append(COMMA);
		s.append("esitoCallbackDto:").append(esitoCallbackDto).append(COMMA);
		s.append("contenutoCallback:").append(contenutoCallback);
		s.append(" }");
		return s.toString();
	}

}
