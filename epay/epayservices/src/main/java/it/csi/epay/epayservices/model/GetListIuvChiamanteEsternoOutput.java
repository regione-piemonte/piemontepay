/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.util.List;


/**
 *
 */

public class GetListIuvChiamanteEsternoOutput implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 728205792459357381L;

    private String codiceEsito;

    private String descrizioneEsito;

    private List<GetIuvChiamanteEsternoOutput> posizioniDebitorie;

    /**
     * @return the codiceEsito
     */
    public String getCodiceEsito () {
        return codiceEsito;
    }

    /**
     * @return the descrizioneEsito
     */
    public String getDescrizioneEsito () {
        return descrizioneEsito;
    }

    /**
     * @return the posizioniDebitorie
     */
    public List<GetIuvChiamanteEsternoOutput> getPosizioniDebitorie () {
        return posizioniDebitorie;
    }

    /**
     * @param codiceEsito the codiceEsito to set
     */
    public void setCodiceEsito ( String codiceEsito ) {
        this.codiceEsito = codiceEsito;
    }

    /**
     * @param descrizioneEsito the descrizioneEsito to set
     */
    public void setDescrizioneEsito ( String descrizioneEsito ) {
        this.descrizioneEsito = descrizioneEsito;
    }

    /**
     * @param posizioniDebitorie the posizioniDebitorie to set
     */
    public void setPosizioniDebitorie ( List<GetIuvChiamanteEsternoOutput> posizioniDebitorie ) {
        this.posizioniDebitorie = posizioniDebitorie;
    }

}
