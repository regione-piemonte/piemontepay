/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.options;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPException;


public final class AliasOptions extends Options {

	public static final int PROP_DIRECT = 0;

	public static final int PROP_ARRAY = PropertyOptions.ARRAY;

	public static final int PROP_ARRAY_ORDERED = PropertyOptions.ARRAY_ORDERED;

	public static final int PROP_ARRAY_ALTERNATE = PropertyOptions.ARRAY_ALTERNATE;

	public static final int PROP_ARRAY_ALT_TEXT = PropertyOptions.ARRAY_ALT_TEXT;

	public AliasOptions () {
		// EMPTY
	}

	public AliasOptions ( int options ) throws XMPException {
		super ( options );
	}

	public boolean isSimple () {
		return getOptions () == PROP_DIRECT;
	}

	public boolean isArray () {
		return getOption ( PROP_ARRAY );
	}

	public AliasOptions setArray ( boolean value ) {
		setOption ( PROP_ARRAY, value );
		return this;
	}

	public AliasOptions setArrayOrdered ( boolean value ) {
		setOption ( PROP_ARRAY | PROP_ARRAY_ORDERED, value );
		return this;
	}

	public boolean isArrayAltText () {
		return getOption ( PROP_ARRAY_ALT_TEXT );
	}

	public AliasOptions setArrayAltText ( boolean value ) {
		setOption ( PROP_ARRAY | PROP_ARRAY_ORDERED |
						PROP_ARRAY_ALTERNATE | PROP_ARRAY_ALT_TEXT, value );
		return this;
	}

	public PropertyOptions toPropertyOptions () throws XMPException {
		return new PropertyOptions ( getOptions () );
	}

	protected int getValidOptions () {
		return
						PROP_DIRECT |
										PROP_ARRAY |
										PROP_ARRAY_ORDERED |
										PROP_ARRAY_ALTERNATE |
										PROP_ARRAY_ALT_TEXT;
	}
}	
