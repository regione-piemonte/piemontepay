/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.util.pdf.barcode;

public class BadElementException extends DocumentException {

	private static final long serialVersionUID = -799006030723822254L;

	public BadElementException ( Exception ex ) {
		super ( ex );
	}

	BadElementException () {
		super ();
	}

	public BadElementException ( String message ) {
		super ( message );
	}
}
