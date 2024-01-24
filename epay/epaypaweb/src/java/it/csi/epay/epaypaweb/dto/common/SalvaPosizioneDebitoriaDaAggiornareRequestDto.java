/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.common;

import it.csi.epay.epaypaweb.dto.FlussoCompletoDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDaAggiornareDto;

public class SalvaPosizioneDebitoriaDaAggiornareRequestDto extends PrincipalDto {

	private static final long serialVersionUID = 1L;
	
	private FlussoCompletoDto<PosizioneDebitoriaDaAggiornareDto> flussoCompleto;

	public SalvaPosizioneDebitoriaDaAggiornareRequestDto(String ipAddress,  Long idUtente, String codiceFiscaleUtente,
			String codiceApplicazione, FlussoCompletoDto<PosizioneDebitoriaDaAggiornareDto> flussoCompleto) {
		super(ipAddress, idUtente, codiceFiscaleUtente, codiceApplicazione);
		this.flussoCompleto = flussoCompleto;
	}

	public FlussoCompletoDto<PosizioneDebitoriaDaAggiornareDto> getFlussoCompleto() {
		return flussoCompleto;
	}

	public void setFlussoCompleto(FlussoCompletoDto<PosizioneDebitoriaDaAggiornareDto> flussoCompleto) {
		this.flussoCompleto = flussoCompleto;
	}
}
