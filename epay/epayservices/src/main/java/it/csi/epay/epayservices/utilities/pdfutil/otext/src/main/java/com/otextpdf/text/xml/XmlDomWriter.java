/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.xml;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


public class XmlDomWriter {

	protected PrintWriter fOut;

	protected boolean fCanonical;

	protected boolean fXML11;

	public XmlDomWriter () {
	}

	public void setCanonical ( boolean canonical ) {
		fCanonical = canonical;
	}

	public void setOutput ( OutputStream stream, String encoding )
					throws UnsupportedEncodingException {

		if ( encoding == null ) {
			encoding = "UTF8";
		}

		java.io.Writer writer = new OutputStreamWriter ( stream, encoding );
		fOut = new PrintWriter ( writer );

	}

	public void setOutput ( java.io.Writer writer ) {

		fOut = writer instanceof PrintWriter
						? (PrintWriter) writer : new PrintWriter ( writer );

	}

	public void write ( Node node ) {

		if ( node == null ) {
			return;
		}

		short type = node.getNodeType ();
		switch ( type ) {
		case Node.DOCUMENT_NODE: {
			Document document = (Document) node;
			fXML11 = false;
			if ( !fCanonical ) {
				fOut.println ( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" );
				fOut.flush ();
				write ( document.getDoctype () );
			}
			write ( document.getDocumentElement () );
			break;
		}

		case Node.DOCUMENT_TYPE_NODE: {
			DocumentType doctype = (DocumentType) node;
			fOut.print ( "<!DOCTYPE " );
			fOut.print ( doctype.getName () );
			String publicId = doctype.getPublicId ();
			String systemId = doctype.getSystemId ();
			if ( publicId != null ) {
				fOut.print ( " PUBLIC '" );
				fOut.print ( publicId );
				fOut.print ( "' '" );
				fOut.print ( systemId );
				fOut.print ( '\'' );
			} else if ( systemId != null ) {
				fOut.print ( " SYSTEM '" );
				fOut.print ( systemId );
				fOut.print ( '\'' );
			}
			String internalSubset = doctype.getInternalSubset ();
			if ( internalSubset != null ) {
				fOut.println ( " [" );
				fOut.print ( internalSubset );
				fOut.print ( ']' );
			}
			fOut.println ( '>' );
			break;
		}

		case Node.ELEMENT_NODE: {
			fOut.print ( '<' );
			fOut.print ( node.getNodeName () );
			Attr[] attrs = sortAttributes ( node.getAttributes () );
			for ( Attr attr : attrs ) {
				fOut.print ( ' ' );
				fOut.print ( attr.getNodeName () );
				fOut.print ( "=\"" );
				normalizeAndPrint ( attr.getNodeValue (), true );
				fOut.print ( '"' );
			}
			fOut.print ( '>' );
			fOut.flush ();

			Node child = node.getFirstChild ();
			while ( child != null ) {
				write ( child );
				child = child.getNextSibling ();
			}
			break;
		}

		case Node.ENTITY_REFERENCE_NODE: {
			if ( fCanonical ) {
				Node child = node.getFirstChild ();
				while ( child != null ) {
					write ( child );
					child = child.getNextSibling ();
				}
			} else {
				fOut.print ( '&' );
				fOut.print ( node.getNodeName () );
				fOut.print ( ';' );
				fOut.flush ();
			}
			break;
		}

		case Node.CDATA_SECTION_NODE: {
			if ( fCanonical ) {
				normalizeAndPrint ( node.getNodeValue (), false );
			} else {
				fOut.print ( "<![CDATA[" );
				fOut.print ( node.getNodeValue () );
				fOut.print ( "]]>" );
			}
			fOut.flush ();
			break;
		}

		case Node.TEXT_NODE: {
			normalizeAndPrint ( node.getNodeValue (), false );
			fOut.flush ();
			break;
		}

		case Node.PROCESSING_INSTRUCTION_NODE: {
			fOut.print ( "<?" );
			fOut.print ( node.getNodeName () );
			String data = node.getNodeValue ();
			if ( data != null && !data.isEmpty () ) {
				fOut.print ( ' ' );
				fOut.print ( data );
			}
			fOut.print ( "?>" );
			fOut.flush ();
			break;
		}

		case Node.COMMENT_NODE: {
			if ( !fCanonical ) {
				fOut.print ( "<!--" );
				String comment = node.getNodeValue ();
				if ( comment != null && !comment.isEmpty () ) {
					fOut.print ( comment );
				}
				fOut.print ( "-->" );
				fOut.flush ();
			}
		}
		}

		if ( type == Node.ELEMENT_NODE ) {
			fOut.print ( "</" );
			fOut.print ( node.getNodeName () );
			fOut.print ( '>' );
			fOut.flush ();
		}

	}

	protected Attr[] sortAttributes ( NamedNodeMap attrs ) {

		int len = ( attrs != null ) ? attrs.getLength () : 0;
		Attr[] array = new Attr[len];
		for ( int i = 0; i < len; i++ ) {
			array[i] = (Attr) attrs.item ( i );
		}
		for ( int i = 0; i < len - 1; i++ ) {
			String name = array[i].getNodeName ();
			int index = i;
			for ( int j = i + 1; j < len; j++ ) {
				String curName = array[j].getNodeName ();
				if ( curName.compareTo ( name ) < 0 ) {
					name = curName;
					index = j;
				}
			}
			if ( index != i ) {
				Attr temp = array[i];
				array[i] = array[index];
				array[index] = temp;
			}
		}

		return array;

	}

	protected void normalizeAndPrint ( String s, boolean isAttValue ) {

		int len = ( s != null ) ? s.length () : 0;
		for ( int i = 0; i < len; i++ ) {
			char c = s.charAt ( i );
			normalizeAndPrint ( c, isAttValue );
		}

	}

	protected void normalizeAndPrint ( char c, boolean isAttValue ) {

		switch ( c ) {
		case '<': {
			fOut.print ( "&lt;" );
			break;
		}
		case '>': {
			fOut.print ( "&gt;" );
			break;
		}
		case '&': {
			fOut.print ( "&amp;" );
			break;
		}
		case '"': {

			if ( isAttValue ) {
				fOut.print ( "&quot;" );
			} else {
				fOut.print ( "\"" );
			}
			break;
		}
		case '\r': {
			fOut.print ( "&#xD;" );
			break;
		}
		case '\n': {
			if ( fCanonical ) {
				fOut.print ( "&#xA;" );
				break;
			}
		}
		default: {
			if ( fXML11 && ( ( c >= 0x01 && c <= 0x1F && c != 0x09 && c != 0x0A )
							|| ( c >= 0x7F && c <= 0x9F ) || c == 0x2028 )
							|| isAttValue && ( c == 0x09 || c == 0x0A ) ) {
				fOut.print ( "&#x" );
				fOut.print ( Integer.toHexString ( c ).toUpperCase () );
				fOut.print ( ";" );
			} else {
				fOut.print ( c );
			}
		}
		}
	}

}
