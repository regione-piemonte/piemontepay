/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.xml.xmp;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPConst;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPMeta;


public class XmpBasicProperties {

	public static final String ADVISORY = "Advisory";

	public static final String BASEURL = "BaseURL";

	public static final String CREATEDATE = "CreateDate";

	public static final String CREATORTOOL = "CreatorTool";

	public static final String IDENTIFIER = "Identifier";

	public static final String METADATADATE = "MetadataDate";

	public static final String MODIFYDATE = "ModifyDate";

	public static final String NICKNAME = "Nickname";

	public static final String THUMBNAILS = "Thumbnails";

	static public void setModDate ( XMPMeta xmpMeta, String date ) throws XMPException {
		xmpMeta.setProperty ( XMPConst.NS_XMP, MODIFYDATE, date );
	}

	static public void setMetaDataDate ( XMPMeta xmpMeta, String date ) throws XMPException {
		xmpMeta.setProperty ( XMPConst.NS_XMP, METADATADATE, date );
	}

}
