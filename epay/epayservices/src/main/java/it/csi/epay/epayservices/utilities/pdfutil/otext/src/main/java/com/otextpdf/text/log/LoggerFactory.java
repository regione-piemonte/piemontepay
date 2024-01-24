/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.log;

public class LoggerFactory {

	private static final LoggerFactory myself;

	static {
		myself = new LoggerFactory ();
	}

	private Logger logger = new NoOpLogger ();

	private LoggerFactory () {
	}

	public static Logger getLogger ( final Class<?> klass ) {
		return myself.logger.getLogger ( klass );
	}

	public static Logger getLogger ( final String name ) {
		return myself.logger.getLogger ( name );
	}

	public static LoggerFactory getInstance () {
		return myself;
	}

	public void setLogger ( final Logger logger ) {
		this.logger = logger;
	}

	public Logger logger () {
		return logger;
	}

}
