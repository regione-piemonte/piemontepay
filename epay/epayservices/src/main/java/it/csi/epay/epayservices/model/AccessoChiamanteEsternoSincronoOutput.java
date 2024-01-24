/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;


public class AccessoChiamanteEsternoSincronoOutput implements Serializable {

    private static final long serialVersionUID = -6903007449346036409L;

    private String identificativoPagamento;

    private String iuv;

    private String urlWisp;

    private String codiceEsito;

    private String descrizioneEsito;

    public String getIdentificativoPagamento () {
        return identificativoPagamento;
    }

    public void setIdentificativoPagamento ( String identificativoPagamento ) {
        this.identificativoPagamento = identificativoPagamento;
    }

    public String getIuv () {
        return iuv;
    }

    public void setIuv ( String iuv ) {
        this.iuv = iuv;
    }

    public String getUrlWisp () {
        return urlWisp;
    }

    public void setUrlWisp ( String urlWisp ) {
        this.urlWisp = urlWisp;
    }

    public String getCodiceEsito () {
        return codiceEsito;
    }

    public void setCodiceEsito ( String codiceEsito ) {
        this.codiceEsito = codiceEsito;
    }

    public String getDescrizioneEsito () {
        return descrizioneEsito;
    }

    public void setDescrizioneEsito ( String descrizioneEsito ) {
        this.descrizioneEsito = descrizioneEsito;
    }

    @Override
    public String toString () {
        return "AccessoChiamanteEsternoSincronoOutput ["
            + ( identificativoPagamento != null ? "identificativoPagamento=" + identificativoPagamento + ", " : "" )
            + ( iuv != null ? "iuv=" + iuv + ", " : "" ) + ( urlWisp != null ? "urlWisp=" + urlWisp + ", " : "" )
            + ( codiceEsito != null ? "codiceEsito=" + codiceEsito + ", " : "" ) + ( descrizioneEsito != null ? "descrizioneEsito=" + descrizioneEsito : "" )
            + "]";
    }

}
