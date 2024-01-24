/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.common;

import it.csi.epay.epaypaweb.dto.FlussoCompletoDto;
import it.csi.epay.epaypaweb.dto.NotificaPagamentoDto;

public class TrasmettiFlussoNotifichePagamentoRequestDto extends PrincipalDto {

	private static final long serialVersionUID = 1L;
	
	private FlussoCompletoDto<NotificaPagamentoDto> flussoCompletoDto;

	public TrasmettiFlussoNotifichePagamentoRequestDto(String ipAddress, Long idUtente, String codiceFiscaleUtente,
			String codiceApplicazione, FlussoCompletoDto<NotificaPagamentoDto> flussoCompletoDto) {
		super(ipAddress, idUtente, codiceFiscaleUtente, codiceApplicazione);
		this.setFlussoCompletoDto(flussoCompletoDto);
	}

	public FlussoCompletoDto<NotificaPagamentoDto> getFlussoCompletoDto() {
		return flussoCompletoDto;
	}

	public void setFlussoCompletoDto(FlussoCompletoDto<NotificaPagamentoDto> flussoCompletoDto) {
		this.flussoCompletoDto = flussoCompletoDto;
	}
}
