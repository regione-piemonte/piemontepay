/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.common;

import it.csi.epay.epaypaweb.dto.FlussoCompletoDto;
import it.csi.epay.epaypaweb.dto.RichiestaDiRevocaDto;

public class TrasmettiFlussoRichiesteDiRevocaRequestDto extends PrincipalDto {

	private static final long serialVersionUID = 1L;
	
	private FlussoCompletoDto<RichiestaDiRevocaDto> flussoCompletoDto;

	public TrasmettiFlussoRichiesteDiRevocaRequestDto(String ipAddress, Long idUtente, String codiceFiscaleUtente,
			String codiceApplicazione, FlussoCompletoDto<RichiestaDiRevocaDto> flussoCompletoDto) {
		super(ipAddress, idUtente, codiceFiscaleUtente, codiceApplicazione);
		this.flussoCompletoDto = flussoCompletoDto;
	}

	public FlussoCompletoDto<RichiestaDiRevocaDto> getFlussoCompletoDto() {
		return flussoCompletoDto;
	}

	public void setFlussoCompletoDto(FlussoCompletoDto<RichiestaDiRevocaDto> flussoCompletoDto) {
		this.flussoCompletoDto = flussoCompletoDto;
	}
}
