/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPConst;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPDateTime;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPError;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPMeta;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPMetaFactory;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPUtils;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl.xpath.XMPPath;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl.xpath.XMPPathParser;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.options.ParseOptions;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.options.PropertyOptions;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.properties.XMPAliasInfo;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class XMPNormalizer {

	private static Map dcArrayForms;

	static {
		initDCArrays ();
	}

	private XMPNormalizer () {
		// EMPTY
	}

	static XMPMeta process ( XMPMetaImpl xmp, ParseOptions options ) throws XMPException {
		XMPNode tree = xmp.getRoot ();

		touchUpDataModel ( xmp );
		moveExplicitAliases ( tree, options );

		tweakOldXMP ( tree );

		deleteEmptySchemas ( tree );

		return xmp;
	}

	private static void tweakOldXMP ( XMPNode tree ) throws XMPException {
		if ( tree.getName () != null && tree.getName ().length () >= Utils.UUID_LENGTH ) {
			String nameStr = tree.getName ().toLowerCase ();
			if ( nameStr.startsWith ( "uuid:" ) ) {
				nameStr = nameStr.substring ( 5 );
			}

			if ( Utils.checkUUIDFormat ( nameStr ) ) {
				// move UUID to xmpMM:InstanceID and remove it from the root node
				XMPPath path = XMPPathParser.expandXPath ( XMPConst.NS_XMP_MM, "InstanceID" );
				XMPNode idNode = XMPNodeUtils.findNode ( tree, path, true, null );
				if ( idNode != null ) {
					idNode.setOptions ( null );    // Clobber any existing xmpMM:InstanceID.
					idNode.setValue ( "uuid:" + nameStr );
					idNode.removeChildren ();
					idNode.removeQualifiers ();
					tree.setName ( null );
				} else {
					throw new XMPException ( "Failure creating xmpMM:InstanceID",
									XMPError.INTERNALFAILURE );
				}
			}
		}
	}

	private static void touchUpDataModel ( XMPMetaImpl xmp ) throws XMPException {
		// make sure the DC schema is existing, because it might be needed within the normalization
		// if not touched it will be removed by removeEmptySchemas
		XMPNodeUtils.findSchemaNode ( xmp.getRoot (), XMPConst.NS_DC, true );

		// Do the special case fixes within each schema.
		for ( Iterator it = xmp.getRoot ().iterateChildren (); it.hasNext (); ) {
			XMPNode currSchema = (XMPNode) it.next ();
			if ( XMPConst.NS_DC.equals ( currSchema.getName () ) ) {
				normalizeDCArrays ( currSchema );
			} else if ( XMPConst.NS_EXIF.equals ( currSchema.getName () ) ) {
				// Do a special case fix for exif:GPSTimeStamp.
				fixGPSTimeStamp ( currSchema );
				XMPNode arrayNode = XMPNodeUtils.findChildNode ( currSchema, "exif:UserComment",
								false );
				if ( arrayNode != null ) {
					repairAltText ( arrayNode );
				}
			} else if ( XMPConst.NS_DM.equals ( currSchema.getName () ) ) {
				// Do a special case migration of xmpDM:copyright to
				// dc:rights['x-default'].
				XMPNode dmCopyright = XMPNodeUtils.findChildNode ( currSchema, "xmpDM:copyright",
								false );
				if ( dmCopyright != null ) {
					migrateAudioCopyright ( xmp, dmCopyright );
				}
			} else if ( XMPConst.NS_XMP_RIGHTS.equals ( currSchema.getName () ) ) {
				XMPNode arrayNode = XMPNodeUtils.findChildNode ( currSchema, "xmpRights:UsageTerms",
								false );
				if ( arrayNode != null ) {
					repairAltText ( arrayNode );
				}
			}
		}
	}

	private static void normalizeDCArrays ( XMPNode dcSchema ) throws XMPException {
		for ( int i = 1; i <= dcSchema.getChildrenLength (); i++ ) {
			XMPNode currProp = dcSchema.getChild ( i );

			PropertyOptions arrayForm = (PropertyOptions) dcArrayForms.get ( currProp.getName () );
			if ( arrayForm == null ) {
			} else if ( currProp.getOptions ().isSimple () ) {
				// create a new array and add the current property as child, 
				// if it was formerly simple 
				XMPNode newArray = new XMPNode ( currProp.getName (), arrayForm );
				currProp.setName ( XMPConst.ARRAY_ITEM_NAME );
				newArray.addChild ( currProp );
				dcSchema.replaceChild ( i, newArray );

				// fix language alternatives
				if ( arrayForm.isArrayAltText () && !currProp.getOptions ().getHasLanguage () ) {
					XMPNode newLang = new XMPNode ( XMPConst.XML_LANG, XMPConst.X_DEFAULT, null );
					currProp.addQualifier ( newLang );
				}
			} else {
				// clear array options and add corrected array form if it has been an array before
				currProp.getOptions ().setOption (
								PropertyOptions.ARRAY |
												PropertyOptions.ARRAY_ORDERED |
												PropertyOptions.ARRAY_ALTERNATE |
												PropertyOptions.ARRAY_ALT_TEXT,
								false );
				currProp.getOptions ().mergeWith ( arrayForm );

				if ( arrayForm.isArrayAltText () ) {
					// applying for "dc:description", "dc:rights", "dc:title"
					repairAltText ( currProp );
				}
			}

		}
	}

	private static void repairAltText ( XMPNode arrayNode ) throws XMPException {
		if ( arrayNode == null ||
						!arrayNode.getOptions ().isArray () ) {
			// Already OK or not even an array.
			return;
		}

		// fix options
		arrayNode.getOptions ().setArrayOrdered ( true ).setArrayAlternate ( true ).setArrayAltText ( true );

		for ( Iterator it = arrayNode.iterateChildren (); it.hasNext (); ) {
			XMPNode currChild = (XMPNode) it.next ();
			if ( currChild.getOptions ().isCompositeProperty () ) {
				// Delete non-simple children.
				it.remove ();
			} else if ( !currChild.getOptions ().getHasLanguage () ) {
				String childValue = currChild.getValue ();
				if ( childValue == null || childValue.isEmpty () ) {
					// Delete empty valued children that have no xml:lang.
					it.remove ();
				} else {
					// Add an xml:lang qualifier with the value "x-repair".
					XMPNode repairLang = new XMPNode ( XMPConst.XML_LANG, "x-repair", null );
					currChild.addQualifier ( repairLang );
				}
			}
		}
	}

	private static void moveExplicitAliases ( XMPNode tree, ParseOptions options )
					throws XMPException {
		if ( tree.getHasAliases () ) {
			return;
		}
		tree.setHasAliases ( false );

		boolean strictAliasing = options.getStrictAliasing ();

		for ( Object o : tree.getUnmodifiableChildren () ) {
			XMPNode currSchema = (XMPNode) o;
			if ( currSchema.getHasAliases () ) {
				continue;
			}

			for ( Iterator propertyIt = currSchema.iterateChildren (); propertyIt.hasNext (); ) {
				XMPNode currProp = (XMPNode) propertyIt.next ();

				if ( !currProp.isAlias () ) {
					continue;
				}

				currProp.setAlias ( false );

				// Find the base path, look for the base schema and root node.
				XMPAliasInfo info = XMPMetaFactory.getSchemaRegistry ()
								.findAlias ( currProp.getName () );
				if ( info != null ) {
					// find or create schema
					XMPNode baseSchema = XMPNodeUtils.findSchemaNode ( tree, info
									.getNamespace (), null, true );
					baseSchema.setImplicit ( false );

					XMPNode baseNode = XMPNodeUtils
									.findChildNode ( baseSchema,
													info.getPrefix () + info.getPropName (), false );
					if ( baseNode == null ) {
						if ( info.getAliasForm ().isSimple () ) {
							// A top-to-top alias, transplant the property.
							// change the alias property name to the base name
							String qname = info.getPrefix () + info.getPropName ();
							currProp.setName ( qname );
							baseSchema.addChild ( currProp );
							// remove the alias property
							propertyIt.remove ();
						} else {
							// An alias to an array item, 
							// create the array and transplant the property.
							baseNode = new XMPNode ( info.getPrefix () + info.getPropName (), info
											.getAliasForm ().toPropertyOptions () );
							baseSchema.addChild ( baseNode );
							transplantArrayItemAlias ( propertyIt, currProp, baseNode );
						}

					} else if ( info.getAliasForm ().isSimple () ) {
						// The base node does exist and this is a top-to-top alias.
						// Check for conflicts if strict aliasing is on. 
						// Remove and delete the alias subtree.
						if ( strictAliasing ) {
							compareAliasedSubtrees ( currProp, baseNode, true );
						}

						propertyIt.remove ();
					} else {
						// This is an alias to an array item and the array exists.
						// Look for the aliased item.
						// Then transplant or check & delete as appropriate.

						XMPNode itemNode = null;
						if ( info.getAliasForm ().isArrayAltText () ) {
							int xdIndex = XMPNodeUtils.lookupLanguageItem ( baseNode,
											XMPConst.X_DEFAULT );
							if ( xdIndex != -1 ) {
								itemNode = baseNode.getChild ( xdIndex );
							}
						} else if ( baseNode.hasChildren () ) {
							itemNode = baseNode.getChild ( 1 );
						}

						if ( itemNode == null ) {
							transplantArrayItemAlias ( propertyIt, currProp, baseNode );
						} else {
							if ( strictAliasing ) {
								compareAliasedSubtrees ( currProp, itemNode, true );
							}

							propertyIt.remove ();
						}
					}
				}
			}
			currSchema.setHasAliases ( false );
		}
	}

	private static void transplantArrayItemAlias ( Iterator propertyIt, XMPNode childNode,
					XMPNode baseArray ) throws XMPException {
		if ( baseArray.getOptions ().isArrayAltText () ) {
			if ( childNode.getOptions ().getHasLanguage () ) {
				throw new XMPException ( "Alias to x-default already has a language qualifier",
								XMPError.BADXMP );
			}

			XMPNode langQual = new XMPNode ( XMPConst.XML_LANG, XMPConst.X_DEFAULT, null );
			childNode.addQualifier ( langQual );
		}

		propertyIt.remove ();
		childNode.setName ( XMPConst.ARRAY_ITEM_NAME );
		baseArray.addChild ( childNode );
	}

	private static void fixGPSTimeStamp ( XMPNode exifSchema )
					throws XMPException {
		// Note: if dates are not found the convert-methods throws an exceptions,
		// 		 and this methods returns.
		XMPNode gpsDateTime = XMPNodeUtils.findChildNode ( exifSchema, "exif:GPSTimeStamp", false );
		if ( gpsDateTime == null ) {
			return;
		}

		try {
			XMPDateTime binGPSStamp;
			XMPDateTime binOtherDate;

			binGPSStamp = XMPUtils.convertToDate ( gpsDateTime.getValue () );
			if ( binGPSStamp.getYear () != 0 ||
							binGPSStamp.getMonth () != 0 ||
							binGPSStamp.getDay () != 0 ) {
				return;
			}

			XMPNode otherDate = XMPNodeUtils.findChildNode ( exifSchema, "exif:DateTimeOriginal",
							false );
			if ( otherDate == null ) {
				otherDate = XMPNodeUtils.findChildNode ( exifSchema, "exif:DateTimeDigitized", false );
			}

			binOtherDate = XMPUtils.convertToDate ( otherDate.getValue () );
			Calendar cal = binGPSStamp.getCalendar ();
			cal.set ( Calendar.YEAR, binOtherDate.getYear () );
			cal.set ( Calendar.MONTH, binOtherDate.getMonth () );
			cal.set ( Calendar.DAY_OF_MONTH, binOtherDate.getDay () );
			binGPSStamp = new XMPDateTimeImpl ( cal );
			gpsDateTime.setValue ( XMPUtils.convertFromDate ( binGPSStamp ) );
		} catch ( XMPException e ) {
			// Don't let a missing or bad date stop other things.
		}
	}

	private static void deleteEmptySchemas ( XMPNode tree ) {
		// Delete empty schema nodes. Do this last, other cleanup can make empty
		// schema.

		for ( Iterator it = tree.iterateChildren (); it.hasNext (); ) {
			XMPNode schema = (XMPNode) it.next ();
			if ( !schema.hasChildren () ) {
				it.remove ();
			}
		}
	}

	private static void compareAliasedSubtrees ( XMPNode aliasNode, XMPNode baseNode,
					boolean outerCall ) throws XMPException {
		if ( !aliasNode.getValue ().equals ( baseNode.getValue () ) ||
						aliasNode.getChildrenLength () != baseNode.getChildrenLength () ) {
			throw new XMPException ( "Mismatch between alias and base nodes", XMPError.BADXMP );
		}

		if (
						!outerCall &&
										( !aliasNode.getName ().equals ( baseNode.getName () ) ||
														!aliasNode.getOptions ().equals ( baseNode.getOptions () ) ||
														aliasNode.getQualifierLength () != baseNode.getQualifierLength () )
		) {
			throw new XMPException ( "Mismatch between alias and base nodes",
							XMPError.BADXMP );
		}

		for ( Iterator an = aliasNode.iterateChildren (),
			  bn = baseNode.iterateChildren ();
			  an.hasNext () && bn.hasNext (); ) {
			XMPNode aliasChild = (XMPNode) an.next ();
			XMPNode baseChild = (XMPNode) bn.next ();
			compareAliasedSubtrees ( aliasChild, baseChild, false );
		}

		for ( Iterator an = aliasNode.iterateQualifier (),
			  bn = baseNode.iterateQualifier ();
			  an.hasNext () && bn.hasNext (); ) {
			XMPNode aliasQual = (XMPNode) an.next ();
			XMPNode baseQual = (XMPNode) bn.next ();
			compareAliasedSubtrees ( aliasQual, baseQual, false );
		}
	}

	private static void migrateAudioCopyright ( XMPMeta xmp, XMPNode dmCopyright ) {
		try {
			XMPNode dcSchema = XMPNodeUtils.findSchemaNode (
							( (XMPMetaImpl) xmp ).getRoot (), XMPConst.NS_DC, true );

			String dmValue = dmCopyright.getValue ();
			String doubleLF = "\n\n";

			XMPNode dcRightsArray = XMPNodeUtils.findChildNode ( dcSchema, "dc:rights", false );

			if ( dcRightsArray == null || !dcRightsArray.hasChildren () ) {
				// 1. No dc:rights array, create from double linefeed and xmpDM:copyright.
				dmValue = doubleLF + dmValue;
				xmp.setLocalizedText ( XMPConst.NS_DC, "rights", "", XMPConst.X_DEFAULT, dmValue,
								null );
			} else {
				int xdIndex = XMPNodeUtils.lookupLanguageItem ( dcRightsArray, XMPConst.X_DEFAULT );

				if ( xdIndex < 0 ) {
					// 2. No x-default item, create from the first item.
					String firstValue = dcRightsArray.getChild ( 1 ).getValue ();
					xmp.setLocalizedText ( XMPConst.NS_DC, "rights", "", XMPConst.X_DEFAULT,
									firstValue, null );
					xdIndex = XMPNodeUtils.lookupLanguageItem ( dcRightsArray, XMPConst.X_DEFAULT );
				}

				// 3. Look for a double linefeed in the x-default value.
				XMPNode defaultNode = dcRightsArray.getChild ( xdIndex );
				String defaultValue = defaultNode.getValue ();
				int lfPos = defaultValue.indexOf ( doubleLF );

				if ( lfPos < 0 ) {
					// 3A. No double LF, compare whole values.
					if ( !dmValue.equals ( defaultValue ) ) {
						// 3A2. Append the xmpDM:copyright to the x-default
						// item.
						defaultNode.setValue ( defaultValue + doubleLF + dmValue );
					}
				} else {
					// 3B. Has double LF, compare the tail.
					if ( !defaultValue.substring ( lfPos + 2 ).equals ( dmValue ) ) {
						// 3B2. Replace the x-default tail.
						defaultNode.setValue ( defaultValue.substring ( 0, lfPos + 2 ) + dmValue );
					}
				}

			}

			// 4. Get rid of the xmpDM:copyright.
			dmCopyright.getParent ().removeChild ( dmCopyright );
		} catch ( XMPException e ) {
			// Don't let failures (like a bad dc:rights form) stop other
			// cleanup.
		}
	}

	private static void initDCArrays () {
		dcArrayForms = new HashMap ();

		// Properties supposed to be a "Bag".
		PropertyOptions bagForm = new PropertyOptions ();
		bagForm.setArray ( true );
		dcArrayForms.put ( "dc:contributor", bagForm );
		dcArrayForms.put ( "dc:language", bagForm );
		dcArrayForms.put ( "dc:publisher", bagForm );
		dcArrayForms.put ( "dc:relation", bagForm );
		dcArrayForms.put ( "dc:subject", bagForm );
		dcArrayForms.put ( "dc:type", bagForm );

		// Properties supposed to be a "Seq".
		PropertyOptions seqForm = new PropertyOptions ();
		seqForm.setArray ( true );
		seqForm.setArrayOrdered ( true );
		dcArrayForms.put ( "dc:creator", seqForm );
		dcArrayForms.put ( "dc:date", seqForm );

		// Properties supposed to be an "Alt" in alternative-text form.
		PropertyOptions altTextForm = new PropertyOptions ();
		altTextForm.setArray ( true );
		altTextForm.setArrayOrdered ( true );
		altTextForm.setArrayAlternate ( true );
		altTextForm.setArrayAltText ( true );
		dcArrayForms.put ( "dc:description", altTextForm );
		dcArrayForms.put ( "dc:rights", altTextForm );
		dcArrayForms.put ( "dc:title", altTextForm );
	}
}
