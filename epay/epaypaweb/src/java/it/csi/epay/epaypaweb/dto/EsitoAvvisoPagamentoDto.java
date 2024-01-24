/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import static it.csi.epay.epaypaweb.util.Util.quote;

import java.io.Serializable;


/** dto presentation <-> business <-> integration */
public class EsitoAvvisoPagamentoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codEsito;

    private String desEsito;

    private byte [] avvisoPagamentoData;

    public EsitoAvvisoPagamentoDto () {
    }

    public EsitoAvvisoPagamentoDto ( String codEsito, String desEsito ) {
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
        final String COMMA = ", ";
        StringBuilder s = new StringBuilder ();
        s.append ( "{ " );
        s.append ( "codEsito:" ).append ( quote ( codEsito ) ).append ( COMMA );
        s.append ( "desEsito:" ).append ( quote ( desEsito ) ).append ( COMMA );
        s.append ( "avvisoPagamentoData:" ).append ( avvisoPagamentoData );
        s.append ( " }" );
        return s.toString ();
    }

}
