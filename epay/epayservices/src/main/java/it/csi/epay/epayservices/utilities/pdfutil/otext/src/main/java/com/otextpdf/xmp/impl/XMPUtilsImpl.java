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
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl.xpath.XMPPath;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl.xpath.XMPPathParser;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.options.PropertyOptions;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.properties.XMPAliasInfo;

import java.util.Iterator;


public class XMPUtilsImpl implements XMPConst {

	private static final int UCK_NORMAL = 0;

	private static final int UCK_SPACE = 1;

	private static final int UCK_COMMA = 2;

	private static final int UCK_SEMICOLON = 3;

	private static final int UCK_QUOTE = 4;

	private XMPUtilsImpl () {
		// EMPTY
	}

	public static void removeProperties ( XMPMeta xmp, String schemaNS, String propName,
					boolean doAllProperties, boolean includeAliases ) throws XMPException {
		ParameterAsserts.assertImplementation ( xmp );
		XMPMetaImpl xmpImpl = (XMPMetaImpl) xmp;

		if ( propName != null && !propName.isEmpty () ) {
			// Remove just the one indicated property. This might be an alias,
			// the named schema might not actually exist. So don't lookup the
			// schema node.

			if ( schemaNS == null || schemaNS.isEmpty () ) {
				throw new XMPException ( "Property name requires schema namespace",
								XMPError.BADPARAM );
			}

			XMPPath expPath = XMPPathParser.expandXPath ( schemaNS, propName );

			XMPNode propNode = XMPNodeUtils.findNode ( xmpImpl.getRoot (), expPath, false, null );
			if ( propNode != null ) {
				if ( doAllProperties
								|| Utils.isInternalProperty ( expPath.getSegment ( XMPPath.STEP_SCHEMA )
								.getName (), expPath.getSegment ( XMPPath.STEP_ROOT_PROP ).getName () ) ) {
					XMPNode parent = propNode.getParent ();
					parent.removeChild ( propNode );
					if ( parent.getOptions ().isSchemaNode () && !parent.hasChildren () ) {
						// remove empty schema node
						parent.getParent ().removeChild ( parent );
					}

				}
			}
		} else if ( schemaNS != null && !schemaNS.isEmpty () ) {

			// Remove all properties from the named schema. Optionally include
			// aliases, in which case
			// there might not be an actual schema node.

			// XMP_NodePtrPos schemaPos;
			XMPNode schemaNode = XMPNodeUtils.findSchemaNode ( xmpImpl.getRoot (), schemaNS, false );
			if ( schemaNode != null ) {
				if ( removeSchemaChildren ( schemaNode, doAllProperties ) ) {
					xmpImpl.getRoot ().removeChild ( schemaNode );
				}
			}

			if ( includeAliases ) {
				// We're removing the aliases also. Look them up by their
				// namespace prefix.
				// But that takes more code and the extra speed isn't worth it.
				// Lookup the XMP node
				// from the alias, to make sure the actual exists.

				XMPAliasInfo[] aliases = XMPMetaFactory.getSchemaRegistry ().findAliases ( schemaNS );
				for ( XMPAliasInfo info : aliases ) {
					XMPPath path = XMPPathParser.expandXPath ( info.getNamespace (), info
									.getPropName () );
					XMPNode actualProp = XMPNodeUtils
									.findNode ( xmpImpl.getRoot (), path, false, null );
					if ( actualProp != null ) {
						XMPNode parent = actualProp.getParent ();
						parent.removeChild ( actualProp );
					}
				}
			}
		} else {
			// Remove all appropriate properties from all schema. In this case
			// we don't have to be
			// concerned with aliases, they are handled implicitly from the
			// actual properties.
			for ( Iterator it = xmpImpl.getRoot ().iterateChildren (); it.hasNext (); ) {
				XMPNode schema = (XMPNode) it.next ();
				if ( removeSchemaChildren ( schema, doAllProperties ) ) {
					it.remove ();
				}
			}
		}
	}

