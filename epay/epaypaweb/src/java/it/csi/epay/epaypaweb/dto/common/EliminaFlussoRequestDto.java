/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.common;

public class EliminaFlussoRequestDto extends PrincipalDto {

	private static final long serialVersionUID = 1L;

	private Long idFlusso;
	
	private String  tipoFlusso;

	public EliminaFlussoRequestDto(String ipAddress,  Long idUtente, String codiceFiscaleUtente,
			String codiceApplicazione, Long idFlusso, String tipoFlusso) {
		super(ipAddress, idUtente, codiceFiscaleUtente, codiceApplicazione);
		this.idFlusso = idFlusso;
		this.tipoFlusso = tipoFlusso;
	}

	public Long getIdFlusso() {
		return idFlusso;
	}

	public void setIdFlusso(Long idFlusso) {
		this.idFlusso = idFlusso;
	}

	/**
	 * @return the tipoFlusso
	 */
	public String getTipoFlusso() {
		return tipoFlusso;
	}

	/**
	 * @param tipoFlusso the tipoFlusso to set
	 */
	public void setTipoFlusso(String tipoFlusso) {
		this.tipoFlusso = tipoFlusso;
	}
	
	
}
