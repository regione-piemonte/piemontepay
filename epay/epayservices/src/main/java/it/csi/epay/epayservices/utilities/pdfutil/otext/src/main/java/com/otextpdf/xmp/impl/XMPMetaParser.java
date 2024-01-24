/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPConst;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPError;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPMeta;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.options.ParseOptions;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;


public class XMPMetaParser {

	private static final Object XMP_RDF = new Object ();

	private static final DocumentBuilderFactory factory = createDocumentBuilderFactory ();

	private XMPMetaParser () {
	}

	public static XMPMeta parse ( Object input, ParseOptions options ) throws XMPException {
		ParameterAsserts.assertNotNull ( input );
		options = options != null ? options : new ParseOptions ();

		Document document = parseXml ( input, options );

		boolean xmpmetaRequired = options.getRequireXMPMeta ();
		Object[] result = new Object[3];
		result = findRootNode ( document, xmpmetaRequired, result );

		if ( result != null && result[1] == XMP_RDF ) {
			XMPMetaImpl xmp = ParseRDF.parse ( (Node) result[0] );
			xmp.setPacketHeader ( (String) result[2] );

			// Check if the XMP object shall be normalized
			if ( !options.getOmitNormalization () ) {
				return XMPNormalizer.process ( xmp, options );
			} else {
				return xmp;
			}
		} else {
			// no appropriate root node found, return empty metadata object
			return new XMPMetaImpl ();
		}
	}

	private static Document parseXml ( Object input, ParseOptions options )
					throws XMPException {
		if ( input instanceof InputStream ) {
			return parseXmlFromInputStream ( (InputStream) input, options );
		} else if ( input instanceof byte[] ) {
			return parseXmlFromBytebuffer ( new ByteBuffer ( (byte[]) input ), options );
		} else {
			return parseXmlFromString ( (String) input, options );
		}
	}

	private static Document parseXmlFromInputStream ( InputStream stream, ParseOptions options )
					throws XMPException {
		if ( !options.getAcceptLatin1 () && !options.getFixControlChars () ) {
			return parseInputSource ( new InputSource ( stream ) );
		} else {
			// load stream into bytebuffer
			try {
				ByteBuffer buffer = new ByteBuffer ( stream );
				return parseXmlFromBytebuffer ( buffer, options );
			} catch ( IOException e ) {
				throw new XMPException ( "Error reading the XML-file",
								XMPError.BADSTREAM, e );
			}
		}
	}

	private static Document parseXmlFromBytebuffer ( ByteBuffer buffer, ParseOptions options )
					throws XMPException {
		InputSource source = new InputSource ( buffer.getByteStream () );
		try {
			return parseInputSource ( source );
		} catch ( XMPException e ) {
			if ( e.getErrorCode () == XMPError.BADXML ||
							e.getErrorCode () == XMPError.BADSTREAM ) {
				if ( options.getAcceptLatin1 () ) {
					buffer = Latin1Converter.convert ( buffer );
				}

				if ( options.getFixControlChars () ) {
					try {
						String encoding = buffer.getEncoding ();
						Reader fixReader = new FixASCIIControlsReader (
										new InputStreamReader (
														buffer.getByteStream (), encoding ) );
						return parseInputSource ( new InputSource ( fixReader ) );
					} catch ( UnsupportedEncodingException e1 ) {
						// can normally not happen as the encoding is provided by a util function
						throw new XMPException ( "Unsupported Encoding",
										XMPError.INTERNALFAILURE, e );
					}
				}
				source = new InputSource ( buffer.getByteStream () );
				return parseInputSource ( source );
			} else {
				throw e;
			}
		}
	}

	private static Document parseXmlFromString ( String input, ParseOptions options )
					throws XMPException {
		InputSource source = new InputSource ( new StringReader ( input ) );
		try {
			return parseInputSource ( source );
		} catch ( XMPException e ) {
			if ( e.getErrorCode () == XMPError.BADXML && options.getFixControlChars () ) {
				source = new InputSource ( new FixASCIIControlsReader ( new StringReader ( input ) ) );
				return parseInputSource ( source );
			} else {
				throw e;
			}
		}
	}

	private static Document parseInputSource ( InputSource source ) throws XMPException {
		try {
			DocumentBuilder builder = factory.newDocumentBuilder ();
			builder.setErrorHandler ( null );
			return builder.parse ( source );
		} catch ( SAXException e ) {
			throw new XMPException ( "XML parsing failure", XMPError.BADXML, e );
		} catch ( ParserConfigurationException e ) {
			throw new XMPException ( "XML Parser not correctly configured",
							XMPError.UNKNOWN, e );
		} catch ( IOException e ) {
			throw new XMPException ( "Error reading the XML-file", XMPError.BADSTREAM, e );
		}
	}

	private static Object[] findRootNode ( Node root, boolean xmpmetaRequired, Object[] result ) {
		// Look among this parent's content for x:xapmeta or x:xmpmeta.
		// The recursion for x:xmpmeta is broader than the strictly defined choice, 
		// but gives us smaller code.
		NodeList children = root.getChildNodes ();
		for ( int i = 0; i < children.getLength (); i++ ) {
			root = children.item ( i );
			if ( Node.PROCESSING_INSTRUCTION_NODE == root.getNodeType () &&
							XMPConst.XMP_PI.equals ( ( (ProcessingInstruction) root ).getTarget () ) ) {
				// Store the processing instructions content
				if ( result != null ) {
					result[2] = ( (ProcessingInstruction) root ).getData ();
				}
			} else if ( Node.TEXT_NODE != root.getNodeType () &&
							Node.PROCESSING_INSTRUCTION_NODE != root.getNodeType () ) {
				String rootNS = root.getNamespaceURI ();
				String rootLocal = root.getLocalName ();
				if (
								(
												XMPConst.TAG_XMPMETA.equals ( rootLocal ) ||
																XMPConst.TAG_XAPMETA.equals ( rootLocal )
								) &&
												XMPConst.NS_X.equals ( rootNS )
				) {
					// by not passing the RequireXMPMeta-option, the rdf-Node will be valid
					return findRootNode ( root, false, result );
				} else if ( !xmpmetaRequired &&
								"RDF".equals ( rootLocal ) &&
								XMPConst.NS_RDF.equals ( rootNS ) ) {
					if ( result != null ) {
						result[0] = root;
						result[1] = XMP_RDF;
					}
					return result;
				} else {
					Object[] newResult = findRootNode ( root, xmpmetaRequired, result );
					if ( newResult != null ) {
						return newResult;
					}
				}
			}
		}

		return null;
	}

	private static DocumentBuilderFactory createDocumentBuilderFactory () {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance ();
		factory.setNamespaceAware ( true );
		factory.setIgnoringComments ( true );

		try {
			factory.setFeature ( XMLConstants.FEATURE_SECURE_PROCESSING, true );
		} catch ( Exception ignored ) {
		}
		return factory;
	}
}
