/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.common;

import it.csi.epay.epaypaweb.dto.EsitoPosizioniDebitorieDto;

public class RegistraEsitoPosizioniDebitorieAggiornateRequestDto extends PrincipalDto {

	private static final long serialVersionUID = 1L;

	private EsitoPosizioniDebitorieDto esitoDto;

	public RegistraEsitoPosizioniDebitorieAggiornateRequestDto(String ipAddress, Long idUtente,
			String codiceFiscaleUtente, String codiceApplicazione, EsitoPosizioniDebitorieDto esitoDto) {
		super(ipAddress, idUtente, codiceFiscaleUtente, codiceApplicazione);
		this.setEsitoDto(esitoDto);
	}

	public EsitoPosizioniDebitorieDto getEsitoDto() {
		return esitoDto;
	}

	public void setEsitoDto(EsitoPosizioniDebitorieDto esitoDto) {
		this.esitoDto = esitoDto;
	}
}
