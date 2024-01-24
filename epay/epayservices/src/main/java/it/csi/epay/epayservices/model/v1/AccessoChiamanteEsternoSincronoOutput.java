/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model.v1;

import java.io.Serializable;


public class AccessoChiamanteEsternoSincronoOutput implements Serializable {

    private static final long serialVersionUID = -6903007449346036409L;

    private String identificativoPagamento;

    private String iuv;

    private String paymentUrl;

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
   
    
    public String getPaymentUrl () {
        return paymentUrl;
    }

    
    public void setPaymentUrl ( String paymentUrl ) {
        this.paymentUrl = paymentUrl;
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
            + ( iuv != null ? "iuv=" + iuv + ", " : "" ) + ( paymentUrl != null ? "paymentUrl=" + paymentUrl + ", " : "" )
            + ( codiceEsito != null ? "codiceEsito=" + codiceEsito + ", " : "" ) + ( descrizioneEsito != null ? "descrizioneEsito=" + descrizioneEsito : "" )
            + "]";
    }

}
