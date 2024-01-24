/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;


public class PdfICCBased extends PdfStream {

	public PdfICCBased ( ICC_Profile profile, int compressionLevel ) {
		super ();
		try {
			int numberOfComponents = profile.getNumComponents ();
			switch ( numberOfComponents ) {
			case 1:
				put ( PdfName.ALTERNATE, PdfName.DEVICEGRAY );
				break;
			case 3:
				put ( PdfName.ALTERNATE, PdfName.DEVICERGB );
				break;
			case 4:
				put ( PdfName.ALTERNATE, PdfName.DEVICECMYK );
				break;
			default:
				throw new PdfException ( MessageLocalization.getComposedMessage ( "1.component.s.is.not.supported", numberOfComponents ) );
			}
			put ( PdfName.N, new PdfNumber ( numberOfComponents ) );
			bytes = profile.getData ();
			put ( PdfName.LENGTH, new PdfNumber ( bytes.length ) );
			flateCompress ( compressionLevel );
		} catch ( Exception e ) {
			throw new ExceptionConverter ( e );
		}
	}
}
