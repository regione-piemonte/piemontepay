/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.common;

import it.csi.epay.epaypaweb.dto.AvvisoScadutoDto;
import it.csi.epay.epaypaweb.dto.FlussoCompletoDto;

public class TrasmettiFlussoAvvisoScadutoRequestDto extends PrincipalDto {

	private static final long serialVersionUID = 1L;

	private FlussoCompletoDto<AvvisoScadutoDto> flussoCompletoDto;

	public TrasmettiFlussoAvvisoScadutoRequestDto(String ipAddress, Long idUtente, String codiceFiscaleUtente,
			String codiceApplicazione, FlussoCompletoDto<AvvisoScadutoDto> flussoCompletoDto) {
		super(ipAddress, idUtente, codiceFiscaleUtente, codiceApplicazione);
		this.setFlussoCompletoDto(flussoCompletoDto);
	}

	public FlussoCompletoDto<AvvisoScadutoDto> getFlussoCompletoDto() {
		return flussoCompletoDto;
	}

	public void setFlussoCompletoDto(FlussoCompletoDto<AvvisoScadutoDto> flussoCompletoDto) {
		this.flussoCompletoDto = flussoCompletoDto;
	}
}
