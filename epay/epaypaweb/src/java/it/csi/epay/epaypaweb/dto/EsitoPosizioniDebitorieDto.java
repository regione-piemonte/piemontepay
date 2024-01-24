/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;
import java.util.List;

public class EsitoPosizioniDebitorieDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private TestataEsitoPosizioniDebitorieDto testataDto;
	private List<EsitoPosizioneDebitoriaDto> esitoList;

	public TestataEsitoPosizioniDebitorieDto getTestataDto() {
		return testataDto;
	}

	public void setTestataDto(TestataEsitoPosizioniDebitorieDto testataDto) {
		this.testataDto = testataDto;
	}

	public List<EsitoPosizioneDebitoriaDto> getEsitoList() {
		return esitoList;
	}

	public void setEsitoList(List<EsitoPosizioneDebitoriaDto> esitoList) {
		this.esitoList = esitoList;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("testataDto:").append(testataDto).append(COMMA);
		s.append("esitoList:").append(esitoList);
		s.append(" }");
		return s.toString();
	}

}
