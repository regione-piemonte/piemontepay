/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.common;

import java.util.List;

import it.csi.epay.epaypaweb.dto.CodiceVersamentoDto;
import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.FlussoLightFilterDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNameNotificaPagamentoEnum;

public class TotalSizeAndFlussoNotificheListRequestDto extends PrincipalDto {

	private static final long serialVersionUID = 1L;
	
	private FlussoLightFilterDto filter;
	private List<CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>> ord;
	private PaginazioneDto pag;
    private List<CodiceVersamentoDto> listCodiciVersamento;
  
	public TotalSizeAndFlussoNotificheListRequestDto(String ipAddress,  Long idUtente, String codiceFiscaleUtente,
			String codiceApplicazione, FlussoLightFilterDto filter,
			List<CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>> ord, PaginazioneDto pag,
			List<CodiceVersamentoDto> listCodiciVersamento) {
		super(ipAddress, idUtente, codiceFiscaleUtente, codiceApplicazione);
		this.filter = filter;
		this.ord = ord;
		this.pag = pag;
		this.listCodiciVersamento = listCodiciVersamento;
	}

	public FlussoLightFilterDto getFilter() {
		return filter;
	}

	public void setFilter(FlussoLightFilterDto filter) {
		this.filter = filter;
	}

	public List<CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>> getOrd() {
		return ord;
	}

	public void setOrd(List<CriterioOrdinamentoDto<ColumnNameNotificaPagamentoEnum>> ord) {
		this.ord = ord;
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
