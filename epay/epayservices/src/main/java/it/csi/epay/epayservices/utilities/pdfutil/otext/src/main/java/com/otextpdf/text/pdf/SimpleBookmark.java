/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.xml.simpleparser.SimpleXMLDocHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;


public final class SimpleBookmark implements SimpleXMLDocHandler {

	private final Stack<HashMap<String, Object>> attr = new Stack<> ();

	private ArrayList<HashMap<String, Object>> topList;

	private SimpleBookmark () {
	}

	static void createOutlineAction ( PdfDictionary outline, HashMap<String, Object> map, PdfWriter writer, boolean namedAsNames ) {
		try {
			String action = (String) map.get ( "Action" );
			if ( "GoTo".equals ( action ) ) {
				String p;
				if ( ( p = (String) map.get ( "Named" ) ) != null ) {
					if ( namedAsNames )
						outline.put ( PdfName.DEST, new PdfName ( p ) );
					else
						outline.put ( PdfName.DEST, new PdfString ( p, null ) );
				} else if ( ( p = (String) map.get ( "Page" ) ) != null ) {
					PdfArray ar = new PdfArray ();
					StringTokenizer tk = new StringTokenizer ( p );
					int n = Integer.parseInt ( tk.nextToken () );
					ar.add ( writer.getPageReference ( n ) );
					if ( !tk.hasMoreTokens () ) {
						ar.add ( PdfName.XYZ );
						ar.add ( new float[] { 0, 10000, 0 } );
					} else {
						String fn = tk.nextToken ();
						if ( fn.startsWith ( "/" ) )
							fn = fn.substring ( 1 );
						ar.add ( new PdfName ( fn ) );
						for ( int k = 0; k < 4 && tk.hasMoreTokens (); ++k ) {
							fn = tk.nextToken ();
							if ( fn.equals ( "null" ) )
								ar.add ( PdfNull.PDFNULL );
							else
								ar.add ( new PdfNumber ( fn ) );
						}
					}
					outline.put ( PdfName.DEST, ar );
				}
			} else if ( "GoToR".equals ( action ) ) {
				String p;
				PdfDictionary dic = new PdfDictionary ();
				if ( ( p = (String) map.get ( "Named" ) ) != null )
					dic.put ( PdfName.D, new PdfString ( p, null ) );
				else if ( ( p = (String) map.get ( "NamedN" ) ) != null )
					dic.put ( PdfName.D, new PdfName ( p ) );
				else if ( ( p = (String) map.get ( "Page" ) ) != null ) {
					PdfArray ar = new PdfArray ();
					StringTokenizer tk = new StringTokenizer ( p );
					ar.add ( new PdfNumber ( tk.nextToken () ) );
					if ( !tk.hasMoreTokens () ) {
						ar.add ( PdfName.XYZ );
						ar.add ( new float[] { 0, 10000, 0 } );
					} else {
						String fn = tk.nextToken ();
						if ( fn.startsWith ( "/" ) )
							fn = fn.substring ( 1 );
						ar.add ( new PdfName ( fn ) );
						for ( int k = 0; k < 4 && tk.hasMoreTokens (); ++k ) {
							fn = tk.nextToken ();
							if ( fn.equals ( "null" ) )
								ar.add ( PdfNull.PDFNULL );
							else
								ar.add ( new PdfNumber ( fn ) );
						}
					}
					dic.put ( PdfName.D, ar );
				}
				String file = (String) map.get ( "File" );
				if ( dic.size () > 0 && file != null ) {
					dic.put ( PdfName.S, PdfName.GOTOR );
					dic.put ( PdfName.F, new PdfString ( file ) );
					String nw = (String) map.get ( "NewWindow" );
					if ( nw != null ) {
						if ( nw.equals ( "true" ) )
							dic.put ( PdfName.NEWWINDOW, PdfBoolean.PDFTRUE );
						else if ( nw.equals ( "false" ) )
							dic.put ( PdfName.NEWWINDOW, PdfBoolean.PDFFALSE );
					}
					outline.put ( PdfName.A, dic );
				}
			} else if ( "URI".equals ( action ) ) {
				String uri = (String) map.get ( "URI" );
				if ( uri != null ) {
					PdfDictionary dic = new PdfDictionary ();
					dic.put ( PdfName.S, PdfName.URI );
					dic.put ( PdfName.URI, new PdfString ( uri ) );
					outline.put ( PdfName.A, dic );
				}
			} else if ( "JS".equals ( action ) ) {
				String code = (String) map.get ( "Code" );
				if ( code != null ) {
					outline.put ( PdfName.A, PdfAction.javaScript ( code, writer ) );
				}
			} else if ( "Launch".equals ( action ) ) {
				String file = (String) map.get ( "File" );
				if ( file != null ) {
					PdfDictionary dic = new PdfDictionary ();
					dic.put ( PdfName.S, PdfName.LAUNCH );
					dic.put ( PdfName.F, new PdfString ( file ) );
					outline.put ( PdfName.A, dic );
				}
			}
		} catch ( Exception ignored ) {
		}
	}

