/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPConst;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPError;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.options.PropertyOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


class XMPNode implements Comparable {

	private String name;

	private String value;

	private XMPNode parent;

	private List children = null;

	private List qualifier = null;

	private PropertyOptions options;

	private boolean implicit;

	private boolean hasAliases;

	private boolean alias;

	private boolean hasValueChild;

	public XMPNode ( String name, String value, PropertyOptions options ) {
		this.name = name;
		this.value = value;
		this.options = options;
	}

	public XMPNode ( String name, PropertyOptions options ) {
		this ( name, null, options );
	}

	public void clear () {
		options = null;
		name = null;
		value = null;
		children = null;
		qualifier = null;
	}

	public XMPNode getParent () {
		return parent;
	}

	protected void setParent ( XMPNode parent ) {
		this.parent = parent;
	}

	public XMPNode getChild ( int index ) {
		return (XMPNode) getChildren ().get ( index - 1 );
	}

	public void addChild ( XMPNode node ) throws XMPException {
		// check for duplicate properties
		assertChildNotExisting ( node.getName () );
		node.setParent ( this );
		getChildren ().add ( node );
	}

	public void addChild ( int index, XMPNode node ) throws XMPException {
		assertChildNotExisting ( node.getName () );
		node.setParent ( this );
		getChildren ().add ( index - 1, node );
	}

	public void replaceChild ( int index, XMPNode node ) {
		node.setParent ( this );
		getChildren ().set ( index - 1, node );
	}

	public void removeChild ( int itemIndex ) {
		getChildren ().remove ( itemIndex - 1 );
		cleanupChildren ();
	}

	public void removeChild ( XMPNode node ) {
		getChildren ().remove ( node );
		cleanupChildren ();
	}

	protected void cleanupChildren () {
		if ( children.isEmpty () ) {
			children = null;
		}
	}

	public void removeChildren () {
		children = null;
	}

	public int getChildrenLength () {
		return children != null ?
						children.size () :
						0;
	}

	public XMPNode findChildByName ( String expr ) {
		return find ( getChildren (), expr );
	}

	public XMPNode getQualifier ( int index ) {
		return (XMPNode) getQualifier ().get ( index - 1 );
	}

	public int getQualifierLength () {
		return qualifier != null ?
						qualifier.size () :
						0;
	}

	public void addQualifier ( XMPNode qualNode ) throws XMPException {
		assertQualifierNotExisting ( qualNode.getName () );
		qualNode.setParent ( this );
		qualNode.getOptions ().setQualifier ( true );
		getOptions ().setHasQualifiers ( true );

		// contraints
		if ( qualNode.isLanguageNode () ) {
			// "xml:lang" is always first and the option "hasLanguage" is set
			options.setHasLanguage ( true );
			getQualifier ().add ( 0, qualNode );
		} else if ( qualNode.isTypeNode () ) {
			// "rdf:type" must be first or second after "xml:lang" and the option "hasType" is set
			options.setHasType ( true );
			getQualifier ().add (
							!options.getHasLanguage () ? 0 : 1,
							qualNode );
		} else {
			// other qualifiers are appended
			getQualifier ().add ( qualNode );
		}
	}

	public void removeQualifier ( XMPNode qualNode ) {
		PropertyOptions opts = getOptions ();
		if ( qualNode.isLanguageNode () ) {
			// if "xml:lang" is removed, remove hasLanguage-flag too
			opts.setHasLanguage ( false );
		} else if ( qualNode.isTypeNode () ) {
			// if "rdf:type" is removed, remove hasType-flag too
			opts.setHasType ( false );
		}

		getQualifier ().remove ( qualNode );
		if ( qualifier.isEmpty () ) {
			opts.setHasQualifiers ( false );
			qualifier = null;
		}

	}

	public void removeQualifiers () {
		PropertyOptions opts = getOptions ();
		// clear qualifier related options
		opts.setHasQualifiers ( false );
		opts.setHasLanguage ( false );
		opts.setHasType ( false );
		qualifier = null;
	}

	public XMPNode findQualifierByName ( String expr ) {
		return find ( qualifier, expr );
	}

	public boolean hasChildren () {
		return children != null && !children.isEmpty ();
	}

	public Iterator iterateChildren () {
		if ( children != null ) {
			return getChildren ().iterator ();
		} else {
			return Collections.EMPTY_LIST.listIterator ();
		}
	}

	public boolean hasQualifier () {
		return qualifier != null && !qualifier.isEmpty ();
	}

	public Iterator iterateQualifier () {
		if ( qualifier != null ) {
			final Iterator it = getQualifier ().iterator ();

			return new Iterator () {

				public boolean hasNext () {
					return it.hasNext ();
				}

				public Object next () {
					return it.next ();
				}

				public void remove () {
					throw new UnsupportedOperationException (
									"remove() is not allowed due to the internal contraints" );
				}

			};
		} else {
			return Collections.EMPTY_LIST.iterator ();
		}
	}

