/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epaypa.alertRiferimentiContabiliInScadenza;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.csi.epay.epayjob.model.EnteRiferimentiContabiliInScadenza;



public class EntiRiferimentiContabiliInScadenzaOutput implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @JsonProperty("codiceEsito")
    private String codiceEsito;

    @JsonProperty("descrizioneEsito")
    private String descrizioneEsito;

  
    @JsonProperty("enti")
    private List<EnteRiferimentiContabiliInScadenza> enti;

    /**
     * @return the codiceEsito
     */
    public String getCodiceEsito () {
        return codiceEsito;
    }

    /**
     * @param codiceEsito the codiceEsito to set
     */
    public void setCodiceEsito ( String codiceEsito ) {
        this.codiceEsito = codiceEsito;
    }

    /**
     * @return the descrizioneEsito
     */
    public String getDescrizioneEsito () {
        return descrizioneEsito;
    }

    /**
     * @param descrizioneEsito the descrizioneEsito to set
     */
    public void setDescrizioneEsito ( String descrizioneEsito ) {
        this.descrizioneEsito = descrizioneEsito;
    }

	

    /**
     * @return the entiRiferimentiContabiliInScadenza
     */
    public List<EnteRiferimentiContabiliInScadenza> getEnti() {
		return enti;
	}

	
    /**
     * @param entiRiferimentiContabiliInScadenza the entiRiferimentiContabiliInScadenza to set
     */
    public void setEnti(List<EnteRiferimentiContabiliInScadenza> enti) {
		this.enti = enti;
	}

    

}
