/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPConst;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPError;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPSchemaRegistry;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.options.AliasOptions;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.properties.XMPAliasInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;


public final class XMPSchemaRegistryImpl implements XMPSchemaRegistry, XMPConst {

	private final Map namespaceToPrefixMap = new HashMap ();

	private final Map prefixToNamespaceMap = new HashMap ();

	private final Map aliasMap = new HashMap ();

	private final Pattern p = Pattern.compile ( "[/*?\\[\\]]" );

	public XMPSchemaRegistryImpl () {
		try {
			registerStandardNamespaces ();
			registerStandardAliases ();
		} catch ( XMPException e ) {
			throw new RuntimeException ( "The XMPSchemaRegistry cannot be initialized!" );
		}
	}

	public synchronized String registerNamespace ( String namespaceURI, String suggestedPrefix )
					throws XMPException {
		ParameterAsserts.assertSchemaNS ( namespaceURI );
		ParameterAsserts.assertPrefix ( suggestedPrefix );

		if ( suggestedPrefix.charAt ( suggestedPrefix.length () - 1 ) != ':' ) {
			suggestedPrefix += ':';
		}

		if ( !Utils.isXMLNameNS ( suggestedPrefix.substring ( 0,
						suggestedPrefix.length () - 1 ) ) ) {
			throw new XMPException ( "The prefix is a bad XML name", XMPError.BADXML );
		}

		String registeredPrefix = (String) namespaceToPrefixMap.get ( namespaceURI );
		String registeredNS = (String) prefixToNamespaceMap.get ( suggestedPrefix );
		if ( registeredPrefix != null ) {
			// Return the actual prefix
			return registeredPrefix;
		} else {
			if ( registeredNS != null ) {
				// the namespace is new, but the prefix is already engaged,
				// we generate a new prefix out of the suggested
				String generatedPrefix = suggestedPrefix;
				for ( int i = 1; prefixToNamespaceMap.containsKey ( generatedPrefix ); i++ ) {
					generatedPrefix = suggestedPrefix
									.substring ( 0, suggestedPrefix.length () - 1 )
									+ "_" + i + "_:";
				}
				suggestedPrefix = generatedPrefix;
			}
			prefixToNamespaceMap.put ( suggestedPrefix, namespaceURI );
			namespaceToPrefixMap.put ( namespaceURI, suggestedPrefix );

			// Return the suggested prefix
			return suggestedPrefix;
		}
	}

	public synchronized String getNamespacePrefix ( String namespaceURI ) {
		return (String) namespaceToPrefixMap.get ( namespaceURI );
	}

	public synchronized String getNamespaceURI ( String namespacePrefix ) {
		if ( namespacePrefix != null && !namespacePrefix.endsWith ( ":" ) ) {
			namespacePrefix += ":";
		}
		return (String) prefixToNamespaceMap.get ( namespacePrefix );
	}

	public synchronized Map getNamespaces () {
		return Collections.unmodifiableMap ( new TreeMap ( namespaceToPrefixMap ) );
	}

	public synchronized Map getPrefixes () {
		return Collections.unmodifiableMap ( new TreeMap ( prefixToNamespaceMap ) );
	}

