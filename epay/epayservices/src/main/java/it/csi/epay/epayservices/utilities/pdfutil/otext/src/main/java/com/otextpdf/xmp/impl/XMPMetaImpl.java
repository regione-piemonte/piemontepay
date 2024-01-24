/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPConst;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPDateTime;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPError;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPIterator;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPMeta;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPUtils;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl.xpath.XMPPath;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl.xpath.XMPPathParser;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.options.IteratorOptions;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.options.ParseOptions;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.options.PropertyOptions;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.properties.XMPProperty;

import java.util.Iterator;


public class XMPMetaImpl implements XMPMeta, XMPConst {

	private static final int VALUE_STRING = 0;

	private static final int VALUE_BOOLEAN = 1;

	private static final int VALUE_INTEGER = 2;

	private static final int VALUE_LONG = 3;

	private static final int VALUE_DOUBLE = 4;

	private static final int VALUE_DATE = 5;

	private static final int VALUE_CALENDAR = 6;

	private static final int VALUE_BASE64 = 7;

	private final XMPNode tree;

	public XMPMetaImpl () {
		// create root node
		tree = new XMPNode ( null, null, null );
	}

	public XMPMetaImpl ( XMPNode tree ) {
		this.tree = tree;
	}

	public void appendArrayItem ( String schemaNS, String arrayName, PropertyOptions arrayOptions,
					String itemValue, PropertyOptions itemOptions ) throws XMPException {
		ParameterAsserts.assertSchemaNS ( schemaNS );
		ParameterAsserts.assertArrayName ( arrayName );

		if ( arrayOptions == null ) {
			arrayOptions = new PropertyOptions ();
		}
		if ( !arrayOptions.isOnlyArrayOptions () ) {
			throw new XMPException ( "Only array form flags allowed for arrayOptions",
							XMPError.BADOPTIONS );
		}

		// Check if array options are set correctly.
		arrayOptions = XMPNodeUtils.verifySetOptions ( arrayOptions, null );

		// Locate or create the array. If it already exists, make sure the array
		// form from the options
		// parameter is compatible with the current state.
		XMPPath arrayPath = XMPPathParser.expandXPath ( schemaNS, arrayName );

		XMPNode arrayNode = XMPNodeUtils.findNode ( tree, arrayPath, false, null );

		if ( arrayNode != null ) {
			if ( !arrayNode.getOptions ().isArray () ) {
				throw new XMPException ( "The named property is not an array", XMPError.BADXPATH );
			}
		} else {
			if ( arrayOptions.isArray () ) {
				arrayNode = XMPNodeUtils.findNode ( tree, arrayPath, true, arrayOptions );
				if ( arrayNode == null ) {
					throw new XMPException ( "Failure creating array node", XMPError.BADXPATH );
				}
			} else {
				throw new XMPException ( "Explicit arrayOptions required to create new array",
								XMPError.BADOPTIONS );
			}
		}

		doSetArrayItem ( arrayNode, ARRAY_LAST_ITEM, itemValue, itemOptions );
	}

	public boolean doesPropertyExist ( String schemaNS, String propName ) {
		try {
			ParameterAsserts.assertSchemaNS ( schemaNS );
			ParameterAsserts.assertPropName ( propName );

			XMPPath expPath = XMPPathParser.expandXPath ( schemaNS, propName );
			final XMPNode propNode = XMPNodeUtils.findNode ( tree, expPath, false, null );
			return propNode != null;
		} catch ( XMPException e ) {
			return false;
		}
	}

