/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.io;

import java.io.IOException;


@SuppressWarnings ( "serial" )
public class MapFailedException extends IOException {

	public MapFailedException ( IOException e ) {
		super ( e.getMessage () );
		initCause ( e );
	}
}
