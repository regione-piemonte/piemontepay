/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.common;

public class InviaListadicaricoRequestDto extends PrincipalDto {

	private static final long serialVersionUID = 1L;
	
	private Long idFlusso;
	private String serviceEndpoint;

	public InviaListadicaricoRequestDto(String ipAddress,  Long idUtente, String codiceFiscaleUtente,
			String codiceApplicazione, Long idFlusso, String serviceEndpoint) {
		super(ipAddress, idUtente, codiceFiscaleUtente, codiceApplicazione);
		this.idFlusso = idFlusso;
		this.serviceEndpoint = serviceEndpoint;
	}

	public Long getIdFlusso() {
		return idFlusso;
	}

	public void setIdFlusso(Long idFlusso) {
		this.idFlusso = idFlusso;
	}

	public String getServiceEndpoint() {
		return serviceEndpoint;
	}

	public void setServiceEndpoint(String serviceEndpoint) {
		this.serviceEndpoint = serviceEndpoint;
	}
	
}
