/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.model;

import java.util.List;


public class RicercaTassonomieAggiornateOutput extends ParentOutput{

	private static final long serialVersionUID = 1L;

	private Integer numeroRisultatiTotali;

	private List<TassonomiaAggiornataOutputDto> risultati;

	public Integer getNumeroRisultatiTotali () {
		return numeroRisultatiTotali;
	}

	public void setNumeroRisultatiTotali ( Integer numeroRisultatiTotali ) {
		this.numeroRisultatiTotali = numeroRisultatiTotali;
	}

	public List<TassonomiaAggiornataOutputDto> getRisultati () {
		return risultati;
	}

	public void setRisultati ( List<TassonomiaAggiornataOutputDto> risultati ) {
		this.risultati = risultati;
	}
}
