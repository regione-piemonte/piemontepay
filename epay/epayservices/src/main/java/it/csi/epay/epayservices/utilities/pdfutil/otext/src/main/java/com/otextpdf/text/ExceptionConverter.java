/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

public class ExceptionConverter extends RuntimeException {

	private static final long serialVersionUID = 8657630363395849399L;

	private final Exception ex;

	private final String prefix;

	public ExceptionConverter ( Exception ex ) {
		super ( ex );
		this.ex = ex;
		prefix = ( ex instanceof RuntimeException ) ? "" : "ExceptionConverter: ";
	}

	public static RuntimeException convertException ( Exception ex ) {
		if ( ex instanceof RuntimeException ) {
			return (RuntimeException) ex;
		}
		return new ExceptionConverter ( ex );
	}

	public Exception getException () {
		return ex;
	}

	public String getMessage () {
		return ex.getMessage ();
	}

	public String getLocalizedMessage () {
		return ex.getLocalizedMessage ();
	}

	public String toString () {
		return prefix + ex;
	}

	public void printStackTrace () {
		printStackTrace ( System.err );
	}

	public void printStackTrace ( java.io.PrintStream s ) {
		synchronized ( s ) {
			s.print ( prefix );
			ex.printStackTrace ( s );
		}
	}

	public void printStackTrace ( java.io.PrintWriter s ) {
		synchronized ( s ) {
			s.print ( prefix );
			ex.printStackTrace ( s );
		}
	}

	public Throwable fillInStackTrace () {
		return this;
	}
}
