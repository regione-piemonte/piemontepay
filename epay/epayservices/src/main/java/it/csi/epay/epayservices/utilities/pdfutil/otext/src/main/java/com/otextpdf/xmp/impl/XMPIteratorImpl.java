/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPError;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPIterator;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPMetaFactory;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl.xpath.XMPPath;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl.xpath.XMPPathParser;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.options.IteratorOptions;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.options.PropertyOptions;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.properties.XMPPropertyInfo;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class XMPIteratorImpl implements XMPIterator {

	private final IteratorOptions options;

	private final Iterator nodeIterator;

	protected boolean skipSiblings = false;

	protected boolean skipSubtree = false;

	private String baseNS = null;

	public XMPIteratorImpl ( XMPMetaImpl xmp, String schemaNS, String propPath,
					IteratorOptions options ) throws XMPException {
		// make sure that options is defined at least with defaults
		this.options = options != null ? options : new IteratorOptions ();

		// the start node of the iteration depending on the schema and property filter
		XMPNode startNode;
		String initialPath = null;
		boolean baseSchema = schemaNS != null && !schemaNS.isEmpty ();
		boolean baseProperty = propPath != null && !propPath.isEmpty ();

		if ( !baseSchema && !baseProperty ) {
			// complete tree will be iterated
			startNode = xmp.getRoot ();
		} else if ( baseSchema && baseProperty ) {
			// Schema and property node provided
			XMPPath path = XMPPathParser.expandXPath ( schemaNS, propPath );

			// base path is the prop path without the property leaf
			XMPPath basePath = new XMPPath ();
			for ( int i = 0; i < path.size () - 1; i++ ) {
				basePath.add ( path.getSegment ( i ) );
			}

			startNode = XMPNodeUtils.findNode ( xmp.getRoot (), path, false, null );
			baseNS = schemaNS;
			initialPath = basePath.toString ();
		} else if ( baseSchema ) {
			// Only Schema provided
			startNode = XMPNodeUtils.findSchemaNode ( xmp.getRoot (), schemaNS, false );
		} else // !baseSchema  &&  baseProperty
		{
			// No schema but property provided -> error
			throw new XMPException ( "Schema namespace URI is required", XMPError.BADSCHEMA );
		}

		// create iterator
		if ( startNode != null ) {
			if ( !this.options.isJustChildren () ) {
				nodeIterator = new NodeIterator ( startNode, initialPath, 1 );
			} else {
				nodeIterator = new NodeIteratorChildren ( startNode, initialPath );
			}
		} else {
			// create null iterator
			nodeIterator = Collections.EMPTY_LIST.iterator ();
		}
	}

	public boolean hasNext () {
		return nodeIterator.hasNext ();
	}

	public Object next () {
		return nodeIterator.next ();
	}

	public void remove () {
		throw new UnsupportedOperationException ( "The XMPIterator does not support remove()." );
	}

	protected IteratorOptions getOptions () {
		return options;
	}

	protected String getBaseNS () {
		return baseNS;
	}

	protected void setBaseNS ( String baseNS ) {
		this.baseNS = baseNS;
	}

	private class NodeIterator implements Iterator {

		protected static final int ITERATE_NODE = 0;

		protected static final int ITERATE_CHILDREN = 1;

		protected static final int ITERATE_QUALIFIER = 2;

		private int state = ITERATE_NODE;

		private XMPNode visitedNode;

		private String path;

		private Iterator childrenIterator = null;

		private int index = 0;

		private Iterator subIterator = Collections.EMPTY_LIST.iterator ();

		private XMPPropertyInfo returnProperty = null;

		public NodeIterator () {
			// EMPTY
		}

		public NodeIterator ( XMPNode visitedNode, String parentPath, int index ) {
			this.visitedNode = visitedNode;
			this.state = NodeIterator.ITERATE_NODE;
			if ( visitedNode.getOptions ().isSchemaNode () ) {
				setBaseNS ( visitedNode.getName () );
			}

			// for all but the root node and schema nodes
			path = accumulatePath ( visitedNode, parentPath, index );
		}

		public boolean hasNext () {
			if ( returnProperty != null ) {
				// hasNext has been called before
				return true;
			}

			// find next node
			if ( state == ITERATE_NODE ) {
				return reportNode ();
			} else if ( state == ITERATE_CHILDREN ) {
				if ( childrenIterator == null ) {
					childrenIterator = visitedNode.iterateChildren ();
				}

				boolean hasNext = iterateChildren ( childrenIterator );

				if ( !hasNext && visitedNode.hasQualifier () && !getOptions ().isOmitQualifiers () ) {
					state = ITERATE_QUALIFIER;
					childrenIterator = null;
					hasNext = hasNext ();
				}
				return hasNext;
			} else {
				if ( childrenIterator == null ) {
					childrenIterator = visitedNode.iterateQualifier ();
				}

				return iterateChildren ( childrenIterator );
			}
		}

		protected boolean reportNode () {
			state = ITERATE_CHILDREN;
			if ( visitedNode.getParent () != null &&
							( getOptions ().isJustLeafnodes () || !visitedNode.hasChildren () ) ) {
				returnProperty = createPropertyInfo ( visitedNode, getBaseNS (), path );
				return true;
			} else {
				return hasNext ();
			}
		}

		private boolean iterateChildren ( Iterator iterator ) {
			if ( skipSiblings ) {
				// setSkipSiblings(false);
				skipSiblings = false;
				subIterator = Collections.EMPTY_LIST.iterator ();
			}

			// create sub iterator for every child,
			// if its the first child visited or the former child is finished 
			if ( ( !subIterator.hasNext () ) && iterator.hasNext () ) {
				XMPNode child = (XMPNode) iterator.next ();
				index++;
				subIterator = new NodeIterator ( child, path, index );
			}

			if ( subIterator.hasNext () ) {
				returnProperty = (XMPPropertyInfo) subIterator.next ();
				return true;
			} else {
				return false;
			}
		}

		public Object next () {
			if ( hasNext () ) {
				XMPPropertyInfo result = returnProperty;
				returnProperty = null;
				return result;
			} else {
				throw new NoSuchElementException ( "There are no more nodes to return" );
			}
		}

		public void remove () {
			throw new UnsupportedOperationException ();
		}

		protected String accumulatePath ( XMPNode currNode, String parentPath, int currentIndex ) {
			String separator;
			String segmentName;
			if ( currNode.getParent () == null || currNode.getOptions ().isSchemaNode () ) {
				return null;
			} else if ( currNode.getParent ().getOptions ().isArray () ) {
				separator = "";
				segmentName = "[" + currentIndex + "]";
			} else {
				separator = "/";
				segmentName = currNode.getName ();
			}

			if ( parentPath == null || parentPath.isEmpty () ) {
				return segmentName;
			} else if ( getOptions ().isJustLeafname () ) {
				return !segmentName.startsWith ( "?" ) ?
								segmentName :
								segmentName.substring ( 1 ); // qualifier
			} else {
				return parentPath + separator + segmentName;
			}
		}

		protected XMPPropertyInfo createPropertyInfo ( final XMPNode node, final String baseNS,
						final String path ) {
			final String value = node.getOptions ().isSchemaNode () ? null : node.getValue ();

			return new XMPPropertyInfo () {

				public String getNamespace () {
					if ( !node.getOptions ().isSchemaNode () ) {
						// determine namespace of leaf node
						QName qname = new QName ( node.getName () );
						return XMPMetaFactory.getSchemaRegistry ().getNamespaceURI ( qname.getPrefix () );
					} else {
						return baseNS;
					}
				}

				public String getPath () {
					return path;
				}

				public String getValue () {
					return value;
				}

				public PropertyOptions getOptions () {
					return node.getOptions ();
				}

				public String getLanguage () {
					// the language is not reported
					return null;
				}
			};
		}

		protected XMPPropertyInfo getReturnProperty () {
			return returnProperty;
		}

		protected void setReturnProperty ( XMPPropertyInfo returnProperty ) {
			this.returnProperty = returnProperty;
		}
	}


	private class NodeIteratorChildren extends NodeIterator {

		private final String parentPath;

		private final Iterator childrenIterator;

		private int index = 0;

		public NodeIteratorChildren ( XMPNode parentNode, String parentPath ) {
			if ( parentNode.getOptions ().isSchemaNode () ) {
				setBaseNS ( parentNode.getName () );
			}
			this.parentPath = accumulatePath ( parentNode, parentPath, 1 );

			childrenIterator = parentNode.iterateChildren ();
		}

		public boolean hasNext () {
			if ( getReturnProperty () != null ) {
				// hasNext has been called before
				return true;
			} else if ( skipSiblings ) {
				return false;
			} else if ( childrenIterator.hasNext () ) {
				XMPNode child = (XMPNode) childrenIterator.next ();
				index++;

				String path = null;
				if ( child.getOptions ().isSchemaNode () ) {
					setBaseNS ( child.getName () );
				} else if ( child.getParent () != null ) {
					// for all but the root node and schema nodes
					path = accumulatePath ( child, parentPath, index );
				}

				// report next property, skip not-leaf nodes in case options is set
				if ( getOptions ().isJustLeafnodes () || !child.hasChildren () ) {
					setReturnProperty ( createPropertyInfo ( child, getBaseNS (), path ) );
					return true;
				} else {
					return hasNext ();
				}
			} else {
				return false;
			}
		}
	}
}
