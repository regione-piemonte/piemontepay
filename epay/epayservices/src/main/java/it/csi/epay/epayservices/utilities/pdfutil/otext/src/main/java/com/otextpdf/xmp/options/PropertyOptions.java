/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.options;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPError;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPException;


public final class PropertyOptions extends Options {

	public static final int NO_OPTIONS = 0x00000000;

	public static final int URI = 0x00000002;

	public static final int HAS_QUALIFIERS = 0x00000010;

	public static final int QUALIFIER = 0x00000020;

	public static final int HAS_LANGUAGE = 0x00000040;

	public static final int HAS_TYPE = 0x00000080;

	public static final int STRUCT = 0x00000100;

	public static final int ARRAY = 0x00000200;

	public static final int ARRAY_ORDERED = 0x00000400;

	public static final int ARRAY_ALTERNATE = 0x00000800;

	public static final int ARRAY_ALT_TEXT = 0x00001000;

	public static final int SCHEMA_NODE = 0x80000000;

	public static final int DELETE_EXISTING = 0x20000000;

	public static final int SEPARATE_NODE = 0x40000000;

	public PropertyOptions () {
		// reveal default constructor
	}

	public PropertyOptions ( int options ) throws XMPException {
		super ( options );
	}

	public boolean isURI () {
		return getOption ( URI );
	}

	public PropertyOptions setURI ( boolean value ) {
		setOption ( URI, value );
		return this;
	}

	public void setHasQualifiers ( boolean value ) {
		setOption ( HAS_QUALIFIERS, value );
	}

	public boolean isQualifier () {
		return getOption ( QUALIFIER );
	}

	public PropertyOptions setQualifier ( boolean value ) {
		setOption ( QUALIFIER, value );
		return this;
	}

	public boolean getHasLanguage () {
		return getOption ( HAS_LANGUAGE );
	}

	public void setHasLanguage ( boolean value ) {
		setOption ( HAS_LANGUAGE, value );
	}

	public void setHasType ( boolean value ) {
		setOption ( HAS_TYPE, value );
	}

	public boolean isStruct () {
		return getOption ( STRUCT );
	}

	public PropertyOptions setStruct ( boolean value ) {
		setOption ( STRUCT, value );
		return this;
	}

	public boolean isArray () {
		return getOption ( ARRAY );
	}

	public PropertyOptions setArray ( boolean value ) {
		setOption ( ARRAY, value );
		return this;
	}

	public boolean isArrayOrdered () {
		return getOption ( ARRAY_ORDERED );
	}

	public PropertyOptions setArrayOrdered ( boolean value ) {
		setOption ( ARRAY_ORDERED, value );
		return this;
	}

	public boolean isArrayAlternate () {
		return getOption ( ARRAY_ALTERNATE );
	}

	public PropertyOptions setArrayAlternate ( boolean value ) {
		setOption ( ARRAY_ALTERNATE, value );
		return this;
	}

	public boolean isArrayAltText () {
		return getOption ( ARRAY_ALT_TEXT );
	}

	public void setArrayAltText ( boolean value ) {
		setOption ( ARRAY_ALT_TEXT, value );
	}

	public boolean isSchemaNode () {
		return getOption ( SCHEMA_NODE );
	}

	public PropertyOptions setSchemaNode ( boolean value ) {
		setOption ( SCHEMA_NODE, value );
		return this;
	}

	//-------------------------------------------------------------------------- convenience methods

	public boolean isCompositeProperty () {
		return ( getOptions () & ( ARRAY | STRUCT ) ) > 0;
	}

	public boolean isSimple () {
		return ( getOptions () & ( ARRAY | STRUCT ) ) == 0;
	}

	public void mergeWith ( PropertyOptions options ) throws XMPException {
		if ( options != null ) {
			setOptions ( getOptions () | options.getOptions () );
		}
	}

	public boolean isOnlyArrayOptions () {
		return ( getOptions () &
						~( ARRAY | ARRAY_ORDERED | ARRAY_ALTERNATE | ARRAY_ALT_TEXT ) ) == 0;
	}

	protected int getValidOptions () {
		return
						URI |
										HAS_QUALIFIERS |
										QUALIFIER |
										HAS_LANGUAGE |
										HAS_TYPE |
										STRUCT |
										ARRAY |
										ARRAY_ORDERED |
										ARRAY_ALTERNATE |
										ARRAY_ALT_TEXT |
										SCHEMA_NODE |
										SEPARATE_NODE;
	}

	public void assertConsistency ( int options ) throws XMPException {
		if ( ( options & STRUCT ) > 0 && ( options & ARRAY ) > 0 ) {
			throw new XMPException ( "IsStruct and IsArray options are mutually exclusive",
							XMPError.BADOPTIONS );
		} else if ( ( options & URI ) > 0 && ( options & ( ARRAY | STRUCT ) ) > 0 ) {
			throw new XMPException ( "Structs and arrays can't have \"value\" options",
							XMPError.BADOPTIONS );
		}
	}
}
