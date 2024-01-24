/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.ws.business.coopapplicativapec.util;

import javax.ejb.ApplicationException;

/**
 *
 */
@ApplicationException ( rollback = true )
public class ProgrammedRollbackException extends Exception {

    public ProgrammedRollbackException () {
        // TODO Auto-generated constructor stub
    }

    public ProgrammedRollbackException ( String message ) {
        super ( message );
        // TODO Auto-generated constructor stub
    }

    public ProgrammedRollbackException ( Throwable cause ) {
        super ( cause );
        // TODO Auto-generated constructor stub
    }

    public ProgrammedRollbackException ( String message, Throwable cause ) {
        super ( message, cause );
        // TODO Auto-generated constructor stub
    }

    public ProgrammedRollbackException ( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace ) {
        super ( message, cause, enableSuppression, writableStackTrace );
        // TODO Auto-generated constructor stub
    }

}