	@SuppressWarnings ( "unchecked" )
	public static Object[] iterateOutlines ( PdfWriter writer, PdfIndirectReference parent, List<HashMap<String, Object>> kids, boolean namedAsNames )
					throws IOException {
		PdfIndirectReference[] refs = new PdfIndirectReference[kids.size ()];
		for ( int k = 0; k < refs.length; ++k )
			refs[k] = writer.getPdfIndirectReference ();
		int ptr = 0;
		int count = 0;
		for ( Iterator<HashMap<String, Object>> it = kids.listIterator (); it.hasNext (); ++ptr ) {
			HashMap<String, Object> map = it.next ();
			Object[] lower = null;
			List<HashMap<String, Object>> subKid = (List<HashMap<String, Object>>) map.get ( "Kids" );
			if ( subKid != null && !subKid.isEmpty () )
				lower = iterateOutlines ( writer, refs[ptr], subKid, namedAsNames );
			PdfDictionary outline = new PdfDictionary ();
			++count;
			if ( lower != null ) {
				outline.put ( PdfName.FIRST, (PdfIndirectReference) lower[0] );
				outline.put ( PdfName.LAST, (PdfIndirectReference) lower[1] );
				int n = (Integer) lower[2];
				if ( "false".equals ( map.get ( "Open" ) ) ) {
					outline.put ( PdfName.COUNT, new PdfNumber ( -n ) );
				} else {
					outline.put ( PdfName.COUNT, new PdfNumber ( n ) );
					count += n;
				}
			}
			outline.put ( PdfName.PARENT, parent );
			if ( ptr > 0 )
				outline.put ( PdfName.PREV, refs[ptr - 1] );
			if ( ptr < refs.length - 1 )
				outline.put ( PdfName.NEXT, refs[ptr + 1] );
			outline.put ( PdfName.TITLE, new PdfString ( (String) map.get ( "Title" ), PdfObject.TEXT_UNICODE ) );
			String color = (String) map.get ( "Color" );
			if ( color != null ) {
				try {
					PdfArray arr = new PdfArray ();
					StringTokenizer tk = new StringTokenizer ( color );
					for ( int k = 0; k < 3; ++k ) {
						float f = Float.parseFloat ( tk.nextToken () );
						if ( f < 0 )
							f = 0;
						if ( f > 1 )
							f = 1;
						arr.add ( new PdfNumber ( f ) );
					}
					outline.put ( PdfName.C, arr );
				} catch ( Exception ignored ) {
				}
			}
			String style = (String) map.get ( "Style" );
			if ( style != null ) {
				style = style.toLowerCase ();
				int bits = 0;
				if ( style.contains ( "italic" ) )
					bits |= 1;
				if ( style.contains ( "bold" ) )
					bits |= 2;
				if ( bits != 0 )
					outline.put ( PdfName.F, new PdfNumber ( bits ) );
			}
			createOutlineAction ( outline, map, writer, namedAsNames );
			writer.addToBody ( outline, refs[ptr] );
		}
		return new Object[] { refs[0], refs[refs.length - 1], count };
	}

	public void endDocument () {
	}

	@SuppressWarnings ( "unchecked" )
	public void endElement ( String tag ) {
		if ( tag.equals ( "Bookmark" ) ) {
			if ( attr.isEmpty () )
				return;
			else
				throw new RuntimeException ( MessageLocalization.getComposedMessage ( "bookmark.end.tag.out.of.place" ) );
		}
		if ( !tag.equals ( "Title" ) )
			throw new RuntimeException ( MessageLocalization.getComposedMessage ( "invalid.end.tag.1", tag ) );
		HashMap<String, Object> attributes = attr.pop ();
		String title = (String) attributes.get ( "Title" );
		attributes.put ( "Title", title.trim () );
		String named = (String) attributes.get ( "Named" );
		if ( named != null )
			attributes.put ( "Named", SimpleNamedDestination.unEscapeBinaryString ( named ) );
		named = (String) attributes.get ( "NamedN" );
		if ( named != null )
			attributes.put ( "NamedN", SimpleNamedDestination.unEscapeBinaryString ( named ) );
		if ( attr.isEmpty () )
			topList.add ( attributes );
		else {
			HashMap<String, Object> parent = attr.peek ();
			List<HashMap<String, Object>> kids = (List<HashMap<String, Object>>) parent.get ( "Kids" );
			if ( kids == null ) {
				kids = new ArrayList<> ();
				parent.put ( "Kids", kids );
			}
			kids.add ( attributes );
		}
	}

	public void startDocument () {
	}

	public void startElement ( String tag, Map<String, String> h ) {
		if ( topList == null ) {
			if ( tag.equals ( "Bookmark" ) ) {
				topList = new ArrayList<> ();
				return;
			} else
				throw new RuntimeException ( MessageLocalization.getComposedMessage ( "root.element.is.not.bookmark.1", tag ) );
		}
		if ( !tag.equals ( "Title" ) )
			throw new RuntimeException ( MessageLocalization.getComposedMessage ( "tag.1.not.allowed", tag ) );
		HashMap<String, Object> attributes = new HashMap<> ( h );
		attributes.put ( "Title", "" );
		attributes.remove ( "Kids" );
		attr.push ( attributes );
	}

	public void text ( String str ) {
		if ( attr.isEmpty () )
			return;
		HashMap<String, Object> attributes = attr.peek ();
		String title = (String) attributes.get ( "Title" );
		title += str;
		attributes.put ( "Title", title );
	}
}