	public Object clone () {
		PropertyOptions newOptions;
		try {
			newOptions = new PropertyOptions ( getOptions ().getOptions () );
		} catch ( XMPException e ) {
			newOptions = new PropertyOptions ();
		}

		XMPNode newNode = new XMPNode ( name, value, newOptions );
		cloneSubtree ( newNode );

		return newNode;
	}

	public void cloneSubtree ( XMPNode destination ) {
		try {
			for ( Iterator it = iterateChildren (); it.hasNext (); ) {
				XMPNode child = (XMPNode) it.next ();
				destination.addChild ( (XMPNode) child.clone () );
			}

			for ( Iterator it = iterateQualifier (); it.hasNext (); ) {
				XMPNode qualifier = (XMPNode) it.next ();
				destination.addQualifier ( (XMPNode) qualifier.clone () );
			}
		} catch ( XMPException e ) {
			// cannot happen (duplicate childs/quals do not exist in this node)
			assert false;
		}

	}

	public int compareTo ( Object xmpNode ) {
		if ( getOptions ().isSchemaNode () ) {
			return this.value.compareTo ( ( (XMPNode) xmpNode ).getValue () );
		} else {
			return this.name.compareTo ( ( (XMPNode) xmpNode ).getName () );
		}
	}

	public String getName () {
		return name;
	}

	public void setName ( String name ) {
		this.name = name;
	}

	public String getValue () {
		return value;
	}

	public void setValue ( String value ) {
		this.value = value;
	}

	public PropertyOptions getOptions () {
		if ( options == null ) {
			options = new PropertyOptions ();
		}
		return options;
	}

	public void setOptions ( PropertyOptions options ) {
		this.options = options;
	}

	public boolean isImplicit () {
		return implicit;
	}

	public void setImplicit ( boolean implicit ) {
		this.implicit = implicit;
	}

	public boolean getHasAliases () {
		return !hasAliases;
	}

	public void setHasAliases ( boolean hasAliases ) {
		this.hasAliases = hasAliases;
	}

	public boolean isAlias () {
		return alias;
	}

	public void setAlias ( boolean alias ) {
		this.alias = alias;
	}

	public boolean getHasValueChild () {
		return hasValueChild;
	}

	public void setHasValueChild ( boolean hasValueChild ) {
		this.hasValueChild = hasValueChild;
	}

	//------------------------------------------------------------------------------ private methods

	public void sort () {
		// sort qualifier
		if ( hasQualifier () ) {
			XMPNode[] quals = (XMPNode[]) getQualifier ()
							.toArray ( new XMPNode[getQualifierLength ()] );
			int sortFrom = 0;
			while (
							quals.length > sortFrom &&
											( XMPConst.XML_LANG.equals ( quals[sortFrom].getName () ) ||
															"rdf:type".equals ( quals[sortFrom].getName () ) )
			) {
				quals[sortFrom].sort ();
				sortFrom++;
			}

			Arrays.sort ( quals, sortFrom, quals.length );
			ListIterator it = qualifier.listIterator ();
			for ( XMPNode qual : quals ) {
				it.next ();
				it.set ( qual );
				qual.sort ();
			}
		}

		// sort children
		if ( hasChildren () ) {
			if ( !getOptions ().isArray () ) {
				Collections.sort ( children );
			}
			for ( Iterator it = iterateChildren (); it.hasNext (); ) {
				( (XMPNode) it.next () ).sort ();

			}
		}
	}

	private boolean isLanguageNode () {
		return XMPConst.XML_LANG.equals ( name );
	}

	private boolean isTypeNode () {
		return "rdf:type".equals ( name );
	}

	private List getChildren () {
		if ( children == null ) {
			children = new ArrayList ( 0 );
		}
		return children;
	}

	public List getUnmodifiableChildren () {
		return Collections.unmodifiableList ( new ArrayList ( getChildren () ) );
	}

	private List getQualifier () {
		if ( qualifier == null ) {
			qualifier = new ArrayList ( 0 );
		}
		return qualifier;
	}

	private XMPNode find ( List list, String expr ) {

		if ( list != null ) {
			for ( Object o : list ) {
				XMPNode child = (XMPNode) o;
				if ( child.getName ().equals ( expr ) ) {
					return child;
				}
			}
		}
		return null;
	}

	private void assertChildNotExisting ( String childName ) throws XMPException {
		if ( !XMPConst.ARRAY_ITEM_NAME.equals ( childName ) &&
						findChildByName ( childName ) != null ) {
			throw new XMPException ( "Duplicate property or field node '" + childName + "'",
							XMPError.BADXMP );
		}
	}

	private void assertQualifierNotExisting ( String qualifierName ) throws XMPException {
		if ( !XMPConst.ARRAY_ITEM_NAME.equals ( qualifierName ) &&
						findQualifierByName ( qualifierName ) != null ) {
			throw new XMPException ( "Duplicate '" + qualifierName + "' qualifier", XMPError.BADXMP );
		}
	}
}
