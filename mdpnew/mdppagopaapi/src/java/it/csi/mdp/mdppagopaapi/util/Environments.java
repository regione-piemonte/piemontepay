/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.util;

/**
 *
 */

public enum Environments {
        LOCAL ( "local" ),
        DEV ( "dev-int-01" ),
        DEV_ENGINEERING ( "dev-eng" ),
		DOCKER ( "docker" ),
        TEST ( "tst-int-01" ),
        TEST_UTENTE ( "tu1-int-01" ),
        COLL ( "coll-int-01" ),
        PROD ( "prod-int-01" );

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
