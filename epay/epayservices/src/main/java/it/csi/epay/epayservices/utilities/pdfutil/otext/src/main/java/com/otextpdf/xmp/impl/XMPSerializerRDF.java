/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPConst;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPError;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPMeta;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPMetaFactory;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.options.PropertyOptions;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.options.SerializeOptions;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class XMPSerializerRDF {

	static final Set RDF_ATTR_QUALIFIER = new HashSet ( Arrays.asList ( XMPConst.XML_LANG, "rdf:resource", "rdf:ID", "rdf:bagID", "rdf:nodeID" ) );

	private static final int DEFAULT_PAD = 2048;

	private static final String PACKET_HEADER =
					"<?xpacket begin=\"\uFEFF\" id=\"W5M0MpCehiHzreSzNTczkc9d\"?>";

	private static final String PACKET_TRAILER = "<?xpacket end=\"";

	private static final String PACKET_TRAILER2 = "\"?>";

	private static final String RDF_XMPMETA_START =
					"<x:xmpmeta xmlns:x=\"adobe:ns:meta/\" x:xmptk=\"";

	private static final String RDF_XMPMETA_END = "</x:xmpmeta>";

	private static final String RDF_RDF_START =
					"<rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\">";

	private static final String RDF_RDF_END = "</rdf:RDF>";

	private static final String RDF_SCHEMA_START = "<rdf:Description rdf:about=";

	private static final String RDF_SCHEMA_END = "</rdf:Description>";

	private static final String RDF_STRUCT_START = "<rdf:Description";

	private static final String RDF_STRUCT_END = "</rdf:Description>";

	private static final String RDF_EMPTY_STRUCT = "<rdf:Description/>";

	private XMPMetaImpl xmp;

	private CountOutputStream outputStream;

	private OutputStreamWriter writer;

	private SerializeOptions options;

	private int unicodeSize = 1; // UTF-8

	private int padding;

	public void serialize ( XMPMeta xmp, OutputStream out,
					SerializeOptions options ) throws XMPException {
		try {
			outputStream = new CountOutputStream ( out );
			writer = new OutputStreamWriter ( outputStream, options.getEncoding () );

			this.xmp = (XMPMetaImpl) xmp;
			this.options = options;
			this.padding = options.getPadding ();

			writer = new OutputStreamWriter ( outputStream, options.getEncoding () );

			checkOptionsConsistence ();

			// serializes the whole packet, but don't write the tail yet 
			// and flush to make sure that the written bytes are calculated correctly
			String tailStr = serializeAsRDF ();
			writer.flush ();

			// adds padding
			addPadding ( tailStr.length () );

			// writes the tail
			write ( tailStr );
			writer.flush ();

			outputStream.close ();
		} catch ( IOException e ) {
			throw new XMPException ( "Error writing to the OutputStream", XMPError.UNKNOWN );
		}
	}

	private void addPadding ( int tailLength ) throws XMPException, IOException {
		if ( options.getExactPacketLength () ) {
			// the string length is equal to the length of the UTF-8 encoding
			int minSize = outputStream.getBytesWritten () + tailLength * unicodeSize;
			if ( minSize > padding ) {
				throw new XMPException ( "Can't fit into specified packet size",
								XMPError.BADSERIALIZE );
			}
			padding -= minSize;    // Now the actual amount of padding to add.
		}

		// fix rest of the padding according to Unicode unit size.
		padding /= unicodeSize;

		int newlineLen = options.getNewline ().length ();
		if ( padding >= newlineLen ) {
			padding -= newlineLen;    // Write this newline last.
			while ( padding >= ( 100 + newlineLen ) ) {
				writeChars ( 100 );
				writeNewline ();
				padding -= ( 100 + newlineLen );
			}
			writeChars ( padding );
			writeNewline ();
		} else {
			writeChars ( padding );
		}
	}

	protected void checkOptionsConsistence () throws XMPException {
		if ( options.getEncodeUTF16BE () | options.getEncodeUTF16LE () ) {
			unicodeSize = 2;
		}

		if ( options.getExactPacketLength () ) {
			if ( options.getOmitPacketWrapper () | options.getIncludeThumbnailPad () ) {
				throw new XMPException ( "Inconsistent options for exact size serialize",
								XMPError.BADOPTIONS );
			}
			if ( ( options.getPadding () & ( unicodeSize - 1 ) ) != 0 ) {
				throw new XMPException ( "Exact size must be a multiple of the Unicode element",
								XMPError.BADOPTIONS );
			}
		} else if ( options.getReadOnlyPacket () ) {
			if ( options.getOmitPacketWrapper () | options.getIncludeThumbnailPad () ) {
				throw new XMPException ( "Inconsistent options for read-only packet",
								XMPError.BADOPTIONS );
			}
			padding = 0;
		} else if ( options.getOmitPacketWrapper () ) {
			if ( options.getIncludeThumbnailPad () ) {
				throw new XMPException ( "Inconsistent options for non-packet serialize",
								XMPError.BADOPTIONS );
			}
			padding = 0;
		} else {
			if ( padding == 0 ) {
				padding = DEFAULT_PAD * unicodeSize;
			}

			if ( options.getIncludeThumbnailPad () ) {
				if ( !xmp.doesPropertyExist ( XMPConst.NS_XMP, "Thumbnails" ) ) {
					padding += 10000 * unicodeSize;
				}
			}
		}
	}

	private String serializeAsRDF () throws IOException, XMPException {
		int level = 0;

		// Write the packet header PI.
		if ( !options.getOmitPacketWrapper () ) {
			writeIndent ( level );
			write ( PACKET_HEADER );
			writeNewline ();
		}

		// Write the x:xmpmeta element's start tag.
		if ( options.getOmitXmpMetaElement () ) {
			writeIndent ( level );
			write ( RDF_XMPMETA_START );
			// Note: this flag can only be set by unit tests
			if ( !options.getOmitVersionAttribute () ) {
				write ( XMPMetaFactory.getVersionInfo ().getMessage () );
			}
			write ( "\">" );
			writeNewline ();
			level++;
		}

		// Write the rdf:RDF start tag.
		writeIndent ( level );
		write ( RDF_RDF_START );
		writeNewline ();

		// Write all of the properties.
		if ( options.getUseCanonicalFormat () ) {
			serializeCanonicalRDFSchemas ( level );
		} else {
			serializeCompactRDFSchemas ( level );
		}

		// Write the rdf:RDF end tag.
		writeIndent ( level );
		write ( RDF_RDF_END );
		writeNewline ();

		// Write the xmpmeta end tag.
		if ( options.getOmitXmpMetaElement () ) {
			level--;
			writeIndent ( level );
			write ( RDF_XMPMETA_END );
			writeNewline ();
		}
		// Write the packet trailer PI into the tail string as UTF-8.
		StringBuilder tailStr = new StringBuilder ();
		if ( !options.getOmitPacketWrapper () ) {
			for ( level = options.getBaseIndent (); level > 0; level-- ) {
				tailStr.append ( options.getIndent () );
			}

			tailStr.append ( PACKET_TRAILER );
			tailStr.append ( options.getReadOnlyPacket () ? 'r' : 'w' );
			tailStr.append ( PACKET_TRAILER2 );
		}

		return tailStr.toString ();
	}

	private void serializeCanonicalRDFSchemas ( int level ) throws IOException, XMPException {
		if ( xmp.getRoot ().getChildrenLength () > 0 ) {
			startOuterRDFDescription ( xmp.getRoot (), level );

			for ( Iterator it = xmp.getRoot ().iterateChildren (); it.hasNext (); ) {
				XMPNode currSchema = (XMPNode) it.next ();
				serializeCanonicalRDFSchema ( currSchema, level );
			}

			endOuterRDFDescription ( level );
		} else {
			writeIndent ( level + 1 );
			write ( RDF_SCHEMA_START ); // Special case an empty XMP object.
			writeTreeName ();
			write ( "/>" );
			writeNewline ();
		}
	}

	private void writeTreeName () throws IOException {
		write ( '"' );
		String name = xmp.getRoot ().getName ();
		if ( name != null ) {
			appendNodeValue ( name, true );
		}
		write ( '"' );
	}

	private void serializeCompactRDFSchemas ( int level ) throws IOException, XMPException {
		// Begin the rdf:Description start tag.
		writeIndent ( level + 1 );
		write ( RDF_SCHEMA_START );
		writeTreeName ();

		// Write all necessary xmlns attributes.
		Set usedPrefixes = new HashSet ();
		usedPrefixes.add ( "xml" );
		usedPrefixes.add ( "rdf" );

		for ( Iterator it = xmp.getRoot ().iterateChildren (); it.hasNext (); ) {
			XMPNode schema = (XMPNode) it.next ();
			declareUsedNamespaces ( schema, usedPrefixes, level + 3 );
		}

		// Write the top level "attrProps" and close the rdf:Description start tag.
		boolean allAreAttrs = true;
		for ( Iterator it = xmp.getRoot ().iterateChildren (); it.hasNext (); ) {
			XMPNode schema = (XMPNode) it.next ();
			allAreAttrs &= serializeCompactRDFAttrProps ( schema, level + 2 );
		}

		if ( !allAreAttrs ) {
			write ( '>' );
			writeNewline ();
		} else {
			write ( "/>" );
			writeNewline ();
			return;    // ! Done if all properties in all schema are written as attributes.
		}

		// Write the remaining properties for each schema.
		for ( Iterator it = xmp.getRoot ().iterateChildren (); it.hasNext (); ) {
			XMPNode schema = (XMPNode) it.next ();
			serializeCompactRDFElementProps ( schema, level + 2 );
		}

		// Write the rdf:Description end tag.
		writeIndent ( level + 1 );
		write ( RDF_SCHEMA_END );
		writeNewline ();
	}

	private boolean serializeCompactRDFAttrProps ( XMPNode parentNode, int indent ) throws IOException {
		boolean allAreAttrs = true;

		for ( Iterator it = parentNode.iterateChildren (); it.hasNext (); ) {
			XMPNode prop = (XMPNode) it.next ();

			if ( canBeRDFAttrProp ( prop ) ) {
				writeNewline ();
				writeIndent ( indent );
				write ( prop.getName () );
				write ( "=\"" );
				appendNodeValue ( prop.getValue (), true );
				write ( '"' );
			} else {
				allAreAttrs = false;
			}
		}
		return allAreAttrs;
	}

	private void serializeCompactRDFElementProps ( XMPNode parentNode, int indent )
					throws IOException, XMPException {
		for ( Iterator it = parentNode.iterateChildren (); it.hasNext (); ) {
			XMPNode node = (XMPNode) it.next ();
			if ( canBeRDFAttrProp ( node ) ) {
				continue;
			}

			boolean emitEndTag = true;
			boolean indentEndTag = true;

			// Determine the XML element name, write the name part of the start tag. Look over the
			// qualifiers to decide on "normal" versus "rdf:value" form. Emit the attribute
			// qualifiers at the same time.
			String elemName = node.getName ();
			if ( XMPConst.ARRAY_ITEM_NAME.equals ( elemName ) ) {
				elemName = "rdf:li";
			}

			writeIndent ( indent );
			write ( '<' );
			write ( elemName );

			boolean hasGeneralQualifiers = false;
			boolean hasRDFResourceQual = false;

			for ( Iterator iq = node.iterateQualifier (); iq.hasNext (); ) {
				XMPNode qualifier = (XMPNode) iq.next ();
				if ( !RDF_ATTR_QUALIFIER.contains ( qualifier.getName () ) ) {
					hasGeneralQualifiers = true;
				} else {
					hasRDFResourceQual = "rdf:resource".equals ( qualifier.getName () );
					write ( ' ' );
					write ( qualifier.getName () );
					write ( "=\"" );
					appendNodeValue ( qualifier.getValue (), true );
					write ( '"' );
				}
			}

			// Process the property according to the standard patterns.
			if ( hasGeneralQualifiers ) {
				serializeCompactRDFGeneralQualifier ( indent, node );
			} else {
				// This node has only attribute qualifiers. Emit as a property element.
				if ( !node.getOptions ().isCompositeProperty () ) {
					Object[] result = serializeCompactRDFSimpleProp ( node );
					emitEndTag = (Boolean) result[0];
					indentEndTag = (Boolean) result[1];
				} else if ( node.getOptions ().isArray () ) {
					serializeCompactRDFArrayProp ( node, indent );
				} else {
					emitEndTag = serializeCompactRDFStructProp (
									node, indent, hasRDFResourceQual );
				}

			}

			// Emit the property element end tag.
			if ( emitEndTag ) {
				if ( indentEndTag ) {
					writeIndent ( indent );
				}
				write ( "</" );
				write ( elemName );
				write ( '>' );
				writeNewline ();
			}

		}
	}

	private Object[] serializeCompactRDFSimpleProp ( XMPNode node ) throws IOException {
		// This is a simple property.
		Boolean emitEndTag = Boolean.TRUE;
		Boolean indentEndTag = Boolean.TRUE;

		if ( node.getOptions ().isURI () ) {
			write ( " rdf:resource=\"" );
			appendNodeValue ( node.getValue (), true );
			write ( "\"/>" );
			writeNewline ();
			emitEndTag = Boolean.FALSE;
		} else if ( node.getValue () == null || node.getValue ().isEmpty () ) {
			write ( "/>" );
			writeNewline ();
			emitEndTag = Boolean.FALSE;
		} else {
			write ( '>' );
			appendNodeValue ( node.getValue (), false );
			indentEndTag = Boolean.FALSE;
		}

		return new Object[] { emitEndTag, indentEndTag };
	}

	private void serializeCompactRDFArrayProp ( XMPNode node, int indent ) throws IOException,
					XMPException {
		// This is an array.
		write ( '>' );
		writeNewline ();
		emitRDFArrayTag ( node, true, indent + 1 );

		if ( node.getOptions ().isArrayAltText () ) {
			XMPNodeUtils.normalizeLangArray ( node );
		}

		serializeCompactRDFElementProps ( node, indent + 2 );

		emitRDFArrayTag ( node, false, indent + 1 );
	}

	private boolean serializeCompactRDFStructProp ( XMPNode node, int indent,
					boolean hasRDFResourceQual ) throws XMPException, IOException {
		// This must be a struct.
		boolean hasAttrFields = false;
		boolean hasElemFields = false;
		boolean emitEndTag = true;

		for ( Iterator ic = node.iterateChildren (); ic.hasNext (); ) {
			XMPNode field = (XMPNode) ic.next ();
			if ( canBeRDFAttrProp ( field ) ) {
				hasAttrFields = true;
			} else {
				hasElemFields = true;
			}

			if ( hasAttrFields && hasElemFields ) {
				break;    // No sense looking further.
			}
		}

		if ( hasRDFResourceQual && hasElemFields ) {
			throw new XMPException (
							"Can't mix rdf:resource qualifier and element fields",
							XMPError.BADRDF );
		}

		if ( !node.hasChildren () ) {
			// Catch an empty struct as a special case. The case
			// below would emit an empty
			// XML element, which gets reparsed as a simple property
			// with an empty value.
			write ( " rdf:parseType=\"Resource\"/>" );
			writeNewline ();
			emitEndTag = false;

		} else if ( !hasElemFields ) {
			// All fields can be attributes, use the
			// emptyPropertyElt form.
			serializeCompactRDFAttrProps ( node, indent + 1 );
			write ( "/>" );
			writeNewline ();
			emitEndTag = false;

		} else if ( !hasAttrFields ) {
			// All fields must be elements, use the
			// parseTypeResourcePropertyElt form.
			write ( " rdf:parseType=\"Resource\">" );
			writeNewline ();
			serializeCompactRDFElementProps ( node, indent + 1 );

		} else {
			// Have a mix of attributes and elements, use an inner rdf:Description.
			write ( '>' );
			writeNewline ();
			writeIndent ( indent + 1 );
			write ( RDF_STRUCT_START );
			serializeCompactRDFAttrProps ( node, indent + 2 );
			write ( ">" );
			writeNewline ();
			serializeCompactRDFElementProps ( node, indent + 1 );
			writeIndent ( indent + 1 );
			write ( RDF_STRUCT_END );
			writeNewline ();
		}
		return emitEndTag;
	}

	private void serializeCompactRDFGeneralQualifier ( int indent, XMPNode node )
					throws IOException, XMPException {
		// The node has general qualifiers, ones that can't be
		// attributes on a property element.
		// Emit using the qualified property pseudo-struct form. The
		// value is output by a call
		// to SerializePrettyRDFProperty with emitAsRDFValue set.
		write ( " rdf:parseType=\"Resource\">" );
		writeNewline ();

		serializeCanonicalRDFProperty ( node, false, true, indent + 1 );

		for ( Iterator iq = node.iterateQualifier (); iq.hasNext (); ) {
			XMPNode qualifier = (XMPNode) iq.next ();
			serializeCanonicalRDFProperty ( qualifier, false, false, indent + 1 );
		}
	}

	private void serializeCanonicalRDFSchema ( XMPNode schemaNode, int level ) throws IOException, XMPException {
		// Write each of the schema's actual properties.
		for ( Iterator it = schemaNode.iterateChildren (); it.hasNext (); ) {
			XMPNode propNode = (XMPNode) it.next ();
			serializeCanonicalRDFProperty ( propNode, options.getUseCanonicalFormat (), false, level + 2 );
		}
	}

	private void declareUsedNamespaces ( XMPNode node, Set usedPrefixes, int indent )
					throws IOException {
		if ( node.getOptions ().isSchemaNode () ) {
			// The schema node name is the URI, the value is the prefix.
			String prefix = node.getValue ().substring ( 0, node.getValue ().length () - 1 );
			declareNamespace ( prefix, node.getName (), usedPrefixes, indent );
		} else if ( node.getOptions ().isStruct () ) {
			for ( Iterator it = node.iterateChildren (); it.hasNext (); ) {
				XMPNode field = (XMPNode) it.next ();
				declareNamespace ( field.getName (), null, usedPrefixes, indent );
			}
		}

		for ( Iterator it = node.iterateChildren (); it.hasNext (); ) {
			XMPNode child = (XMPNode) it.next ();
			declareUsedNamespaces ( child, usedPrefixes, indent );
		}

		for ( Iterator it = node.iterateQualifier (); it.hasNext (); ) {
			XMPNode qualifier = (XMPNode) it.next ();
			declareNamespace ( qualifier.getName (), null, usedPrefixes, indent );
			declareUsedNamespaces ( qualifier, usedPrefixes, indent );
		}
	}

	private void declareNamespace ( String prefix, String namespace, Set usedPrefixes, int indent )
					throws IOException {
		if ( namespace == null ) {
			// prefix contains qname, extract prefix and lookup namespace with prefix
			QName qname = new QName ( prefix );
			if ( qname.hasPrefix () ) {
				prefix = qname.getPrefix ();
				// add colon for lookup
				namespace = XMPMetaFactory.getSchemaRegistry ().getNamespaceURI ( prefix + ":" );
				// prefix w/o colon
				declareNamespace ( prefix, namespace, usedPrefixes, indent );
			} else {
				return;
			}
		}

		if ( !usedPrefixes.contains ( prefix ) ) {
			writeNewline ();
			writeIndent ( indent );
			write ( "xmlns:" );
			write ( prefix );
			write ( "=\"" );
			write ( namespace );
			write ( '"' );
			usedPrefixes.add ( prefix );
		}
	}

	private void startOuterRDFDescription ( XMPNode schemaNode, int level ) throws IOException {
		writeIndent ( level + 1 );
		write ( RDF_SCHEMA_START );
		writeTreeName ();

		Set usedPrefixes = new HashSet ();
		usedPrefixes.add ( "xml" );
		usedPrefixes.add ( "rdf" );

		declareUsedNamespaces ( schemaNode, usedPrefixes, level + 3 );

		write ( '>' );
		writeNewline ();
	}

	private void endOuterRDFDescription ( int level ) throws IOException {
		writeIndent ( level + 1 );
		write ( RDF_SCHEMA_END );
		writeNewline ();
	}

	private void serializeCanonicalRDFProperty (
					XMPNode node, boolean useCanonicalRDF, boolean emitAsRDFValue, int indent )
					throws IOException, XMPException {
		boolean emitEndTag = true;
		boolean indentEndTag = true;

		// Determine the XML element name. Open the start tag with the name and
		// attribute qualifiers.

		String elemName = node.getName ();
		if ( emitAsRDFValue ) {
			elemName = "rdf:value";
		} else if ( XMPConst.ARRAY_ITEM_NAME.equals ( elemName ) ) {
			elemName = "rdf:li";
		}

		writeIndent ( indent );
		write ( '<' );
		write ( elemName );

		boolean hasGeneralQualifiers = false;
		boolean hasRDFResourceQual = false;

		for ( Iterator it = node.iterateQualifier (); it.hasNext (); ) {
			XMPNode qualifier = (XMPNode) it.next ();
			if ( !RDF_ATTR_QUALIFIER.contains ( qualifier.getName () ) ) {
				hasGeneralQualifiers = true;
			} else {
				hasRDFResourceQual = "rdf:resource".equals ( qualifier.getName () );
				if ( !emitAsRDFValue ) {
					write ( ' ' );
					write ( qualifier.getName () );
					write ( "=\"" );
					appendNodeValue ( qualifier.getValue (), true );
					write ( '"' );
				}
			}
		}

		// Process the property according to the standard patterns.

		if ( hasGeneralQualifiers && !emitAsRDFValue ) {
			// This node has general, non-attribute, qualifiers. Emit using the
			// qualified property form.
			// ! The value is output by a recursive call ON THE SAME NODE with
			// emitAsRDFValue set.

			if ( hasRDFResourceQual ) {
				throw new XMPException ( "Can't mix rdf:resource and general qualifiers",
								XMPError.BADRDF );
			}

			// Change serialization to canonical format with inner rdf:Description-tag
			// depending on option
			if ( useCanonicalRDF ) {
				write ( ">" );
				writeNewline ();

				indent++;
				writeIndent ( indent );
				write ( RDF_STRUCT_START );
				write ( ">" );
			} else {
				write ( " rdf:parseType=\"Resource\">" );
			}
			writeNewline ();

			serializeCanonicalRDFProperty ( node, useCanonicalRDF, true, indent + 1 );

			for ( Iterator it = node.iterateQualifier (); it.hasNext (); ) {
				XMPNode qualifier = (XMPNode) it.next ();
				if ( !RDF_ATTR_QUALIFIER.contains ( qualifier.getName () ) ) {
					serializeCanonicalRDFProperty ( qualifier, useCanonicalRDF, false, indent + 1 );
				}
			}

			if ( useCanonicalRDF ) {
				writeIndent ( indent );
				write ( RDF_STRUCT_END );
				writeNewline ();
				indent--;
			}
		} else {
			// This node has no general qualifiers. Emit using an unqualified form.

			if ( !node.getOptions ().isCompositeProperty () ) {
				// This is a simple property.

				if ( node.getOptions ().isURI () ) {
					write ( " rdf:resource=\"" );
					appendNodeValue ( node.getValue (), true );
					write ( "\"/>" );
					writeNewline ();
					emitEndTag = false;
				} else if ( node.getValue () == null || "".equals ( node.getValue () ) ) {
					write ( "/>" );
					writeNewline ();
					emitEndTag = false;
				} else {
					write ( '>' );
					appendNodeValue ( node.getValue (), false );
					indentEndTag = false;
				}
			} else if ( node.getOptions ().isArray () ) {
				// This is an array.
				write ( '>' );
				writeNewline ();
				emitRDFArrayTag ( node, true, indent + 1 );
				if ( node.getOptions ().isArrayAltText () ) {
					XMPNodeUtils.normalizeLangArray ( node );
				}
				for ( Iterator it = node.iterateChildren (); it.hasNext (); ) {
					XMPNode child = (XMPNode) it.next ();
					serializeCanonicalRDFProperty ( child, useCanonicalRDF, false, indent + 2 );
				}
				emitRDFArrayTag ( node, false, indent + 1 );

			} else if ( !hasRDFResourceQual ) {
				// This is a "normal" struct, use the rdf:parseType="Resource" form.
				if ( !node.hasChildren () ) {
					// Change serialization to canonical format with inner rdf:Description-tag
					// if option is set
					if ( useCanonicalRDF ) {
						write ( ">" );
						writeNewline ();
						writeIndent ( indent + 1 );
						write ( RDF_EMPTY_STRUCT );
					} else {
						write ( " rdf:parseType=\"Resource\"/>" );
						emitEndTag = false;
					}
					writeNewline ();
				} else {
					// Change serialization to canonical format with inner rdf:Description-tag
					// if option is set
					if ( useCanonicalRDF ) {
						write ( ">" );
						writeNewline ();
						indent++;
						writeIndent ( indent );
						write ( RDF_STRUCT_START );
						write ( ">" );
					} else {
						write ( " rdf:parseType=\"Resource\">" );
					}
					writeNewline ();

					for ( Iterator it = node.iterateChildren (); it.hasNext (); ) {
						XMPNode child = (XMPNode) it.next ();
						serializeCanonicalRDFProperty ( child, useCanonicalRDF, false, indent + 1 );
					}

					if ( useCanonicalRDF ) {
						writeIndent ( indent );
						write ( RDF_STRUCT_END );
						writeNewline ();
						indent--;
					}
				}
			} else {
				// This is a struct with an rdf:resource attribute, use the
				// "empty property element" form.
				for ( Iterator it = node.iterateChildren (); it.hasNext (); ) {
					XMPNode child = (XMPNode) it.next ();
					if ( !canBeRDFAttrProp ( child ) ) {
						throw new XMPException ( "Can't mix rdf:resource and complex fields",
										XMPError.BADRDF );
					}
					writeNewline ();
					writeIndent ( indent + 1 );
					write ( ' ' );
					write ( child.getName () );
					write ( "=\"" );
					appendNodeValue ( child.getValue (), true );
					write ( '"' );
				}
				write ( "/>" );
				writeNewline ();
				emitEndTag = false;
			}
		}

		// Emit the property element end tag.
		if ( emitEndTag ) {
			if ( indentEndTag ) {
				writeIndent ( indent );
			}
			write ( "</" );
			write ( elemName );
			write ( '>' );
			writeNewline ();
		}
	}

	private void emitRDFArrayTag ( XMPNode arrayNode, boolean isStartTag, int indent )
					throws IOException {
		if ( isStartTag || arrayNode.hasChildren () ) {
			writeIndent ( indent );
			write ( isStartTag ? "<rdf:" : "</rdf:" );

			if ( arrayNode.getOptions ().isArrayAlternate () ) {
				write ( "Alt" );
			} else if ( arrayNode.getOptions ().isArrayOrdered () ) {
				write ( "Seq" );
			} else {
				write ( "Bag" );
			}

			if ( isStartTag && !arrayNode.hasChildren () ) {
				write ( "/>" );
			} else {
				write ( ">" );
			}

			writeNewline ();
		}
	}

	private void appendNodeValue ( String value, boolean forAttribute ) throws IOException {
		if ( value == null ) {
			value = "";
		}
		write ( Utils.escapeXML ( value, forAttribute, true ) );
	}

	private boolean canBeRDFAttrProp ( XMPNode node ) {
		return
						!node.hasQualifier () &&
										!node.getOptions ().isURI () &&
										!node.getOptions ().isCompositeProperty () &&
										!node.getOptions ().containsOneOf ( PropertyOptions.SEPARATE_NODE ) &&
										!XMPConst.ARRAY_ITEM_NAME.equals ( node.getName () );
	}

	private void writeIndent ( int times ) throws IOException {
		for ( int i = options.getBaseIndent () + times; i > 0; i-- ) {
			writer.write ( options.getIndent () );
		}
	}

	private void write ( int c ) throws IOException {
		writer.write ( c );
	}

	private void write ( String str ) throws IOException {
		writer.write ( str );
	}

	private void writeChars ( int number ) throws IOException {
		for ( ; number > 0; number-- ) {
			writer.write ( ' ' );
		}
	}

	private void writeNewline () throws IOException {
		writer.write ( options.getNewline () );
	}
}
