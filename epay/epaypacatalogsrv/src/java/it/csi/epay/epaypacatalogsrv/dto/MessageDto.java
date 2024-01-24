/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto;

import java.io.Serializable;


public class MessageDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codiceMessaggio;

    private String messaggio;

    public MessageDto () {
    }

    public MessageDto ( String codiceMessaggio, String messaggio ) {
        super ();
        this.codiceMessaggio = codiceMessaggio;
        this.messaggio = messaggio;
    }

    public String getCodiceMessaggio () {
        return codiceMessaggio;
    }

    public void setCodiceMessaggio ( String codiceMessaggio ) {
        this.codiceMessaggio = codiceMessaggio;
    }

    public String getMessaggio () {
        return messaggio;
    }

    public void setMessaggio ( String messaggio ) {
        this.messaggio = messaggio;
    }

}
