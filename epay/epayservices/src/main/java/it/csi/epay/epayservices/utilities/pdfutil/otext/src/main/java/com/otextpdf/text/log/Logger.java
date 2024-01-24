/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.log;

public interface Logger {

	Logger getLogger ( Class<?> klass );

	Logger getLogger ( String name );

	boolean isLogging ( Level level );

	void warn ( final String message );

	void trace ( final String message );

	void debug ( final String message );

	void info ( final String message );

	void error ( final String message );

	void error ( final String message, Exception e );

}
