/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.interfacews.epaymodric.exception;

public class NotAuthorizedException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NotAuthorizedException () {
        super ();
        // TODO Auto-generated constructor stub
    }

    public NotAuthorizedException ( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace ) {
        super ( message, cause, enableSuppression, writableStackTrace );
        // TODO Auto-generated constructor stub
    }

    public NotAuthorizedException ( String message, Throwable cause ) {
        super ( message, cause );
        // TODO Auto-generated constructor stub
    }

    public NotAuthorizedException ( String message ) {
        super ( message );
        // TODO Auto-generated constructor stub
    }

    public NotAuthorizedException ( Throwable cause ) {
        super ( cause );
        // TODO Auto-generated constructor stub
    }

}
