/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.dto;

import static it.csi.epay.epaywsosrv.util.Util.quote;

import java.io.Serializable;
import java.util.Date;

public class EsitoInvioDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idApplicativo;
	private String desApplicativo;
	private String messageStore;
	private Date dataEsitoInvio;
	private EsitoRichiestaDto esitoInvioRichiestaDto;

	public Integer getIdApplicativo() {
		return idApplicativo;
	}

	public void setIdApplicativo(Integer idApplicativo) {
		this.idApplicativo = idApplicativo;
	}

	public String getDesApplicativo() {
		return desApplicativo;
	}

	public void setDesApplicativo(String desApplicativo) {
		this.desApplicativo = desApplicativo;
	}

	public String getMessageStore() {
		return messageStore;
	}

	public void setMessageStore(String messageStore) {
		this.messageStore = messageStore;
	}

	public EsitoRichiestaDto getEsitoInvioRichiestaDto() {
		return esitoInvioRichiestaDto;
	}

	public void setEsitoInvioRichiestaDto(EsitoRichiestaDto esitoInvioRichiestaDto) {
		this.esitoInvioRichiestaDto = esitoInvioRichiestaDto;
	}

	public Date getDataEsitoInvio() {
		return dataEsitoInvio;
	}

	public void setDataEsitoInvio(Date dataEsitoInvio) {
		this.dataEsitoInvio = dataEsitoInvio;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("idApplicativo:").append(idApplicativo).append(COMMA);
		s.append("desApplicativo:").append(quote(desApplicativo)).append(COMMA);
		s.append("messageStore:").append(quote(messageStore)).append(COMMA);
		s.append("esitoInvioRichiestaDto:").append(esitoInvioRichiestaDto).append(COMMA);
		s.append("dataEsitoInvio:").append(dataEsitoInvio);
		s.append(" }");
		return s.toString();
	}

}
