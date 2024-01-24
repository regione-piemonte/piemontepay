/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.integration.rs;

import org.codehaus.jackson.annotate.JsonProperty;

public class VerificaPresenzaRiferimentiContabiliOutput extends ParentOutput {

    @JsonProperty("messaggioErrore")
    private String messaggioErrore;

	/**
	 * @return the messaggioErrore
	 */
	public String getMessaggioErrore() {
		return messaggioErrore;
	}

	/**
	 * @param messaggioErrore the messaggioErrore to set
	 */
	public void setMessaggioErrore(String messaggioErrore) {
		this.messaggioErrore = messaggioErrore;
	}
    
    
    
    

  

	

   
    

}
