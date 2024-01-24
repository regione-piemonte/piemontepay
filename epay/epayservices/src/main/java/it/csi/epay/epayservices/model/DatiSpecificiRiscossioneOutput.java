/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class DatiSpecificiRiscossioneOutput implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @JsonProperty ( "codiceEsito" )
    private String codiceEsito;

    @JsonProperty ( "descrizioneEsito" )
    private String descrizioneEsito;

    @JsonProperty ( "codiceFiscaleEnte" )
    private String codiceFiscaleEnte;

    @JsonProperty ( "tipoPagamento" )
    private String tipoPagamento;

    @JsonProperty ( "elencoDatiSpecifici" )
    private List<DatiSpecificiRiscossione> elencoDatiSpecifici;

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
     * @return the codiceFiscaleEnte
     */
    public String getCodiceFiscaleEnte () {
        return codiceFiscaleEnte;
    }

    /**
     * @param codiceFiscaleEnte the codiceFiscaleEnte to set
     */
    public void setCodiceFiscaleEnte ( String codiceFiscaleEnte ) {
        this.codiceFiscaleEnte = codiceFiscaleEnte;
    }

    /**
     * @return the tipoPagamento
     */
    public String getTipoPagamento () {
        return tipoPagamento;
    }

    /**
     * @param tipoPagamento the tipoPagamento to set
     */
    public void setTipoPagamento ( String tipoPagamento ) {
        this.tipoPagamento = tipoPagamento;
    }

    
    /**
     * @return the elencoDatiSpecifici
     */
    public List<DatiSpecificiRiscossione> getElencoDatiSpecifici () {
        return elencoDatiSpecifici;
    }

    
    /**
     * @param elencoDatiSpecifici the elencoDatiSpecifici to set
     */
    public void setElencoDatiSpecifici ( List<DatiSpecificiRiscossione> elencoDatiSpecifici ) {
        this.elencoDatiSpecifici = elencoDatiSpecifici;
    }

    
}
