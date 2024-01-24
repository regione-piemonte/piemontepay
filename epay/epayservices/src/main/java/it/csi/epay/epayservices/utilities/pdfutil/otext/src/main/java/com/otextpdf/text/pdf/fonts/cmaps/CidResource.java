/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.fonts.cmaps;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.io.RandomAccessSourceFactory;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.io.StreamUtil;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.BaseFont;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PRTokeniser;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.RandomAccessFileOrArray;

import java.io.IOException;
import java.io.InputStream;


public class CidResource implements CidLocation {

	public PRTokeniser getLocation ( String location ) throws IOException {
		String fullName = BaseFont.RESOURCE_PATH + "cmaps/" + location;
		InputStream inp = StreamUtil.getResourceStream ( fullName );
		if ( inp == null )
			throw new IOException ( MessageLocalization.getComposedMessage ( "the.cmap.1.was.not.found", fullName ) );
		return new PRTokeniser ( new RandomAccessFileOrArray ( new RandomAccessSourceFactory ().createSource ( inp ) ) );
	}
}
