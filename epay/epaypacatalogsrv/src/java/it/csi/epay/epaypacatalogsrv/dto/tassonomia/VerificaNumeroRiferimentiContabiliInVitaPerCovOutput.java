/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.tassonomia;

import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;



public class VerificaNumeroRiferimentiContabiliInVitaPerCovOutput extends ParentOutput {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
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