	public void setLocalizedText ( String schemaNS, String altTextName, String genericLang,
					String specificLang, String itemValue, PropertyOptions options ) throws XMPException {
		ParameterAsserts.assertSchemaNS ( schemaNS );
		ParameterAsserts.assertArrayName ( altTextName );
		ParameterAsserts.assertSpecificLang ( specificLang );

		genericLang = genericLang != null ? Utils.normalizeLangValue ( genericLang ) : null;
		specificLang = Utils.normalizeLangValue ( specificLang );

		XMPPath arrayPath = XMPPathParser.expandXPath ( schemaNS, altTextName );

		// Find the array node and set the options if it was just created.
		XMPNode arrayNode = XMPNodeUtils.findNode ( tree, arrayPath, true, new PropertyOptions (
						PropertyOptions.ARRAY | PropertyOptions.ARRAY_ORDERED
										| PropertyOptions.ARRAY_ALTERNATE | PropertyOptions.ARRAY_ALT_TEXT ) );

		if ( arrayNode == null ) {
			throw new XMPException ( "Failed to find or create array node", XMPError.BADXPATH );
		} else if ( !arrayNode.getOptions ().isArrayAltText () ) {
			if ( !arrayNode.hasChildren () && arrayNode.getOptions ().isArrayAlternate () ) {
				arrayNode.getOptions ().setArrayAltText ( true );
			} else {
				throw new XMPException (
								"Specified property is no alt-text array", XMPError.BADXPATH );
			}
		}

		// Make sure the x-default item, if any, is first.
		boolean haveXDefault = false;
		XMPNode xdItem = null;

		for ( Iterator it = arrayNode.iterateChildren (); it.hasNext (); ) {
			XMPNode currItem = (XMPNode) it.next ();
			if ( !currItem.hasQualifier ()
							|| !XMPConst.XML_LANG.equals ( currItem.getQualifier ( 1 ).getName () ) ) {
				throw new XMPException ( "Language qualifier must be first", XMPError.BADXPATH );
			} else if ( XMPConst.X_DEFAULT.equals ( currItem.getQualifier ( 1 ).getValue () ) ) {
				xdItem = currItem;
				haveXDefault = true;
				break;
			}
		}

		// Moves x-default to the beginning of the array
		if ( xdItem != null && arrayNode.getChildrenLength () > 1 ) {
			arrayNode.removeChild ( xdItem );
			arrayNode.addChild ( 1, xdItem );
		}

		// Find the appropriate item.
		// chooseLocalizedText will make sure the array is a language
		// alternative.
		Object[] result = XMPNodeUtils.chooseLocalizedText ( arrayNode, genericLang, specificLang );
		int match = (Integer) result[0];
		XMPNode itemNode = (XMPNode) result[1];

		boolean specificXDefault = XMPConst.X_DEFAULT.equals ( specificLang );

		switch ( match ) {
		case XMPNodeUtils.CLT_NO_VALUES:

			// Create the array items for the specificLang and x-default, with
			// x-default first.
			XMPNodeUtils.appendLangItem ( arrayNode, XMPConst.X_DEFAULT, itemValue );
			haveXDefault = true;
			if ( !specificXDefault ) {
				XMPNodeUtils.appendLangItem ( arrayNode, specificLang, itemValue );
			}
			break;

		case XMPNodeUtils.CLT_SPECIFIC_MATCH:

			if ( !specificXDefault ) {
				// Update the specific item, update x-default if it matches the
				// old value.
				if ( haveXDefault && xdItem != itemNode && xdItem.getValue ().equals ( itemNode.getValue () ) ) {
					xdItem.setValue ( itemValue );
				}
				// ! Do this after the x-default check!
				itemNode.setValue ( itemValue );
			} else {
				// Update all items whose values match the old x-default value.
				assert haveXDefault && xdItem == itemNode;
				for ( Iterator it = arrayNode.iterateChildren (); it.hasNext (); ) {
					XMPNode currItem = (XMPNode) it.next ();
					if ( currItem == xdItem
									|| !currItem.getValue ().equals (
									xdItem.getValue () ) ) {
						continue;
					}
					currItem.setValue ( itemValue );
				}
				// And finally do the x-default item.
				xdItem.setValue ( itemValue );
			}
			break;

		case XMPNodeUtils.CLT_SINGLE_GENERIC:

			// Update the generic item, update x-default if it matches the old
			// value.
			if ( haveXDefault && xdItem != itemNode && xdItem.getValue ().equals ( itemNode.getValue () ) ) {
				xdItem.setValue ( itemValue );
			}
			itemNode.setValue ( itemValue ); // ! Do this after
			// the x-default
			// check!
			break;

		case XMPNodeUtils.CLT_MULTIPLE_GENERIC:

		case XMPNodeUtils.CLT_FIRST_ITEM:

			// Create the specific language, don't add an x-default item.

			// Create the specific language, ignore x-default.
			XMPNodeUtils.appendLangItem ( arrayNode, specificLang, itemValue );
			if ( specificXDefault ) {
				haveXDefault = true;
			}
			break;

		case XMPNodeUtils.CLT_XDEFAULT:

			// Create the specific language, update x-default if it was the only
			// item.
			if ( xdItem != null && arrayNode.getChildrenLength () == 1 ) {
				xdItem.setValue ( itemValue );
			}
			XMPNodeUtils.appendLangItem ( arrayNode, specificLang, itemValue );
			break;

		default:
			// does not happen under normal circumstances
			throw new XMPException ( "Unexpected result from ChooseLocalizedText",
							XMPError.INTERNALFAILURE );

		}

		// Add an x-default at the front if needed.
		if ( !haveXDefault && arrayNode.getChildrenLength () == 1 ) {
			XMPNodeUtils.appendLangItem ( arrayNode, XMPConst.X_DEFAULT, itemValue );
		}
	}

