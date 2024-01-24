/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.xml.xmp;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.ExceptionConverter;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;


@Deprecated
public class XmpReader {

	public static final String EXTRASPACE = "                                                                                                   \n";

	public static final String XPACKET_PI_BEGIN = "<?xpacket begin=\"\uFEFF\" id=\"W5M0MpCehiHzreSzNTczkc9d\"?>\n";

	public static final String XPACKET_PI_END_W = "<?xpacket end=\"w\"?>";

	private final Document domDocument;

	public XmpReader ( byte[] bytes ) throws SAXException, IOException {
		try {
			DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance ();
			fact.setNamespaceAware ( true );
			DocumentBuilder db = fact.newDocumentBuilder ();
			ByteArrayInputStream bais = new ByteArrayInputStream ( bytes );
			domDocument = db.parse ( bais );
		} catch ( ParserConfigurationException e ) {
			throw new ExceptionConverter ( e );
		}
	}

	public boolean add ( String parent, String namespaceURI, String localName, String value ) {
		NodeList nodes = domDocument.getElementsByTagName ( parent );
		if ( nodes.getLength () == 0 )
			return false;
		Node pNode;
		Node node;
		String prefix;
		for ( int i = 0; i < nodes.getLength (); i++ ) {
			pNode = nodes.item ( i );
			NamedNodeMap attrs = pNode.getAttributes ();
			for ( int j = 0; j < attrs.getLength (); j++ ) {
				node = attrs.item ( j );
				if ( namespaceURI.equals ( node.getNodeValue () ) ) {
					prefix = node.getLocalName ();
					node = domDocument.createElementNS ( namespaceURI, localName );
					node.setPrefix ( prefix );
					node.appendChild ( domDocument.createTextNode ( value ) );
					pNode.appendChild ( node );
					return true;
				}
			}
		}
		return false;
	}

}
