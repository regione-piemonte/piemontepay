/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.common;

import it.csi.epay.epaypaweb.dto.FlussoCompletoDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDto;

public class SalvaPosizioneDebitoriaRequestDto extends PrincipalDto {

	private static final long serialVersionUID = 1L;

	private FlussoCompletoDto<PosizioneDebitoriaDto> flussoCompleto;

	public SalvaPosizioneDebitoriaRequestDto ( String ipAddress, Long idUtente, String codiceFiscaleUtente,
		String codiceApplicazione, FlussoCompletoDto<PosizioneDebitoriaDto> flussoCompleto) {
		super(ipAddress, idUtente, codiceFiscaleUtente, codiceApplicazione);
		this.flussoCompleto = flussoCompleto;
	}

	public FlussoCompletoDto<PosizioneDebitoriaDto> getFlussoCompleto() {
		return flussoCompleto;
	}

	public void setFlussoCompleto(FlussoCompletoDto<PosizioneDebitoriaDto> flussoCompleto) {
		this.flussoCompleto = flussoCompleto;
	}
}