	public static void appendProperties ( XMPMeta source, XMPMeta destination,
					boolean doAllProperties, boolean replaceOldValues, boolean deleteEmptyValues )
					throws XMPException {
		ParameterAsserts.assertImplementation ( source );
		ParameterAsserts.assertImplementation ( destination );

		XMPMetaImpl src = (XMPMetaImpl) source;
		XMPMetaImpl dest = (XMPMetaImpl) destination;

		for ( Iterator it = src.getRoot ().iterateChildren (); it.hasNext (); ) {
			XMPNode sourceSchema = (XMPNode) it.next ();

			// Make sure we have a destination schema node
			XMPNode destSchema = XMPNodeUtils.findSchemaNode ( dest.getRoot (),
							sourceSchema.getName (), false );
			boolean createdSchema = false;
			if ( destSchema == null ) {
				destSchema = new XMPNode ( sourceSchema.getName (), sourceSchema.getValue (),
								new PropertyOptions ().setSchemaNode ( true ) );
				dest.getRoot ().addChild ( destSchema );
				createdSchema = true;
			}

			// Process the source schema's children.
			for ( Iterator ic = sourceSchema.iterateChildren (); ic.hasNext (); ) {
				XMPNode sourceProp = (XMPNode) ic.next ();
				if ( doAllProperties
								|| Utils.isInternalProperty ( sourceSchema.getName (), sourceProp.getName () ) ) {
					appendSubtree (
									dest, sourceProp, destSchema, replaceOldValues, deleteEmptyValues );
				}
			}

			if ( !destSchema.hasChildren () && ( createdSchema || deleteEmptyValues ) ) {
				// Don't create an empty schema / remove empty schema.
				dest.getRoot ().removeChild ( destSchema );
			}
		}
	}

	private static boolean removeSchemaChildren ( XMPNode schemaNode, boolean doAllProperties ) {
		for ( Iterator it = schemaNode.iterateChildren (); it.hasNext (); ) {
			XMPNode currProp = (XMPNode) it.next ();
			if ( doAllProperties
							|| Utils.isInternalProperty ( schemaNode.getName (), currProp.getName () ) ) {
				it.remove ();
			}
		}

		return !schemaNode.hasChildren ();
	}

	private static void appendSubtree ( XMPMetaImpl destXMP, XMPNode sourceNode, XMPNode destParent,
					boolean replaceOldValues, boolean deleteEmptyValues ) throws XMPException {
		XMPNode destNode = XMPNodeUtils.findChildNode ( destParent, sourceNode.getName (), false );

		boolean valueIsEmpty = false;
		if ( deleteEmptyValues ) {
			valueIsEmpty = sourceNode.getOptions ().isSimple () ?
							sourceNode.getValue () == null || sourceNode.getValue ().isEmpty () :
							!sourceNode.hasChildren ();
		}

		if ( deleteEmptyValues && valueIsEmpty ) {
			if ( destNode != null ) {
				destParent.removeChild ( destNode );
			}
		} else if ( destNode == null ) {
			// The one easy case, the destination does not exist.
			destParent.addChild ( (XMPNode) sourceNode.clone () );
		} else if ( replaceOldValues ) {
			// The destination exists and should be replaced.
			destXMP.setNode ( destNode, sourceNode.getValue (), sourceNode.getOptions (), true );
			destParent.removeChild ( destNode );
			destNode = (XMPNode) sourceNode.clone ();
			destParent.addChild ( destNode );
		} else {
			// The destination exists and is not totally replaced. Structs and
			// arrays are merged.

			PropertyOptions sourceForm = sourceNode.getOptions ();
			PropertyOptions destForm = destNode.getOptions ();
			if ( sourceForm != destForm ) {
				return;
			}
			if ( sourceForm.isStruct () ) {
				// To merge a struct process the fields recursively. E.g. add simple missing fields.
				// The recursive call to AppendSubtree will handle deletion for fields with empty
				// values.
				for ( Iterator it = sourceNode.iterateChildren (); it.hasNext (); ) {
					XMPNode sourceField = (XMPNode) it.next ();
					appendSubtree ( destXMP, sourceField, destNode,
									false, deleteEmptyValues );
					if ( deleteEmptyValues && !destNode.hasChildren () ) {
						destParent.removeChild ( destNode );
					}
				}
			} else if ( sourceForm.isArrayAltText () ) {
				// Merge AltText arrays by the "xml:lang" qualifiers. Make sure x-default is first.
				// Make a special check for deletion of empty values. Meaningful in AltText arrays
				// because the "xml:lang" qualifier provides unambiguous source/dest correspondence.
				for ( Iterator it = sourceNode.iterateChildren (); it.hasNext (); ) {
					XMPNode sourceItem = (XMPNode) it.next ();
					if ( !sourceItem.hasQualifier ()
									|| !XMPConst.XML_LANG.equals ( sourceItem.getQualifier ( 1 ).getName () ) ) {
						continue;
					}

					int destIndex = XMPNodeUtils.lookupLanguageItem ( destNode,
									sourceItem.getQualifier ( 1 ).getValue () );
					if ( deleteEmptyValues &&
									( sourceItem.getValue () == null ||
													sourceItem.getValue ().isEmpty () ) ) {
						if ( destIndex != -1 ) {
							destNode.removeChild ( destIndex );
							if ( !destNode.hasChildren () ) {
								destParent.removeChild ( destNode );
							}
						}
					} else if ( destIndex == -1 ) {
						// Not replacing, keep the existing item.
						if ( !XMPConst.X_DEFAULT.equals ( sourceItem.getQualifier ( 1 ).getValue () )
										|| !destNode.hasChildren () ) {
							sourceItem.cloneSubtree ( destNode );
						} else {
							XMPNode destItem = new XMPNode (
											sourceItem.getName (),
											sourceItem.getValue (),
											sourceItem.getOptions () );
							sourceItem.cloneSubtree ( destItem );
							destNode.addChild ( 1, destItem );
						}
					}
				}
			} else if ( sourceForm.isArray () ) {
				// Merge other arrays by item values. Don't worry about order or duplicates. Source
				// items with empty values do not cause deletion, that conflicts horribly with
				// merging.

				for ( Iterator is = sourceNode.iterateChildren (); is.hasNext (); ) {
					XMPNode sourceItem = (XMPNode) is.next ();

					boolean match = false;
					for ( Iterator id = destNode.iterateChildren (); id.hasNext (); ) {
						XMPNode destItem = (XMPNode) id.next ();
						if ( itemValuesMatch ( sourceItem, destItem ) ) {
							match = true;
						}
					}
					if ( !match ) {
						destNode = (XMPNode) sourceItem.clone ();
						destParent.addChild ( destNode );
					}
				}
			}
		}
	}

