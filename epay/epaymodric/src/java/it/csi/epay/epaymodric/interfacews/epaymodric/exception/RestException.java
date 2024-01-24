/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.interfacews.epaymodric.exception;

import org.springframework.http.HttpStatus;

/**
 * @author vsgro
 */

public class RestException extends Exception {
	private static final int DEFAULT_STATUS = 500;
	
	private static final String DEFAULT_CODE = "INTERNAL";
	
	private int status;
	
	private String codice;
	
	private static final long serialVersionUID = -7478790655529042486L;
	
	public RestException ( String message ) {
	    super ( message );
	    status = DEFAULT_STATUS;
	    codice = DEFAULT_CODE;
	}
	
	public RestException ( HttpStatus status ) {
	    super ( status.getReasonPhrase () );
	    this.status = status.value ();
	    codice = status.name ();
	}
	
	public RestException ( HttpStatus status, String message ) {
	    super ( message );
	    this.status = status.value ();
	    codice = status.name ();
	}
	
	public RestException ( int status, String message ) {
	    super ( message );
	    this.status = status;
	    codice = DEFAULT_CODE;
	}
	
	public RestException ( int status, String codice, String message ) {
	    super ( message );
	    this.status = status;
	    this.codice = codice;
	}
	
	public RestException ( String codice, String message ) {
	    super ( message );
	    status = DEFAULT_STATUS;
	    this.codice = codice;
	}
	
	public RestException ( String message, Throwable cause ) {
	    super ( message, cause );
	    status = DEFAULT_STATUS;
	    codice = DEFAULT_CODE;
	}
	
	public RestException ( int status, String message, Throwable cause ) {
	    super ( message, cause );
	    this.status = status;
	    codice = DEFAULT_CODE;
	}
	
	public RestException ( int status, String codice, String message, Throwable cause ) {
	    super ( message, cause );
	    this.status = status;
	    this.codice = codice;
	}
	
	public RestException ( String codice, String message, Throwable cause ) {
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
