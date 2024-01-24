/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Annotation implements Element {

	public static final int TEXT = 0;

	public static final int URL_NET = 1;

	public static final int URL_AS_STRING = 2;

	public static final int FILE_DEST = 3;

	public static final int FILE_PAGE = 4;

	public static final int NAMED_DEST = 5;

	public static final int LAUNCH = 6;

	public static final int SCREEN = 7;

	public static final String TITLE = "title";

	public static final String CONTENT = "content";

	public static final String URL = "url";

	public static final String FILE = "file";

	public static final String DESTINATION = "destination";

	public static final String PAGE = "page";

	public static final String NAMED = "named";

	public static final String APPLICATION = "application";

	public static final String PARAMETERS = "parameters";

	public static final String OPERATION = "operation";

	public static final String DEFAULTDIR = "defaultdir";

	public static final String LLX = "llx";

	public static final String LLY = "lly";

	public static final String URX = "urx";

	public static final String URY = "ury";

	public static final String MIMETYPE = "mime";

	protected int annotationtype;

	protected HashMap<String, Object> annotationAttributes = new HashMap<> ();

	protected float llx = Float.NaN;

	protected float lly = Float.NaN;

	protected float urx = Float.NaN;

	protected float ury = Float.NaN;

	private Annotation ( final float llx, final float lly, final float urx, final float ury ) {
		this.llx = llx;
		this.lly = lly;
		this.urx = urx;
		this.ury = ury;
	}

	public Annotation ( final Annotation an ) {
		annotationtype = an.annotationtype;
		annotationAttributes = an.annotationAttributes;
		llx = an.llx;
		lly = an.lly;
		urx = an.urx;
		ury = an.ury;
	}

	public Annotation ( final String title, final String text ) {
		annotationtype = TEXT;
		annotationAttributes.put ( TITLE, title );
		annotationAttributes.put ( CONTENT, text );
	}

	public Annotation ( final String title, final String text, final float llx, final float lly,
					final float urx, final float ury ) {
		this ( llx, lly, urx, ury );
		annotationtype = TEXT;
		annotationAttributes.put ( TITLE, title );
		annotationAttributes.put ( CONTENT, text );
	}

	public Annotation ( final float llx, final float lly, final float urx, final float ury, final URL url ) {
		this ( llx, lly, urx, ury );
		annotationtype = URL_NET;
		annotationAttributes.put ( URL, url );
	}

	public Annotation ( final float llx, final float lly, final float urx, final float ury, final String url ) {
		this ( llx, lly, urx, ury );
		annotationtype = URL_AS_STRING;
		annotationAttributes.put ( FILE, url );
	}

	public Annotation ( final float llx, final float lly, final float urx, final float ury, final String file,
					final String dest ) {
		this ( llx, lly, urx, ury );
		annotationtype = FILE_DEST;
		annotationAttributes.put ( FILE, file );
		annotationAttributes.put ( DESTINATION, dest );
	}

	public Annotation ( final float llx, final float lly, final float urx, final float ury,
					final String moviePath, final String mimeType, final boolean showOnDisplay ) {
		this ( llx, lly, urx, ury );
		annotationtype = SCREEN;
		annotationAttributes.put ( FILE, moviePath );
		annotationAttributes.put ( MIMETYPE, mimeType );
		annotationAttributes.put ( PARAMETERS, new boolean[] {
						false /* embedded */, showOnDisplay } );
	}

	public Annotation ( final float llx, final float lly, final float urx, final float ury, final String file,
					final int page ) {
		this ( llx, lly, urx, ury );
		annotationtype = FILE_PAGE;
		annotationAttributes.put ( FILE, file );
		annotationAttributes.put ( PAGE, page );
	}

	public Annotation ( final float llx, final float lly, final float urx, final float ury, final int named ) {
		this ( llx, lly, urx, ury );
		annotationtype = NAMED_DEST;
		annotationAttributes.put ( NAMED, named );
	}

	public Annotation ( final float llx, final float lly, final float urx, final float ury,
					final String application, final String parameters, final String operation,
					final String defaultdir ) {
		this ( llx, lly, urx, ury );
		annotationtype = LAUNCH;
		annotationAttributes.put ( APPLICATION, application );
		annotationAttributes.put ( PARAMETERS, parameters );
		annotationAttributes.put ( OPERATION, operation );
		annotationAttributes.put ( DEFAULTDIR, defaultdir );
	}

	public int type () {
		return Element.ANNOTATION;
	}

	public boolean process ( final ElementListener listener ) {
		try {
			return listener.add ( this );
		} catch ( DocumentException de ) {
			return false;
		}
	}

	public List<Chunk> getChunks () {
		return new ArrayList<> ();
	}

	public void setDimensions ( final float llx, final float lly, final float urx, final float ury ) {
		this.llx = llx;
		this.lly = lly;
		this.urx = urx;
		this.ury = ury;
	}

	public float llx () {
		return llx;
	}

	public float lly () {
		return lly;
	}

	public float urx () {
		return urx;
	}

	public float ury () {
		return ury;
	}

	public float llx ( final float def ) {
		if ( Float.isNaN ( llx ) )
			return def;
		return llx;
	}

	public float lly ( final float def ) {
		if ( Float.isNaN ( lly ) )
			return def;
		return lly;
	}

	public float urx ( final float def ) {
		if ( Float.isNaN ( urx ) )
			return def;
		return urx;
	}

	public float ury ( final float def ) {
		if ( Float.isNaN ( ury ) )
			return def;
		return ury;
	}

	public int annotationType () {
		return annotationtype;
	}

	public String title () {
		String s = (String) annotationAttributes.get ( TITLE );
		if ( s == null )
			s = "";
		return s;
	}

	public String content () {
		String s = (String) annotationAttributes.get ( CONTENT );
		if ( s == null )
			s = "";
		return s;
	}

	public HashMap<String, Object> attributes () {
		return annotationAttributes;
	}

	public boolean isContent () {
		return true;
	}

	public boolean isNestable () {
		return true;
	}

}
