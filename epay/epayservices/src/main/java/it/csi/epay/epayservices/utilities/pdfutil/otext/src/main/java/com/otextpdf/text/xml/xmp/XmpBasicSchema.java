/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.xml.xmp;

@Deprecated
public class XmpBasicSchema extends XmpSchema {

	public static final String DEFAULT_XPATH_ID = "xmp";

	public static final String DEFAULT_XPATH_URI = "http://ns.adobe.com/xap/1.0/";

	public static final String ADVISORY = "xmp:Advisory";

	public static final String BASEURL = "xmp:BaseURL";

	public static final String CREATEDATE = "xmp:CreateDate";

	public static final String CREATORTOOL = "xmp:CreatorTool";

	public static final String IDENTIFIER = "xmp:Identifier";

	public static final String METADATADATE = "xmp:MetadataDate";

	public static final String MODIFYDATE = "xmp:ModifyDate";

	public static final String NICKNAME = "xmp:Nickname";

	public static final String THUMBNAILS = "xmp:Thumbnails";

	private static final long serialVersionUID = -2416613941622479298L;

	public XmpBasicSchema () {
		super ( "xmlns:" + DEFAULT_XPATH_ID + "=\"" + DEFAULT_XPATH_URI + "\"" );
	}

}
