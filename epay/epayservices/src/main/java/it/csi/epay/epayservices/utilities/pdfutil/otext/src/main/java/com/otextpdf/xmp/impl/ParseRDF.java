/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPConst;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPError;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPMetaFactory;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPSchemaRegistry;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.options.PropertyOptions;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ParseRDF implements XMPError, XMPConst {

	public static final int RDFTERM_OTHER = 0;

	public static final int RDFTERM_RDF = 1;

	public static final int RDFTERM_ID = 2;

	public static final int RDFTERM_ABOUT = 3;

	public static final int RDFTERM_PARSE_TYPE = 4;

	public static final int RDFTERM_RESOURCE = 5;

	public static final int RDFTERM_NODE_ID = 6;

	public static final int RDFTERM_DATATYPE = 7;

	public static final int RDFTERM_DESCRIPTION = 8;

	public static final int RDFTERM_LI = 9;

	public static final int RDFTERM_ABOUT_EACH = 10;

	public static final int RDFTERM_ABOUT_EACH_PREFIX = 11;

	public static final int RDFTERM_BAG_ID = 12;

	public static final int RDFTERM_FIRST_CORE = RDFTERM_RDF;

	public static final int RDFTERM_FIRST_SYNTAX = RDFTERM_FIRST_CORE;

	public static final int RDFTERM_LAST_CORE = RDFTERM_DATATYPE;

	public static final int RDFTERM_LAST_SYNTAX = RDFTERM_LI;

	public static final int RDFTERM_FIRST_OLD = RDFTERM_ABOUT_EACH;

	public static final int RDFTERM_LAST_OLD = RDFTERM_BAG_ID;

	public static final String DEFAULT_PREFIX = "_dflt";

	static XMPMetaImpl parse ( Node xmlRoot ) throws XMPException {
		XMPMetaImpl xmp = new XMPMetaImpl ();
		rdf_RDF ( xmp, xmlRoot );
		return xmp;
	}

	static void rdf_RDF ( XMPMetaImpl xmp, Node rdfRdfNode ) throws XMPException {
		if ( rdfRdfNode.hasAttributes () ) {
			rdf_NodeElementList ( xmp, xmp.getRoot (), rdfRdfNode );
		} else {
			throw new XMPException ( "Invalid attributes of rdf:RDF element", BADRDF );
		}
	}

	private static void rdf_NodeElementList ( XMPMetaImpl xmp, XMPNode xmpParent, Node rdfRdfNode )
					throws XMPException {
		for ( int i = 0; i < rdfRdfNode.getChildNodes ().getLength (); i++ ) {
			Node child = rdfRdfNode.getChildNodes ().item ( i );
			// filter whitespaces (and all text nodes)
			if ( !isWhitespaceNode ( child ) ) {
				rdf_NodeElement ( xmp, xmpParent, child, true );
			}
		}
	}

	private static void rdf_NodeElement ( XMPMetaImpl xmp, XMPNode xmpParent, Node xmlNode,
					boolean isTopLevel ) throws XMPException {
		int nodeTerm = getRDFTermKind ( xmlNode );
		if ( nodeTerm != RDFTERM_DESCRIPTION && nodeTerm != RDFTERM_OTHER ) {
			throw new XMPException ( "Node element must be rdf:Description or typed node",
							BADRDF );
		} else if ( isTopLevel && nodeTerm == RDFTERM_OTHER ) {
			throw new XMPException ( "Top level typed node not allowed", BADXMP );
		} else {
			rdf_NodeElementAttrs ( xmp, xmpParent, xmlNode, isTopLevel );
			rdf_PropertyElementList ( xmp, xmpParent, xmlNode, isTopLevel );
		}

	}

	private static void rdf_NodeElementAttrs ( XMPMetaImpl xmp, XMPNode xmpParent, Node xmlNode,
					boolean isTopLevel ) throws XMPException {
		// Used to detect attributes that are mutually exclusive.
		int exclusiveAttrs = 0;

		for ( int i = 0; i < xmlNode.getAttributes ().getLength (); i++ ) {
			Node attribute = xmlNode.getAttributes ().item ( i );

			// quick hack, ns declarations do not appear in C++
			// ignore "ID" without namespace
			if ( "xmlns".equals ( attribute.getPrefix () ) ||
							( attribute.getPrefix () == null && "xmlns".equals ( attribute.getNodeName () ) ) ) {
				continue;
			}

			int attrTerm = getRDFTermKind ( attribute );

			switch ( attrTerm ) {
			case RDFTERM_ID:
			case RDFTERM_NODE_ID:
			case RDFTERM_ABOUT:
				if ( exclusiveAttrs > 0 ) {
					throw new XMPException ( "Mutally exclusive about, ID, nodeID attributes",
									BADRDF );
				}

				exclusiveAttrs++;

				if ( isTopLevel && ( attrTerm == RDFTERM_ABOUT ) ) {
					// This is the rdf:about attribute on a top level node. Set
					// the XMP tree name if
					// it doesn't have a name yet. Make sure this name matches
					// the XMP tree name.
					if ( xmpParent.getName () != null && !xmpParent.getName ().isEmpty () ) {
						if ( !xmpParent.getName ().equals ( attribute.getNodeValue () ) ) {
							throw new XMPException ( "Mismatched top level rdf:about values",
											BADXMP );
						}
					} else {
						xmpParent.setName ( attribute.getNodeValue () );
					}
				}
				break;

			case RDFTERM_OTHER:
				addChildNode ( xmp, xmpParent, attribute, attribute.getNodeValue (), isTopLevel );
				break;

			default:
				throw new XMPException ( "Invalid nodeElement attribute", BADRDF );
			}

		}
	}

	private static void rdf_PropertyElementList ( XMPMetaImpl xmp, XMPNode xmpParent, Node xmlParent,
					boolean isTopLevel ) throws XMPException {
		for ( int i = 0; i < xmlParent.getChildNodes ().getLength (); i++ ) {
			Node currChild = xmlParent.getChildNodes ().item ( i );
			if ( isWhitespaceNode ( currChild ) ) {
			} else if ( currChild.getNodeType () != Node.ELEMENT_NODE ) {
				throw new XMPException ( "Expected property element node not found", BADRDF );
			} else {
				rdf_PropertyElement ( xmp, xmpParent, currChild, isTopLevel );
			}
		}
	}

	private static void rdf_PropertyElement ( XMPMetaImpl xmp, XMPNode xmpParent, Node xmlNode,
					boolean isTopLevel ) throws XMPException {
		int nodeTerm = getRDFTermKind ( xmlNode );
		if ( !isPropertyElementName ( nodeTerm ) ) {
			throw new XMPException ( "Invalid property element name", BADRDF );
		}

		// remove the namespace-definitions from the list
		NamedNodeMap attributes = xmlNode.getAttributes ();
		List nsAttrs = null;
		for ( int i = 0; i < attributes.getLength (); i++ ) {
			Node attribute = attributes.item ( i );
			if ( "xmlns".equals ( attribute.getPrefix () ) ||
							( attribute.getPrefix () == null && "xmlns".equals ( attribute.getNodeName () ) ) ) {
				if ( nsAttrs == null ) {
					nsAttrs = new ArrayList ();
				}
				nsAttrs.add ( attribute.getNodeName () );
			}
		}
		if ( nsAttrs != null ) {
			for ( Object nsAttr : nsAttrs ) {
				String ns = (String) nsAttr;
				attributes.removeNamedItem ( ns );
			}
		}

		if ( attributes.getLength () > 3 ) {
			// Only an emptyPropertyElt can have more than 3 attributes.
			rdf_EmptyPropertyElement ( xmp, xmpParent, xmlNode, isTopLevel );
		} else {
			// Look through the attributes for one that isn't rdf:ID or xml:lang, 
			// it will usually tell what we should be dealing with. 
			// The called routines must verify their specific syntax!

			for ( int i = 0; i < attributes.getLength (); i++ ) {
				Node attribute = attributes.item ( i );
				String attrLocal = attribute.getLocalName ();
				String attrNS = attribute.getNamespaceURI ();
				String attrValue = attribute.getNodeValue ();
				if ( !( XML_LANG.equals ( attribute.getNodeName () ) &&
								!( "ID".equals ( attrLocal ) && NS_RDF.equals ( attrNS ) ) ) ) {
					if ( "datatype".equals ( attrLocal ) && NS_RDF.equals ( attrNS ) ) {
						rdf_LiteralPropertyElement ( xmp, xmpParent, xmlNode, isTopLevel );
					} else if ( !( "parseType".equals ( attrLocal ) && NS_RDF.equals ( attrNS ) ) ) {
						rdf_EmptyPropertyElement ( xmp, xmpParent, xmlNode, isTopLevel );
					} else if ( "Literal".equals ( attrValue ) ) {
						rdf_ParseTypeLiteralPropertyElement ();
					} else if ( "Resource".equals ( attrValue ) ) {
						rdf_ParseTypeResourcePropertyElement ( xmp, xmpParent, xmlNode, isTopLevel );
					} else if ( "Collection".equals ( attrValue ) ) {
						rdf_ParseTypeCollectionPropertyElement ();
					} else {
						rdf_ParseTypeOtherPropertyElement ();
					}

					return;
				}
			}

			// Only rdf:ID and xml:lang, could be a resourcePropertyElt, a literalPropertyElt, 
			// or an emptyPropertyElt. Look at the child XML nodes to decide which.

			if ( xmlNode.hasChildNodes () ) {
				for ( int i = 0; i < xmlNode.getChildNodes ().getLength (); i++ ) {
					Node currChild = xmlNode.getChildNodes ().item ( i );
					if ( currChild.getNodeType () != Node.TEXT_NODE ) {
						rdf_ResourcePropertyElement ( xmp, xmpParent, xmlNode, isTopLevel );
						return;
					}
				}

				rdf_LiteralPropertyElement ( xmp, xmpParent, xmlNode, isTopLevel );
			} else {
				rdf_EmptyPropertyElement ( xmp, xmpParent, xmlNode, isTopLevel );
			}
		}
	}

	private static void rdf_ResourcePropertyElement ( XMPMetaImpl xmp, XMPNode xmpParent,
					Node xmlNode, boolean isTopLevel ) throws XMPException {
		if ( isTopLevel && "iX:changes".equals ( xmlNode.getNodeName () ) ) {
			// Strip old "punchcard" chaff which has on the prefix "iX:".
			return;
		}

		XMPNode newCompound = addChildNode ( xmp, xmpParent, xmlNode, "", isTopLevel );

		// walk through the attributes
		for ( int i = 0; i < xmlNode.getAttributes ().getLength (); i++ ) {
			Node attribute = xmlNode.getAttributes ().item ( i );
			if ( "xmlns".equals ( attribute.getPrefix () ) ||
							( attribute.getPrefix () == null && "xmlns".equals ( attribute.getNodeName () ) ) ) {
				continue;
			}

			String attrLocal = attribute.getLocalName ();
			String attrNS = attribute.getNamespaceURI ();
			if ( XML_LANG.equals ( attribute.getNodeName () ) ) {
				addQualifierNode ( newCompound, XML_LANG, attribute.getNodeValue () );
			} else if ( "ID".equals ( attrLocal ) && NS_RDF.equals ( attrNS ) ) {
				// Ignore all rdf:ID attributes.
			} else {
				throw new XMPException (
								"Invalid attribute for resource property element", BADRDF );
			}
		}

		// walk through the children

		Node currChild;
		boolean found = false;
		int i;
		for ( i = 0; i < xmlNode.getChildNodes ().getLength (); i++ ) {
			currChild = xmlNode.getChildNodes ().item ( i );
			if ( !isWhitespaceNode ( currChild ) ) {
				if ( currChild.getNodeType () == Node.ELEMENT_NODE && !found ) {
					boolean isRDF = NS_RDF.equals ( currChild.getNamespaceURI () );
					String childLocal = currChild.getLocalName ();

					if ( isRDF && "Bag".equals ( childLocal ) ) {
						newCompound.getOptions ().setArray ( true );
					} else if ( isRDF && "Seq".equals ( childLocal ) ) {
						newCompound.getOptions ().setArray ( true ).setArrayOrdered ( true );
					} else if ( isRDF && "Alt".equals ( childLocal ) ) {
						newCompound.getOptions ().setArray ( true ).setArrayOrdered ( true )
										.setArrayAlternate ( true );
					} else {
						newCompound.getOptions ().setStruct ( true );
						if ( !isRDF && !"Description".equals ( childLocal ) ) {
							String typeName = currChild.getNamespaceURI ();
							if ( typeName == null ) {
								throw new XMPException (
												"All XML elements must be in a namespace", BADXMP );
							}
							typeName += ':' + childLocal;
							addQualifierNode ( newCompound, "rdf:type", typeName );
						}
					}

					rdf_NodeElement ( xmp, newCompound, currChild, false );

					if ( newCompound.getHasValueChild () ) {
						fixupQualifiedNode ( newCompound );
					} else if ( newCompound.getOptions ().isArrayAlternate () ) {
						XMPNodeUtils.detectAltText ( newCompound );
					}

					found = true;
				} else if ( found ) {
					// found second child element
					throw new XMPException (
									"Invalid child of resource property element", BADRDF );
				} else {
					throw new XMPException (
									"Children of resource property element must be XML elements", BADRDF );
				}
			}
		}

		if ( !found ) {
			// didn't found any child elements
			throw new XMPException ( "Missing child of resource property element", BADRDF );
		}
	}

	private static void rdf_LiteralPropertyElement ( XMPMetaImpl xmp, XMPNode xmpParent,
					Node xmlNode, boolean isTopLevel ) throws XMPException {
		XMPNode newChild = addChildNode ( xmp, xmpParent, xmlNode, null, isTopLevel );

		for ( int i = 0; i < xmlNode.getAttributes ().getLength (); i++ ) {
			Node attribute = xmlNode.getAttributes ().item ( i );
			if ( "xmlns".equals ( attribute.getPrefix () ) ||
							( attribute.getPrefix () == null && "xmlns".equals ( attribute.getNodeName () ) ) ) {
				continue;
			}

			String attrNS = attribute.getNamespaceURI ();
			String attrLocal = attribute.getLocalName ();
			if ( XML_LANG.equals ( attribute.getNodeName () ) ) {
				addQualifierNode ( newChild, XML_LANG, attribute.getNodeValue () );
			} else if ( NS_RDF.equals ( attrNS ) &&
							( "ID".equals ( attrLocal ) || "datatype".equals ( attrLocal ) ) ) {
				// Ignore all rdf:ID and rdf:datatype attributes.
			} else {
				throw new XMPException (
								"Invalid attribute for literal property element", BADRDF );
			}
		}
		String textValue = "";
		for ( int i = 0; i < xmlNode.getChildNodes ().getLength (); i++ ) {
			Node child = xmlNode.getChildNodes ().item ( i );
			if ( child.getNodeType () == Node.TEXT_NODE ) {
				textValue += child.getNodeValue ();
			} else {
				throw new XMPException ( "Invalid child of literal property element", BADRDF );
			}
		}
		newChild.setValue ( textValue );
	}

	private static void rdf_ParseTypeLiteralPropertyElement () throws XMPException {
		throw new XMPException ( "ParseTypeLiteral property element not allowed", BADXMP );
	}

	private static void rdf_ParseTypeResourcePropertyElement ( XMPMetaImpl xmp, XMPNode xmpParent,
					Node xmlNode, boolean isTopLevel ) throws XMPException {
		XMPNode newStruct = addChildNode ( xmp, xmpParent, xmlNode, "", isTopLevel );

		newStruct.getOptions ().setStruct ( true );

		for ( int i = 0; i < xmlNode.getAttributes ().getLength (); i++ ) {
			Node attribute = xmlNode.getAttributes ().item ( i );
			if ( "xmlns".equals ( attribute.getPrefix () ) ||
							( attribute.getPrefix () == null && "xmlns".equals ( attribute.getNodeName () ) ) ) {
				continue;
			}

			String attrLocal = attribute.getLocalName ();
			String attrNS = attribute.getNamespaceURI ();
			if ( XML_LANG.equals ( attribute.getNodeName () ) ) {
				addQualifierNode ( newStruct, XML_LANG, attribute.getNodeValue () );
			} else if ( NS_RDF.equals ( attrNS ) &&
							( "ID".equals ( attrLocal ) || "parseType".equals ( attrLocal ) ) ) {
				// The caller ensured the value is "Resource".
				// Ignore all rdf:ID attributes.
			} else {
				throw new XMPException ( "Invalid attribute for ParseTypeResource property element",
								BADRDF );
			}
		}

		rdf_PropertyElementList ( xmp, newStruct, xmlNode, false );

		if ( newStruct.getHasValueChild () ) {
			fixupQualifiedNode ( newStruct );
		}
	}

	private static void rdf_ParseTypeCollectionPropertyElement () throws XMPException {
		throw new XMPException ( "ParseTypeCollection property element not allowed", BADXMP );
	}

	private static void rdf_ParseTypeOtherPropertyElement () throws XMPException {
		throw new XMPException ( "ParseTypeOther property element not allowed", BADXMP );
	}

	private static void rdf_EmptyPropertyElement ( XMPMetaImpl xmp, XMPNode xmpParent, Node xmlNode,
					boolean isTopLevel ) throws XMPException {
		boolean hasPropertyAttrs = false;
		boolean hasResourceAttr = false;
		boolean hasNodeIDAttr = false;
		boolean hasValueAttr = false;

		Node valueNode = null;    // ! Can come from rdf:value or rdf:resource.

		if ( xmlNode.hasChildNodes () ) {
			throw new XMPException (
							"Nested content not allowed with rdf:resource or property attributes",
							BADRDF );
		}

		// First figure out what XMP this maps to and remember the XML node for a simple value.
		for ( int i = 0; i < xmlNode.getAttributes ().getLength (); i++ ) {
			Node attribute = xmlNode.getAttributes ().item ( i );
			if ( "xmlns".equals ( attribute.getPrefix () ) ||
							( attribute.getPrefix () == null && "xmlns".equals ( attribute.getNodeName () ) ) ) {
				continue;
			}

			int attrTerm = getRDFTermKind ( attribute );

			switch ( attrTerm ) {
			case RDFTERM_ID:
				// Nothing to do.
				break;

			case RDFTERM_RESOURCE:
				if ( hasNodeIDAttr ) {
					throw new XMPException (
									"Empty property element can't have both rdf:resource and rdf:nodeID",
									BADRDF );
				} else if ( hasValueAttr ) {
					throw new XMPException (
									"Empty property element can't have both rdf:value and rdf:resource",
									BADXMP );
				}

				hasResourceAttr = true;
				valueNode = attribute;
				break;

			case RDFTERM_NODE_ID:
				if ( hasResourceAttr ) {
					throw new XMPException (
									"Empty property element can't have both rdf:resource and rdf:nodeID",
									BADRDF );
				}
				hasNodeIDAttr = true;
				break;

			case RDFTERM_OTHER:
				if ( "value".equals ( attribute.getLocalName () )
								&& NS_RDF.equals ( attribute.getNamespaceURI () ) ) {
					if ( hasResourceAttr ) {
						throw new XMPException (
										"Empty property element can't have both rdf:value and rdf:resource",
										BADXMP );
					}
					hasValueAttr = true;
					valueNode = attribute;
				} else if ( !XML_LANG.equals ( attribute.getNodeName () ) ) {
					hasPropertyAttrs = true;
				}
				break;

			default:
				throw new XMPException ( "Unrecognized attribute of empty property element",
								BADRDF );
			}
		}

		XMPNode childNode = addChildNode ( xmp, xmpParent, xmlNode, "", isTopLevel );
		boolean childIsStruct = false;

		if ( hasValueAttr || hasResourceAttr ) {
			childNode.setValue ( valueNode.getNodeValue () );
			if ( !hasValueAttr ) {
				// ! Might have both rdf:value and rdf:resource.
				childNode.getOptions ().setURI ( true );
			}
		} else if ( hasPropertyAttrs ) {
			childNode.getOptions ().setStruct ( true );
			childIsStruct = true;
		}

		for ( int i = 0; i < xmlNode.getAttributes ().getLength (); i++ ) {
			Node attribute = xmlNode.getAttributes ().item ( i );
			if ( attribute == valueNode ||
							"xmlns".equals ( attribute.getPrefix () ) ||
							( attribute.getPrefix () == null && "xmlns".equals ( attribute.getNodeName () ) ) ) {
				continue;    // Skip the rdf:value or rdf:resource attribute holding the value.
			}

			int attrTerm = getRDFTermKind ( attribute );

			switch ( attrTerm ) {
			case RDFTERM_ID:
			case RDFTERM_NODE_ID:
				break;    // Ignore all rdf:ID and rdf:nodeID attributes.

			case RDFTERM_RESOURCE:
				addQualifierNode ( childNode, "rdf:resource", attribute.getNodeValue () );
				break;

			case RDFTERM_OTHER:
				if ( !childIsStruct ) {
					addQualifierNode (
									childNode, attribute.getNodeName (), attribute.getNodeValue () );
				} else if ( XML_LANG.equals ( attribute.getNodeName () ) ) {
					addQualifierNode ( childNode, XML_LANG, attribute.getNodeValue () );
				} else {
					addChildNode ( xmp, childNode, attribute, attribute.getNodeValue (), false );
				}
				break;

			default:
				throw new XMPException ( "Unrecognized attribute of empty property element",
								BADRDF );
			}

		}
	}

	private static XMPNode addChildNode ( XMPMetaImpl xmp, XMPNode xmpParent, Node xmlNode,
					String value, boolean isTopLevel ) throws XMPException {
		XMPSchemaRegistry registry = XMPMetaFactory.getSchemaRegistry ();
		String namespace = xmlNode.getNamespaceURI ();
		String childName;
		if ( namespace != null ) {
			if ( NS_DC_DEPRECATED.equals ( namespace ) ) {
				// Fix a legacy DC namespace
				namespace = NS_DC;
			}

			String prefix = registry.getNamespacePrefix ( namespace );
			if ( prefix == null ) {
				prefix = xmlNode.getPrefix () != null ? xmlNode.getPrefix () : DEFAULT_PREFIX;
				prefix = registry.registerNamespace ( namespace, prefix );
			}
			childName = prefix + xmlNode.getLocalName ();
		} else {
			throw new XMPException (
							"XML namespace required for all elements and attributes", BADRDF );
		}

		// create schema node if not already there
		PropertyOptions childOptions = new PropertyOptions ();
		boolean isAlias = false;
		if ( isTopLevel ) {
			// Lookup the schema node, adjust the XMP parent pointer.
			// Incoming parent must be the tree root.
			XMPNode schemaNode = XMPNodeUtils.findSchemaNode ( xmp.getRoot (), namespace,
							DEFAULT_PREFIX, true );
			schemaNode.setImplicit ( false );    // Clear the implicit node bit.
			// need runtime check for proper 32 bit code.
			xmpParent = schemaNode;

			// If this is an alias set the alias flag in the node 
			// and the hasAliases flag in the tree.
			if ( registry.findAlias ( childName ) != null ) {
				isAlias = true;
				xmp.getRoot ().setHasAliases ( true );
				schemaNode.setHasAliases ( true );
			}
		}

		// Make sure that this is not a duplicate of a named node.
		boolean isArrayItem = "rdf:li".equals ( childName );
		boolean isValueNode = "rdf:value".equals ( childName );

		// Create XMP node and so some checks
		XMPNode newChild = new XMPNode (
						childName, value, childOptions );
		newChild.setAlias ( isAlias );

		// Add the new child to the XMP parent node, a value node first.
		if ( !isValueNode ) {
			xmpParent.addChild ( newChild );
		} else {
			xmpParent.addChild ( 1, newChild );
		}

		if ( isValueNode ) {
			if ( isTopLevel || !xmpParent.getOptions ().isStruct () ) {
				throw new XMPException ( "Misplaced rdf:value element", BADRDF );
			}
			xmpParent.setHasValueChild ( true );
		}

		if ( isArrayItem ) {
			if ( !xmpParent.getOptions ().isArray () ) {
				throw new XMPException ( "Misplaced rdf:li element", BADRDF );
			}
			newChild.setName ( ARRAY_ITEM_NAME );
		}

		return newChild;
	}

	private static XMPNode addQualifierNode ( XMPNode xmpParent, String name, String value )
					throws XMPException {
		boolean isLang = XML_LANG.equals ( name );

		XMPNode newQual;

		// normalize value of language qualifiers
		newQual = new XMPNode ( name, isLang ? Utils.normalizeLangValue ( value ) : value, null );
		xmpParent.addQualifier ( newQual );

		return newQual;
	}

	private static void fixupQualifiedNode ( XMPNode xmpParent ) throws XMPException {
		assert xmpParent.getOptions ().isStruct () && xmpParent.hasChildren ();

		XMPNode valueNode = xmpParent.getChild ( 1 );
		assert "rdf:value".equals ( valueNode.getName () );
		if ( valueNode.getOptions ().getHasLanguage () ) {
			if ( xmpParent.getOptions ().getHasLanguage () ) {
				throw new XMPException ( "Redundant xml:lang for rdf:value element",
								BADXMP );
			}
			XMPNode langQual = valueNode.getQualifier ( 1 );
			valueNode.removeQualifier ( langQual );
			xmpParent.addQualifier ( langQual );
		}

		// Start the remaining copy after the xml:lang qualifier.		
		for ( int i = 1; i <= valueNode.getQualifierLength (); i++ ) {
			XMPNode qualifier = valueNode.getQualifier ( i );
			xmpParent.addQualifier ( qualifier );
		}

		// Change the parent's other children into qualifiers. 
		// This loop starts at 1, child 0 is the rdf:value node.
		for ( int i = 2; i <= xmpParent.getChildrenLength (); i++ ) {
			XMPNode qualifier = xmpParent.getChild ( i );
			xmpParent.addQualifier ( qualifier );
		}

		// Move the options and value last, other checks need the parent's original options. 
		// Move the value node's children to be the parent's children.
		assert xmpParent.getOptions ().isStruct () || xmpParent.getHasValueChild ();

		xmpParent.setHasValueChild ( false );
		xmpParent.getOptions ().setStruct ( false );
		xmpParent.getOptions ().mergeWith ( valueNode.getOptions () );
		xmpParent.setValue ( valueNode.getValue () );

		xmpParent.removeChildren ();
		for ( Iterator it = valueNode.iterateChildren (); it.hasNext (); ) {
			XMPNode child = (XMPNode) it.next ();
			xmpParent.addChild ( child );
		}
	}

	private static boolean isWhitespaceNode ( Node node ) {
		if ( node.getNodeType () != Node.TEXT_NODE ) {
			return false;
		}

		String value = node.getNodeValue ();
		for ( int i = 0; i < value.length (); i++ ) {
			if ( !Character.isWhitespace ( value.charAt ( i ) ) ) {
				return false;
			}
		}

		return true;
	}

	private static boolean isPropertyElementName ( int term ) {
		if ( term == RDFTERM_DESCRIPTION || isOldTerm ( term ) ) {
			return false;
		} else {
			return ( !isCoreSyntaxTerm ( term ) );
		}
	}

	private static boolean isOldTerm ( int term ) {
		return RDFTERM_FIRST_OLD <= term && term <= RDFTERM_LAST_OLD;
	}

	private static boolean isCoreSyntaxTerm ( int term ) {
		return RDFTERM_FIRST_CORE <= term && term <= RDFTERM_LAST_CORE;
	}

	private static int getRDFTermKind ( Node node ) {
		String localName = node.getLocalName ();
		String namespace = node.getNamespaceURI ();

		if (
						namespace == null &&
										( "about".equals ( localName ) || "ID".equals ( localName ) ) &&
										( node instanceof Attr ) &&
										NS_RDF.equals ( ( (Attr) node ).getOwnerElement ().getNamespaceURI () )
		) {
			namespace = NS_RDF;
		}

		if ( NS_RDF.equals ( namespace ) ) {
			if ( "li".equals ( localName ) ) {
				return RDFTERM_LI;
			} else if ( "parseType".equals ( localName ) ) {
				return RDFTERM_PARSE_TYPE;
			} else if ( "Description".equals ( localName ) ) {
				return RDFTERM_DESCRIPTION;
			} else if ( "about".equals ( localName ) ) {
				return RDFTERM_ABOUT;
			} else if ( "resource".equals ( localName ) ) {
				return RDFTERM_RESOURCE;
			} else if ( "RDF".equals ( localName ) ) {
				return RDFTERM_RDF;
			} else if ( "ID".equals ( localName ) ) {
				return RDFTERM_ID;
			} else if ( "nodeID".equals ( localName ) ) {
				return RDFTERM_NODE_ID;
			} else if ( "datatype".equals ( localName ) ) {
				return RDFTERM_DATATYPE;
			} else if ( "aboutEach".equals ( localName ) ) {
				return RDFTERM_ABOUT_EACH;
			} else if ( "aboutEachPrefix".equals ( localName ) ) {
				return RDFTERM_ABOUT_EACH_PREFIX;
			} else if ( "bagID".equals ( localName ) ) {
				return RDFTERM_BAG_ID;
			}
		}

		return RDFTERM_OTHER;
	}
}
