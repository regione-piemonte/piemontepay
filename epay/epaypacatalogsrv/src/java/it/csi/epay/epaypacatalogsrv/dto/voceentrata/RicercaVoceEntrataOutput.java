/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.voceentrata;

import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;

public class RicercaVoceEntrataOutput extends ParentOutput {

	private static final long serialVersionUID = 1L;

	private Integer numeroRisultatiTotali;

	private List<RicercaVoceEntrataOutputDto> risultati;

	public static RicercaVoceEntrataOutput ok(List<RicercaVoceEntrataOutputDto> risultati) {
		RicercaVoceEntrataOutput output = ok(RicercaVoceEntrataOutput.class);
		output.setNumeroRisultatiTotali(risultati.size());
		output.setRisultati(risultati);
		output.setDescrizioneEsito(risultati.size() + " risultati");
		return output;
	}

	public Integer getNumeroRisultatiTotali() {
		return numeroRisultatiTotali;
	}

	public void setNumeroRisultatiTotali(Integer numeroRisultatiTotali) {
		this.numeroRisultatiTotali = numeroRisultatiTotali;
	}

	public List<RicercaVoceEntrataOutputDto> getRisultati() {
		return risultati;
	}

	public void setRisultati(List<RicercaVoceEntrataOutputDto> risultati) {
		this.risultati = risultati;
	}

}
