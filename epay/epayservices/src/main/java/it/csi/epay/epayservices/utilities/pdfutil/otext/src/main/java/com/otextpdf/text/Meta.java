/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import java.util.ArrayList;
import java.util.List;


public class Meta implements Element {

	public static final String UNKNOWN = "unknown";

	public static final String PRODUCER = "producer";

	public static final String CREATIONDATE = "creationdate";

	public static final String AUTHOR = "author";

	public static final String KEYWORDS = "keywords";

	public static final String SUBJECT = "subject";

	public static final String TITLE = "title";

	private final int type;

	private final StringBuffer content;

	Meta ( final int type, final String content ) {
		this.type = type;
		this.content = new StringBuffer ( content );
	}

	public Meta ( final String tag, final String content ) {
		this.type = Meta.getType ( tag );
		this.content = new StringBuffer ( content );
	}

	public static int getType ( final String tag ) {
		if ( Meta.SUBJECT.equals ( tag ) ) {
			return Element.SUBJECT;
		}
		if ( Meta.KEYWORDS.equals ( tag ) ) {
			return Element.KEYWORDS;
		}
		if ( Meta.AUTHOR.equals ( tag ) ) {
			return Element.AUTHOR;
		}
		if ( Meta.TITLE.equals ( tag ) ) {
			return Element.TITLE;
		}
		if ( Meta.PRODUCER.equals ( tag ) ) {
			return Element.PRODUCER;
		}
		if ( Meta.CREATIONDATE.equals ( tag ) ) {
			return Element.CREATIONDATE;
		}
		return Element.HEADER;
	}

	public boolean process ( final ElementListener listener ) {
		try {
			return listener.add ( this );
		} catch ( DocumentException de ) {
			return false;
		}
	}

	public int type () {
		return type;
	}

	public List<Chunk> getChunks () {
		return new ArrayList<> ();
	}

	public boolean isContent () {
		return false;
	}

	public boolean isNestable () {
		return false;
	}

	public StringBuffer append ( final String string ) {
		return content.append ( string );
	}

	public String getContent () {
		return content.toString ();
	}

	public String getName () {
		switch ( type ) {
		case Element.SUBJECT:
			return Meta.SUBJECT;
		case Element.KEYWORDS:
			return Meta.KEYWORDS;
		case Element.AUTHOR:
			return Meta.AUTHOR;
		case Element.TITLE:
			return Meta.TITLE;
		case Element.PRODUCER:
			return Meta.PRODUCER;
		case Element.CREATIONDATE:
			return Meta.CREATIONDATE;
		default:
			return Meta.UNKNOWN;
		}
	}

}