	private void registerStandardNamespaces () throws XMPException {
		// register standard namespaces
		registerNamespace ( NS_XML, "xml" );
		registerNamespace ( NS_RDF, "rdf" );
		registerNamespace ( NS_DC, "dc" );
		registerNamespace ( NS_IPTCCORE, "Iptc4xmpCore" );
		registerNamespace ( NS_IPTCEXT, "Iptc4xmpExt" );
		registerNamespace ( NS_DICOM, "DICOM" );
		registerNamespace ( NS_PLUS, "plus" );

		// register Adobe standard namespaces
		registerNamespace ( NS_X, "x" );
		registerNamespace ( NS_IX, "iX" );

		registerNamespace ( NS_XMP, "xmp" );
		registerNamespace ( NS_XMP_RIGHTS, "xmpRights" );
		registerNamespace ( NS_XMP_MM, "xmpMM" );
		registerNamespace ( NS_XMP_BJ, "xmpBJ" );
		registerNamespace ( NS_XMP_NOTE, "xmpNote" );

		registerNamespace ( NS_PDF, "pdf" );
		registerNamespace ( NS_PDFX, "pdfx" );
		registerNamespace ( NS_PDFX_ID, "pdfxid" );
		registerNamespace ( NS_PDFA_SCHEMA, "pdfaSchema" );
		registerNamespace ( NS_PDFA_PROPERTY, "pdfaProperty" );
		registerNamespace ( NS_PDFA_TYPE, "pdfaType" );
		registerNamespace ( NS_PDFA_FIELD, "pdfaField" );
		registerNamespace ( NS_PDFA_ID, "pdfaid" );
		registerNamespace ( NS_PDFUA_ID, "pdfuaid" );
		registerNamespace ( NS_PDFA_EXTENSION, "pdfaExtension" );
		registerNamespace ( NS_PHOTOSHOP, "photoshop" );
		registerNamespace ( NS_PSALBUM, "album" );
		registerNamespace ( NS_EXIF, "exif" );
		registerNamespace ( NS_EXIFX, "exifEX" );
		registerNamespace ( NS_EXIF_AUX, "aux" );
		registerNamespace ( NS_TIFF, "tiff" );
		registerNamespace ( NS_PNG, "png" );
		registerNamespace ( NS_JPEG, "jpeg" );
		registerNamespace ( NS_JP2K, "jp2k" );
		registerNamespace ( NS_CAMERARAW, "crs" );
		registerNamespace ( NS_ADOBESTOCKPHOTO, "bmsp" );
		registerNamespace ( NS_CREATOR_ATOM, "creatorAtom" );
		registerNamespace ( NS_ASF, "asf" );
		registerNamespace ( NS_WAV, "wav" );
		registerNamespace ( NS_BWF, "bext" );
		registerNamespace ( NS_RIFFINFO, "riffinfo" );
		registerNamespace ( NS_SCRIPT, "xmpScript" );
		registerNamespace ( NS_TXMP, "txmp" );
		registerNamespace ( NS_SWF, "swf" );

		// register Adobe private namespaces
		registerNamespace ( NS_DM, "xmpDM" );
		registerNamespace ( NS_TRANSIENT, "xmpx" );

		// register Adobe standard type namespaces
		registerNamespace ( TYPE_TEXT, "xmpT" );
		registerNamespace ( TYPE_PAGEDFILE, "xmpTPg" );
		registerNamespace ( TYPE_GRAPHICS, "xmpG" );
		registerNamespace ( TYPE_IMAGE, "xmpGImg" );
		registerNamespace ( TYPE_FONT, "stFnt" );
		registerNamespace ( TYPE_DIMENSIONS, "stDim" );
		registerNamespace ( TYPE_RESOURCEEVENT, "stEvt" );
		registerNamespace ( TYPE_RESOURCEREF, "stRef" );
		registerNamespace ( TYPE_ST_VERSION, "stVer" );
		registerNamespace ( TYPE_ST_JOB, "stJob" );
		registerNamespace ( TYPE_MANIFESTITEM, "stMfs" );
		registerNamespace ( TYPE_IDENTIFIERQUAL, "xmpidq" );
	}

	// ---------------------------------------------------------------------------------------------
	// Alias Functions

	public synchronized XMPAliasInfo findAlias ( String qname ) {
		return (XMPAliasInfo) aliasMap.get ( qname );
	}

	public synchronized XMPAliasInfo[] findAliases ( String aliasNS ) {
		String prefix = getNamespacePrefix ( aliasNS );
		List result = new ArrayList ();
		if ( prefix != null ) {
			for ( Object o : aliasMap.keySet () ) {
				String qname = (String) o;
				if ( qname.startsWith ( prefix ) ) {
					result.add ( findAlias ( qname ) );
				}
			}

		}
		return (XMPAliasInfo[]) result.toArray ( new XMPAliasInfo[result.size ()] );
	}

	synchronized void registerAlias ( String aliasNS, String aliasProp, final String actualNS,
					final String actualProp, final AliasOptions aliasForm ) throws XMPException {
		ParameterAsserts.assertSchemaNS ( aliasNS );
		ParameterAsserts.assertPropName ( aliasProp );
		ParameterAsserts.assertSchemaNS ( actualNS );
		ParameterAsserts.assertPropName ( actualProp );

		// Fix the alias options
		final AliasOptions aliasOpts = aliasForm != null ?
						new AliasOptions ( XMPNodeUtils.verifySetOptions (
										aliasForm.toPropertyOptions (), null ).getOptions () ) :
						new AliasOptions ();

		if ( p.matcher ( aliasProp ).find () || p.matcher ( actualProp ).find () ) {
			throw new XMPException ( "Alias and actual property names must be simple",
							XMPError.BADXPATH );
		}

		// check if both namespaces are registered
		final String aliasPrefix = getNamespacePrefix ( aliasNS );
		final String actualPrefix = getNamespacePrefix ( actualNS );
		if ( aliasPrefix == null ) {
			throw new XMPException ( "Alias namespace is not registered", XMPError.BADSCHEMA );
		} else if ( actualPrefix == null ) {
			throw new XMPException ( "Actual namespace is not registered",
							XMPError.BADSCHEMA );
		}

		String key = aliasPrefix + aliasProp;

		// check if alias is already existing
		if ( aliasMap.containsKey ( key ) ) {
			throw new XMPException ( "Alias is already existing", XMPError.BADPARAM );
		} else if ( aliasMap.containsKey ( actualPrefix + actualProp ) ) {
			throw new XMPException (
							"Actual property is already an alias, use the base property",
							XMPError.BADPARAM );
		}

		XMPAliasInfo aliasInfo = new XMPAliasInfo () {

			public String getNamespace () {
				return actualNS;
			}

			public String getPrefix () {
				return actualPrefix;
			}

			public String getPropName () {
				return actualProp;
			}

			public AliasOptions getAliasForm () {
				return aliasOpts;
			}

			public String toString () {
				return actualPrefix + actualProp + " NS(" + actualNS + "), FORM ("
								+ getAliasForm () + ")";
			}
		};

		aliasMap.put ( key, aliasInfo );
	}

