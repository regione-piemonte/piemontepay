/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.integration.rs;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author manue
 *
 */
public class RicercaTassonomiaOutput extends ParentOutput {

	@JsonProperty("numeroRisultatiTotali")
    private Integer numeroRisultatiTotali;

	
	 @JsonProperty("risultati")
    private List<TassonomiaDto> risultati;


	/**
	 * @return the risultati
	 */
	public List<TassonomiaDto> getRisultati() {
		return risultati;
	}


	/**
	 * @param risultati the risultati to set
	 */
	public void setRisultati(List<TassonomiaDto> risultati) {
		this.risultati = risultati;
	}
	 
	 
	 



   

}
