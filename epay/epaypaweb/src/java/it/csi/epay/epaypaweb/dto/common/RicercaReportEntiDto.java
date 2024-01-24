/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.common;

import it.csi.epay.epaypaweb.dto.PagamentiFilterDto;

public class RicercaReportEntiDto extends PrincipalDto {
	
	private static final long serialVersionUID = 1L;
	
	private PagamentiFilterDto filter;
	
	public RicercaReportEntiDto(String ipAddress, Long idUtente, String codiceFiscaleUtente,
			String codiceApplicazione, PagamentiFilterDto filter) {
		super(ipAddress, idUtente, codiceFiscaleUtente, codiceApplicazione);
		this.filter = filter;
	}

	public PagamentiFilterDto getFilter() {
		return filter;
	}

	public void setFilter(PagamentiFilterDto filter) {
		this.filter = filter;
	}

	


}
