/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.security;

import it.csi.epay.epaymodric.interfacews.epaymodric.exception.NotAuthorizedException;


public abstract class SecurityUtils {

    public static void assertValidPrincipal () {

        PrincipalVO principal = RequestContextUtils.getPrincipal ();
        if ( principal == null || principal.getUtente () == null ) {
            throw new NotAuthorizedException ( "E' richiesto un chiamante identificabile ma non e' stato possibile verificare il chiamante corrente" );
        }

    }

    public static void assertUseCase ( String uc ) {

        PrincipalVO principal = RequestContextUtils.getPrincipal ();
        if ( principal == null ) {
            throw new NotAuthorizedException ( "E' richiesto uno specifico use-case ma non e' stato possibile identificare il chiamante" );
        } else {
            if ( !principal.hasUseCase ( uc ) ) {
                throw new NotAuthorizedException ( "E' richiesto uno specifico use-case che non e' stato garantito all'utente chiamante" );
            }
        }

    }

    public static void assertAnyUseCase ( String... ucs ) {

        PrincipalVO principal = RequestContextUtils.getPrincipal ();
        if ( principal == null ) {
            throw new NotAuthorizedException ( "E' richiesto uno di alcuni use-case ma non e' stato possibile identificare il chiamante" );
        } else {
            for ( String uc: ucs ) {
                if ( principal.hasUseCase ( uc ) ) {
                    return;
                }
            }
            throw new NotAuthorizedException ( "E' richiesto uno di alcuni use-case ma nessuno di essi e' stato garantito all'utente chiamante" );
        }

    }


}
