/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import it.csi.epay.epaybeapi.api.util.SpringApplicationContextHelper;


/**
 * Loader dell'applicazione RestEasy: specifica le risorse da caricare e il primo segmento per il routing (es. '/api')
 */
@ApplicationPath ( "api" )
public class EpaybeapiWebRestApplication extends Application {

    private Set<Object> singletons = new HashSet<> ();

    private Set<Class<?>> classes = new HashSet<> ();

    /**
     * Registra tutte le risorse che costituiranno l'applicazione RestEasy: endpoints, interceptors, ...
     *
     * @throws IOException in caso di problemi con lo scan dei package
     */
    public EpaybeapiWebRestApplication () throws IOException {

        /*
         * invece di aggiungere manualmente le risorse di restEasy, eseguo un 'autoscan' simile a quello eseguito da spring
         */
        SpringApplicationContextHelper.caricaRisorseRestEasyInPackage (
            this.getClass ().getClassLoader (), singletons, "it.csi.epay.epaybeapi.api" );
    }

    @Override
    public Set<Class<?>> getClasses () {
        return classes;
    }

    @Override
    public Set<Object> getSingletons () {
        return singletons;
    }

}
