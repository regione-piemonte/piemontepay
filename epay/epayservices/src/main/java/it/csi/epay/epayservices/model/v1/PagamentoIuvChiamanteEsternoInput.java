/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model.v1;

import java.io.Serializable;


/**
 *
 */

public class PagamentoIuvChiamanteEsternoInput extends AccessoChiamanteEsternoSincronoSplitInput implements Serializable {

    private static final long serialVersionUID = 5297807441397131380L;

    private String iuv;

    private String codiceFiscale;

    private String codiceFiscaleEnte;

    private String tipoPagamento;

    public PagamentoIuvChiamanteEsternoInput ( String codiceFiscale, String iuv ) {
        this.iuv = iuv;
        this.codiceFiscale = codiceFiscale;
    }

    public String getIuv () {
        return iuv;
    }

    public void setIuv ( String iuv ) {
        this.iuv = iuv;
    }

    public String getCodiceFiscale () {
        return codiceFiscale;
    }

    public void setCodiceFiscale ( String codiceFiscale ) {
        this.codiceFiscale = codiceFiscale;
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

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString () {
        return "PagamentoIuvChiamanteEsternoInput [iuv=" + iuv + ", codiceFiscale=" + codiceFiscale + ", codiceFiscaleEnte=" + codiceFiscaleEnte
            + ", tipoPagamento=" + tipoPagamento + "]";
    }

}
