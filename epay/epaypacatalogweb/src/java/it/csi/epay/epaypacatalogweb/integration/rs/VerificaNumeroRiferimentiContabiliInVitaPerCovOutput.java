/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.integration.rs;

import org.codehaus.jackson.annotate.JsonProperty;

public class VerificaNumeroRiferimentiContabiliInVitaPerCovOutput extends ParentOutput {

    @JsonProperty("numRiferimentiInVita")
    private Integer numRiferimentiInVita;

    
    /**
     * @return the numRiferimentiInVita
     */
    public Integer getNumRiferimentiInVita () {
        return numRiferimentiInVita;
    }

    
    /**
     * @param numRiferimentiInVita the numRiferimentiInVita to set
     */
    public void setNumRiferimentiInVita ( Integer numRiferimentiInVita ) {
        this.numRiferimentiInVita = numRiferimentiInVita;
    }

}
