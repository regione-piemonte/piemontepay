/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.migrazione;

/**
 *
 */

public class DataMigrationPreviewOnlyException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private MigrationContext context;

    public MigrationContext getContext () {
        return context;
    }

    public DataMigrationPreviewOnlyException ( MigrationContext context ) {
        this.context = context;
    }

    public DataMigrationPreviewOnlyException ( MigrationContext context, String message ) {
        super ( message );
        this.context = context;
    }

    public DataMigrationPreviewOnlyException ( MigrationContext context, Throwable cause ) {
        super ( cause );
        this.context = context;
    }

    public DataMigrationPreviewOnlyException ( MigrationContext context, String message, Throwable cause ) {
        super ( message, cause );
        this.context = context;
    }

    public DataMigrationPreviewOnlyException ( MigrationContext context, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace ) {
        super ( message, cause, enableSuppression, writableStackTrace );
        this.context = context;
    }

}
