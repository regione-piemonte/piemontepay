/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.frontend.util;

import it.csi.epay.epaysimweb.common.Constants;


public enum Routing {

    HOME ( "/home" ),
    FLUSSI ( "/flussi" ),
    UTENTE ( "/utente" ),
    FUNZIONE ( "/funzione" ),
	ENTE ( "/ente" );

    private SubRouting subRouting;

    Routing ( SubRouting subRouting ) {
        this.subRouting = subRouting;
    }

    Routing () {

    }

    Routing ( SubRouting subRouting, String value ) {
        this.subRouting = subRouting;
        this.value = value;
    }

    Routing ( String value ) {
        this.value = value;
    }

    public enum SubRouting {

        RICERCA ( "ricerca" ),
        INSERISCI ( "inserisci" ),
        MODIFICA ( "modifica" ),
        VISUALIZZA ( "visualizza" );

        Routing parentRouting;

        String value;

        private SubRouting ( String value ) {
            this.value = value;
        }

        private SubRouting ( Routing routing, String value ) {
            this.value = routing.getValue () + Constants.ROUTING_SEPARATOR + value;
        }

        public String getValue () {
            return value;
        }

        public void setValue ( String value ) {
            this.value = value;
        }

        public Routing getParentRouting () {
            return parentRouting;
        }

        public void setParentRouting ( Routing parentRouting ) {
            this.parentRouting = parentRouting;
        }

    }

    String value;

    public SubRouting getSubRouting () {
        return subRouting;
    }

    public void setSubRouting ( SubRouting subRouting ) {
        this.subRouting = subRouting;
    }

    public String getValue () {
        return value;
    }

    public void setValue ( String value ) {
        this.value = value;
    }

}
