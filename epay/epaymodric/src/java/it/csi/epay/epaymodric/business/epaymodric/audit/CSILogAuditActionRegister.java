/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.audit;

public enum CSILogAuditActionRegister {

        INSERIMENTO ( "001" ),
        MODIFICA ( "002" ),
        ELIMINAZIONE ( "003" ),
        ESTRAZIONE ( "004" ),
        ACCESSO ( "010" ),
        ACCESSO_NEGATO ( "011" ),
        UNKNOWN ( "000" );

    public static CSILogAuditActionRegister getById ( String id ) {
        for ( CSILogAuditActionRegister e: values () ) {
            if ( e.id.equals ( id ) ) {
                return e;
            }
        }
        return UNKNOWN;
    }

    private String id;

    CSILogAuditActionRegister ( String id ) {
        this.id = id;
    }

    public String getId () {
        return id;
    }

}
