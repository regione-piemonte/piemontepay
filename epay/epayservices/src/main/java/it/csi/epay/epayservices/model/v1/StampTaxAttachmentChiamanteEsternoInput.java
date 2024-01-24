/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.csi.epay.epayservices.model.AccessoChiamanteEsternoSincronoSplitOutput;

import java.io.Serializable;
import java.util.List;


@SuppressWarnings ( "unused" )
public class StampTaxAttachmentChiamanteEsternoInput extends AccessoChiamanteEsternoSincronoSplitInput implements Serializable {


    private static final long serialVersionUID = -9091409868529412163L;

    private String codiceFiscaleEnte;

    private String tipoPagamento;
    
    
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
     * @return the codiceVersamento
     */
   


    @Override public String toString () {
        return "PagamentoPerStampaAvvisoInput{" +
                        "codiceFiscaleEnte='" + codiceFiscaleEnte + '\'' +
                        ", tipoPagamento='" + tipoPagamento + '\'' +
                        ", iuv='" + getIuv () + '\'' +
                        ", codiceChiamante='" + getCodiceChiamante () + '\'' +
                        '}';
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




}
