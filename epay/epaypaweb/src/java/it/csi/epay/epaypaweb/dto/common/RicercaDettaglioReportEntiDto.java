/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.common;

public class RicercaDettaglioReportEntiDto extends PrincipalDto {
	
	private static final long serialVersionUID = 1L;
	
	private String iuv;
	
	public RicercaDettaglioReportEntiDto(String ipAddress, Long idUtente, String codiceFiscaleUtente,
			String codiceApplicazione, String iuv) {
		super(ipAddress, idUtente, codiceFiscaleUtente, codiceApplicazione);
		this.iuv = iuv;
	}

	public String getIuv() {
		return iuv;
	}

	public void setIuv(String iuv) {
		this.iuv = iuv;
	}


}
