/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.enums;

public enum EmailEnum {
        TEST ( "TEST" ),
        MODIFICA_ENTE ( "MODIFICA_ENTE" ),
        CODICE_VERSAMENTO ( "CODICE_VERSAMENTO" ),
        RIFERIMENTO_CONTABILE ( "RIFERIMENTO_CONTABILE" ),
        INSERIMENTO_UTENTE ( "INSERIMENTO_UTENTE" ),
        DISATTIVAZIONE_UTENTE ( "DISATTIVAZIONE_UTENTE" ),
        ELIMINAZIONE_UTENTE ( "ELIMINAZIONE_UTENTE" ),
        AUTORIZZAZIONE_TEMATICA ( "AUTORIZZAZIONE_TEMATICA" ),
        REVOCA_TEMATICA ( "REVOCA_TEMATICA" ),
        RIATTIVAZIONE_UTENTE ( "RIATTIVAZIONE_UTENTE" ),
        ASSISTENZA_WSO2 ( "ASSISTENZA_WSO2" ),
        UNKNOWN ( "UNKNOWN" );

    private final String codice;

    EmailEnum ( String codice ) {
        this.codice = codice;
    }

    public String getCodice () {
        return codice;
    }

}
