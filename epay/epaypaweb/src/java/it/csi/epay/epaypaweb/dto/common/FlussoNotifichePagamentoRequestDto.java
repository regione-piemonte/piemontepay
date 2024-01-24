/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.common;

public class FlussoNotifichePagamentoRequestDto extends PrincipalDto {
	
	private static final long serialVersionUID = 1L;
	
	private Long idFlusso;

	public FlussoNotifichePagamentoRequestDto(String ipAddress,  Long idUtente, String codiceFiscaleUtente,
			String codiceApplicazione, Long idFlusso) {
		super(ipAddress, idUtente, codiceFiscaleUtente, codiceApplicazione);
		this.idFlusso = idFlusso;
	}

	public Long getIdFlusso() {
		return idFlusso;
	}

	public void setIdFlusso(Long idFlusso) {
		this.idFlusso = idFlusso;
	}

}
