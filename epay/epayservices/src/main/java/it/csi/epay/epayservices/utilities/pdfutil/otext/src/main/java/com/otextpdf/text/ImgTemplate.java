/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfTemplate;

import java.net.URL;


public class ImgTemplate extends Image {

	public ImgTemplate ( PdfTemplate template ) throws BadElementException {
		super ( (URL) null );
		if ( template == null )
			throw new BadElementException ( MessageLocalization.getComposedMessage ( "the.template.can.not.be.null" ) );
		if ( template.getType () == PdfTemplate.TYPE_PATTERN )
			throw new BadElementException ( MessageLocalization.getComposedMessage ( "a.pattern.can.not.be.used.as.a.template.to.create.an.image" ) );
		type = IMGTEMPLATE;
		scaledHeight = template.getHeight ();
		setTop ( scaledHeight );
		scaledWidth = template.getWidth ();
		setRight ( scaledWidth );
		setTemplateData ( template );
		plainWidth = getWidth ();
		plainHeight = getHeight ();
	}

}
