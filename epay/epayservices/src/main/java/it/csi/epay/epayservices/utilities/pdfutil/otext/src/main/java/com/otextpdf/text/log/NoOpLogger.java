/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.log;

public final class NoOpLogger implements Logger {

	public Logger getLogger ( final Class<?> name ) {
		return this;
	}

	public void warn ( final String message ) {
	}

	public void trace ( final String message ) {
	}

	public void debug ( final String message ) {
	}

	public void info ( final String message ) {
	}

	public void error ( final String message, final Exception e ) {
	}

	public boolean isLogging ( final Level level ) {
		return false;
	}

	public void error ( final String message ) {
	}

	public Logger getLogger ( final String name ) {
		return this;
	}
}
