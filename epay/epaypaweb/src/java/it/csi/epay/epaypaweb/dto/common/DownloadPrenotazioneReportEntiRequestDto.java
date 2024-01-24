/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.common;

public class DownloadPrenotazioneReportEntiRequestDto extends PrincipalDto {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idEnte; 
	private Long idFile;
	
	
	public DownloadPrenotazioneReportEntiRequestDto(String ipAddress, Long idUtente, String codiceFiscaleUtente,
			String codiceApplicazione, Integer idEnte,Long idFile) {
		super(ipAddress, idUtente, codiceFiscaleUtente, codiceApplicazione);
		
		this.idEnte= idEnte;
		this.idFile= idFile;
		
	}

	public Integer getIdEnte() {
		return idEnte;
	}


	public void setIdEnte(Integer idEnte) {
		this.idEnte = idEnte;
	}


	public Long getIdFile() {
		return idFile;
	}


	public void setIdFile(Long idFile) {
		this.idFile = idFile;
	}




}
