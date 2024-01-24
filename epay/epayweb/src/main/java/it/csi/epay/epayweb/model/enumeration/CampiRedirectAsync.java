/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayweb.model.enumeration;

/**
 *
 */

public enum CampiRedirectAsync {
        EMAIL ( "EMAIL" ),
        RIPETI_EMAIL ( "RIPETI_EMAIL" ),
        VERIFICA_PRIVACY ( "VERIFICA_PRIVACY" ),
        VERIFICA_CAPTCHA ( "VERIFICA_CAPTCHA" );

    private String id;

    private CampiRedirectAsync ( String id ) {
        this.id = id;
    }

    public String getId () {
        return id;
    }

    public static CampiRedirectAsync findById ( String id ) {
        for ( CampiRedirectAsync c: CampiRedirectAsync.values () ) {
            if ( c.getId ().equals ( id ) ) {
                return c;
            }
        }
        return null;
    }

}