	public synchronized Map getAliases () {
		return Collections.unmodifiableMap ( new TreeMap ( aliasMap ) );
	}

	private void registerStandardAliases () throws XMPException {
		AliasOptions aliasToArrayOrdered = new AliasOptions ().setArrayOrdered ( true );
		AliasOptions aliasToArrayAltText = new AliasOptions ().setArrayAltText ( true );

		// Aliases from XMP to DC.
		registerAlias ( NS_XMP, "Author", NS_DC, "creator", aliasToArrayOrdered );
		registerAlias ( NS_XMP, "Authors", NS_DC, "creator", null );
		registerAlias ( NS_XMP, "Description", NS_DC, "description", null );
		registerAlias ( NS_XMP, "Format", NS_DC, "format", null );
		registerAlias ( NS_XMP, "Keywords", NS_DC, "subject", null );
		registerAlias ( NS_XMP, "Locale", NS_DC, "language", null );
		registerAlias ( NS_XMP, "Title", NS_DC, "title", null );
		registerAlias ( NS_XMP_RIGHTS, "Copyright", NS_DC, "rights", null );

		// Aliases from PDF to DC and XMP.
		registerAlias ( NS_PDF, "Author", NS_DC, "creator", aliasToArrayOrdered );
		registerAlias ( NS_PDF, "BaseURL", NS_XMP, "BaseURL", null );
		registerAlias ( NS_PDF, "CreationDate", NS_XMP, "CreateDate", null );
		registerAlias ( NS_PDF, "Creator", NS_XMP, "CreatorTool", null );
		registerAlias ( NS_PDF, "ModDate", NS_XMP, "ModifyDate", null );
		registerAlias ( NS_PDF, "Subject", NS_DC, "description", aliasToArrayAltText );
		registerAlias ( NS_PDF, "Title", NS_DC, "title", aliasToArrayAltText );

		// Aliases from PHOTOSHOP to DC and XMP.
		registerAlias ( NS_PHOTOSHOP, "Author", NS_DC, "creator", aliasToArrayOrdered );
		registerAlias ( NS_PHOTOSHOP, "Caption", NS_DC, "description", aliasToArrayAltText );
		registerAlias ( NS_PHOTOSHOP, "Copyright", NS_DC, "rights", aliasToArrayAltText );
		registerAlias ( NS_PHOTOSHOP, "Keywords", NS_DC, "subject", null );
		registerAlias ( NS_PHOTOSHOP, "Marked", NS_XMP_RIGHTS, "Marked", null );
		registerAlias ( NS_PHOTOSHOP, "Title", NS_DC, "title", aliasToArrayAltText );
		registerAlias ( NS_PHOTOSHOP, "WebStatement", NS_XMP_RIGHTS, "WebStatement", null );

		// Aliases from TIFF and EXIF to DC and XMP.
		registerAlias ( NS_TIFF, "Artist", NS_DC, "creator", aliasToArrayOrdered );
		registerAlias ( NS_TIFF, "Copyright", NS_DC, "rights", null );
		registerAlias ( NS_TIFF, "DateTime", NS_XMP, "ModifyDate", null );
		registerAlias ( NS_TIFF, "ImageDescription", NS_DC, "description", null );
		registerAlias ( NS_TIFF, "Software", NS_XMP, "CreatorTool", null );

		// Aliases from PNG (Acrobat ImageCapture) to DC and XMP.
		registerAlias ( NS_PNG, "Author", NS_DC, "creator", aliasToArrayOrdered );
		registerAlias ( NS_PNG, "Copyright", NS_DC, "rights", aliasToArrayAltText );
		registerAlias ( NS_PNG, "CreationTime", NS_XMP, "CreateDate", null );
		registerAlias ( NS_PNG, "Description", NS_DC, "description", aliasToArrayAltText );
		registerAlias ( NS_PNG, "ModificationTime", NS_XMP, "ModifyDate", null );
		registerAlias ( NS_PNG, "Software", NS_XMP, "CreatorTool", null );
		registerAlias ( NS_PNG, "Title", NS_DC, "title", aliasToArrayAltText );
	}
}
