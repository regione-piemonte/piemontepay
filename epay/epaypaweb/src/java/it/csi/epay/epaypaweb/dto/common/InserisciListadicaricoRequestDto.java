/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.common;

import java.util.List;

import it.csi.epay.epaypaweb.dto.ParametriInserimentoFlussoDto;

public class InserisciListadicaricoRequestDto extends PrincipalDto {

	private static final long serialVersionUID = 1L;

	private ParametriInserimentoFlussoDto params;
	private List<List<Object>> lines;
	private String tipoFormato;
	
	public InserisciListadicaricoRequestDto(String ipAddress,  Long idUtente, String codiceFiscaleUtente,
			String codiceApplicazione, ParametriInserimentoFlussoDto params, List<List<Object>> lines,
			String tipoFormato) {
		super(ipAddress, idUtente, codiceFiscaleUtente, codiceApplicazione);
		this.params = params;
		this.lines = lines;
		this.tipoFormato = tipoFormato;
	}

	public ParametriInserimentoFlussoDto getParams() {
		return params;
	}

	public void setParams(ParametriInserimentoFlussoDto params) {
		this.params = params;
	}

	public List<List<Object>> getLines() {
		return lines;
	}

	public void setLines(List<List<Object>> lines) {
		this.lines = lines;
	}

	public String getTipoFormato() {
		return tipoFormato;
	}

	public void setTipoFormato(String tipoFormato) {
		this.tipoFormato = tipoFormato;
	}
	
}