	public void setLocalizedText ( String schemaNS, String altTextName, String genericLang,
					String specificLang, String itemValue ) throws XMPException {
		setLocalizedText ( schemaNS, altTextName, genericLang, specificLang, itemValue, null );
	}

	public XMPProperty getProperty ( String schemaNS, String propName ) throws XMPException {
		return getProperty ( schemaNS, propName, VALUE_STRING );
	}

	protected XMPProperty getProperty ( String schemaNS, String propName, int valueType )
					throws XMPException {
		ParameterAsserts.assertSchemaNS ( schemaNS );
		ParameterAsserts.assertPropName ( propName );

		final XMPPath expPath = XMPPathParser.expandXPath ( schemaNS, propName );
		final XMPNode propNode = XMPNodeUtils.findNode ( tree, expPath, false, null );

		if ( propNode != null ) {
			if ( valueType != VALUE_STRING && propNode.getOptions ().isCompositeProperty () ) {
				throw new XMPException ( "Property must be simple when a value type is requested",
								XMPError.BADXPATH );
			}

			final Object value = evaluateNodeValue ( valueType, propNode );

			return new XMPProperty () {

				public String getValue () {
					return value != null ? value.toString () : null;
				}

				public PropertyOptions getOptions () {
					return propNode.getOptions ();
				}

				public String getLanguage () {
					return null;
				}

				public String toString () {
					return value.toString ();
				}
			};
		} else {
			return null;
		}
	}

	public XMPIterator iterator () throws XMPException {
		return iterator ( null, null, null );
	}

	public XMPIterator iterator ( IteratorOptions options ) throws XMPException {
		return iterator ( null, null, options );
	}

	public XMPIterator iterator ( String schemaNS, String propName, IteratorOptions options )
					throws XMPException {
		return new XMPIteratorImpl ( this, schemaNS, propName, options );
	}

	public void setProperty ( String schemaNS, String propName, Object propValue,
					PropertyOptions options ) throws XMPException {
		ParameterAsserts.assertSchemaNS ( schemaNS );
		ParameterAsserts.assertPropName ( propName );

		options = XMPNodeUtils.verifySetOptions ( options, propValue );

		XMPPath expPath = XMPPathParser.expandXPath ( schemaNS, propName );

		XMPNode propNode = XMPNodeUtils.findNode ( tree, expPath, true, options );
		if ( propNode != null ) {
			setNode ( propNode, propValue, options, false );
		} else {
			throw new XMPException ( "Specified property does not exist", XMPError.BADXPATH );
		}
	}

