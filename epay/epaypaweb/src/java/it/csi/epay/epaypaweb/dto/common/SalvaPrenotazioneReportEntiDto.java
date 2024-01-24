/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.common;

import it.csi.epay.epaypaweb.dto.ReportBaseDto;

public class SalvaPrenotazioneReportEntiDto extends PrincipalDto {
	
	private static final long serialVersionUID = 1L;
	
	private ReportBaseDto reportBaseDto;
	
	public SalvaPrenotazioneReportEntiDto(String ipAddress, Long idUtente, String codiceFiscaleUtente,
			String codiceApplicazione, ReportBaseDto reportBaseDto) {
		super(ipAddress, idUtente, codiceFiscaleUtente, codiceApplicazione);
		this.reportBaseDto= reportBaseDto;
		
	}

	public ReportBaseDto getReportBaseDto() {
		return reportBaseDto;
	}

	public void setReportBaseDto(ReportBaseDto reportBaseDto) {
		this.reportBaseDto = reportBaseDto;
	}





}
