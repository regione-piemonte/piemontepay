/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.common;

import java.util.List;

import it.csi.epay.epaypaweb.dto.CodiceVersamentoDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.dto.RichiestaRevocheFilterDto;

public class TotalSizeAndFlussoRevocheListRequestDto extends PrincipalDto {
	
	private static final long serialVersionUID = 1L;
	
	private RichiestaRevocheFilterDto filter;
	private PaginazioneDto pag;
	private List<CodiceVersamentoDto> listCodiciVersamento;
	
	public TotalSizeAndFlussoRevocheListRequestDto(String ipAddress, Long idUtente, String codiceFiscaleUtente,
			String codiceApplicazione, RichiestaRevocheFilterDto filter, PaginazioneDto pag,
			List<CodiceVersamentoDto> listCodiciVersamento) {
		super(ipAddress, idUtente, codiceFiscaleUtente, codiceApplicazione);
		this.filter = filter;
		this.pag = pag;
		this.listCodiciVersamento = listCodiciVersamento;
	}

	public RichiestaRevocheFilterDto getFilter() {
		return filter;
	}

	public void setFilter(RichiestaRevocheFilterDto filter) {
		this.filter = filter;
	}

	public PaginazioneDto getPag() {
		return pag;
	}

	public void setPag(PaginazioneDto pag) {
		this.pag = pag;
	}

	public List<CodiceVersamentoDto> getListCodiciVersamento() {
		return listCodiciVersamento;
	}

	public void setListCodiciVersamento(List<CodiceVersamentoDto> listCodiciVersamento) {
		this.listCodiciVersamento = listCodiciVersamento;
	} 

}
