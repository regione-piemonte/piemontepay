/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import static it.csi.epay.epaypaweb.util.Util.quote;

import java.io.Serializable;


public class RichiestaAvvisoPagamentoDto implements Serializable {

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
        final String COMMA = ", ";
        StringBuilder s = new StringBuilder ();
        s.append ( "{ " );
        s.append ( "\"iuv\":" ).append ( quote ( iuv ) ).append ( COMMA );
        s.append ( "\"codAvviso\":" ).append ( quote ( codAvviso ) );
        s.append ( " }" );
        return s.toString ();
    }
}
