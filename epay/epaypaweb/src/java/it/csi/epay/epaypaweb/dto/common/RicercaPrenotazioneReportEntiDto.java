/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.common;

import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.enumeration.TipoReportEnum;

public class RicercaPrenotazioneReportEntiDto extends PrincipalDto {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idEnte; 
//	Long idUtente, 
	private PaginazioneDto pag; 
	private TipoReportEnum tipoReport;
	
	
	public RicercaPrenotazioneReportEntiDto(String ipAddress, Long idUtente, String codiceFiscaleUtente,
			String codiceApplicazione, Integer idEnte,PaginazioneDto pag,  TipoReportEnum tipoReport) {
		super(ipAddress, idUtente, codiceFiscaleUtente, codiceApplicazione);
		
		this.idEnte= idEnte;
		this.pag= pag;
		this.tipoReport= tipoReport;
		
	}


	public Integer getIdEnte() {
		return idEnte;
	}


	public void setIdEnte(Integer idEnte) {
		this.idEnte = idEnte;
	}


	public PaginazioneDto getPag() {
		return pag;
	}


	public void setPag(PaginazioneDto pag) {
		this.pag = pag;
	}


	public TipoReportEnum getTipoReport() {
		return tipoReport;
	}


	public void setTipoReport(TipoReportEnum tipoReport) {
		this.tipoReport = tipoReport;
	}



}