	public void setProperty ( String schemaNS, String propName, Object propValue ) throws XMPException {
		setProperty ( schemaNS, propName, propValue, null );
	}

	public String getObjectName () {
		return tree.getName () != null ? tree.getName () : "";
	}

	public void setObjectName ( String name ) {
		tree.setName ( name );
	}

	public void setPacketHeader ( String packetHeader ) {
	}

	public Object clone () {
		XMPNode clonedTree = (XMPNode) tree.clone ();
		return new XMPMetaImpl ( clonedTree );
	}

	public void sort () {
		this.tree.sort ();
	}

	public void normalize ( ParseOptions options ) throws XMPException {
		if ( options == null ) {
			options = new ParseOptions ();
		}
		XMPNormalizer.process ( this, options );
	}

	public XMPNode getRoot () {
		return tree;
	}

	// -------------------------------------------------------------------------------------
	// private

	private void doSetArrayItem ( XMPNode arrayNode, int itemIndex, String itemValue,
					PropertyOptions itemOptions ) throws XMPException {
		XMPNode itemNode = new XMPNode ( ARRAY_ITEM_NAME, null );
		itemOptions = XMPNodeUtils.verifySetOptions ( itemOptions, itemValue );

		// in insert mode the index after the last is allowed,
		// even ARRAY_LAST_ITEM points to the index *after* the last.
		int maxIndex = arrayNode.getChildrenLength () + 1;
		if ( itemIndex == ARRAY_LAST_ITEM ) {
			itemIndex = maxIndex;
		}

		if ( 1 <= itemIndex && itemIndex <= maxIndex ) {
			arrayNode.addChild ( itemIndex, itemNode );
			setNode ( itemNode, itemValue, itemOptions, false );
		} else {
			throw new XMPException ( "Array index out of bounds", XMPError.BADINDEX );
		}
	}

	void setNode ( XMPNode node, Object value, PropertyOptions newOptions, boolean deleteExisting )
					throws XMPException {
		if ( deleteExisting ) {
			node.clear ();
		}

		// its checked by setOptions(), if the merged result is a valid options set
		node.getOptions ().mergeWith ( newOptions );

		if ( !node.getOptions ().isCompositeProperty () ) {
			// This is setting the value of a leaf node.
			XMPNodeUtils.setNodeValue ( node, value );
		} else {
			if ( value != null && !value.toString ().isEmpty () ) {
				throw new XMPException ( "Composite nodes can't have values", XMPError.BADXPATH );
			}

			node.removeChildren ();
		}

	}

	private Object evaluateNodeValue ( int valueType, final XMPNode propNode ) throws XMPException {
		final Object value;
		String rawValue = propNode.getValue ();
		switch ( valueType ) {
		case VALUE_BOOLEAN:
			value = XMPUtils.convertToBoolean ( rawValue );
			break;
		case VALUE_INTEGER:
			value = XMPUtils.convertToInteger ( rawValue );
			break;
		case VALUE_LONG:
			value = XMPUtils.convertToLong ( rawValue );
			break;
		case VALUE_DOUBLE:
			value = XMPUtils.convertToDouble ( rawValue );
			break;
		case VALUE_DATE:
			value = XMPUtils.convertToDate ( rawValue );
			break;
		case VALUE_CALENDAR:
			XMPDateTime dt = XMPUtils.convertToDate ( rawValue );
			value = dt.getCalendar ();
			break;
		case VALUE_BASE64:
			value = XMPUtils.decodeBase64 ( rawValue );
			break;
		case VALUE_STRING:
		default:
			// leaf values return empty string instead of null
			// for the other cases the converter methods provides a "null"
			// value.
			// a default value can only occur if this method is made public.
			value = rawValue != null || propNode.getOptions ().isCompositeProperty () ? rawValue : "";
			break;
		}
		return value;
	}
}
