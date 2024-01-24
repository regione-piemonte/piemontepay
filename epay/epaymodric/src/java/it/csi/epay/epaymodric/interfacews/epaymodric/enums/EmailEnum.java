/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.interfacews.epaymodric.enums;

public enum EmailEnum {
        ERRORE_DI_SISTEMA ( "001" ),
        ERRORE_ACQUISIZIONE ( "002" ),
        ERRORE_BUSINESS ( "003" ),
        ERRORE_FLUSSO ( "004" ),
		ERRORE_ELABORAZIONE ( "005" );

    private final String codice;

    EmailEnum ( String codice ) {
        this.codice = codice;
    }

    public String getCodice () {
        return codice;
    }

}