	private static boolean itemValuesMatch ( XMPNode leftNode, XMPNode rightNode ) throws XMPException {
		PropertyOptions leftForm = leftNode.getOptions ();
		PropertyOptions rightForm = rightNode.getOptions ();

		if ( leftForm.equals ( rightForm ) ) {
			return false;
		}

		if ( leftForm.getOptions () == 0 ) {
			// Simple nodes, check the values and xml:lang qualifiers.
			if ( !leftNode.getValue ().equals ( rightNode.getValue () ) ) {
				return false;
			}
			if ( leftNode.getOptions ().getHasLanguage () != rightNode.getOptions ().getHasLanguage () ) {
				return false;
			}
			return !leftNode.getOptions ().getHasLanguage ()
							|| leftNode.getQualifier ( 1 ).getValue ().equals (
							rightNode.getQualifier ( 1 ).getValue () );
		} else if ( leftForm.isStruct () ) {
			// Struct nodes, see if all fields match, ignoring order.

			if ( leftNode.getChildrenLength () != rightNode.getChildrenLength () ) {
				return false;
			}

			for ( Iterator it = leftNode.iterateChildren (); it.hasNext (); ) {
				XMPNode leftField = (XMPNode) it.next ();
				XMPNode rightField = XMPNodeUtils.findChildNode ( rightNode, leftField.getName (),
								false );
				if ( rightField == null || !itemValuesMatch ( leftField, rightField ) ) {
					return false;
				}
			}
		} else {
			// Array nodes, see if the "leftNode" values are present in the
			// "rightNode", ignoring order, duplicates,
			// and extra values in the rightNode-> The rightNode is the
			// destination for AppendProperties.

			assert leftForm.isArray ();

			for ( Iterator il = leftNode.iterateChildren (); il.hasNext (); ) {
				XMPNode leftItem = (XMPNode) il.next ();

				boolean match = false;
				for ( Iterator ir = rightNode.iterateChildren (); ir.hasNext (); ) {
					XMPNode rightItem = (XMPNode) ir.next ();
					if ( itemValuesMatch ( leftItem, rightItem ) ) {
						match = true;
						break;
					}
				}
				if ( !match ) {
					return false;
				}
			}
		}
		return true;
	}

}
