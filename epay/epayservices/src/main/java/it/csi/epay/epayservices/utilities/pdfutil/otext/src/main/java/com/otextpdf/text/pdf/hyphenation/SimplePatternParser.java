/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.hyphenation;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.xml.simpleparser.SimpleXMLDocHandler;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.xml.simpleparser.SimpleXMLParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;


public class SimplePatternParser implements SimpleXMLDocHandler, PatternConsumer {

	static final int ELEM_CLASSES = 1;

	static final int ELEM_EXCEPTIONS = 2;

	static final int ELEM_PATTERNS = 3;

	static final int ELEM_HYPHEN = 4;

	int currElement;

	PatternConsumer consumer;

	StringBuffer token;

	ArrayList<Object> exception;

	char hyphenChar;

	SimpleXMLParser parser;

	public SimplePatternParser () {
		token = new StringBuffer ();
		hyphenChar = '-';
	}

	protected static String getPattern ( String word ) {
		StringBuilder pat = new StringBuilder ();
		int len = word.length ();
		for ( int i = 0; i < len; i++ ) {
			if ( !Character.isDigit ( word.charAt ( i ) ) ) {
				pat.append ( word.charAt ( i ) );
			}
		}
		return pat.toString ();
	}

	protected static String getInterletterValues ( String pat ) {
		StringBuilder il = new StringBuilder ();
		String word = pat + "a";
		int len = word.length ();
		for ( int i = 0; i < len; i++ ) {
			char c = word.charAt ( i );
			if ( Character.isDigit ( c ) ) {
				il.append ( c );
				i++;
			} else {
				il.append ( '0' );
			}
		}
		return il.toString ();
	}

	public void parse ( InputStream stream, PatternConsumer consumer ) {
		this.consumer = consumer;
		try {
			SimpleXMLParser.parse ( this, stream );
		} catch ( IOException e ) {
			throw new ExceptionConverter ( e );
		} finally {
			try {
				stream.close ();
			} catch ( Exception ignored ) {
			}
		}
	}

	protected ArrayList<Object> normalizeException ( ArrayList<Object> ex ) {
		ArrayList<Object> res = new ArrayList<> ();
		for ( Object item : ex ) {
			if ( item instanceof String ) {
				String str = (String) item;
				StringBuilder buf = new StringBuilder ();
				for ( int j = 0; j < str.length (); j++ ) {
					char c = str.charAt ( j );
					if ( c != hyphenChar ) {
						buf.append ( c );
					} else {
						res.add ( buf.toString () );
						buf.setLength ( 0 );
						char[] h = new char[1];
						h[0] = hyphenChar;
						res.add ( new Hyphen ( new String ( h ), null, null ) );
					}
				}
				if ( buf.length () > 0 ) {
					res.add ( buf.toString () );
				}
			} else {
				res.add ( item );
			}
		}
		return res;
	}

	protected String getExceptionWord ( ArrayList<Object> ex ) {
		StringBuilder res = new StringBuilder ();
		for ( Object item : ex ) {
			if ( item instanceof String ) {
				res.append ( (String) item );
			} else {
				if ( ( (Hyphen) item ).noBreak != null ) {
					res.append ( ( (Hyphen) item ).noBreak );
				}
			}
		}
		return res.toString ();
	}

	public void endDocument () {
	}

	@SuppressWarnings ( "unchecked" )
	public void endElement ( String tag ) {
		if ( token.length () > 0 ) {
			String word = token.toString ();
			switch ( currElement ) {
			case ELEM_CLASSES:
				consumer.addClass ( word );
				break;
			case ELEM_EXCEPTIONS:
				exception.add ( word );
				exception = normalizeException ( exception );
				consumer.addException ( getExceptionWord ( exception ),
								(ArrayList<Object>) exception.clone () );
				break;
			case ELEM_PATTERNS:
				consumer.addPattern ( getPattern ( word ),
								getInterletterValues ( word ) );
				break;
			case ELEM_HYPHEN:
				// nothing to do
				break;
			}
			if ( currElement != ELEM_HYPHEN ) {
				token.setLength ( 0 );
			}
		}
		if ( currElement == ELEM_HYPHEN ) {
			currElement = ELEM_EXCEPTIONS;
		} else {
			currElement = 0;
		}
	}

	public void startDocument () {
	}

	public void startElement ( String tag, Map<String, String> h ) {
		switch ( tag ) {
		case "hyphen-char":
			String hh = h.get ( "value" );
			if ( hh != null && hh.length () == 1 ) {
				hyphenChar = hh.charAt ( 0 );
			}
			break;
		case "classes":
			currElement = ELEM_CLASSES;
			break;
		case "patterns":
			currElement = ELEM_PATTERNS;
			break;
		case "exceptions":
			currElement = ELEM_EXCEPTIONS;
			exception = new ArrayList<> ();
			break;
		case "hyphen":
			if ( token.length () > 0 ) {
				exception.add ( token.toString () );
			}
			exception.add ( new Hyphen ( h.get ( "pre" ), h
							.get ( "no" ), h.get ( "post" ) ) );
			currElement = ELEM_HYPHEN;
			break;
		}
		token.setLength ( 0 );
	}

	@SuppressWarnings ( "unchecked" )
	public void text ( String str ) {
		StringTokenizer tk = new StringTokenizer ( str );
		while ( tk.hasMoreTokens () ) {
			String word = tk.nextToken ();
			switch ( currElement ) {
			case ELEM_CLASSES:
				consumer.addClass ( word );
				break;
			case ELEM_EXCEPTIONS:
				exception.add ( word );
				exception = normalizeException ( exception );
				consumer.addException ( getExceptionWord ( exception ),
								(ArrayList<Object>) exception.clone () );
				exception.clear ();
				break;
			case ELEM_PATTERNS:
				consumer.addPattern ( getPattern ( word ),
								getInterletterValues ( word ) );
				break;
			}
		}
	}

	public void addClass ( String c ) {
		System.out.println ( "class: " + c );
	}

	public void addException ( String w, ArrayList<Object> e ) {
		System.out.println ( "exception: " + w + " : " + e.toString () );
	}

	public void addPattern ( String p, String v ) {
		System.out.println ( "pattern: " + p + " : " + v );
	}

}
