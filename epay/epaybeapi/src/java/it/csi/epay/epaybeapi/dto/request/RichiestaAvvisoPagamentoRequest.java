/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.dto.request;

public class RichiestaAvvisoPagamentoRequest extends BasicRequest {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String iuv;

    private String codAvviso;

    public String getIuv () {
        return iuv;
    }

    public void setIuv ( String iuv ) {
        this.iuv = iuv;
    }

    public String getCodAvviso () {
        return codAvviso;
    }

    public void setCodAvviso ( String codAvviso ) {
        this.codAvviso = codAvviso;
    }

    @Override
    public String toString () {
        return "StampaAvvisoPagamentoRequest [iuv=" + iuv + ", codAvviso=" + codAvviso + "]";
    }

}
