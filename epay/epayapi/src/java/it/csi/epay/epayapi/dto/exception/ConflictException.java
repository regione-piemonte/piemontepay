/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayapi.dto.exception;

/**
 *
 */

public class ConflictException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ConflictException () {
        super ();

    }

    public ConflictException ( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace ) {
        super ( message, cause, enableSuppression, writableStackTrace );

    }

    public ConflictException ( String message, Throwable cause ) {
        super ( message, cause );

    }

    public ConflictException ( String message ) {
        super ( message );

    }

    public ConflictException ( Throwable cause ) {
        super ( cause );

    }

}
