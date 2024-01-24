/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.options;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPException;


public final class SerializeOptions extends Options {

	public static final int OMIT_PACKET_WRAPPER = 0x0010;

	public static final int READONLY_PACKET = 0x0020;

	public static final int USE_COMPACT_FORMAT = 0x0040;

	public static final int USE_CANONICAL_FORMAT = 0x0080;

	public static final int INCLUDE_THUMBNAIL_PAD = 0x0100;

	public static final int EXACT_PACKET_LENGTH = 0x0200;

	public static final int OMIT_XMPMETA_ELEMENT = 0x1000;

	public static final int SORT = 0x2000;

	// ---------------------------------------------------------------------------------------------
	// encoding bit constants

	public static final int ENCODE_UTF8 = 0;

	private static final int LITTLEENDIAN_BIT = 0x0001;

	private static final int UTF16_BIT = 0x0002;

	public static final int ENCODE_UTF16BE = UTF16_BIT;

	public static final int ENCODE_UTF16LE = UTF16_BIT | LITTLEENDIAN_BIT;

	private static final int ENCODING_MASK = UTF16_BIT | LITTLEENDIAN_BIT;

	private int padding = 2048;

	private String newline = "\n";

	private String indent = "  ";

	private int baseIndent = 0;

	public SerializeOptions () {
		// reveal default constructor
	}

	public SerializeOptions ( int options ) throws XMPException {
		super ( options );
	}

	public boolean getOmitPacketWrapper () {
		return getOption ( OMIT_PACKET_WRAPPER );
	}

	public boolean getOmitXmpMetaElement () {
		return !getOption ( OMIT_XMPMETA_ELEMENT );
	}

	public boolean getReadOnlyPacket () {
		return getOption ( READONLY_PACKET );
	}

	public boolean getUseCanonicalFormat () {
		return getOption ( USE_CANONICAL_FORMAT );
	}

	public boolean getIncludeThumbnailPad () {
		return getOption ( INCLUDE_THUMBNAIL_PAD );
	}

	public boolean getExactPacketLength () {
		return getOption ( EXACT_PACKET_LENGTH );
	}

	public boolean getSort () {
		return getOption ( SORT );
	}

	public SerializeOptions setSort ( boolean value ) {
		setOption ( SORT, value );
		return this;
	}

	public boolean getEncodeUTF16BE () {
		return ( getOptions () & ENCODING_MASK ) == ENCODE_UTF16BE;
	}

	public void setEncodeUTF16BE ( boolean value ) {
		// clear unicode bits
		setOption ( UTF16_BIT | LITTLEENDIAN_BIT, false );
		setOption ( ENCODE_UTF16BE, value );
	}

	public boolean getEncodeUTF16LE () {
		return ( getOptions () & ENCODING_MASK ) == ENCODE_UTF16LE;
	}

	public void setEncodeUTF16LE ( boolean value ) {
		// clear unicode bits
		setOption ( UTF16_BIT | LITTLEENDIAN_BIT, false );
		setOption ( ENCODE_UTF16LE, value );
	}

	public int getBaseIndent () {
		return baseIndent;
	}

	public void setBaseIndent ( int baseIndent ) {
		this.baseIndent = baseIndent;
	}

	public String getIndent () {
		return indent;
	}

	public SerializeOptions setIndent ( String indent ) {
		this.indent = indent;
		return this;
	}

	public String getNewline () {
		return newline;
	}

	public SerializeOptions setNewline ( String newline ) {
		this.newline = newline;
		return this;
	}

	public int getPadding () {
		return padding;
	}

	public SerializeOptions setPadding ( int padding ) {
		this.padding = padding;
		return this;
	}

	public boolean getOmitVersionAttribute () {
		return false;
	}

	public String getEncoding () {
		if ( getEncodeUTF16BE () ) {
			return "UTF-16BE";
		} else if ( getEncodeUTF16LE () ) {
			return "UTF-16LE";
		} else {
			return "UTF-8";
		}
	}

	public Object clone () throws CloneNotSupportedException {
		SerializeOptions clone;
		try {
			clone = new SerializeOptions ( getOptions () );
			clone.setBaseIndent ( baseIndent );
			clone.setIndent ( indent );
			clone.setNewline ( newline );
			clone.setPadding ( padding );
			return clone;
		} catch ( XMPException e ) {
			// This cannot happen, the options are already checked in "this" object.
			return null;
		}
	}

	protected int getValidOptions () {
		return
						OMIT_PACKET_WRAPPER |
										READONLY_PACKET |
										USE_COMPACT_FORMAT |
										//		USE_CANONICAL_FORMAT |
										INCLUDE_THUMBNAIL_PAD |
										OMIT_XMPMETA_ELEMENT |
										EXACT_PACKET_LENGTH |
										SORT;
	}
}
