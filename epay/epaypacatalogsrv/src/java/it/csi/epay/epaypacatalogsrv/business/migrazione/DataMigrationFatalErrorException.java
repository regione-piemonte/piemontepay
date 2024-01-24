/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.migrazione;

/**
 *
 */

public class DataMigrationFatalErrorException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private MigrationContext context;

    public MigrationContext getContext () {
        return context;
    }

    public DataMigrationFatalErrorException () {

    }

    public DataMigrationFatalErrorException ( String message ) {
        super ( message );

    }

    public DataMigrationFatalErrorException ( Throwable cause ) {
        super ( cause );

    }

    public DataMigrationFatalErrorException ( String message, Throwable cause ) {
        super ( message, cause );

    }

    public DataMigrationFatalErrorException ( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace ) {
        super ( message, cause, enableSuppression, writableStackTrace );

    }

}
