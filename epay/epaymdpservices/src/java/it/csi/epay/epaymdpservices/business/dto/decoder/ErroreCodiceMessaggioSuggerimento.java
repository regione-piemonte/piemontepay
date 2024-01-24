/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymdpservices.business.dto.decoder;

public class ErroreCodiceMessaggioSuggerimento extends ErroreCodiceMessaggio {

    private String suggerimento;

    public ErroreCodiceMessaggioSuggerimento ( String codice, String messaggio, String suggerimento ) {
        super ( codice, messaggio );
        this.suggerimento = suggerimento;
    }

    public ErroreCodiceMessaggioSuggerimento () {
        super ();
    }

    public String getSuggerimento () {
        return suggerimento;
    }

    public void setSuggerimento ( String suggerimento ) {
        this.suggerimento = suggerimento;
    }

}
