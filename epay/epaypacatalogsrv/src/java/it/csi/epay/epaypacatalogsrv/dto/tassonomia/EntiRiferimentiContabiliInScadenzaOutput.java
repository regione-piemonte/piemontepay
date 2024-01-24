/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.tassonomia;

import java.io.Serializable;
import java.util.List;



public class EntiRiferimentiContabiliInScadenzaOutput implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String codiceEsito;

    private String descrizioneEsito;

  

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
