/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.dto.response;

import java.io.Serializable;
import java.util.Arrays;


public class StampaAvvisoPagamentoResponse implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String codEsito;

    private String desEsito;

    private byte [] avvisoPagamentoData;

    public StampaAvvisoPagamentoResponse () {
    }

    public StampaAvvisoPagamentoResponse ( String codEsito, String desEsito ) {
        this.codEsito = codEsito;
        this.desEsito = desEsito;
    }

    public String getCodEsito () {
        return codEsito;
    }

    public void setCodEsito ( String codEsito ) {
        this.codEsito = codEsito;
    }

    public String getDesEsito () {
        return desEsito;
    }

    public void setDesEsito ( String desEsito ) {
        this.desEsito = desEsito;
    }

    public byte [] getAvvisoPagamentoData () {
        return avvisoPagamentoData;
    }

    public void setAvvisoPagamentoData ( byte [] avvisoPagamentoData ) {
        this.avvisoPagamentoData = avvisoPagamentoData;
    }

    @Override
    public String toString () {
        return "StampaAvvisoPagamentoResponse [codEsito=" + codEsito + ", desEsito=" + desEsito + ", avvisoPagamentoData="
            + Arrays.toString ( avvisoPagamentoData ) + "]";
    }
}
