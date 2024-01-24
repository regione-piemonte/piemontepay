/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.common;

import java.util.List;

import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNamePosizioneDebitoriaEnum;

public class TotalSizeAndPosizioneDebitoriaLightListRequestDto extends PrincipalDto {

	private static final long serialVersionUID = 1L;
	private Long idFlusso;
	private List<CriterioOrdinamentoDto<ColumnNamePosizioneDebitoriaEnum>> ord; 
	private PaginazioneDto pag;
	
	public TotalSizeAndPosizioneDebitoriaLightListRequestDto(String ipAddress,  Long idUtente,
			String codiceFiscaleUtente, String codiceApplicazione, Long idFlusso,
			List<CriterioOrdinamentoDto<ColumnNamePosizioneDebitoriaEnum>> ord, PaginazioneDto pag) {
		super(ipAddress, idUtente, codiceFiscaleUtente, codiceApplicazione);
		this.idFlusso = idFlusso;
		this.ord = ord;
		this.pag = pag;
	}
	public Long getIdFlusso() {
		return idFlusso;
	}
	public void setIdFlusso(Long idFlusso) {
		this.idFlusso = idFlusso;
	}
	public List<CriterioOrdinamentoDto<ColumnNamePosizioneDebitoriaEnum>> getOrd() {
		return ord;
	}
	public void setOrd(List<CriterioOrdinamentoDto<ColumnNamePosizioneDebitoriaEnum>> ord) {
		this.ord = ord;
	}
	public PaginazioneDto getPag() {
		return pag;
	}
	public void setPag(PaginazioneDto pag) {
		this.pag = pag;
	}

}
