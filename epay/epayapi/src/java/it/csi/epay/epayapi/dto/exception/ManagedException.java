/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayapi.dto.exception;

import org.springframework.http.HttpStatus;

import it.csi.epay.epayapi.dto.common.MessaggioDTO;


/**
 *
 */

public class ManagedException extends RuntimeException {

    private static final int DEFAULT_STATUS = 500;

    private static final String DEFAULT_CODE = "INTERNAL";

    private int status;

    private String codice;

    private static final long serialVersionUID = 1L;

    public ManagedException ( HttpStatus status, MessaggioDTO messaggio ) {
        super ( messaggio.getMessaggio () );
        this.status = status.value ();
        codice = messaggio.getCodice ();
    }

    public ManagedException ( String message ) {
        super ( message );
        status = DEFAULT_STATUS;
        codice = DEFAULT_CODE;
    }

    public ManagedException ( HttpStatus status ) {
        super ( status.getReasonPhrase () );
        this.status = status.value ();
        codice = status.name ();
    }

    public ManagedException ( HttpStatus status, String message ) {
        super ( message );
        this.status = status.value ();
        codice = status.name ();
    }

    public ManagedException ( HttpStatus status, String codice, String message ) {
        super ( message );
        this.status = status.value ();
        this.codice = codice;
    }

    public ManagedException ( int status, String message ) {
        super ( message );
        this.status = status;
        codice = DEFAULT_CODE;
    }

    public ManagedException ( int status, String codice, String message ) {
        super ( message );
        this.status = status;
        this.codice = codice;
    }

    public ManagedException ( String codice, String message ) {
        super ( message );
        status = DEFAULT_STATUS;
        this.codice = codice;
    }

    public ManagedException ( String message, Throwable cause ) {
        super ( message, cause );
        status = DEFAULT_STATUS;
        codice = DEFAULT_CODE;
    }

    public ManagedException ( int status, String message, Throwable cause ) {
        super ( message, cause );
        this.status = status;
        codice = DEFAULT_CODE;
    }

    public ManagedException ( int status, String codice, String message, Throwable cause ) {
        super ( message, cause );
        this.status = status;
        this.codice = codice;
    }

    public ManagedException ( String codice, String message, Throwable cause ) {
        super ( message, cause );
        status = DEFAULT_STATUS;
        this.codice = codice;
    }

    public int getStatus () {
        return status;
    }

    public String getCodice () {
        return codice;
    }

}
