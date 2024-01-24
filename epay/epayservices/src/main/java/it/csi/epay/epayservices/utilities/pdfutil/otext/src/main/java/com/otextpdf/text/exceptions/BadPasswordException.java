/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.exceptions;

import java.io.IOException;


public class BadPasswordException extends IOException {

	private static final long serialVersionUID = -4333706268155063964L;

	public BadPasswordException ( final String message ) {
		super ( message );
	}
}
