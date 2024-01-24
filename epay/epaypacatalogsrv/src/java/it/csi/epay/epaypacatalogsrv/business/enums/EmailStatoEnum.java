/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.enums;

public enum EmailStatoEnum {
        INVIATA ( "OK" ),
        ERRORE ( "KO" ),
        ATTESA ( "WAIT" );

    private final String codice;

    EmailStatoEnum ( String codice ) {
        this.codice = codice;
    }

    public String getCodice () {
        return codice;
    }

}
