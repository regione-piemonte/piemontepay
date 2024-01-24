/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.dto.security;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * Annotazione utilizzata per specificare le autorizzazioni necessarie all'accesso ai metodi delle risorse RestEasy.
 *
 * puo' essere utilizzata per richiedere: - una singola autorizzazione - piu' autorizzazioni allo stesso tempo - almeno una di una lista di autorizzazioni
 */
@Documented
@Retention ( value = java.lang.annotation.RetentionPolicy.RUNTIME )
@Target ( value = { java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.METHOD } )
public @interface Secured {

    /**
     * @return valore da specificare per permettere l'accesso a chiunque, ma ricordandosi di modificare in seguito
     */
    @Deprecated
    boolean notSecured () default false;

    /**
     * @return valore da specificare quando si vuole permettere l'accesso solo in ambienti di test
     */
    boolean testOnly () default false;

    /**
     * @return valore da specificare quando si vuole permettere l'accesso in ogni caso, es: Secured( permitAll = true )
     */
    boolean permitAll () default false;

    /**
     * @return valore da specificare quando si vuole negare l'accesso in ogni caso, es: Secured( denyAll = true )
     */
    boolean denyAll () default false;

    /**
     * @return valore da specificare quando si vuole permettere l'accesso solo ai client che si sono autenticati e che sono validi
     */
    boolean authenticated () default false;

    Scopes [] value () default {};

    Scopes [] hasAnyScope () default {};

}
