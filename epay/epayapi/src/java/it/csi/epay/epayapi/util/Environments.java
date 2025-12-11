/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayapi.util;

/**
 *
 */

public enum Environments {
        LOCAL ( "local" ),
        DEV ( "dev-rp-01" ),
        DEV_ENGINEERING ( "dev-eng" ),
		DOCKER ( "docker" ),
        TEST ( "tst-rp-01" ),
        TEST_UTENTE ( "tu1-rp-01" ),
        COLL ( "coll-rp-01" ),
        PROD ( "prod-rp-01" );

    private String codice;

    public String getCodice () {
        return codice;
    }

    private Environments ( String codice ) {
        this.codice = codice;
    }

    public static Environments fromCodice ( String codice ) {
        for ( Environments candidate: Environments.values () ) {
            if ( candidate.getCodice ().equals ( codice ) ) {
                return candidate;
            }
        }
        return null;
    }

}
